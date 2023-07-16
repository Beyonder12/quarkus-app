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
        fetch('http://localhost:1000/auth-service/api/v1/auths/registrations', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
            .then(response => {
                console.log(response)
                return response.json()
            })
            .then(data => {
                if(data.id) {
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
                    <input type="text" placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} required/>
                    <input type="email" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} required/>
                    <input type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} required/>
                    <input type="password" placeholder="Confirm Password" value={confirmPassword} onChange={e => setConfirmPassword(e.target.value)} required/>
                    <button type="submit">Register</button>
                </div>
            </form>
            <p>Already have an account? <Link to="/login">Log in</Link></p> {/* Add this line */}
        </div>
    );
}

export default Register;
