import React from 'react';
import {Link} from "react-router-dom";
import getBrowserLocation from "./Map";

function Navbar(props) {
    // function handleBrowserLocation(e){
    //     e.preventDefault()
    //     getBrowserLocation()
    // }
    //GET req to get user devices once logged in
    //Empty Array that gets overwritten with the GET's response.
    let userDeviceArray = [["device1", "mobile"],["device2", "laptop"]]
    let deviceContainer = document.querySelector('.device-container')
    //Function that is creating the span's representing devices

    let deviceSpan = userDeviceArray.map((val) => (<span>{val[0]} type: {val[1]}</span>))

    //make device location filterable upon click

    return (
        <nav className={'nav-tools'}>
            <div className={'user-devices'}>
                <h2>Devices:</h2>
                <div className="device-container">
                    {deviceSpan}

                </div>
            </div>
            <Link className={'new-device-link'} to={'../pages/Home.js'} >Add New Device</Link>
            <span className={"browser-location"} onClick={getBrowserLocation}>Your Location</span>
        </nav>
    );
}

export default Navbar;