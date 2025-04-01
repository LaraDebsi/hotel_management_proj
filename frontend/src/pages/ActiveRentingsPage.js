import React, { useEffect, useState } from 'react';
import '../ActiveRentingsPage.css';

const ActiveRentingsPage = () => {
  const [rentings, setRentings] = useState([]);
  const [loading, setLoading] = useState(false);

  const [selectedRentalId, setSelectedRentalId] = useState(null);
  const [paymentAmount, setPaymentAmount] = useState('');

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
          fetchRentings(); 
        })
        .catch(err => {
          console.error("Failed to end rental:", err);
          alert("Failed to end rental.");
        });
    }
  };

  const submitPayment = (rentingId) => {
    if (!paymentAmount || isNaN(paymentAmount)) {
      alert("Please enter a valid amount.");
      return;
    }
  
    fetch(`/api/rentings/payment?rentingId=${rentingId}&amount=${paymentAmount}`, {
      method: 'POST',
    })
      .then(res => res.text())
      .then(msg => {
        alert(msg);
        setPaymentAmount('');
        setSelectedRentalId(null);
        fetchRentings(); 
      })
      .catch(err => {
        console.error(err);
        alert("Failed to submit payment.");
      });
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
                <td>
                  <button onClick={() => setSelectedRentalId(r.rentingId)}>
                    Add Payment
                  </button>

                  {selectedRentalId === r.rentingId && (
                    <div style={{ marginTop: '10px' }}>
                      <input
                        type="number"
                        step="0.01"
                        value={paymentAmount}
                        onChange={(e) => setPaymentAmount(e.target.value)}
                        placeholder="Enter amount"
                        style={{ width: '100px', marginRight: '8px' }}
                      />
                      <button onClick={() => submitPayment(r.rentingId)}>
                        Submit
                      </button>
                    </div>
                  )}
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
