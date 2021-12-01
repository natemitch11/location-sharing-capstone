import React from 'react';

const Navbar = (props) => {
    return (props.trigger) ? (
        <nav key={'nav-tools'} className={'nav-tools-unauth'}>
            <h2>Login to Add Devices!</h2>
        </nav>
    ) : '';
};

export default Navbar;