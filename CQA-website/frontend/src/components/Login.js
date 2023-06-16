import axios from "axios";
import { useState } from "react";
import {  useHistory } from "react-router-dom"; 
import Cookies from 'js-cookie';
import ip from "./ip-address";


function Login(){
  const id = Cookies.get('User');
  const [username,setUsername] = useState("");
  const [password,setPassword] = useState("");
  const history = useHistory();
  function handleSubmit(event){
    event.preventDefault();
    axios.post(`http://${ip}:8000/login`,{
      username : username,
      password : password
    })
    .then(async(res) => {
      if(res.data.result === 1) {
        Cookies.set('User',res.data.id,{expires:1});
        history.push(`/`);
      }
      else alert("Invalid credentials");
    })
}
if(!id) {
return (
<div id="root" className="container mt-5">
  <h1>Login</h1>

  <div className="row">
    <div className="col-sm-8">
      <div className="card">
        <div className="card-body">

          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label >Email</label>
              <input type="text" className="form-control" name="username"
              value={username} onChange={(e) => setUsername(e.target.value)} />
            </div>
            <div className="form-group">
              <label >Password</label>
              <input type="password" className="form-control" name="password"
              value={password} onChange={(e) => setPassword(e.target.value)} />
            </div>
            <button type="submit" className="btn btn-dark">Login</button>
          </form>

        </div>
      </div>
    </div>

  </div>
</div>
    ); }
    else {
      history.push("/");
    }
}

export default Login;