import L from "leaflet";
import React, {useEffect, useRef, useState} from "react";
import {useLeafletContext} from "@react-leaflet/core";
import {Marker, useMap} from "react-leaflet";

//No Device Selected returns all user device locations, Device Selected should re GET and display only those locations for that device ID


function LocationControl(props) {
    const [markers, setMarkers] = useState([])
    const authenticated = useRef(false);
    const deviceIdPresent = useRef(false)
    const [location, setLocation] = useState({
        latitude: '',
        longitude: ''
    })
    const context = useLeafletContext();
    const mapInstance = useMap();
    const currentDevice = JSON.parse(localStorage.getItem('currentDevice'))

    useEffect(() => {
        if (props.auth !== null) {
            if (currentDevice !== null) {
                return setTimeout(getAllDeviceLocations, 600)
            }
            return setTimeout(getAllUserDeviceLocations, 600)
        }
    }, [])

    useEffect(() => {
        if (authenticated.current && deviceIdPresent.current) {
            setTimeout(postLocationToServer, 500)
        }
    }, [location])

    useEffect(() => {
        if (props.auth !== null) {
            authenticated.current = true
        }
        if (currentDevice !== null) {
            deviceIdPresent.current = true
        }
    }, [props.auth, currentDevice])

    const handleCoordinates = (lat, long) => {
        setLocation({latitude: lat, longitude: long})
    }

    L.Control.Button = L.Control.extend({
        onAdd: function (map) {
            let icon = L.DomUtil.create('button');
            icon.className = 'my-location-button'
            icon.onclick = () => {
                browserLocation()
            }
            return icon;
        },
        onRemove: function (map) {
        },
    })

    async function getAllDeviceLocations() {
        const response = await fetch(`http://localhost:8080/locations/device/${currentDevice}`, {
            mode: 'cors',
            method: "GET",
            headers: {'authorization': `Bearer ${props.auth.token}`},
        })
            .catch(err => console.error(err.message))
        const content = await response.json()
        setMarkers(() => content)
    }

    async function getAllUserDeviceLocations() {
        const response = await fetch(`http://localhost:8080/locations/user/${props.auth.username}`, {
            mode: 'cors',
            method: "GET",
            headers: {'authorization': `Bearer ${props.auth.token}`},
        }).catch(err => console.error(err.message))
        const content = await response.json()
        setMarkers(() => content)
    }

    async function postLocationToServer() {
        const postHeaders = {'Content-Type': 'application/json', 'authorization': `Bearer ${props.auth.token}`}
        await fetch(`http://localhost:8080/locations/${currentDevice}`, {
            mode: "cors",
            method: "POST",
            headers: postHeaders,
            body: JSON.stringify(location)
        }).catch(err => console.error(err.message))
    }

    function browserLocation() {
        navigator.geolocation.getCurrentPosition(success, failure, options)
    }

    function success(position) {
        let deviceId = 0
        const lat = position.coords.latitude
        const long = position.coords.longitude
        console.log(lat)
        console.log(long)
        handleCoordinates(lat, long)
        setMarkers(prevState => [...prevState, [deviceId, lat, long]])
        console.log("Accuracy within: " + position.coords.accuracy + "m")
        mapInstance.flyTo([lat, long], 13)
    }

    function failure(error) {
        console.log(error.message)
    }

    let options = {
        enableHighAccuracy: true
    }

    const locationButton = function (opts) {
        return new L.Control.Button(opts)
    }

    useEffect(() => {
        const container = context.layerContainer || context.map;
        const control = locationButton({position: props.position})
        container.addControl(control)

        return () => {
            container.removeControl(control)
        }
    }, [context.layerContainer, context.map, props.position])


    return (
        <React.Fragment>
            {markers.map((center) => <Marker key={`marker${markers.indexOf(center)}`}
                                             position={[center[1], center[2]]}/>)}
        </React.Fragment>
    )
}

export default LocationControl;

