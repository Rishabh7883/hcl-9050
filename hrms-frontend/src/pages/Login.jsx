
function Login({ setUser }) {
  function handleLogin(e) {
    e.preventDefault();

    const name = e.target.name.value;
    const id = e.target.employeeId.value;

    setUser({
      name: name,
      id: id
    });
  }

  return (
    <div className="login-page">
      <form className="login-box" onSubmit={handleLogin}>
        <h1>HRMS</h1>
        <p>Human Resource Management System</p>

        <input name="name" placeholder="Employee Name" required />
        <input name="employeeId" placeholder="Employee ID" required />

        <button className="primary-btn">Login</button>
      </form>
    </div>
  );
}

export default Login;
