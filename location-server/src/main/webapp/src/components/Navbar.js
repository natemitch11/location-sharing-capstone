import React from 'react';

function Navbar(props) {
    //GET req to get user devices once logged in
    //Empty Array that gets overwritten with the GET's response.
    //Function that is creating the span's representing devices
    //make device location filterable upon click

    return (
        <nav className={'nav-tools'}>
            <div className={'user-devices'}>
                <h2>Devices:</h2>
            </div>
            <a href="../pages/Home.js">
                <p>Add New Device</p>
            </a>

        </nav>
    );
}

export default Navbar;