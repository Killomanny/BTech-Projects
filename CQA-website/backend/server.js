const express = require('express');
const bodyParser = require('body-parser');
const pg = require('pg');
const cors = require('cors');

const app = express()
app.use(cors());
app.use(bodyParser.urlencoded({ extended: true}));
app.use(bodyParser.json());

const conString = "postgres://postgres:1234@localhost:5432/cqadb";
const client = new pg.Client(conString);

client.connect((err) => {
    if (err) {
      console.log('Database Connection failed');
    } else {
      console.log('Successfully connected to database');
    }
});

app.post('/register', async (req,res)=>{
    var max = 0; 
    var count = 0;   
    try{
        const result = await client.query('select max(id) as m from users');
        max = result.rows[0].m;
    }
    catch(err){
        console.log(err.message);
    }
    try{
        const result = await client.query('select count(id) as c from users where user_name = $1',
        [req.body.username]);
        count = result.rows[0].c;
    }
    catch(err){
        console.log(err.message);
    }
    if(count == 0)
    {
        try{
            const date = new Date().toISOString();
            const result = await client.query('INSERT INTO users(id,display_name,user_name,password,creation_date,last_access_date) VALUES($1,$2,$2,$3,$4,$4)',
            [max+1,req.body.username,req.body.password,date]);
            res.send({result : 1});
        }
        catch(err){
            console.log(err.message);
        }    
    }
    else
    {
        res.send({result : 0});
    }
});    

app.post('/login',async (req,res)=>{
    try{
        const result = await client.query('select id,password from users where user_name = $1',
        [req.body.username]);
        if(result.rows.length === 1){
        if(result.rows[0].password === req.body.password) 
        {
            res.send({result : 1, id: result.rows[0].id});
        }
        else res.send({result : 0});
        }
        else res.send({result : 0});
    }
    catch(err){
        console.log(err.message);
    }
});

app.get('/home/:id', async (req,res)=>{
    try{
        const result = await client.query('select * from posts where post_type_id = 1 order by view_count desc limit 20');
        const result2 = await client.query('select display_name from users where id = $1',[req.params.id]);
        res.send({posts : result.rows,name : result2.rows[0].display_name});
    }
    catch(err){
        console.log(err.messgae);
    }
});

app.get('/questions/:pid',async(req,res)=>{
    try{
        const result = await client.query('select * from posts where id = $1',[req.params.pid]);
        const result2 = await client.query('select * from posts where parent_id = $1',[req.params.pid]);
        res.send({content : result.rows[0],answers : result2.rows});
    }
    catch(err){
        console.log(err.messgae);
    }
});

app.get('/tags/:tName',async(req,res)=>{
    try{
        const str = "%<" + req.params.tName + ">%";
        const result = await client.query('select * from posts where tags like $1',[str]);
        const result2 = await client.query('select * from posts where tags like $1 order by creation_date desc',[str]);
        const result3 = await client.query('select * from posts where tags like $1 order by score desc',[str]);
        res.send({questions : result.rows,timeSort : result2.rows, voteSort : result3.rows});
    }
    catch(err){
        console.log(err.messgae);
    }
});

app.post('/multiTags',async(req,res)=>{
    const arr = req.body.array;
    var str = "select * from posts where ";
    var str2 = "",str3="";
    for (var i=0;i<arr.length;i++) 
    {
        if(i!== arr.length - 1) str = str + ` tags like '%<${arr[i]}>%' and `;
        else str = str + ` tags like '%<${arr[i]}>%' `;
    }
    try{
        str2 = str + " order by creation_date desc "
        str3 = str + " order by score desc "
        const result = await client.query(str);
        const result2 = await client.query(str2);
        const result3 = await client.query(str3);
        res.send({questions : result.rows, timeSort : result2.rows, voteSort : result3.rows});     
    }
    catch(err){
        console.log(err.messgae);
    }
})

app.get('/tagsList',async(req,res)=>{
    try{
        const result = await client.query('select id,tag_name from tags');
        res.send({tags : result.rows});    
    }
    catch(err){
        console.log(err.messgae);
    }
})

app.get('/usersList',async(req,res)=>{
    try{
        const result = await client.query('select id,user_name from users');
        res.send({users : result.rows});    
    }
    catch(err){
        console.log(err.messgae);
    }
})

app.get('/profile/:id',async(req,res) => {
    const result = await client.query('select * from users where id = $1',[req.params.id]);
    res.send({userData : result.rows[0]});
})

app.get('/userPosts/:id',async(req,res)=>{
    try{
        const result = await client.query('select * from posts where user_id =$1 and post_type_id = 1',[req.params.id]);    
        const result2 = await client.query('select * from posts where user_id =$1 and post_type_id = 1 order by creation_date desc',[req.params.id]);    
        const result3 = await client.query('select * from posts where user_id =$1 and post_type_id = 1 order by score desc ',[req.params.id]);    
        res.send({questions : result.rows,timeSort : result2.rows, voteSort : result3.rows});    
    }
    catch(err){
        console.log(err.message);
    }
});

app.get('/userAnswers/:id',async(req,res)=>{
    try{
        const result = await client.query('select * from posts where user_id =$1 and post_type_id = 2',[req.params.id]);    
        const result2 = await client.query('select * from posts where user_id =$1 and post_type_id = 2 order by creation_date desc',[req.params.id]);    
        const result3 = await client.query('select * from posts where user_id =$1 and post_type_id = 2 order by score desc ',[req.params.id]);    
        res.send({questions : result.rows,timeSort : result2.rows, voteSort : result3.rows});    
    }
    catch(err){
        console.log(err.message);
    }
});


app.post('/answer/:id/:pid',async(req,res)=>{
    var max = 0;
    var str = ""; 
    try{
        const result = await client.query('select max(id) as m from posts');
        max = result.rows[0].m;
    }
    catch(err){
        console.log(err.message);
    }
    try{
        const result = await client.query('select * from users where id = $1',[req.params.id]);
        str = result.rows[0].user_name;
    }
    catch(err){
        console.log(err.message);
    }
    try{
        if(req.body.content !== ''){
        const result = await client.query('insert into posts (id,user_id,post_type_id,parent_id,body,user_name) values ($1,$2,$3,$4,$5,$6);',
        [max+1,req.params.id,2,req.params.pid,req.body.content,str]);
        console.log(result.rows);
        res.send({output : 1});
        } 
        else res.send({output : 0});
    }
    catch(err){
        res.send({output : 0});
        console.log(err.message);
    }
});

app.post('/createPost/:id',async(req,res)=>{
    var max = 0; 
    let name = '';
    var x = 1;
    try{
        const result = await client.query('select max(id) as m from posts');
        max = result.rows[0].m;
    }
    catch(err){
        console.log(err.message);
    }
    try{
        const result = await client.query('select * from users where id = $1',[req.params.id]);
        name = result.rows[0].user_name;
    }
    catch(err){
        console.log(err.message);
    }
    try{
        const arr = req.body.tags.split(">");
        arr.pop();
        for(var i=0;i<arr.length;i++)
        {
            const str = arr[i].substr(1,arr[i].length);
            const r = await client.query('select * from tags where tag_name = $1',[str]);
            if(r.rows.length !== 1) x = 0;
        }
    }
    catch(err){
        console.log(err.message);
    }
    try{
        if(req.body.content !== '' && req.body.title !== '' && x === 1){
        const date = new Date().toISOString();
        const result = await client.query('insert into posts (id,user_id,post_type_id,title,body,user_name,tags,creation_date) values ($1,$2,$3,$4,$5,$6,$7,$8);',
        [max+1,req.params.id,1,req.body.title,req.body.content,name,req.body.tags,date]);
        res.send({output : 1});
        }
        else res.send({output : 0})
    }
    catch(err){
        res.send({output : 0});
        console.log(err.message);
    }
});

app.post('/edit/:pid',async(req,res) => {
    var x=1;
    try{
        const arr = req.body.tags.split(">");
        arr.pop();
        for(var i=0;i<arr.length;i++)
        {
            const str = arr[i].substr(1,arr[i].length);
            const r = await client.query('select * from tags where tag_name = $1',[str]);
            if(r.rows.length !== 1) x=0;
        }
    }
    catch(err){
        console.log(err.message);
    }
    try{
        if(x===1){
        const result = await client.query('update posts set title = $1,body=$2,tags=$3 where id = $4',
        [req.body.title,req.body.content,req.body.tags,req.body.id]);
        res.send({result : 1});
        }
        else res.send({result : 0})
    }
    catch(err){
        res.send({result : 0});
        console.log(err.message); 
    }
}) 
app.post('/deletePost/:id',async(req,res)=> {
    try{
        client.query('delete from comments where post_id = $1',[req.params.id]);
    }
    catch(err){
        res.send({result :1});
        console.log(err.message);
    }
    try{
        client.query('delete from posts where id = $1',[req.params.id]);
        res.send({result : 1});
    }
    catch(err){
        console.log(err.message);
        res.send({result : 0});
    }
})

app.get('/viewAnswer/:id',async(req,res) => {
    try{
        const result = await client.query('select * from posts where id = $1',[req.params.id]);
        res.send({answer : result.rows[0]});
    }
    catch(err){
        console.log(err.message);
    }
});

app.post('/editAnswer/:id',async(req,res)=>{
    try{
        if(req.body.content !== ''){
            client.query('update posts set body = $1 where id = $2',[req.body.content,req.params.id]);
            res.send({result : 1});
        }
        res.send({result : 0});
    }
    catch(err) {
        console.log(err.message);
    }
})
const PORT = 8000
app.listen(PORT,()=>{
    console.log(`Server running on port ${PORT}`);
})