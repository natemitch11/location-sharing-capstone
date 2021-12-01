import React from 'react';
import './App.css';
import Home from "./pages/Home";
import Login from "./pages/Login";
import CreateAccount from "./pages/CreateAccount";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";


export default function App() {
    return (
        <Router>
            <Switch>
                <Route exact path="/" component={Home}/>
                {/*<Route exact path="/user/:username" component={Home}/>*/}
                <Route exact path="/login" component={Login}/>
                <Route exact path="/register" component={CreateAccount}/>
            </Switch>
      </Router>
  );
}

