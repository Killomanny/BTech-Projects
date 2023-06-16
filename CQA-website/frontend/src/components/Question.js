import { Link, useHistory } from "react-router-dom";
import Cookies from 'js-cookie';
import { useState } from "react";
import axios from "axios";
import { confirm } from "react-confirm-box";
import ip from "./ip-address";

function Question(props){
    const history = useHistory()
    const id = Cookies.get('User');
    var Tags;
    if(props.que.tags !== null)Tags = props.que.tags.split(">");
    else Tags = [];
    Tags.pop(); 
    function deletePost(id){
        axios.post(`http://${ip}:8000/deletePost/${id}`)
        .then(res => {
            if(res.data.result === 1) alert('post deleted');
            else alert('post deletion failed');
        })
        window.location.reload();
    }
    return (
        <div >
            <Link to = {`/questions/${props.que.id}`} > {props.que.title} </Link>
            <br/>
            {Tags.map(item => 
                <button style={{margin : '5px'}} > 
                    <Link to = {`/tags/${item.substr(1,item.length)}`} > 
                        {item.substr(1,item.length)} 
                    </Link> 
                </button> )}
            <p> Asked by <Link to = {`/profile/${props.que.user_id}`}>{props.que.user_name} </Link> </p>
            <p> {props.que.view_count} Views </p>
            <p> {props.que.answer_count} Answers </p>
            <p> {props.que.score} Up Votes</p>
            <p> Created on {props.que.creation_date} </p>
            <button onClick={(e) => {
                if(Number(id) === props.que.user_id) history.push(`/edit/${props.que.id}`);
                else alert('can not edit') 
            }}> Edit </button> 
            <button onClick={async (e) => {
                if(Number(id) === props.que.user_id) {
                    const result = await confirm('Are you sure you want to delete this post')
                    if(result) deletePost(props.que.id)
                }
                else alert('You can not delete this post');
            }}> Delete </button>
        </div>
    );
}

export default Question;