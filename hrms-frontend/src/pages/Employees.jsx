
import { useEffect, useState } from 'react';
import Layout from '../components/Layout';

function Employees({ user, setUser }) {
  const [employees, setEmployees] = useState([]);
  const [form, setForm] = useState({
    name: '', email: '', password: '', designation: '', stack: ''
  });
  const [message, setMessage] = useState('');

  async function loadEmployees() {
    try {
      const res = await fetch('http://localhost:8070/api/v1/employees');
      if (!res.ok) throw new Error('Could not load employees');
      setEmployees(await res.json());
    } catch (err) {
      setMessage(err.message);
    }
  }

  useEffect(() => {
    loadEmployees();
  }, []);

  async function addEmployee(e) {
    e.preventDefault();

    try {
      const res = await fetch('http://localhost:8070/api/v1/employees', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(form)
      });

      if (!res.ok) throw new Error('Could not add employee');

      setForm({
        name: '', email: '', password: '', designation: '', stack: ''
      });
      setMessage('Employee added');
      loadEmployees();
    } catch (err) {
      setMessage(err.message);
    }
  }

  async function deleteEmployee(id) {
    if (!window.confirm('Delete this employee?')) return;

    try {
      const res = await fetch(
        `http://localhost:8070/api/v1/employees/${id}`,
        { method: 'DELETE' }
      );

      if (!res.ok) throw new Error('Could not delete employee');

      loadEmployees();
    } catch (err) {
      setMessage(err.message);
    }
  }

  return (
    <Layout user={user} setUser={setUser}>
      <h1>Employees</h1>
      {message && <p>{message}</p>}

      <div className="panel">
        <h2>Add Employee</h2>

        <form className="employee-form" onSubmit={addEmployee}>
          {Object.keys(form).map(field => (
            <input
              key={field}
              placeholder={field[0].toUpperCase() + field.slice(1)}
              type={field === 'password' ? 'password' : 'text'}
              value={form[field]}
              onChange={e => setForm({
                ...form, [field]: e.target.value
              })}
              required
            />
          ))}
          <button className="primary-btn">Add Employee</button>
        </form>
      </div>

      <div className="panel">
        <h2>Employee List</h2>

        <div className="table-wrap">
          <table>
            <thead>
              <tr><th>ID</th><th>Name</th><th>Email</th><th>Designation</th><th>Action</th></tr>
            </thead>
            <tbody>
              {employees.map(e => (
                <tr key={e.id}>
                  <td>{e.id}</td>
                  <td>{e.name}</td>
                  <td>{e.email || '-'}</td>
                  <td>{e.designation || '-'}</td>
                  <td>
                    <button className="delete-btn"
                      onClick={() => deleteEmployee(e.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </Layout>
  );
}

export default Employees;
