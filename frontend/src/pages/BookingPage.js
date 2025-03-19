import React, { useState } from 'react';
import axios from 'axios';

function BookingPage() {  // ✅ Function name should be `BookingPage`
    const [customerId, setCustomerId] = useState('');
    const [roomId, setRoomId] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');

    const handleBooking = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8080/api/booking/create', {
            customerId,
            roomId,
            startDate,
            endDate
        })
        .then(response => alert('Booking successful!'))
        .catch(error => alert('Booking failed. Please try again.'));
    };

    return (
        <div>
            <h1>Book a Room</h1>
            <form onSubmit={handleBooking}>
                <label>Customer ID:</label>
                <input type="text" value={customerId} onChange={(e) => setCustomerId(e.target.value)} required />

                <label>Room ID:</label>
                <input type="text" value={roomId} onChange={(e) => setRoomId(e.target.value)} required />

                <label>Start Date:</label>
                <input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} required />

                <label>End Date:</label>
                <input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} required />

                <button type="submit">Book Now</button>
            </form>
        </div>
    );
}

// ✅ Ensure the export statement matches the function name exactly
export default BookingPage;
