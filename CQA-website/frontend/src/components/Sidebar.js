import Cookies from "js-cookie";
function Sidebar(){
    return (
        <div className="sidebar">
            <h1> Search By </h1>
            <button> <a href = {`/`}> Home </a></button>
            <button> <a href = {`/multiTags`}> Multiple tags </a></button>
            <button> <a href = {`/searchTags`}> Single Tag</a></button>
            <button> <a href = {`/users`}> User ID </a></button>
            <button onClick={(e) => {
                Cookies.remove('User');
            }} > <a href = {`/login`}> Log Out </a></button>
            <br/>
            <br/>
        </div>
    )
}

export default Sidebar;