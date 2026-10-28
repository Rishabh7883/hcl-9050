
import { useEffect, useState } from 'react';
import Layout from '../components/Layout';

const API = 'http://localhost:8070/api/v1';

function showTime(value) {
  if (!value) return '-';

  const match = value.match(/(?:T|\s)?(\d{2}):(\d{2})/);

  if (!match) return value;

  let hour = Number(match[1]);
  const minute = match[2];
  const ampm = hour >= 12 ? 'PM' : 'AM';

  hour = hour % 12 || 12;

  return `${hour}:${minute} ${ampm}`;
}

function Dashboard({ user, setUser }) {
  const [employees, setEmployees] = useState([]);
  const [records, setRecords] = useState([]);
  const [message, setMessage] = useState('');

  const today = [
    new Date().getFullYear(),
    String(new Date().getMonth() + 1).padStart(2, '0'),
    String(new Date().getDate()).padStart(2, '0')
  ].join('-');

  async function loadData() {
    try {
      const [employeeRes, attendanceRes] = await Promise.all([
        fetch(`${API}/employees`),
        fetch(`${API}/attendance?date=${today}`)
      ]);

      if (!employeeRes.ok || !attendanceRes.ok) {
        throw new Error('Unable to load employee or attendance data');
      }

      setEmployees(await employeeRes.json());
      setRecords(await attendanceRes.json());
    } catch (err) {
      setMessage(err.message);
    }
  }

  useEffect(() => {
    loadData();
  }, []);

  const myRecord = records.find(
    record => String(record.employeeId) === String(user?.id)
  );

  const checkedIn = Boolean(myRecord?.checkIn);
  const checkedOut = Boolean(myRecord?.checkOut);

  async function checkIn() {
    try {
      const res = await fetch(
        `${API}/attendance/check-in/${user.id}`,
        { method: 'POST' }
      );

      if (!res.ok) {
        throw new Error(
          res.status === 409
            ? 'Already checked in or check-in is not allowed'
            : 'Check-in failed'
        );
      }

      setMessage('Check-in successful');
      await loadData();
    } catch (err) {
      setMessage(err.message);
    }
  }

  async function checkOut() {
    if (!myRecord) {
      setMessage('Please check in first');
      return;
    }

    try {
      const res = await fetch(
        `${API}/attendance/${myRecord.id}/check-out`,
        { method: 'PATCH' }
      );

      if (!res.ok) {
        throw new Error('Check-out failed');
      }

      setMessage('Check-out successful');
      await loadData();
    } catch (err) {
      setMessage(err.message);
    }
  }

  return (
    <Layout user={user} setUser={setUser}>
      <div className="welcome">
        <h1>Welcome, {user?.name || user?.email || 'Employee'}!</h1>
        <p>Employee ID: #{user?.id}</p>
      </div>

      {message && <p>{message}</p>}

      <div className="panel">
        <h2>My Daily Attendance</h2>

        <div className="attendance-cards">
          <div className="attendance-card green">
            <p>Check In Time</p>
            <h2>{showTime(myRecord?.checkIn)}</h2>
          </div>

          <div className="attendance-card blue">
            <p>Check Out Time</p>
            <h2>{showTime(myRecord?.checkOut)}</h2>
          </div>
        </div>

        <button
          className="primary-btn"
          disabled={checkedIn}
          onClick={checkIn}
        >
          Check In
        </button>

        <button
          className="secondary-btn"
          disabled={!checkedIn || checkedOut}
          onClick={checkOut}
        >
          Check Out
        </button>
      </div>

      <div className="panel">
        <h2>Employee Attendance Directory</h2>

        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Check In</th>
                <th>Check Out</th>
                <th>Status</th>
              </tr>
            </thead>

            <tbody>
              {employees.map(employee => {
                const record = records.find(
                  item =>
                    String(item.employeeId) === String(employee.id)
                );

                return (
                  <tr key={employee.id}>
                    <td>{employee.id}</td>
                    <td>{employee.name}</td>
                    <td>{showTime(record?.checkIn)}</td>
                    <td>{showTime(record?.checkOut)}</td>
                    <td>
                      {record?.checkOut
                        ? 'Completed'
                        : record?.checkIn
                          ? 'Checked In'
                          : 'No Record'}
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
