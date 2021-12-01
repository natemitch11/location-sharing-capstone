import logo from "../images/Logo.png";
import React from "react";

const AuthHeader = (props) => {

    const handleLogout = async () => {
        // await fetch("http://localhost:8080/auth/users/logout",{
        //     method: "POST",
        //     body: JSON.stringify(localStorage.getItem('auth').username),
        //     headers: {'Content-Type':'application/json',
        //               'Authorization':`Bearer ${props.auth.token}`}
        // }).catch(err => console.error(err.message))
        localStorage.clear();
        window.location.reload()
    }

    return (props.trigger) ? (
        <header key={'header'} className={'header'}>
            <img key={'logo-img'} className={'logo-img'} src={logo} alt="Logo"/>
            <button className={"new-device-button"} onClick={handleLogout}>Logout</button>
        </header>
    ) : '';
}

export default AuthHeader;