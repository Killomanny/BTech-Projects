import axios from "axios";
import Cookies from "js-cookie";
import { useState} from "react";
import { Link } from "react-router-dom";
import Login from "./Login";
import ip from "./ip-address";

function Answer(props){
    const id = Cookies.get('User');
    const [data,setData] = useState(null);
    const [edit,setEdit] = useState(null);
    const [box,setBox] = useState(null);
    const [answer,setAnswer] = useState("");
    axios.get(`http://${ip}:8000/viewAnswer/${props.id}`)
    .then(res => {
        setData(res.data.answer)
        if(Number(id) === data.user_id) setEdit(1);
    });
    function handleSubmit(){
        axios.post(`http://${ip}:8000/editAnswer/${props.id}`,{
            content : answer
        })
        .then(res => {
            if(res.data.result === 1) {
                alert('success');
                setBox(null);
            }
            else alert('Fail');
        })
    }
    if(id) {
    return(
        <div>
        { data &&
        <div>
            <h4>Answered by <Link to = {`/profile/${data.user_id}`}> {data.user_name} </Link></h4>
            <div dangerouslySetInnerHTML={{__html: data.body}} />
            { edit && <div>
                <button onClick ={(e) => {
                setBox(1);
            }} >Edit</button>
            </div> }
            {box && <form onSubmit = {handleSubmit}> 
                <textarea rows = "5" cols = "100" value = {answer}
                onChange = {(e) => setAnswer(e.target.value) }> </textarea> <br/>
                <button type = "submit" >OK</button>
                </form>
            }

        </div> 
        }
        </div>
    )}
    else return (
        <Login />
    )
}

export default Answer;