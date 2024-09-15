import React, { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
    const [message, setMessage] = useState('Loading...');

    useEffect(() => {
        axios.get('http://localhost:8080/api/hola')
            .then(response => {
                setMessage(response.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error); // Log the full error
                console.log('Error details:', error.response); // Log additional error details
                setMessage('Error fetching data');
            });
    }, []);

    return (
        <div className="App">
            <h1>Message from Backend:</h1>
            <p>{message}</p>
        </div>
    );
}

export default App;
