import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import Question from "./Question";
import axios from 'axios';
import Sidebar from "./Sidebar";
import Cookies from "js-cookie";
import Login from "./Login";
import ip from "./ip-address";

function Tags(){
    const id = Cookies.get('User');
    const {tagName} = useParams();
    const [data,setData] = useState(null);
    const [timeSort,setTimeSort] = useState(null);
    const [voteSort,setVoteSort] = useState(null);
    useEffect( () => {
        axios.get(`http://${ip}:8000/tags/${tagName}`)
        .then(res => {
            setData(res.data.questions);
            setTimeSort(res.data.timeSort)
            setVoteSort(res.data.voteSort)
        });
    },[tagName]);
    if(id) {
    return (
        <div className="main">
            <Sidebar  />
            <button onClick={(e) => setData(timeSort) }> Sort by time </button>
            <button onClick={(e) => setData(voteSort) }> Sort by votes </button>
            {data && 
                data.map(item => 
                <Question key = {item.id} que = {item} viewer_id = {id}  />)
            }
        </div>
    ) }
    else {
        return (
            <Login />
        )
    }
}

export default Tags;