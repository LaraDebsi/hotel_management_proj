import React, { useState } from 'react';
import '../HomePage.css';
import { useNavigate } from 'react-router-dom';

function HomePage({role, setRole}) {
  const navigate = useNavigate();

  return (
    <div className="homepage">
    <header className="homepage-header" style={{
      backgroundImage: "url('/photos/background.jpg')",
      backgroundSize: 'cover',
      backgroundPosition: 'center'
    }}>
      <div className="overlay" />
      <div className="header-content">
        <h1>Hotel Management System</h1>
        <p className="subtitle">Book, manage, and explore hotel stays across North America.</p>
      </div>
    </header>

      <section className="quick-actions">
        <h2>--- Quick Actions ---</h2>
        <div className="actions-list">
          <button onClick={() => navigate('/rooms')}>Search Rooms</button>
          <button onClick={() => navigate('/booking')}>Make a Booking</button>

          {role === 'customer' && (
            <>
              <button onClick={() => navigate('/my-bookings')}>View My Bookings</button>
              <button onClick={() => navigate('/cancel-booking')}>Cancel a Booking</button>
            </>
          )}

          {role === 'employee' && (
            <>
              <button onClick={() => navigate('/checkin')}>Check-in a Guest</button>
              <button onClick={() => navigate('/manage')}>Manage</button>
              <button onClick={() => navigate('/analytics')}>View Reports</button>
            </>
          )}
        </div>
      </section>

      <section className="info-banner">
        <p>
          Effortless booking. Real-time availability. Trusted by 5 top hotel chains.
        </p>
      </section>

      <footer className="homepage-footer">
        <p>Built for CSI2132 — Winter 2025 • React + CSS + PostgreSQL + Java</p>
      </footer>
    </div>
  );
}

export default HomePage;
