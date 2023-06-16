import axios from "axios";
import Cookies from "js-cookie";
import { useEffect, useState } from "react";
import { Link, useHistory, useParams } from "react-router-dom"
import Answer from "./Answer";
import Sidebar from "./Sidebar";
import Login from "./Login";
import ip from "./ip-address";

function ViewQuesion() {
    const id = Cookies.get('User');
    const {pid} = useParams();
    const [data,setdata] = useState(null);
    const [body,setBody] = useState('');
    const history = useHistory();
    useEffect( () => {
        axios.get(`http://${ip}:8000/questions/${pid}`)
        .then (res => {
            setdata(res.data);
        }); 
    },[pid]);
    function submitAnswer(e){
        e.preventDefault();
        axios.post(`http://${ip}:8000/answer/${id}/${pid}`,{content : body})
        .then(res => {
            if(res.data.output === 1) {
                alert('Answer posted successfully');
                history.push(`/questions/${pid}`);
            }
            else alert('Failed');
        })
    }
    if(id) {
    return (
        <div className="main">
            <Sidebar />
            { data && 
            <div > 
                <h1> {data.content.title} </h1> 
                <div dangerouslySetInnerHTML={{__html: data.content.body}}/>
                <div>
                    <form onSubmit={(e) => submitAnswer (e) }>
                        <h1> Your Answer </h1>
                        <textarea rows = "10" cols = "100" 
                        onChange={(e) => setBody(e.target.value)}> </textarea> 
                        <br/>
                        <button type = "submit"> Post Your Answer </button> 
                        <br/> <br/>
                    </form>
                 </div>
                { data.answers.map((item,index) => {
                return (
                <div className="container"> 
                    <h3> Answer {index+1} </h3>
                    <Answer id = {item.id} />
                </div>
                );
            }
                 )}
            </div> 
            }
        </div>
    ) }
    else {
        return (
            <Login />
        )
    }
}

export default ViewQuesion;