import React, { useEffect, useState } from 'react';
import axios from 'axios';

function App() {
    const [data, setData] = useState(null);

    useEffect(() => {
        // Conectando con el backend utilizando el nombre del servicio en Docker Compose
        axios.get('http://backend:8080/api/hola')  // Aquí se usa 'backend' como nombre del servicio
            .then(response => {
                setData(response.data);  // Almacenar los datos recibidos del backend
            })
            .catch(error => {
                console.error('Error fetching data', error);  // Manejar errores
            });
    }, []);  // El array vacío [] asegura que este código solo se ejecute una vez cuando el componente se monte.

    return (
        <div>
            <h1>Data from Backend:</h1>
            <pre>{JSON.stringify(data, null, 2)}</pre>  // Mostrar los datos recibidos
        </div>
    );
}

export default App;
