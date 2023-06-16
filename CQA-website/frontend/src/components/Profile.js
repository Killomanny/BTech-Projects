import { Link, useParams } from "react-router-dom";
import axios from 'axios';
import { useEffect, useState } from "react";
import Sidebar from "./Sidebar";
import Cookies from "js-cookie";
import Login from "./Login";
import ip from "./ip-address";

function Profile(){
    const {id} = useParams();
    const ecookie = Cookies.get('User');
    const [userData,setUserData] = useState(null);
    const [image,setImage] = useState(false);
    useEffect(() => {
        axios.get(`http://${ip}:8000/profile/${id}`)
        .then(res => {
            if(res.data.userData.profile_image_url !== null) setImage(true);
            setUserData(res.data.userData);
        })
      },[id]);
      if(ecookie) {   
    return (
        <div className="main">
            <Sidebar  />
            {userData && 
            <div> 
                <h1> Hello {userData.display_name}</h1>
                {image && <img src={userData.profile_image_url} alt = "" height={100} width = {100}/> }
                <div dangerouslySetInnerHTML={{__html: userData.about_me}} />
                <p> Website URL : <a href =  {userData.website_url} >  {userData.website_url}</a></p>
                <p> Location : {userData.location}</p>
            </div>
            }
            <Link to = {`/userPosts/${id}`}> View Posts </Link>
            <Link to = {`/userAnswers/${id}`}> View Answers </Link>
        </div>
    )}
    else {
        return (
            <Login />
        )
    }
}

export default Profile;