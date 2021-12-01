import React, {useEffect, useState} from 'react';
import NewDevice from "./NewDevice";
import {addClassName, removeClassName} from "@react-leaflet/core";


function AuthNavbar(props) {
    const [buttonPopup, setButtonPopup] = useState(false)
    const [newDevice, setNewDevice] = useState({
        name: '',
        classification: ''
    })
    const [submitted, setSubmitted] = useState(false);
    const [userDevices, setUserDevices] = useState([])
    const [isCurrent, setIsCurrent] = useState(localStorage.getItem("currentDevice"))
    const auth = props.auth;

    useEffect(() => {
        if (auth !== null) {
            getUserDevice()
        }
    }, [])

    const handleDeviceName = (event) => {
        setNewDevice({...newDevice, name: event.target.value})
    }

    const handleDeviceType = (event) => {
        setNewDevice({...newDevice, classification: event.target.value})
    }

    const handleSubmit = async (event) => {
        setSubmitted(true);
        const postHeaders = {'Content-Type': 'application/json', 'authorization': `Bearer ${props.auth.token}`}
        await fetch(`http://localhost:8080/users/${props.auth.username}/devices`, {
            mode: 'cors',
            method: 'POST',
            headers: postHeaders,
            body: JSON.stringify(newDevice)
        })
            .catch(err => console.error(err.message))
        await getUserDevice()
    }

    const getUserDevice = async () => {
        const getHeaders = {'authorization': `Bearer ${props.auth.token}`}
        const response = await fetch(`http://localhost:8080/users/${props.auth.username}/devices`, {
            mode: 'cors',
            method: "GET",
            headers: getHeaders
        }).catch(err => console.error(err.message))
        const content = await response.json()
        setUserDevices(() => content)
    }
    const deleteUserDevice = async (e) => {
        const body = {
            id: parseInt(e.target.value)
        }
        const deleteHeaders = {'Content-Type': 'application/json', 'authorization': `Bearer ${props.auth.token}`}
        const response = await fetch(`http://localhost:8080/users/${props.auth.username}/devices`, {
            mode: 'cors',
            method: "DELETE",
            headers: deleteHeaders,
            body: JSON.stringify(body)
        })
            .catch(err => console.error(err.message))
            .finally(() => {
                removeDeviceFromLocal()
                window.location.reload()
            })
    }

    function setCurrentDevice(e) {
        localStorage.setItem("currentDevice", e.target.id)
        if (isCurrent !== null) {
            removeClassName(document.getElementById(isCurrent), 'current-device')
        }
        if (isCurrent === e.target.id) {
            removeDeviceFromLocal()
            return removeClassName(document.getElementById(isCurrent), 'current-device')
        }
        setIsCurrent(e.target.id)
        addClassName(document.getElementById(e.target.id), 'current-device')
    }

    function removeDeviceFromLocal() {
        localStorage.removeItem('currentDevice')
    }

    let deviceSpan = userDevices.map((val) => (
        <span onClick={setCurrentDevice} key={`device${userDevices.indexOf(val)}`} className={'device-span'}
              id={val[2]}>
            ID: {val[2]} | {val[0]} | {val[1]}
            <button onClick={deleteUserDevice} className={'new-device-button'} value={val[2]}
                    id={'delete-device'}>X</button>
        </span>)
    )

    return (props.trigger) ? (
        <nav key={'nav-tools'} className={'nav-tools'}>
            <div key={'user-devices'} className={'user-devices'}>
                <h2>Devices:</h2>
                <div className="device-container">
                    {deviceSpan}
                </div>
            </div>
            <button
                onClick={() => setButtonPopup(true)}
                className={"new-device-button"}> + Add New Device
            </button>
            <NewDevice trigger={buttonPopup} setTrigger={setButtonPopup}>
                <form className={"new-device-form"} onSubmit={handleSubmit}>
                    <input
                        className={"form-field"}
                        onChange={handleDeviceName}
                        name="deviceName"
                        placeholder={"Device Name"}
                        value={newDevice.name}
                        type="text"
                        required/>
                    <input
                        className={"form-field"}
                        onChange={handleDeviceType}
                        name="deviceType"
                        placeholder={"Device Type"}
                        value={newDevice.classification}
                        type="text"
                        required/>
                    <button className={"new-device-button"} type={"submit"}>+ Add Device</button>
                </form>
            </NewDevice>
        </nav>
    ) : '';
}

export default AuthNavbar;