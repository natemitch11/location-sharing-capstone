import React from 'react';

const NewDevice = (props) => {


    return (props.trigger) ? (
        <div className={"new-device-popup"}>
            <div className={"new-device-form-container"}>
                {props.children}
                <button
                    onClick={() => props.setTrigger(false)}
                    className={"popup-close new-device-button"}> X
                </button>
            </div>
        </div>
    ) : '';

};

export default NewDevice;