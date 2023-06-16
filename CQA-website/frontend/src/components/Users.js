import axios from 'axios';
import Cookies from 'js-cookie';
import { useState,useEffect } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import matching from './autoComplete';
import Login from './Login';
import Sidebar from './Sidebar';
import ip from './ip-address';

function Users(){
    const ecookie = Cookies.get('User')
    const [matchUsers,setMatchUsers] = useState([]);
    const [usernames,setUsernames] = useState([]);
    const [username,setUsername] = useState('');
    const [id,setId] = useState('');
    const history = useHistory();
    useEffect( () => {
        axios.get(`http://${ip}:8000/usersList`)
        .then( res => {
            setUsernames(res.data.users);
        });
    },[]);
    function handleSubmit(event){
        event.preventDefault();
        history.push(`/userPosts/${id}`)
    }
    if(ecookie) {
    return (
        <div className='main' >
            <Sidebar />
            <form onSubmit={handleSubmit}>
                <input placeholder='user-name' type = "text" onChange = {async (e) => {
                    setUsername(e.target.value);
                    if(e.target.value !== "") setMatchUsers(matching(e.target.value,usernames,"users"));
                    else setMatchUsers([]);
                }}  value = {username} /> <br /> <br />
                <button type = "submit" > OK </button>
            </form>
            {matchUsers.map((item) => <option onClick={(e) => 
                {
                setId(item.id);
                setUsername(item.user_name);
                }
            }> {item.id} : {item.user_name} </option>)}
        </div>
    ) }
    else return (
        <Login />
    )
}

export default Users;