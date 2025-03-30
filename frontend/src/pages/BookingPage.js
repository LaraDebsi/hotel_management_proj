import React, { useState } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router-dom';
import '../BookingPage.css'; 

function BookingPage() {
  const location = useLocation();
  const room = location.state?.room;

  const [customerId, setCustomerId] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');

  const handleBooking = (e) => {
    e.preventDefault();
    axios.post('http://localhost:8080/api/booking/create', {
      customerId: Number(customerId),
      roomId: room.roomId,
      startDate,
      endDate
    })
      .then(() => alert('Booking successful!'))
      .catch(() => alert('Booking failed. Please try again.'));
  };

  if (!room) {
    return (
      <div className="booking-container">
        <h2>No room selected</h2>
        <p>Please select a room to book.</p>
        <button onClick={() => window.location.href = '/rooms'} className="go-search-btn">
          Go to Search Rooms
        </button>
      </div>
    );
  }

  return (
    <div className="booking-container">
      <h1>Book a Room</h1>

      <div className="room-summary">
        <h2>Room {room.roomNumber} – {room.hotel?.name}</h2>
        <p><strong>Price:</strong> ${room.price}</p>
        <p><strong>View:</strong> {room.viewType}</p>
        <p><strong>Location:</strong> {room.hotel?.address}</p>
      </div>

      <form className="booking-form" onSubmit={handleBooking}>
        <label>Customer ID:
          <input type="text" value={customerId} onChange={(e) => setCustomerId(e.target.value)} required />
        </label>

        <label>Start Date:
          <input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} required />
        </label>

        <label>End Date:
          <input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} required />
        </label>

        <button type="submit">📅 Book Now</button>
      </form>
    </div>
  );
}

export default BookingPage;
