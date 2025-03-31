import React, { useEffect, useState } from 'react';
import '../AnalyticsPage.css';

function AnalyticsPage() {
  const [availableRooms, setAvailableRooms] = useState([]);
  const [hotelCapacities, setHotelCapacities] = useState([]);

  useEffect(() => {
    fetch('/api/views/available-rooms')
      .then(res => res.json())
      .then(setAvailableRooms);

    fetch('/api/views/hotel-capacities')
      .then(res => res.json())
      .then(setHotelCapacities);
  }, []);

  return (
    <div className="analytics-container">
      <h2>Analytics & Reports</h2>

      <section>
        <h3>Available Rooms per City</h3>
        <table>
          <thead>
            <tr><th>City</th><th>Available Rooms</th></tr>
          </thead>
          <tbody>
            {availableRooms.map((row, i) => (
              <tr key={i}>
                <td>{row.city}</td>
                <td>{row.availableRooms}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>

      <section>
        <h3>Total Room Capacity per Hotel</h3>
        <table>
          <thead>
            <tr><th>Hotel</th><th>Total Capacity</th></tr>
          </thead>
          <tbody>
            {hotelCapacities.map((row, i) => (
              <tr key={i}>
                <td>{row.hotelName}</td>
                <td>{row.totalCapacity}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </section>
    </div>
  );
}

export default AnalyticsPage;
