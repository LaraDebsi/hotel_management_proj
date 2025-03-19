import React, { useState, useEffect } from 'react';
import axios from 'axios';

function RoomSearch() {
    const [rooms, setRooms] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/rooms/search')
            .then(response => {
                console.log("API Response:", response.data); // ✅ Debugging: See if API is returning data
                setRooms(response.data);
            })
            .catch(error => {
                console.error('Error fetching rooms:', error);
                alert('Failed to fetch rooms.');
            });
    }, []);

    return (
        <div>
            <h1>Available Rooms</h1>
            {rooms.length === 0 ? <p>No rooms found.</p> : null} {/* ✅ Shows message if empty */}
            <ul>
                {rooms.map(room => (
                    <li key={room.roomId}>
                        <strong>Hotel:</strong> {room.hotel?.name || "Unknown"} |
                        <strong> Price:</strong> ${room.price} |
                        <strong> View:</strong> {room.viewType}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default RoomSearch;
