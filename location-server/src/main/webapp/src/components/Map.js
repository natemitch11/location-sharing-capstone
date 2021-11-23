import React from 'react';
import {MapContainer, Marker, Popup, TileLayer} from 'react-leaflet';

let center1 = [40.35836778727895, -111.9542038456369]

let center = []

const Map = () => {
    //set function to a button
    //loop through device location coords and add a marker for each.
    let options = {
        enableHighAccuracy: true,
        timeout: 10,
        maximumAge: 500
    }

    function getBrowserLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(successPosition, errorPosition, options)
        } else {
            center = [0, 0]
        }
    }

    function errorPosition(error) {
        console.warn(`${error.code}: ${error.message}`)
    }

    function successPosition(position) {
        let lat = position.coords.latitude
        let long = position.coords.longitude
        let accuracy = position.coords.accuracy
        console.log(`Accuracy: ${accuracy}m`)
        center[0] = lat
        center[1] = long
    }


    return (
        <MapContainer center={center} zoom={12}>
            <TileLayer
                attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <Marker position={center1}>
                <Popup>GPS Coords from Address</Popup>
            </Marker>
            <Marker position={center}>
                <Popup>GPS Coords from HTML Geolocation</Popup>
            </Marker>
        </MapContainer>
    );
}

export default Map;
