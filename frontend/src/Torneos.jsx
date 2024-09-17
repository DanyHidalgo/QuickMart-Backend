import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Torneos.css';
import defaultImage from '../images/default-image.webp'; // Ensure the relative path is correct

function Torneos() {
    const [torneos, setTorneos] = useState([]);

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
        <section className="torneos">
            <h2>Torneos</h2>
            <div className="torneos-list">
                {torneos.map(torneo => (
                    <div key={torneo.id} className="torneo-item">
                        {/* Using the imported default image */}
                        <img src={defaultImage} alt="Torneo" className="torneo-image" />
                        <div className="torneo-name">{torneo.nombre}</div>
                    </div>
                ))}
            </div>
        </section>
    );
}

export default Torneos;
