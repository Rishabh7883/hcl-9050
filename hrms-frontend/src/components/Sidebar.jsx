
import { Link } from 'react-router-dom';

function Sidebar() {
  return (
    <div className="sidebar">
      <h2>HRMS</h2>

      <nav className="sidebar-nav">
        <Link to="/">Dashboard</Link>
        <Link to="/employees">Employees</Link>
        <Link to="/attendance">Attendance</Link>
        <Link to="/leaves">Leave Requests</Link>
        <Link to="/reports">Reports</Link>
        <Link to="/settings">Settings</Link>
      </nav>
    </div>
  );
}

export default Sidebar;
