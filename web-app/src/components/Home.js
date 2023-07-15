import React from 'react';
import { Link } from 'react-router-dom';

function Home() {
    return (
        <div className="Home">
            <h1>Welcome to our app!</h1>
            <p>Please choose an option below:</p>
            <Link to="/login">Login</Link>
            <br/>
            <Link to="/register">Register</Link>
        </div>
    );
}

export default Home;
