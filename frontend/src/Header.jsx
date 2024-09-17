// src/Header.jsx

import React from 'react';
import './Header.css'; // Import the CSS file

function Header() {
    return (
        <header className="header">
            <div className="logo">
                <h1>QuickMatch</h1>
            </div>
            <nav className="nav">
                <ul>
                    <li><a href="#tournaments">Tournaments</a></li>
                    <li><a href="#teams">Teams</a></li>
                    <li><a href="#matches">Matches</a></li>
                    <li><a href="#signup">Sign Up</a></li>
                    <li><a href="#login">Log In</a></li>
                </ul>
            </nav>
        </header>
    );
}

export default Header;
