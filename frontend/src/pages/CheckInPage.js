import React, { useEffect, useState } from 'react';
import '../CheckInPage.css';

const employeeId = 5; // Replace this with dynamic value if needed

const CheckInPage = () => {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("/api/bookings/upcoming")
      .then(res => res.json())
      .then(data => {
        console.log("📦 API Response:", data);
        setBookings(data);
        setLoading(false);
      })
      .catch(err => {
        console.error("Failed to load bookings:", err);
        setLoading(false);
      });
  }, []);

  const handleCheckIn = (bookingId) => {
    fetch(`/api/renting/convert?bookingId=${bookingId}&employeeId=${employeeId}`, {
      method: "POST"
    })
      .then(res => res.text())
      .then(msg => {
        alert(msg);
        setBookings(prev => prev.filter(b => b.bookingId !== bookingId)); // remove from list
      })
      .catch(err => {
        console.error("Check-in failed:", err);
        alert("Check-in failed.");
      });
  };

  if (loading) return <p>Loading upcoming bookings...</p>;

  return (
    <div className="checkin-container">
    <h2 className="checkin-title">Upcoming Bookings</h2>
    {Array.isArray(bookings) && bookings.length > 0 ? (
        bookings.map(booking => (
        <div key={booking.bookingId} className="booking-card">
            <p><strong>Booking ID:</strong> {booking.bookingId}</p>
            <p><strong>Customer:</strong> {booking.customerName}</p>
            <p><strong>Room:</strong> {booking.roomId}</p>
            <p><strong>Dates:</strong> {booking.startDate} to {booking.endDate}</p>
            <button
            className="checkin-button"
            onClick={() => handleCheckIn(booking.bookingId)}
            >
            Check In
            </button>
        </div>
        ))
    ) : (
        <p>No bookings to check in.</p>
    )}
    </div>
  );
};

export default CheckInPage;
