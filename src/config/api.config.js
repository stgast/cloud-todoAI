const API_USER_URL = import.meta.env.VITE_API_USER_URL || 'http://localhost:8081';
const API_BOOKING_URL = import.meta.env.VITE_API_BOOKING_URL || 'http://localhost:8082';

export const API_ENDPOINTS = {
  // User Service
  USER_REGISTER: `${API_USER_URL}/api/users/register`,
  USER_LOGIN: `${API_USER_URL}/api/users/login`,
  USER_GET: (id) => `${API_USER_URL}/api/users/${id}`,
  USER_UPDATE: (id) => `${API_USER_URL}/api/users/${id}`,
  USER_DELETE: (id) => `${API_USER_URL}/api/users/${id}`,
  
  // Booking Service
  BOOKING_CREATE: `${API_BOOKING_URL}/api/bookings`,
  BOOKING_LIST: `${API_BOOKING_URL}/api/bookings`,
  BOOKING_GET: (id) => `${API_BOOKING_URL}/api/bookings/${id}`,
  BOOKING_UPDATE: (id) => `${API_BOOKING_URL}/api/bookings/${id}`,
  BOOKING_DELETE: (id) => `${API_BOOKING_URL}/api/bookings/${id}`,
  BOOKING_BY_USER: (userId) => `${API_BOOKING_URL}/api/bookings/user/${userId}`,
  BOOKING_BY_STATUS: (status) => `${API_BOOKING_URL}/api/bookings/status/${status}`,
};
