
import { useEffect, useState } from 'react';
import Layout from '../components/Layout';

function Reports({ user, setUser }) {
  const [employees, setEmployees] = useState([]);
  const [attendance, setAttendance] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8070/api/v1/employees')
      .then(res => res.json())
      .then(data => setEmployees(data))
      .catch(console.error);

    fetch('http://localhost:8070/api/v1/attendance')
      .then(res => res.json())
      .then(data => setAttendance(data))
      .catch(console.error);
  }, []);

  return (
    <Layout user={user} setUser={setUser}>
      <h1>Reports</h1>

      <div className="stats-grid">
        <div className="panel">
          <h3>Total Employees</h3>
          <h1>{employees.length}</h1>
        </div>

        <div className="panel">
          <h3>Attendance Records</h3>
          <h1>{attendance.length}</h1>
        </div>
      </div>
    </Layout>
  );
}

export default Reports;
