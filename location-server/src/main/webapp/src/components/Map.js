import React from 'react';
import {MapContainer, Marker, Popup, TileLayer} from 'react-leaflet';

let coordsArray = [[40.35836778727895, -111.9542038456369]]


function Map() {
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
            return console.error("Please Allow Location Permission")
        }
    }

    function errorPosition(error) {
        console.warn(`${error.code}: ${error.message}`)
    }

    function successPosition(position) {
        let latLong = []
        let lat = position.coords.latitude
        let long = position.coords.longitude
        let accuracy = position.coords.accuracy
        console.log(`Accuracy: ${accuracy}m`)
        latLong = [lat, long]
        coordsArray.push(latLong)
    }

    //let latLongMarker = coordsArray.map((value => <Marker center={`${value[0]} , ${value[1]}`}></Marker> ))

    return (
        <MapContainer center={coordsArray[0]} zoom={12}>

            <TileLayer
                attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            {latLongMarker}
        </MapContainer>
    );
}

export default Map;
