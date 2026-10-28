
import { useEffect, useState } from 'react';
import Layout from '../components/Layout';

function Attendance({ user, setUser }) {
  const [records, setRecords] = useState([]);

  function loadAttendance() {
    fetch('http://localhost:8070/api/v1/attendance')
      .then(res => res.json())
      .then(data => setRecords(data))
      .catch(console.error);
  }

  useEffect(() => {
    loadAttendance();
  }, []);

  function deleteRecord(id) {
    if (!window.confirm('Delete this attendance record?')) return;

    fetch('http://localhost:8080/api/v1/attendance/' + id, {
      method: 'DELETE'
    })
      .then(res => {
        if (!res.ok) throw new Error('Could not delete record');
        loadAttendance();
      })
      .catch(err => alert(err.message));
  }

  return (
    <Layout user={user} setUser={setUser}>
      <h1>Attendance Management</h1>

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
                  <td>#{record.id}</td>
                  <td>{record.employeeId}</td>
                  <td>{record.checkInTime || '-'}</td>
                  <td>{record.checkOutTime || '-'}</td>
                  <td>
                    <button className="delete-btn"
                      onClick={() => deleteRecord(record.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
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
