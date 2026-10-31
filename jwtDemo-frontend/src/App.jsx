import { useState } from "react";
import AuthForm from "./components/AuthForm";
import PostForm from "./components/PostForm";

const App = () => {

  const [user, setUser] = useState(
    localStorage.getItem("username") || ""
  );

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    setUser("");
  };

  return (
    <div className="app">

      {!user ? (

        <div className="auth-page">

          <div className="auth-left">
            <h1>JWT Demo</h1>
            <h2>Welcome Back!</h2>
            <p>
              Secure authentication and simple post management
              in one place.
            </p>
          </div>

          <div className="auth-right">
            <AuthForm onLogin={setUser} />
          </div>

        </div>

      ) : (

        <div className="dashboard">

          <header className="navbar">
            <h2>JWT Demo</h2>

            <div className="nav-right">
              <span>Welcome, {user}</span>
              <button onClick={handleLogout}>Logout</button>
            </div>
          </header>

          <main className="dashboard-content">

            <div className="welcome-section">
              <h1>Dashboard</h1>
              <p>Manage and create your posts here.</p>
            </div>

            <PostForm />

          </main>

        </div>

      )}

    </div>
  );
};

export default App;