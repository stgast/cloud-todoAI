package com.example.demo.service;

import com.example.demo.dto.BookingDTO;
import com.example.demo.dto.CreateBookingRequest;
import com.example.demo.entity.Booking;
import com.example.demo.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    
    private final BookingRepository bookingRepository;
    
    /**
     * Створити нову резервацію
     */
    public BookingDTO createBooking(CreateBookingRequest request) {
        Booking booking = Booking.builder()
                .userId(request.getUserId())
                .serviceName(request.getServiceName())
                .bookingDate(request.getBookingDate())
                .description(request.getDescription())
                .status("PENDING")
                .build();
        
        Booking savedBooking = bookingRepository.save(booking);
        return mapToDTO(savedBooking);
    }
    
    /**
     * Отримати резервацію за ID
     */
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Резервація не знайдена"));
        return mapToDTO(booking);
    }
    
    /**
     * Отримати всі резервації користувача
     */
    public List<BookingDTO> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Отримати всі резервації за статусом
     */
    public List<BookingDTO> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Отримати всі резервації
     */
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Оновити резервацію
     */
    public BookingDTO updateBooking(Long id, CreateBookingRequest request) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Резервація не знайдена"));
        
        booking.setServiceName(request.getServiceName());
        booking.setBookingDate(request.getBookingDate());
        booking.setDescription(request.getDescription());
        
        Booking updatedBooking = bookingRepository.save(booking);
        return mapToDTO(updatedBooking);
    }
    
    /**
     * Оновити статус резервації
     */
    public BookingDTO updateBookingStatus(Long id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Резервація не знайдена"));
        
        booking.setStatus(status);
        Booking updatedBooking = bookingRepository.save(booking);
        return mapToDTO(updatedBooking);
    }
    
    /**
     * Видалити резервацію
     */
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Резервація не знайдена"));
        bookingRepository.delete(booking);
    }
    
    private BookingDTO mapToDTO(Booking booking) {
        return BookingDTO.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .serviceName(booking.getServiceName())
                .bookingDate(booking.getBookingDate())
                .description(booking.getDescription())
                .status(booking.getStatus())
                .build();
    }
}
