import axios from 'axios';
import Cookies from 'js-cookie';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Login from './Login';
import Question from './Question';
import Sidebar from './Sidebar';
import ip from './ip-address';

function UserPosts(){
    const ecookie = Cookies.get('User');
    const {id} = useParams();
    const [data,setData] = useState(null);
    const [count,setCount] = useState(-1);
    const [timeSort,setTimeSort] = useState(null);
    const [voteSort,setVoteSort] = useState(null);
    useEffect(() => {               
        axios.get(`http://${ip}:8000/userPosts/${id}`)
        .then(res => {
            setCount(res.data.questions.length);
            setData(res.data.questions);
            setTimeSort(res.data.timeSort)
            setVoteSort(res.data.voteSort)
           
        })
    },[id]);
    if(id) {
    return (
        <div className='main'>
            <Sidebar  />
            {count===0 && <h1>This user has no Posts</h1>}
            <button onClick={(e) => setData(timeSort) }> Sort by time </button>
            <button onClick={(e) => setData(voteSort) }> Sort by votes </button>
            {data && data.map(item => <Question que = {item}  /> )}
        </div>
    ) }
    else return(
        <Login/>
    )
}

export default UserPosts;