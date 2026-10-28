import { useState } from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';

import Login from './pages/Login';
import Signup from './pages/Signup';
import Dashboard from './pages/Dashboard';
import Employees from './pages/Employees';
import Attendance from './pages/Attendance';
import LeaveRequests from './pages/LeaveRequests';
import Reports from './pages/Reports';
import Settings from './pages/Settings';

function App() {
  const [user, setUser] = useState(null);

  return (
    <Routes>
      <Route path="/login" element={
        <Login setUser={setUser} />
      } />

      <Route path="/signup" element={
        <Signup />
      } />

      <Route path="/" element={
        user ? <Dashboard user={user} setUser={setUser} /> : <Navigate to="/login" />
      } />

      <Route path="/employees" element={
        user ? <Employees user={user} setUser={setUser} /> : <Navigate to="/login" />
      } />

      <Route path="/attendance" element={
        user ? <Attendance user={user} setUser={setUser} /> : <Navigate to="/login" />
      } />

      <Route path="/leaves" element={
        user ? <LeaveRequests user={user} setUser={setUser} /> : <Navigate to="/login" />
      } />

      <Route path="/reports" element={
        user ? <Reports user={user} setUser={setUser} /> : <Navigate to="/login" />
      } />

      <Route path="/settings" element={
        user ? <Settings user={user} setUser={setUser} /> : <Navigate to="/login" />
      } />

      <Route path="*" element={<Navigate to="/login" />} />
    </Routes>
  );
}

export default App;