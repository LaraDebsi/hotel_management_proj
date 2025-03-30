import React, { useEffect, useState } from 'react';
import '../ActiveRentingsPage.css';

const ActiveRentingsPage = () => {
  const [rentings, setRentings] = useState([]);
  const [loading, setLoading] = useState(false);

  const fetchRentings = () => {
    setLoading(true);
    const url = '/api/rentings/active';
    fetch(url)
      .then(res => res.json())
      .then(data => {
        console.log("Fetched rentings:", data);
        setRentings(data);
        setLoading(false);
      })
      .catch(err => {
        console.error('Failed to load rentings:', err);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchRentings();
  }, []);

  const endRental = (rentingId) => {
    if (window.confirm("Are you sure you want to end this rental?")) {
      fetch(`/api/rentings/end/${rentingId}`, { method: 'DELETE' })
        .then(res => res.text())
        .then(msg => {
          alert(msg);
          fetchRentings(); // Refresh list
        })
        .catch(err => {
          console.error("Failed to end rental:", err);
          alert("Failed to end rental.");
        });
    }
  };

  return (
    <div className="active-rentings-container">
      <h2>Active Rentings</h2>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <table className="rentings-table">
          <thead>
            <tr>
              <th>Customer</th>
              <th>Room #</th>
              <th>Hotel</th>
              <th>Start</th>
              <th>End</th>
              <th>Payment ($)</th>
            </tr>
          </thead>
          <tbody>
            {rentings.map(r => (
              <tr key={r.rentingId}>
                <td>{r.customerName}</td>
                <td>{r.roomNumber}</td>
                <td>{r.hotelName}</td>
                <td>{r.startDate}</td>
                <td>{r.endDate}</td>
                <td>{r.payment}</td>
                <td>
                    <button onClick={() => endRental(r.rentingId)}>End Rental</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default ActiveRentingsPage;
