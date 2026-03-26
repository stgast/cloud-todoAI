import api from './api.service';
import { API_ENDPOINTS } from '../config/api.config';

export const userService = {
  register: (data) => api.post(API_ENDPOINTS.USER_REGISTER, data),
  login: (data) => api.post(API_ENDPOINTS.USER_LOGIN, data),
  getUser: (id) => api.get(API_ENDPOINTS.USER_GET(id)),
  updateUser: (id, data) => api.put(API_ENDPOINTS.USER_UPDATE(id), data),
  deleteUser: (id) => api.delete(API_ENDPOINTS.USER_DELETE(id)),
};

export const bookingService = {
  createBooking: (data) => api.post(API_ENDPOINTS.BOOKING_CREATE, data),
  listBookings: () => api.get(API_ENDPOINTS.BOOKING_LIST),
  getBooking: (id) => api.get(API_ENDPOINTS.BOOKING_GET(id)),
  updateBooking: (id, data) => api.put(API_ENDPOINTS.BOOKING_UPDATE(id), data),
  deleteBooking: (id) => api.delete(API_ENDPOINTS.BOOKING_DELETE(id)),
  getUserBookings: (userId) => api.get(API_ENDPOINTS.BOOKING_BY_USER(userId)),
  getBookingsByStatus: (status) => api.get(API_ENDPOINTS.BOOKING_BY_STATUS(status)),
};
