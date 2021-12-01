import React from 'react';
import logo from '../images/Logo.png'
import {Link} from "react-router-dom";

const Header = (props) => {
    //link to server for authentication and authorization
    return (props.trigger) ? (
        <header key={'header'} className={'header'}>
            <img key={'logo-img'} className={'logo-img'} src={logo} alt="Logo"/>
            <Link key={'login-link'} className={'header-links'} to={'/login'}><h1>Login</h1></Link>
            <Link key={'account-link'} className={'header-links'} to="/register"><h1>Sign Up</h1></Link>
        </header>
    ) : '';
}

export default Header;
