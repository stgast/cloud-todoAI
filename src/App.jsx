import { useState } from 'react';
import Header from './components/Header';
import UserForm from './components/UserForm';
import BookingForm from './components/BookingForm';
import BookingList from './components/BookingList';

export default function App() {
  const [bookings, setBookings] = useState([]);
  const [user, setUser] = useState(null);

  const handleUserCreate = (userData) => {
    setUser(userData);
  };

  const handleBookingCreate = (bookingData) => {
    setBookings([...bookings, { ...bookingData, id: Date.now() }]);
  };

  return (
    <div className="min-h-screen bg-gray-100">
      <Header />
      <div className="max-w-6xl mx-auto p-6 space-y-8">
        <div className="grid md:grid-cols-2 gap-6">
          <UserForm onUserCreate={handleUserCreate} />
          {user && <BookingForm onBookingCreate={handleBookingCreate} user={user} />}
        </div>
        <BookingList bookings={bookings} />
      </div>
    </div>
  );
}
