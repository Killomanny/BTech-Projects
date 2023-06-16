import axios from "axios";
import { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router-dom";
import Sidebar from "./Sidebar";
import matching from "./autoComplete";
import Cookies from "js-cookie";
import Login from "./Login";
import ip from "./ip-address";

function EditQuestion(){
    const id = Cookies.get('User');
    const {pid} = useParams();
    const [body,setBody] = useState("");
    const [title,setTitle] = useState("");
    const [multiTags,setMultiTags] = useState("");
    const [tags,setTags] = useState([]);
    const [tagName,setTagName] = useState("");
    const [matchTags,setMatchTags] = useState([]);

    useEffect( () =>{
    axios.get(`http://${ip}:8000/tagsList`)
    .then( res => {
        setTags(res.data.tags);
    });    
    axios.get(`http://${ip}:8000/questions/${pid}`)
    .then (res => {
        setTitle(res.data.content.title);
        setBody(res.data.content.body);
        setMultiTags(res.data.content.tags);
    }); 
},[])
function handleSubmit(e){
    e.preventDefault();
    axios.post(`http://${ip}:8000/edit/${pid}`,{
        title : title,
        content : body,
        tags : multiTags,
        id : pid
    })
    .then(res => {
        if(res.data.result === 1) 
        {
            alert('successful edit');
        }
        else alert('unsuccessful');
    })
}
    if(id) {
    return(
        <div className="main">
            <Sidebar  />
            <h1> Edit Post </h1>
            <form onSubmit={(e) => handleSubmit(e)}>
            <label> Title </label> <br/>
            <textarea rows = "2" cols = "100"  value = {title} 
            onChange = {(e) => setTitle(e.target.value)} /> <br/>
            <label> Content </label> <br/>
            <textarea rows = "10" cols = "100" value = {body}
            onChange = {(e) => setBody(e.target.value) } ></textarea> <br/>
            <label> Tags </label> <br/>
            <textarea rows = "2" cols = "100" value = {multiTags}
            onChange ={(e) => setMultiTags(e.target.value)} ></textarea> <br/>
            <input placeholder="tag-name" type = "text" value = {tagName}
                onChange = {(e) => {
                    setTagName(e.target.value);
                    if(e.target.value !== "") setMatchTags(matching(e.target.value,tags,"tags"));
                    else setMatchTags([]);
                }} />
                {matchTags.map((item) => <option onClick = {(e) => {
                    setTagName(item.tag_name);
                    setMatchTags([]);
                    }}>
                 {item.id} : {item.tag_name} </option>) }
                <button type = "button" onClick={(e) => {
                    setMultiTags(multiTags + "<" + tagName + ">");
                    setTagName("");}
                }> + </button> <br/> <br/>
            <button type = "submit" > OK </button>
            </form>
        </div>
    ) } 
    else {
        return (
            <Login />
        )
    }
}

export default EditQuestion;