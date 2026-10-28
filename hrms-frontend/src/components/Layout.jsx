
import Sidebar from './Sidebar';
import { useNavigate } from 'react-router-dom';

function Layout({ user, setUser, children }) {
  const navigate = useNavigate();

  function logout() {
    setUser(null);
    navigate('/login', { replace: true });
  }

  return (
    <div className="app-layout">
      <Sidebar setUser={setUser} />

      <div className="main-area">
        <div className="topbar">
          <input placeholder="Search..." />

          <button
            type="button"
            className="logout-btn topbar-logout"
            onClick={logout}
          >
            Logout
          </button>
        </div>

        <main className="content">
          {children}
        </main>
      </div>
    </div>
  );
}

export default Layout;
