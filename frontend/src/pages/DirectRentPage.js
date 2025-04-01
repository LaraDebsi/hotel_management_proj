import React, { useState, useEffect } from 'react';
import '../DirectRentPage.css';

const employeeId = 7; // hardcoded employee id for frontend 
const DirectRentPage = () => {
  const [form, setForm] = useState({
    roomId: '',
    customerName: '',
    address: '',
    ssn: '',
    startDate: '',
    endDate: ''
  });
  const [message, setMessage] = useState('');

  const [rooms, setRooms] = useState([]);
  const [selectedRoom, setSelectedRoom] = useState(null);

  useEffect(() => {
    fetch('/api/rooms/search') 
      .then(res => res.json())
      .then(data => setRooms(data))
      .catch(err => console.error("Failed to load rooms", err));
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    fetch('/api/renting/direct', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ ...form, employeeId })
    })
    .then(res => res.ok ? res.text() : Promise.reject(res.text()))
    .then(msg => setMessage(msg))
    .catch(async err => {
        const msg = await err;
        setMessage(msg);
        console.error("❌", msg);
      });
  };

  return (
    <div className="direct-rent-container">
      <h2>Direct Rent (Walk-in Guest)</h2>
      {selectedRoom && (
        <p className="hotel-label">📍 Hotel: {selectedRoom.hotel.name}</p>
      )}
      <form onSubmit={handleSubmit} className="direct-rent-form">
        <select
        name="roomId"
        value={form.roomId}
        onChange={(e) => {
            const roomId = e.target.value;
            setForm({ ...form, roomId });
            const selected = rooms.find(r => r.roomId.toString() === roomId);
            setSelectedRoom(selected);
        }}
        required>
        <option value="">Select a Room</option>
        {rooms.map(room => (
            <option key={room.roomId} value={room.roomId}>
            Room {room.roomNumber} · {room.capacity} · ID: {room.roomId}
            </option>
        ))}
        </select>
        <input name="customerName" placeholder="Customer Name" value={form.customerName} onChange={handleChange} required />
        <input name="address" placeholder="Address" value={form.address} onChange={handleChange} required />
        <input name="ssn" placeholder="SSN/SIN" value={form.ssn} onChange={handleChange} required />
        <input name="startDate" type="date" value={form.startDate} onChange={handleChange} required />
        <input name="endDate" type="date" value={form.endDate} onChange={handleChange} required />
        <button type="submit">Create Rental</button>
      </form>
      {message && <p className="rent-message">{message}</p>}
    </div>
  );
};

export default DirectRentPage;
