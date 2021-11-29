import {MapContainer, TileLayer} from 'react-leaflet';
import {LatLng} from "leaflet/dist/leaflet-src.esm";
import {useState} from "react";
import LocationControl from "./LocationControl";

const Map = () => {
    const [map, setMap] = useState(null)
    const initialView: LatLng[] = new LatLng(39.8300172658557, -98.57428599054246)

    return (
        <>
            <MapContainer
                key={'react-leaflet-map-instance'}
                center={initialView}
                zoom={4}
                whenCreated={map => setMap(map)}
            >
                <TileLayer
                    key={'attribution-tag'}
                    attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                <LocationControl key={'location-control'} position={'topleft'}/>
            </MapContainer>
        </>

    );
}

export default Map;
