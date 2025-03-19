import React, { useState, useEffect } from 'react';
import axios from 'axios';

function RoomSearch() {
    const [rooms, setRooms] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/rooms/search')
            .then(response => setRooms(response.data))
            .catch(error => console.error('Error fetching rooms:', error));
    }, []);

    return (
        <div>
            <h1>Available Rooms</h1>
            <ul>
                {rooms.map(room => (
                    <li key={room.roomId}>
                        <strong>Hotel:</strong> {room.hotel.name} | 
                        <strong> Price:</strong> ${room.price} | 
                        <strong> View:</strong> {room.viewType}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default RoomSearch;
