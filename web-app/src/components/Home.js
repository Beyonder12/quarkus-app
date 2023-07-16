import React from 'react';
import {Link} from "react-router-dom";


function Home() {
    return (
        <div className="Home">
            <h1>Welcome to our app, Mr. Fajri!</h1>
            <p>MODULE</p>
            <Link to="/">Logout</Link>
        </div>
    );
}

export default Home;
