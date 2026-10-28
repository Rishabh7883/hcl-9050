
import { useEffect, useState } from 'react';
import Layout from '../components/Layout';

function Dashboard({ user, setUser }) {
  const [employees, setEmployees] = useState([]);
  const [attendance, setAttendance] = useState([]);
  const [message, setMessage] = useState('');

  const today = new Date().toLocaleDateString('en-CA');

  function loadData() {
    fetch('http://localhost:8070/api/v1/employees')
      .then(res => res.json())
      .then(data => setEmployees(data))
      .catch(() => setMessage('Could not load employees'));

    fetch('http://localhost:8070/api/v1/attendance?date=' + today)
      .then(res => res.json())
      .then(data => setAttendance(data))
      .catch(() => setMessage('Could not load attendance'));
  }

  useEffect(() => {
    loadData();
  }, []);

  const myAttendance = attendance.find(
    a => String(a.employeeId) === String(user.id)
  );

  function checkIn() {
    fetch('http://localhost:8070/api/v1/attendance/check-in/' + user.id, {
      method: 'POST'
    })
      .then(res => {
        if (!res.ok) throw new Error('Check-in failed');
        return res.json();
      })
      .then(() => {
        setMessage('Check-in successful');
        loadData();
      })
      .catch(err => setMessage(err.message));
  }

  function checkOut() {
    if (!myAttendance) {
      setMessage('Please check in first');
      return;
    }

    fetch('http://localhost:8070/api/v1/attendance/' + myAttendance.id + '/check-out', {
      method: 'PATCH'
    })
      .then(res => {
        if (!res.ok) throw new Error('Check-out failed');
        return res.json();
      })
      .then(() => {
        setMessage('Check-out successful');
        loadData();
      })
      .catch(err => setMessage(err.message));
  }

  return (
    <Layout user={user} setUser={setUser}>
      <div className="welcome">
        <h1>Welcome, {user.name} 👋</h1>
        <p>Employee ID: #{user.id} | {today}</p>
      </div>

      {message && <p>{message}</p>}

      <div className="panel">
        <h2>🕒 My Daily Attendance</h2>

        <div className="attendance-cards">
          <div className="attendance-card green">
            <p>Check In Time</p>
            <h2>{myAttendance?.checkInTime || 'Not checked in'}</h2>
          </div>

          <div className="attendance-card blue">
            <p>Check Out Time</p>
            <h2>{myAttendance?.checkOutTime || 'Not checked out'}</h2>
          </div>
        </div>

        <button
          className="primary-btn"
          disabled={!!myAttendance}
          onClick={checkIn}
        >
          Check In
        </button>

        <button
          className="secondary-btn"
          disabled={!myAttendance || !!myAttendance.checkOutTime}
          onClick={checkOut}
        >
          Check Out
        </button>
      </div>

      <div className="panel">
        <div className="section-title">
          <div>
            <h2>👥 Employee Attendance Directory</h2>
            <p>Status of registered employees for today</p>
          </div>
          <button className="primary-btn" onClick={loadData}>Refresh</button>
        </div>

        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Employee Name</th>
                <th>Designation</th>
                <th>Stack</th>
                <th>Check In</th>
                <th>Check Out</th>
                <th>Status</th>
              </tr>
            </thead>

            <tbody>
              {employees.map(employee => {
                const record = attendance.find(
                  a => String(a.employeeId) === String(employee.id)
                );

                return (
                  <tr key={employee.id}>
                    <td>#{employee.id}</td>
                    <td>{employee.name}</td>
                    <td>{employee.designation || '-'}</td>
                    <td>{employee.stack || '-'}</td>
                    <td>{record?.checkInTime || '-'}</td>
                    <td>{record?.checkOutTime || '-'}</td>
                    <td>
                      {record?.checkOutTime
                        ? 'Completed'
                        : record ? 'Checked In' : 'Absent'}
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
    </Layout>
  );
}

export default Dashboard;
