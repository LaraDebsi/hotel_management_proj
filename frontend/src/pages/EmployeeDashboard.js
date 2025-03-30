import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../EmployeeDashboard.css';

const EmployeeDashboard = () => {
  const navigate = useNavigate();

  return (
    <div className="dashboard-container">
      <h2 className="dashboard-title">Employee Dashboard</h2>

      <div className="dashboard-card" onClick={() => navigate('/direct-rent')}>
        <h3>Direct Rent</h3>
        <p>Create a renting for a walk-in customer.</p>
      </div>

      <div className="dashboard-card" onClick={() => navigate('/active-rentings')}>
        <h3>View Active Rentings</h3>
        <p>See current guests who are checked in.</p>
      </div>

      <div className="dashboard-card" onClick={() => navigate('/rental-history')}>
        <h3>Rental History</h3>
        <p>Browse all past rentals and archived check-outs.</p>
      </div>

      {/* Optional: Add this later */}
      {/* <div className="dashboard-card" onClick={() => navigate('/end-rental')}>
        <h3>End a Rental</h3>
        <p>Mark a room as available again.</p>
      </div> */}
    </div>
  );
};

export default EmployeeDashboard;
