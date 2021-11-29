import React from 'react';
import logo from '../images/Logo.png'
import {Link} from "react-router-dom";

const Header = () => {
    //link to server for authentication and authorization
    return (
        <header key={'header'} className={'header'}>
            <img key={'logo-img'} className={'logo-img'} src={logo} alt="Logo"/>
            <Link key={'login-link'} className={'header-links'} to={'../pages/Login.js'}><h1>Login</h1></Link>
            <Link key={'account-link'} className={'header-links'} to="../pages/CreateAccount.js"><h1>Sign Up</h1></Link>
        </header>
    );
}

export default Header;
