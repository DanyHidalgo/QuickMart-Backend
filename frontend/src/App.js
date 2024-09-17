import React from 'react';
import Banner from './Banner';
import Torneos from './Torneos';
import Grupos from './Grupos'; // Assuming you'll have this component

function App() {
    return (
        <div className="App">
            <Banner /> {/* Displaying Banner */}
            <div className="container">
                <Torneos /> {/* Left side: Torneos */}
                <Grupos />   {/* Right side: Grupos */}
            </div>
        </div>
    );
}

export default App;
