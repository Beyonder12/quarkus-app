import React, { useState } from 'react';
import {Link, useNavigate} from 'react-router-dom'; // Import Link from react-router-dom
import '../styles/Register.css';


function Register() {

    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleRegister = event => {
        event.preventDefault();
        const userData = {
            username: username,
            password: password
        }
        console.log('Registered User', userData);
        setUsername('');
        setPassword('');

        if(username === 'guest') navigate('/login');

        // Assuming your registration is successful navigate to login pageIn a real application,
        // you would make an API call to register the userand only naviga
        // te after receiving a successful response

  }

  return (
      <div className="register-container">
          <h1>Register</h1>
          <form className="register-form" onSubmit={handleRegister}>
              <div id="register-input-units" >
                  <input type="text" placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} />
                  <input type="email" placeholder="Email" value={password} onChange={e => setPassword(e.target.value)} />
                  <input type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} />
                  <input type="password" placeholder="Confirm Password" value={password} onChange={e => setPassword(e.target.value)} />
                  <button type="submit">Register</button>
              </div>

          </form>
          <p>Already have an account? <Link to="/login">Log in</Link></p> {/* Add this line */}
      </div>
  );
}

export default Register;
