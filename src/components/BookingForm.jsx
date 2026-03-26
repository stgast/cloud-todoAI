import { useState } from 'react';

export default function BookingForm({ onBookingCreate, user }) {
  const [formData, setFormData] = useState({ service: '', date: '', time: '' });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (formData.service && formData.date && formData.time) {
      onBookingCreate({ ...formData, userName: user.name });
      setFormData({ service: '', date: '', time: '' });
    }
  };

  return (
    <div className="bg-white rounded-xl shadow-md p-6">
      <h2 className="text-2xl font-bold text-gray-800 mb-4">Nowa Rezerwacja</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <select
          name="service"
          value={formData.service}
          onChange={handleChange}
          className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
        >
          <option value="">Wybierz usługę</option>
          <option value="Konsultacja">Konsultacja</option>
          <option value="Masaż">Masaż</option>
          <option value="Badanie">Badanie</option>
        </select>
        <input
          type="date"
          name="date"
          value={formData.date}
          onChange={handleChange}
          className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        <input
          type="time"
          name="time"
          value={formData.time}
          onChange={handleChange}
          className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        <button
          type="submit"
          className="w-full bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-lg transition"
        >
          Zarezerwuj
        </button>
      </form>
    </div>
  );
}
