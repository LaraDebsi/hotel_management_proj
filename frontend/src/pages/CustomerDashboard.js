import React, { useEffect, useState } from 'react';
import '../CustomerDashboard.css';

const CustomerDashboard = ({ customerId = 8 }) => {
  const [bookings, setBookings] = useState([]);
  const [rentings, setRentings] = useState([]);

  useEffect(() => {
    fetch(`/api/bookings/customer/${customerId}`)
      .then(res => res.json())
      .then(setBookings);

    fetch(`/api/rentings/customer/${customerId}`)
      .then(res => res.json())
      .then(setRentings);
  }, [customerId]);

  return (
    <div className="dashboard-container">
      <h2>Welcome, Customer #{customerId}</h2>

      <section>
        <h3>Your Bookings</h3>
        <table>
          <thead>
            <tr><th>Room</th><th>Hotel</th><th>Start</th><th>End</th></tr>
          </thead>
          <tbody>
            {bookings.map(b => (
              <tr key={b.bookingId}>
                <td>{b.roomNumber}</td>
                <td>{b.hotelName}</td>
                <td>{b.startDate}</td>
                <td>{b.endDate}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>

      <section>
        <h3>Your Rentings</h3>
        <table>
          <thead>
            <tr><th>Room</th><th>Hotel</th><th>Start</th><th>End</th><th>Paid ($)</th></tr>
          </thead>
          <tbody>
            {rentings.map(r => (
              <tr key={r.rentingId}>
                <td>{r.roomNumber}</td>
                <td>{r.hotelName}</td>
                <td>{r.startDate}</td>
                <td>{r.endDate}</td>
                <td>{r.payment}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>
    </div>
  );
};

export default CustomerDashboard;
