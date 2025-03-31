import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css';

function Header({ role, setRole }) {
  return (
    <header className="header">
      <Link to="/" className="logo">Hotel ON the GO</Link>

      <nav className="nav-tabs">
        {role === 'customer' ? (
          <>
            <Link to="/rooms" className="tab">Search</Link>
            <Link to="/customer-dashboard" className="tab">My Bookings</Link>
            <Link to="/cancel-booking" className="tab">Cancel</Link>
          </>
        ) : (
          <>
            <Link to="/check-in" className="tab">Check-In</Link>
            <Link to="/manage" className="tab">Manage</Link>
            <Link to="/analytics" className="tab">Analytics</Link>
          </>
        )}
      </nav>

      <div className="role-toggle-header">
        <label>
          <input
            type="radio"
            name="role"
            value="customer"
            checked={role === 'customer'}
            onChange={() => setRole('customer')}
          /> Customer
        </label>
        <label>
          <input
            type="radio"
            name="role"
            value="employee"
            checked={role === 'employee'}
            onChange={() => setRole('employee')}
          /> Employee
        </label>
      </div>
    </header>
  );
}

export default Header;
