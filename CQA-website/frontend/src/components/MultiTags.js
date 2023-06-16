import {  useEffect, useState } from "react";
import axios from 'axios';
import Question from "./Question";
import { useParams } from "react-router-dom";
import Sidebar from "./Sidebar";
import Cookies from "js-cookie";
import matching from './autoComplete';
import Login from "./Login";
import ip from "./ip-address";

function MultiTags(){
    const id = Cookies.get('User');
    const [multiTags,setMultiTags] = useState("");
    const [result,setResult] = useState([]);
    const [tags,setTags] = useState([]);
    const [tagName,setTagName] = useState("");
    const [matchTags,setMatchTags] = useState([]);
    const [timeSort,setTimeSort] = useState(null);
    const [voteSort,setVoteSort] = useState(null);
    useEffect(() => { 
        axios.get(`http://${ip}:8000/tagsList`)
        .then( res => {
            setTags(res.data.tags);
        });    
    })

    function handleSubmit(e){
        e.preventDefault();
        const array = multiTags.split(" ");
        array.pop();
        console.log(array);
        axios.post(`http://${ip}:8000/multiTags`,{array : array})
        .then(res => {
            setResult(res.data.questions)
            setTimeSort(res.data.timeSort)
            setVoteSort(res.data.voteSort)
        })
    }
    if(id) {
    return (
        <div className="main">
            <Sidebar v_id = {id} />
            <button onClick={(e) => setResult(timeSort) }> Sort by time </button>
            <button onClick={(e) => setResult(voteSort) }> Sort by votes </button>
            <form onSubmit = {(e) => handleSubmit(e)}>
                <input placeholder = "multiple-tags" type = "text" value = {multiTags}
                onChange={(e) => setMultiTags(e.target.value)} /> <br/>
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
                    setMultiTags(multiTags + tagName + " ");
                    setTagName("");}
                }> + </button> <br/> <br/>
                <button type="submit" > OK </button>
            </form>
            {result && result.map(item => <Question key = {item.id} que = {item} /> )}
        </div>
    )
            }
    else {
        return (
            <Login />
        )
    }
}

export default MultiTags;