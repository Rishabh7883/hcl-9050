
import Layout from '../components/Layout';

function Settings({ user, setUser }) {
  return (
    <Layout user={user} setUser={setUser}>
      <h1>Settings</h1>

      <div className="panel">
        <h2>My Profile</h2>
        <p>Employee Name: {user.name}</p>
        <p>Employee ID: #{user.id}</p>
        <p>This is a simple demo profile.</p>
      </div>
    </Layout>
  );
}

export default Settings;
