
import { useEffect, useState } from 'react';
import Layout from '../components/Layout';

function Employees({ user, setUser }) {
  const [employees, setEmployees] = useState([]);
  const [name, setName] = useState('');
  const [designation, setDesignation] = useState('');
  const [stack, setStack] = useState('');

  function loadEmployees() {
    fetch('http://localhost:8070/api/v1/employees')
      .then(res => res.json())
      .then(data => setEmployees(data))
      .catch(console.error);
  }

  useEffect(() => {
    loadEmployees();
  }, []);

  function addEmployee(e) {
    e.preventDefault();

    fetch('http://localhost:8070/api/v1/employees', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name, designation, stack })
    })
      .then(res => {
        if (!res.ok) throw new Error('Could not add employee');
        return res.json();
      })
      .then(() => {
        setName('');
        setDesignation('');
        setStack('');
        loadEmployees();
      })
      .catch(err => alert(err.message));
  }

  function deleteEmployee(id) {
    if (!window.confirm('Delete this employee?')) return;

    fetch('http://localhost:8070/api/v1/employees/' + id, {
      method: 'DELETE'
    })
      .then(res => {
        if (!res.ok) throw new Error('Could not delete employee');
        loadEmployees();
      })
      .catch(err => alert(err.message));
  }

  return (
    <Layout user={user} setUser={setUser}>
      <h1>Employees</h1>

      <div className="panel">
        <h2>Add Employee</h2>

        <form className="employee-form" onSubmit={addEmployee}>
          <input value={name} onChange={e => setName(e.target.value)}
            placeholder="Employee Name" required />

          <input value={designation}
            onChange={e => setDesignation(e.target.value)}
            placeholder="Designation" required />

          <input value={stack} onChange={e => setStack(e.target.value)}
            placeholder="Technology Stack" required />

          <button className="primary-btn">Add Employee</button>
        </form>
      </div>

      <div className="panel">
        <h2>Employee Directory</h2>

        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Designation</th>
                <th>Stack</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {employees.map(employee => (
                <tr key={employee.id}>
                  <td>#{employee.id}</td>
                  <td>{employee.name}</td>
                  <td>{employee.designation}</td>
                  <td>{employee.stack}</td>
                  <td>
                    <button className="delete-btn"
                      onClick={() => deleteEmployee(employee.id)}>
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
