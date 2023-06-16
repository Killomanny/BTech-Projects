import { useState,useEffect } from "react";
import axios from 'axios';
import matching from "./autoComplete";
import { useHistory } from "react-router-dom";
import Sidebar from "./Sidebar";
import Cookies from "js-cookie";
import Login from "./Login";
import ip from "./ip-address";

function SearchTags() {
    const id = Cookies.get('User');
    const [tags,setTags] = useState([]);
    const [matchTags,setMatchTags] = useState([]);
    const [tagName,setTagName] = useState('');
    const history = useHistory();
    useEffect( () => {
        axios.get(`http://${ip}:8000/tagsList`)
        .then( res => {
            setTags(res.data.tags);
        });
    },[]);
    function handleSubmit(event){
        event.preventDefault();
        history.push(`/tags/${tagName}`)
    }
    if(id) {
    return (
        <div className="main">
            <Sidebar />
            <form onSubmit={handleSubmit}>
                <input placeholder='tag-name' type = "text" onChange = {async (e) => {
                    setTagName(e.target.value);
                    if(e.target.value !== "") setMatchTags(matching(e.target.value,tags,"tags"));
                    else setMatchTags([]);
                }} value = {tagName} /> <br /> <br />
                <button type = "submit" > OK </button>
            </form>
            {matchTags.map((item) => <option onClick = {(e) => setTagName(item.tag_name)}>
                 {item.id} : {item.tag_name} </option>)}
        </div>
    ) }
    else {
        return (
            <Login />
        )
    }
}

export default SearchTags; 