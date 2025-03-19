import React from 'react';
import { Link } from 'react-router-dom';

function HomePage() {
    return (
        <div>
            <h1>Welcome to E-Hotel</h1>
            <p>Your best hotel booking experience.</p>
            <Link to="/rooms">
                <button>Find a Room</button>
            </Link>
        </div>
    );
}

export default HomePage;
