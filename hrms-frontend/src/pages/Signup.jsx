
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Signup() {
  const [form, setForm] = useState({
    name: '',
    email: '',
    password: '',
    designation: '',
    stack: ''
  });

  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  function handleChange(e) {
    setForm({ ...form, [e.target.name]: e.target.value });
  }

  function handleSignup(e) {
    e.preventDefault();
    setMessage('');

    fetch('http://localhost:8070/api/v1/employees', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form)
    })
      .then(async res => {
        if (!res.ok) {
          throw new Error(await res.text() || 'Signup failed');
        }
        return res.json();
      })
      .then(() => {
        alert('Signup successful. Please login.');
        navigate('/login');
      })
      .catch(err => setMessage(err.message));
  }

  return (
    <div className="login-page">
      <form className="login-box" onSubmit={handleSignup}>
        <h1>HRMS Signup</h1>

        <input name="name" placeholder="Username / Full Name"
          value={form.name} onChange={handleChange} required />

        <input name="email" type="email" placeholder="Email"
          value={form.email} onChange={handleChange} required />

        <input name="password" type="password" placeholder="Password"
          value={form.password} onChange={handleChange} required />

        <input name="designation" placeholder="Designation"
          value={form.designation} onChange={handleChange} required />

        <input name="stack" placeholder="Technology Stack"
          value={form.stack} onChange={handleChange} required />

        <button className="primary-btn">Sign Up</button>

        {message && <p className="error">{message}</p>}

        <p>Already registered? <Link to="/login">Login</Link></p>
      </form>
    </div>
  );
}

export default Signup;
