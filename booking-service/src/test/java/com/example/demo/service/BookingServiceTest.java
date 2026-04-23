package com.example.demo.service;

import com.example.demo.dto.BookingDTO;
import com.example.demo.dto.CreateBookingRequest;
import com.example.demo.entity.Booking;
import com.example.demo.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Booking Service Tests")
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    private Booking testBooking;
    private BookingDTO testBookingDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testBooking = new Booking();
        testBooking.setId(1L);
        testBooking.setUserId(1L);
        testBooking.setServiceName("Sprzątanie");
        testBooking.setBookingDate(LocalDateTime.now().plusDays(1));
        testBooking.setStatus("PENDING");
        testBooking.setDescription("Sprzątanie mieszkania");

        testBookingDTO = new BookingDTO();
        testBookingDTO.setId(1L);
        testBookingDTO.setUserId(1L);
        testBookingDTO.setServiceName("Sprzątanie");
        testBookingDTO.setStatus("PENDING");
    }

    @Test
    @DisplayName("Should create booking successfully")
    void testCreateBookingSuccess() {
        // Arrange
        CreateBookingRequest createRequest = new CreateBookingRequest();
        createRequest.setUserId(1L);
        createRequest.setServiceName("Sprzątanie");
        createRequest.setBookingDate(LocalDateTime.now().plusDays(1));
        createRequest.setDescription("Sprzątanie mieszkania");

        when(bookingRepository.save(any(Booking.class))).thenReturn(testBooking);

        // Act
        BookingDTO result = bookingService.createBooking(createRequest);

        // Assert
        assertNotNull(result);
        assertEquals("Sprzątanie", result.getServiceName());
        assertEquals("PENDING", result.getStatus());
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    @DisplayName("Should get booking by id")
    void testGetBookingById() {
        // Arrange
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(testBooking));

        // Act
        BookingDTO result = bookingService.getBookingById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Sprzątanie", result.getServiceName());
        verify(bookingRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should get all bookings for user")
    void testGetBookingsByUserId() {
        // Arrange
        List<Booking> bookings = Arrays.asList(testBooking);
        when(bookingRepository.findByUserId(1L)).thenReturn(bookings);

        // Act
        List<BookingDTO> result = bookingService.getBookingsByUserId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Sprzątanie", result.get(0).getServiceName());
        verify(bookingRepository, times(1)).findByUserId(1L);
    }

    @Test
    @DisplayName("Should update booking successfully")
    void testUpdateBookingSuccess() {
        // Arrange
        testBooking.setStatus("CONFIRMED");
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(testBooking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(testBooking);

        BookingDTO updateDTO = new BookingDTO();
        updateDTO.setStatus("CONFIRMED");

        // Act
        BookingDTO result = bookingService.updateBooking(1L, updateDTO);

        // Assert
        assertNotNull(result);
        assertEquals("CONFIRMED", result.getStatus());
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    @DisplayName("Should delete booking successfully")
    void testDeleteBookingSuccess() {
        // Arrange
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(testBooking));

        // Act
        bookingService.deleteBooking(1L);

        // Assert
        verify(bookingRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should throw exception when booking not found")
    void testGetBookingByIdNotFound() {
        // Arrange
        when(bookingRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> bookingService.getBookingById(999L));
    }

    @Test
    @DisplayName("Should get all bookings")
    void testGetAllBookings() {
        // Arrange
        List<Booking> bookings = Arrays.asList(testBooking);
        when(bookingRepository.findAll()).thenReturn(bookings);

        // Act
        List<BookingDTO> result = bookingService.getAllBookings();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should update booking status")
    void testUpdateBookingStatus() {
        // Arrange
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(testBooking));
        testBooking.setStatus("CANCELLED");
        when(bookingRepository.save(any(Booking.class))).thenReturn(testBooking);

        // Act
        BookingDTO result = bookingService.updateBookingStatus(1L, "CANCELLED");

        // Assert
        assertNotNull(result);
        assertEquals("CANCELLED", result.getStatus());
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }
}
