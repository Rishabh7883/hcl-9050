
import { useEffect, useState } from 'react';
import Layout from '../components/Layout';

const API = 'http://localhost:8070/api/v1/attendance';

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

function Attendance({ user, setUser }) {
  const [records, setRecords] = useState([]);
  const [message, setMessage] = useState('');

  async function loadAttendance() {
    try {
      const res = await fetch(API);

      if (!res.ok) {
        throw new Error('Could not load attendance');
      }

      setRecords(await res.json());
      setMessage('');
    } catch (err) {
      setMessage(err.message);
    }
  }

  useEffect(() => {
    loadAttendance();
  }, []);

  async function deleteRecord(id) {
    if (!window.confirm('Delete this attendance record?')) return;

    try {
      const res = await fetch(`${API}/${id}`, {
        method: 'DELETE'
      });

      if (!res.ok) {
        throw new Error('Could not delete record');
      }

      setMessage('Attendance record deleted');
      await loadAttendance();
    } catch (err) {
      setMessage(err.message);
    }
  }

  return (
    <Layout user={user} setUser={setUser}>
      <h1>Attendance Management</h1>

      {message && <p>{message}</p>}

      <div className="panel">
        <h2>Attendance Records</h2>

        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Employee ID</th>
                <th>Check In</th>
                <th>Check Out</th>
                <th>Action</th>
              </tr>
            </thead>

            <tbody>
              {records.map(record => (
                <tr key={record.id}>
                  <td>{record.id}</td>
                  <td>{record.employeeId ?? '-'}</td>
                  <td>{showTime(record.checkIn)}</td>
                  <td>{showTime(record.checkOut)}</td>
                  <td>
                    <button
                      className="delete-btn"
                      onClick={() => deleteRecord(record.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}

              {records.length === 0 && (
                <tr>
                  <td colSpan="5">No attendance records found.</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>

        <button className="primary-btn" onClick={loadAttendance}>
          Refresh
        </button>
      </div>
    </Layout>
  );
}

export default Attendance;
