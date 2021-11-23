import React from 'react';
import './App.css';
import Home from "./pages/Home";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";


export default function App() {
  return (
      <Router>
          <Switch>
              <Route exact path="/" component={Home}/>
              <Route exact path="/user/:username" component={Home}/>
          </Switch>
      </Router>
  );
}

