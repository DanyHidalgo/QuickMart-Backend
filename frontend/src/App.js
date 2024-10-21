import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Banner from './Banner';
import Torneos from './Torneos';
import Grupos from './Grupos';
import FeaturedTeams from './FeaturedTeams';
import './App.css';

function App() {
    const [torneos, setTorneos] = useState([]);

    // Fetch torneos data from the backend API
    // eventualmente llamar API de grupos
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
            <div className="bottom-layout"> {/* Updated layout */}
                <div className="torneos">
                    <Torneos torneos={torneos} /> {/* Torneos on the left */}
                </div>
                <div className="grupos">
                    <Grupos /> {/* Grupos in the middle */}
                </div>
                <div className="featuredteams"> {/* Featured Teams on the right */}
                    <FeaturedTeams />
                </div>
            </div>
        </div>
    );
}

export default App;

