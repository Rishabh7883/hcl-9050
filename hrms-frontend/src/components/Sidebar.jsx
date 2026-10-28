
import { Link } from 'react-router-dom';

function Sidebar({ setUser }) {
  return (
    <div className="sidebar">
      <h1>👥 HRMS</h1>

      <Link to="/">🏠 Dashboard</Link>
      <Link to="/employees">👤 Employees</Link>
      <Link to="/attendance">📅 Attendance</Link>
      <Link to="/leaves">📄 Leave Requests</Link>
      <Link to="/reports">📊 Reports</Link>
      <Link to="/settings">⚙️ Settings</Link>

      <button className="logout-btn" onClick={() => setUser(null)}>
        Logout
      </button>
    </div>
  );
}

export default Sidebar;
