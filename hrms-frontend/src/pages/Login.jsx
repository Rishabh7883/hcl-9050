
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Login({ setUser }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  function handleLogin(e) {
    e.preventDefault();
    setMessage('');

    fetch('http://localhost:8070/api/v1/employees/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password })
    })
      .then(async res => {
        if (!res.ok) {
          throw new Error('Invalid email or password');
        }
        return res.json();
      })
      .then(employee => {
        setUser(employee);
        navigate('/');
      })
      .catch(err => setMessage(err.message));
  }

  return (
    <div className="login-page">
      <form className="login-box" onSubmit={handleLogin}>
        <h1>HRMS Login</h1>

        <input type="email" placeholder="Email"
          value={email} onChange={e => setEmail(e.target.value)} required />

        <input type="password" placeholder="Password"
          value={password} onChange={e => setPassword(e.target.value)} required />

        <button className="primary-btn">Login</button>

        {message && <p className="error">{message}</p>}

        <p>New employee? <Link to="/signup">Sign Up</Link></p>
      </form>
    </div>
  );
}

export default Login;
