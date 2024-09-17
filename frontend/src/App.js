import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Banner from './Banner';
import Torneos from './Torneos';
import './App.css';

function App() {
    const [torneos, setTorneos] = useState([]);

    // Fetch torneos data from the backend API
    useEffect(() => {
        axios.get('http://localhost:8080/api/torneos')
            .then(response => {
                setTorneos(response.data);
            })
            .catch(error => {
                console.error('Error fetching torneos:', error);
            });
    }, []);

    return (
        <div className="App">
            <Banner /> {/* Displaying the banner */}
            <div className="container">
                <Torneos torneos={torneos} /> {/* Passing torneos data to Torneos component */}
            </div>
        </div>
    );
}

export default App;
