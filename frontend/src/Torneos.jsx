import React from 'react';
import './Torneos.css';
import defaultImage from './images/default-image.webp'; // Update the path accordingly

function Torneos({ torneos }) {
    return (
        <section className="torneos">
            <h2>Torneos</h2>
            <div className="torneo-list">
                {torneos.length === 0 ? (
                    <p>No torneos available.</p>
                ) : (
                    torneos.map((torneo) => (
                        <div key={torneo.id} className="torneo-item">
                            <img src={defaultImage} alt="Torneo" className="torneo-image" />
                            <h3>{torneo.nombre}</h3>
                        </div>
                    ))
                )}
            </div>
        </section>
    );
}

export default Torneos;
