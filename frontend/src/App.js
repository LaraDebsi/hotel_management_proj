import React, {useState} from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './Header';
import HomePage from './pages/HomePage';
import RoomSearch from './pages/RoomSearch';
import BookingPage from './pages/BookingPage';



function App() {
    const [role, setRole] = useState('customer');
    const [toastMessage, setToastMessage] = useState('');
    const handleRoleChange = (newRole) => {
        setRole(newRole);
        setToastMessage(`👤 Switched to ${newRole} view`);
        setTimeout(() => setToastMessage(''), 3000);
      };
    
    return (
        <Router>
            <Header role={role} setRole={handleRoleChange} />
            {toastMessage && (
                <div className="toast-message">{toastMessage}</div>
            )}
            <Routes>
                <Route path="/" element={<HomePage role={role} setRole={handleRoleChange} />} />
                <Route path="/rooms" element={<RoomSearch />} />
                <Route path="/booking" element={<BookingPage />} />
            </Routes>
        </Router>
    );
}

export default App;
