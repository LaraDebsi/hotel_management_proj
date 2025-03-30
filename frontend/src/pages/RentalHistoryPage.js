import React, { useEffect, useState } from 'react';
import '../RentalHistoryPage.css';

const RentalHistoryPage = () => {
  const [history, setHistory] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    fetch('/api/archive/rentings')
      .then(res => res.json())
      .then(data => {
        setHistory(data);
        setLoading(false);
      })
      .catch(err => {
        console.error('Failed to fetch rental history:', err);
        setLoading(false);
      });
  }, []);

  return (
    <div className="rental-history-container">
      <h2>Rental History (Archived)</h2>

      {loading ? (
        <p>Loading...</p>
      ) : (
        <table className="history-table">
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
            {history.map(h => (
              <tr key={h.archiveId}>
                <td>{h.customerName}</td>
                <td>{h.roomNumber}</td>
                <td>{h.hotelName}</td>
                <td>{h.startDate}</td>
                <td>{h.endDate}</td>
                <td>{h.payment}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default RentalHistoryPage;
