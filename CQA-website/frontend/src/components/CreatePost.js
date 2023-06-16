import axios from "axios";
import { useState,useEffect } from "react";
import { useHistory, useParams } from "react-router-dom";
import Sidebar from "./Sidebar";
import matching from './autoComplete';
import Cookies from "js-cookie";
import Login from "./Login";
import ip from "./ip-address";

function CreatePost(){
    const id = Cookies.get('User');
    const [title,setTitle] = useState('');
    const [body,setBody] = useState('');
    const [multiTags,setMultiTags] = useState("");
    const [tags,setTags] = useState([]);
    const [tagName,setTagName] = useState("");
    const [matchTags,setMatchTags] = useState([]);
    useEffect( () =>{
        axios.get(`http://${ip}:8000/tagsList`)
        .then( res => {
            setTags(res.data.tags);
        });    
    },[])    
    const history = useHistory();
    function handleSubmit(event){
        event.preventDefault();
        axios.post(`http://${ip}:8000/createPost/${id}`,
        {content : body, title : title,tags : multiTags})
        .then(res => {
            
            if(res.data.output === 1) {
                alert('post created');
                history.push(`/`);
            }
            else alert('post creation failed');
        })
    }
    if(id) {
    return(
        <div className="main">
            <Sidebar  />
            <form onSubmit={handleSubmit}>
            <label> Title </label> <br/>
            <input size= "100"  onChange = { e => setTitle(e.target.value)} /> <br/>
            <label> Content </label> <br/>
            <textarea rows = "10" cols = "100" onChange={e => 
                setBody(e.target.value)}></textarea> <br/>
            <label> Tags </label> <br/>
            <textarea rows = "2" cols = "100" value = {multiTags} onChange={e => 
            setMultiTags(e.target.value)}></textarea> <br/>
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
    )}
    else {
        return (
            <Login />
        )
    }
}

export default CreatePost;