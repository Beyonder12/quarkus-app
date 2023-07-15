import React, { useState } from 'react';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = event => {
        event.preventDefault();
        const userData = {
            username: username,
            password: password
        }
        // In real application, send userData to your API for login process
        console.log('Login User', userData);
        setUsername('');
        setPassword('');
    }

    return (
        <div className="App">
            <h1>Login</h1>
            <form>
                <input type="text" placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} />
                <input type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} />
                <button type="submit" onClick={handleLogin}>Login</button>
            </form>
        </div>
    );
}

export default Login;
