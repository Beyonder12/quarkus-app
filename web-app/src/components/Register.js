import React, { useState } from 'react';
import {Link, useNavigate} from 'react-router-dom'; // Import Link from react-router-dom
import '../styles/Register.css';

function Register() {

    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const handleRegister = event => {
        event.preventDefault();

        if (password !== confirmPassword) {
            alert("Passwords don't match");
            return;
        }

        const userData = {
            username: username,
            email: email,
            password: password,
        }

        // replace '/api/register' with your API endpoint
        fetch('localhost:8080/api/v1/auth-service/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
            .then(response => response.json())
            .then(data => {
                if(data.success) {
                    navigate('/login');
                } else {
                    alert("Registration failed");
                }
            })
            .catch((error) => {
                console.error('Error:', error);
            });

        setUsername('');
        setEmail('');
        setPassword('');
        setConfirmPassword('');
    }

    return (
        <div className="register-container">
            <h1>Register</h1>
            <form className="register-form" onSubmit={handleRegister}>
                <div id="register-input-units" >
                    <input type="text" placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} />
                    <input type="email" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
                    <input type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} />
                    <input type="password" placeholder="Confirm Password" value={confirmPassword} onChange={e => setConfirmPassword(e.target.value)} />
                    <button type="submit">Register</button>
                </div>
            </form>
            <p>Already have an account? <Link to="/login">Log in</Link></p> {/* Add this line */}
        </div>
    );
}

export default Register;
