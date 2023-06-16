import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import Welcome from './components/Welcome';
import ViewQuesion from './components/ViewQuestion';
import Tags from './components/Tags';
import MultiTags from './components/MultiTags';
import Profile from './components/Profile';
import Users from './components/Users';
import SearchTags from './components/SearchTags';
import UserPosts from './components/UserPosts';
import CreatePost from './components/CreatePost';
import EditQuestion from './components/EditQuestion';
import UserAnswers from './components/UserAnswers';

function App() {
  return (
    <Router>
      <div>
        <Switch>
          <Route exact path = "/">
            <Home />
          </Route>
          <Route path = "/login">
            <Login />
          </Route>
          <Route path = "/register">
            <Register />
          </Route>
          {/* <Route path = "/home">
            <Welcome />
          </Route> */}
          <Route path = "/questions/:pid" >
            < ViewQuesion />
          </Route>
          <Route path = "/tags/:tagName" >
            <Tags />
          </Route>
          <Route path = "/multiTags">
            <MultiTags />
          </Route>
          <Route path = "/users" >
            <Users />
          </Route>
          <Route path = "/searchTags" >
            <SearchTags />
          </Route>
          <Route path = "/profile/:id">
            <Profile />
          </Route>
          <Route path = "/userPosts/:id">
            <UserPosts /> 
          </Route>
          <Route path = "/userAnswers/:id">
            <UserAnswers />
          </Route>
          <Route path = "/createPost">
            <CreatePost />
          </Route>
          <Route path = "/edit/:pid">
            <EditQuestion />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;


