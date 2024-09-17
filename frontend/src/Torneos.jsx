import React from 'react';
import defaultImage from './images/default-image.webp'; // Corrected path

function Torneos({ torneos }) {
    return (
        <section className="torneos">
            <h2>Torneos</h2>
            <div className="torneos-list">
                {torneos.map((torneo) => (
                    <div key={torneo.id} className="torneo">
                        <img src={defaultImage} alt="Torneo" className="torneo-image" />
                        <h3>{torneo.nombre}</h3>
                    </div>
                ))}
            </div>
        </section>
    );
}

export default Torneos;
