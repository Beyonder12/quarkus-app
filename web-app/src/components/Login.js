import React, { useState } from 'react';
import { Link } from 'react-router-dom'; // Import Link from react-router-dom
import '../styles/Login.css'; // Import the CSS

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = event => {
        event.preventDefault();
        const userData = {
            username: username,
            password: password
        }
        console.log('Logged In User', userData);
        setUsername('');
        setPassword('');
    }

    return (
        <div className="login-container"> {/* Apply the styles */}
            <h1>Login</h1>
            <form className="login-form" onSubmit={handleLogin}>
                <input type="text" placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} />
                <input type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} />
                <button type="submit">Login</button>
            </form>
            <p>Don't have an account? <Link to="/register">Register</Link></p> {/* Add this line */}
        </div>
    );
}

export default Login;
