package com.example.demo.controller;

import com.example.demo.dto.BookingDTO;
import com.example.demo.dto.CreateBookingRequest;
import com.example.demo.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Booking Controller Integration Tests")
class BookingControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookingService bookingService;

    private BookingDTO testBookingDTO;
    private CreateBookingRequest createBookingRequest;

    @BeforeEach
    void setUp() {
        testBookingDTO = new BookingDTO();
        testBookingDTO.setId(1L);
        testBookingDTO.setUserId(1L);
        testBookingDTO.setServiceName("Sprzątanie");
        testBookingDTO.setStatus("PENDING");

        createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setUserId(1L);
        createBookingRequest.setServiceName("Sprzątanie");
        createBookingRequest.setBookingDate(LocalDateTime.now().plusDays(1));
        createBookingRequest.setDescription("Sprzątanie mieszkania");
    }

    @Test
    @DisplayName("Should create booking successfully")
    void testCreateBookingIntegration() throws Exception {
        // Arrange
        when(bookingService.createBooking(any(CreateBookingRequest.class))).thenReturn(testBookingDTO);

        // Act & Assert
        mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer jwt_token")
                .content(objectMapper.writeValueAsString(createBookingRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serviceName").value("Sprzątanie"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    @DisplayName("Should get all bookings")
    void testGetAllBookingsIntegration() throws Exception {
        // Arrange
        List<BookingDTO> bookings = Arrays.asList(testBookingDTO);
        when(bookingService.getAllBookings()).thenReturn(bookings);

        // Act & Assert
        mockMvc.perform(get("/api/bookings")
                .header("Authorization", "Bearer jwt_token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].serviceName").value("Sprzątanie"));
    }

    @Test
    @DisplayName("Should get booking by id")
    void testGetBookingByIdIntegration() throws Exception {
        // Arrange
        when(bookingService.getBookingById(1L)).thenReturn(testBookingDTO);

        // Act & Assert
        mockMvc.perform(get("/api/bookings/1")
                .header("Authorization", "Bearer jwt_token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.serviceName").value("Sprzątanie"));
    }

    @Test
    @DisplayName("Should update booking successfully")
    void testUpdateBookingIntegration() throws Exception {
        // Arrange
        BookingDTO updateDTO = new BookingDTO();
        updateDTO.setStatus("CONFIRMED");

        testBookingDTO.setStatus("CONFIRMED");
        when(bookingService.updateBooking(any(Long.class), any(BookingDTO.class))).thenReturn(testBookingDTO);

        // Act & Assert
        mockMvc.perform(put("/api/bookings/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer jwt_token")
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CONFIRMED"));
    }

    @Test
    @DisplayName("Should delete booking successfully")
    void testDeleteBookingIntegration() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/bookings/1")
                .header("Authorization", "Bearer jwt_token"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should get bookings by user id")
    void testGetBookingsByUserIdIntegration() throws Exception {
        // Arrange
        List<BookingDTO> bookings = Arrays.asList(testBookingDTO);
        when(bookingService.getBookingsByUserId(1L)).thenReturn(bookings);

        // Act & Assert
        mockMvc.perform(get("/api/bookings/user/1")
                .header("Authorization", "Bearer jwt_token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(1));
    }

    @Test
    @DisplayName("Should check health")
    void testHealthCheck() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return 401 for missing authorization header")
    void testMissingAuthorizationHeader() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/bookings"))
                .andExpect(status().isUnauthorized());
    }
}
