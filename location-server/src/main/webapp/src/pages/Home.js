import React from 'react';
import Map from "../components/Map";
import Header from "../components/Header";
import Navbar from "../components/Navbar"
import Footer from "../components/Footer"

function Home() {
    return (
        <>
            <Header/>
            <div className={'main-content'}>
                <Navbar/>
                <Map/>
            </div>
            <Footer/>
        </>
    );
}

export default Home;