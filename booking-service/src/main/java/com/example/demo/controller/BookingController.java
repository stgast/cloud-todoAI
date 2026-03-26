package com.example.demo.controller;

import com.example.demo.dto.BookingDTO;
import com.example.demo.dto.CreateBookingRequest;
import com.example.demo.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@Tag(name = "Booking Management", description = "API для управління резерваціями")
public class BookingController {
    
    private final BookingService bookingService;
    
    @PostMapping
    @Operation(summary = "Створити нову резервацію")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody CreateBookingRequest request) {
        BookingDTO bookingDTO = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDTO);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Отримати резервацію за ID")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        BookingDTO bookingDTO = bookingService.getBookingById(id);
        return ResponseEntity.ok(bookingDTO);
    }
    
    @GetMapping
    @Operation(summary = "Отримати всі резервації")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Отримати резервації користувача")
    public ResponseEntity<List<BookingDTO>> getUserBookings(@PathVariable Long userId) {
        List<BookingDTO> bookings = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/status/{status}")
    @Operation(summary = "Отримати резервації за статусом")
    public ResponseEntity<List<BookingDTO>> getBookingsByStatus(@PathVariable String status) {
        List<BookingDTO> bookings = bookingService.getBookingsByStatus(status);
        return ResponseEntity.ok(bookings);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Оновити резервацію")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody CreateBookingRequest request) {
        BookingDTO bookingDTO = bookingService.updateBooking(id, request);
        return ResponseEntity.ok(bookingDTO);
    }
    
    @PatchMapping("/{id}/status")
    @Operation(summary = "Оновити статус резервації")
    public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Long id, @RequestParam String status) {
        BookingDTO bookingDTO = bookingService.updateBookingStatus(id, status);
        return ResponseEntity.ok(bookingDTO);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Видалити резервацію")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
