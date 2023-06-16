import axios from "axios";
import Cookies from "js-cookie";
import { useState } from "react";
import {  useHistory } from "react-router-dom";
import Login from "./Login";
import ip from "./ip-address";

function Register () {
  const [username,setUsername] = useState("");
  const [password,setPassword] = useState("");
  const id = Cookies.get('User');
  const history = useHistory();
  function handleClick(event){
    event.preventDefault();
    axios.post(`http://${ip}:8000/register`,{
      username : username,
      password : password
    })
    .then( res => {
      if(res.data.result === 1) history.push('/login');
      else alert('Registration failed');
    })
}
    return (
<div className="container mt-5">
  <h1>Register</h1>

  <div className="row">
    <div className="col-sm-8">
      <div className="card">
        <div className="card-body">

          <form onSubmit={(e) => e.preventDefault()}>
            <div className="form-group">
              <label >Email</label>
              <input type="text" className="form-control" name="username" 
              value={username} onChange={(e) => setUsername(e.target.value)} />
            </div>
            <div className="form-group">
              <label >Password</label>
              <input type="password" className="form-control" name="password" 
               value={password} onChange={(e) => setPassword(e.target.value)}/>
            </div>
            <button className="btn btn-dark" type = "submit" 
            onClick={handleClick}>Register</button>
          </form>

        </div>
      </div>
    </div>

  </div>
</div>

    ); 
}

export default Register;