import React, {useEffect, useState} from 'react';
import Map from "../components/Map";
import Header from "../components/Header";
import AuthNavbar from "../components/AuthNavbar"
import Footer from "../components/Footer"
import AuthHeader from "../components/AuthHeader";
import Navbar from "../components/Navbar";

function Home() {
    const [authenticated, setAuthenticated] = useState(false);
    const [notAuthenticated, setNotAuthenticated] = useState(true);
    const auth = JSON.parse(localStorage.getItem('auth'))

    useEffect(() => {
        if (auth !== null) {
            setAuthenticated(true)
            setNotAuthenticated(false)
        }
        return () => {
            setAuthenticated(false)
            setNotAuthenticated(true)
        }
    }, [auth])


    return (
        <>
            <Header trigger={notAuthenticated}/>
            <AuthHeader auth={auth} trigger={authenticated}/>
            <div className={'main-content'}>
                <Navbar trigger={notAuthenticated}/>
                <AuthNavbar auth={auth} trigger={authenticated}/>
                <Map auth={auth}/>
            </div>
            <Footer/>
        </>
    );
}

export default Home;