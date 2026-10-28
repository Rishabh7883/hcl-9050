
import { useState } from 'react';
import { Routes, Route } from 'react-router-dom';

import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import Employees from './pages/Employees';
import Attendance from './pages/Attendance';
import LeaveRequests from './pages/LeaveRequests';
import Reports from './pages/Reports';
import Settings from './pages/Settings';

function App() {
  const [user, setUser] = useState(null);

  if (!user) {
    return <Login setUser={setUser} />;
  }

  return (
    <Routes>
      <Route path="/" element={<Dashboard user={user} setUser={setUser} />} />
      <Route path="/employees" element={<Employees user={user} setUser={setUser} />} />
      <Route path="/attendance" element={<Attendance user={user} setUser={setUser} />} />
      <Route path="/leaves" element={<LeaveRequests user={user} setUser={setUser} />} />
      <Route path="/reports" element={<Reports user={user} setUser={setUser} />} />
      <Route path="/settings" element={<Settings user={user} setUser={setUser} />} />
    </Routes>
  );
}

export default App;
