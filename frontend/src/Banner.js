import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Banner.css';  // Import your CSS file
import bannerImage from './images/banner.jpg';  // Import the banner image

function Banner() {
    const [message, setMessage] = useState('Loading...');

    useEffect(() => {
        axios.get('http://localhost:8080/api/hola')
            .then((response) => {
                setMessage(response.data);
            })
            .catch((error) => {
                console.error('Error fetching data:', error);
                setMessage('Error fetching data');
            });
    }, []);

    return (
        <section
            className="banner"
            style={{ backgroundImage: `url(${bannerImage})` }}
        >
            <div className="banner-content">
                <h2>{message}</h2>
                <p>Your hub for all tournament updates</p>
                <a href="#tournaments" className="btn">View Tournaments</a>
            </div>
        </section>
    );
}

export default Banner;
