import React from 'react';
import logo from '../images/Found.png'

const Header = () => {
    return (
        <header className={'header'}>
            <img className={'logo-img'} src={logo} alt="Logo"/>
            <a href="../pages/Login.js"><h1>Login</h1></a>
            <a href="../pages/CreateAccount.js"><h1>Sign Up</h1></a>
        </header>
    );
}

export default Header;
