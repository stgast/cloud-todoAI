export default function BookingList({ bookings }) {
  return (
    <div className="bg-white rounded-xl shadow-md p-6">
      <h2 className="text-2xl font-bold text-gray-800 mb-4">Rezerwacje</h2>
      {bookings.length === 0 ? (
        <p className="text-gray-500">Brak rezerwacji</p>
      ) : (
        <div className="space-y-3">
          {bookings.map((booking) => (
            <div key={booking.id} className="border border-gray-200 rounded-lg p-4 hover:shadow-md transition">
              <p className="font-bold text-gray-800">{booking.userName}</p>
              <p className="text-gray-600">Usługa: {booking.service}</p>
              <p className="text-gray-600">Data: {booking.date} o {booking.time}</p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
