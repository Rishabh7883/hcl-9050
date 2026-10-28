import Layout from '../components/Layout';

function LeaveRequests({ user, setUser }) {
  return (
    <Layout user={user} setUser={setUser}>
      <h1>Leave Requests</h1>
      <div className="panel">
        <h2>Leave Management</h2>
        <p>Leave request API has not been created yet.</p>
      </div>
    </Layout>
  );
}

export default LeaveRequests;