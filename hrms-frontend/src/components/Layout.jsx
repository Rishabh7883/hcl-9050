
import Sidebar from './Sidebar';

function Layout({ user, setUser, children }) {
  return (
    <div className="app-layout">
      <Sidebar setUser={setUser} />

      <div className="main-area">
        <div className="topbar">
          <input placeholder="Search employees, attendance, etc..." />
          <h3>{user.name} 👤</h3>
        </div>

        <div className="content">
          {children}
        </div>
      </div>
    </div>
  );
}

export default Layout;
