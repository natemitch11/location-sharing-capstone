import React from 'react';
import {Link} from "react-router-dom";


function Navbar() {

    //Toggle whether devices or login prompt is rendered based on authentication

    //GET req to get user devices once logged in

    //Empty Array that gets overwritten with the GET's response.
    let userDeviceArray = [["device1", "mobile"], ["device2", "laptop"]]
    //Function that is creating the span's representing devices
    let deviceSpan = userDeviceArray.map((val) => (
        <span key={`device${userDeviceArray.indexOf(val)}`} className={'device-span'} id={val[0]}>
            Device Name: {val[0]} Type: {val[1]}
        </span>)
    )
    //make device location filterable upon click

    //new device pop-up form and post request
    return (
        <nav key={'nav-tools'} className={'nav-tools'}>
            <div key={'user-devices'} className={'user-devices'}>
                <h2>Devices:</h2>
                <div className="device-container">
                    {deviceSpan}

                </div>
            </div>
            <Link className={'new-device-link'} to={'../pages/Home.js'}>Add New Device</Link>
        </nav>
    );
}

export default Navbar;