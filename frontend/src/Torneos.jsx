import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Torneos.css'; // Ensure correct styling

function Torneos() {
    const [torneos, setTorneos] = useState([]);

    useEffect(() => {
        // Fetch torneos data from the backend
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
            <div>
                {torneos.map(torneo => (
                    <div key={torneo.id} className="torneo-item">
                        <img src="/default-image.webp" alt="Torneo" /> {/* Default image */}
                        <div className="torneo-name">{torneo.nombre}</div>
                    </div>
                ))}
            </div>
        </section>
    );
}

export default Torneos;
