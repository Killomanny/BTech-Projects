import { Link } from "react-router-dom";
import Cookies from "js-cookie";
import Welcome from "./Welcome";
function Home () {
  const id = Cookies.get('User');
  if(!id){
    return (
    <div id ='root' className="jumbotron centered">
        <div className="container">
          <Link className="btn btn-light btn-lg" to="/register" role="button">Register</Link>
          <Link className="btn btn-dark btn-lg" to="/login" role="button">Login</Link>
        </div>
    </div>
    );
  }
  else {
    return ( <Welcome />);
  }
}
export default Home;