import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../RoomSearch.css';
import { useNavigate } from 'react-router-dom';

function RoomSearch() {
    const [rooms, setRooms] = useState([]);
    const [filters, setFilters] = useState({
        startDate: '',
        endDate: '',
        capacity: '',
        maxPrice: '',
        area: '',
        category: '',
        hotelChain: ''
    });

    const navigate = useNavigate();
    const [hasSearched, setHasSearched] = useState(false);
    const [showModal, setShowModal] = useState(false);
    const [selectedRoom, setSelectedRoom] = useState(null);

    useEffect(() => {
        const loadInitialRooms = async () => {
            setHasSearched(false);
            await fetchRooms();
          };
          loadInitialRooms();       
      }, []);

    const fetchRooms = async (fromFilter = false) => {
        if (fromFilter) setHasSearched(true);

        const filteredParams = {};
        for (let key in filters) {
          if (filters[key]) {
            filteredParams[key] = filters[key];
          }
        }
      
        try {
            const res = await axios.get('http://localhost:8080/api/rooms/search', {
              params: filteredParams
            });
            setRooms(res.data);
          } catch (err) {
            console.error('Failed to fetch rooms:', err);
          }
      };
    
    const [hotelChains, setHotelChains] = useState([]);
    useEffect(() => {
        axios.get('http://localhost:8080/api/hotel-chains')
          .then(res => setHotelChains(res.data))
          .catch(err => console.error("Failed to fetch hotel chains", err));
      }, []);

    const handleChange = (e) => {
        setFilters({ ...filters, [e.target.name]: e.target.value });
      };

      const clearFilters = () => {
        const reset = {
          startDate: '',
          endDate: '',
          capacity: '',
          maxPrice: '',
          area: '',
          category: '',
          hotelChain: ''
        };
        setFilters(reset);
        fetchRooms(reset);
      };

      const handleCardClick = (room) => {
        setSelectedRoom(room);
        setShowModal(true);
      };
    
      const handleCloseModal = () => {
        setShowModal(false);
        setSelectedRoom(null);
      };

    return (
        <div className="room-search-container">
        <h2>Search Available Rooms</h2>
        <div className="room-search-wrapper">
            <aside className="room-filters">
                <h2>Filters</h2>
                <label>Start Date:
                <input type="date" name="startDate" value={filters.startDate} onChange={handleChange} />
                </label>
                <label>End Date:
                <input type="date" name="endDate" value={filters.endDate} onChange={handleChange} />
                </label>
                <label>Room Type:
                <select name="capacity" value={filters.capacity} onChange={handleChange}>
                    <option value="">Any</option>
                    <option value="SINGLE">Single</option>
                    <option value="DOUBLE">Double</option>
                    <option value="SUITE">Suite</option>
                </select>
                </label>
                <label>Max Price:
                <input type="number" name="maxPrice" value={filters.maxPrice} onChange={handleChange} />
                </label>
                <label>Area:
                <input
                    type="text"
                    name="area"
                    value={filters.area}
                    onChange={handleChange}
                    placeholder="City/Region"
                />
                </label>
                <label>Category (Rating):
                    <select name="category" value={filters.category} onChange={handleChange}>
                        <option value="">Any</option>
                        <option value="1">1 Star</option>
                        <option value="2">2 Stars</option>
                        <option value="3">3 Stars</option>
                        <option value="4">4 Stars</option>
                        <option value="5">5 Stars</option>
                    </select>
                </label>
                <label>Hotel Chain:
                    <select name="hotelChain" value={filters.hotelChain || ''} onChange={handleChange}>
                        <option value="">Any</option>
                        <option value="Heritage">Heritage Inns </option>
                        <option value="Prestige">Prestige Suites</option>
                        <option value="Royal">Royal Resorts</option>
                        <option value="Coastal">Coastal Retreats</option>
                        <option value="Metropolitan">Metropolitan Hotels</option>
                        {hotelChains.map((chain, idx) => (
                        <option key={idx} value={chain}>{chain}</option>
                        ))}
                    </select>
                </label>


                <button onClick={() => fetchRooms(true)} className="filter-btn">Apply Filters</button>
                <button onClick={() => {
                    clearFilters();
                    fetchRooms(true);
                }} className="clear-btn">Clear</button>
            </aside>

            <main className="room-results">
               
                {rooms.length === 0 && hasSearched ? (
                    <p className="no-results">No rooms match your search filters.</p>
                ) : (rooms.map((room, index) => (
                        <div key={index} className="room-card" onClick={() => handleCardClick(room)}>
                            <strong>Hotel:</strong> {room.hotel?.name || "Unknown"}<br />
                            <strong>Price:</strong> ${room.price}<br />
                            <strong>View:</strong> {room.viewType}
                        </div>
                    )))}
            </main>
            </div>

  
        {showModal && selectedRoom && (
          <div className="room-modal-overlay" onClick={handleCloseModal}>
            <div className="room-modal" onClick={(e) => e.stopPropagation()}>
              <button className="close-btn" onClick={handleCloseModal}>×</button>
              <h3>Room Details</h3>
              <p><strong>Hotel:</strong> {selectedRoom.hotel?.name}</p>
              <p><strong>Rooms in Hotel:</strong> {selectedRoom.hotel?.numRooms || 'N/A'}</p>
              <p><strong>Room ID:</strong> {selectedRoom.roomId}</p>
              <p><strong>Location:</strong> {selectedRoom.hotel?.address || 'Unknown'}</p>
              <p><strong>Rating:</strong> {selectedRoom.hotel?.category}</p>
              <p><strong>Price:</strong> ${selectedRoom.price}</p>
              <p><strong>Capacity:</strong> {selectedRoom.capacity}</p>
              <p><strong>View:</strong> {selectedRoom.viewType}</p>
              <p><strong>Extendable:</strong> {selectedRoom.extendable ? 'Yes' : 'No'}</p>
              <p><strong>Problems:</strong> {selectedRoom.problems || 'None'}</p>
              
  
              <button className="book-btn" onClick={() => {
                navigate('/booking', { state: { room: selectedRoom } });
              }}>
                Book This Room
              </button>
            </div>
          </div>
        )}
      </div>
    );
}

export default RoomSearch;
