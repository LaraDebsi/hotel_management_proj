import React, {useState} from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './Header';
import HomePage from './pages/HomePage';
import RoomSearch from './pages/RoomSearch';
import BookingPage from './pages/BookingPage';
import CheckInPage from './pages/CheckInPage';
import EmployeeDashboard from './pages/EmployeeDashboard';
import DirectRentPage from './pages/DirectRentPage';
import ActiveRentingsPage from './pages/ActiveRentingsPage';
import RentalHistoryPage from './pages/RentalHistoryPage';
import AnalyticsPage from './pages/AnalyticsPage';



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
                <Route path="/check-in" element={<CheckInPage />} />
                <Route path="/manage" element={<EmployeeDashboard />} />
                <Route path="/direct-rent" element={<DirectRentPage />} />
                <Route path="/active-rentings" element={<ActiveRentingsPage />} />
                <Route path="/rental-history" element={<RentalHistoryPage />} />
                <Route path="/analytics" element={<AnalyticsPage />} />
            </Routes>
        </Router>
    );
}

export default App;
