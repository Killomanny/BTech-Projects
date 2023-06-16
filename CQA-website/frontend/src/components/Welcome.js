import axios from 'axios';
import { useEffect, useState } from 'react';
import Question from './Question';
import { Link } from "react-router-dom";
import Sidebar from './Sidebar';
import Cookies from 'js-cookie';
import Login from './Login';
import ip from './ip-address';


function Welcome () {
    const [data,setData] = useState(null);
    const id = Cookies.get('User');
    useEffect(() => {
        axios.get(`http://${ip}:8000/home/${id}`)
        .then(res => {
            setData(res.data);
        })
      },[id]);
      if(id){
        return (
        <div className='main'>
            <Sidebar  />
            <button> <a href = {`/createPost`} > Ask a Question </a> </button>
            { data && <h1> Hello <Link to = {`/profile/${id}`}> {data.name} </Link> </h1> }
            { data && data.posts.map(item => <Question key = {item.id} que = {item} /> ) }
            
        </div> 
    ); }
    else {
        return(
            <Login />
        )
    }
}

export default Welcome;