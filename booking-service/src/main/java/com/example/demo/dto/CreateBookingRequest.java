package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {
    private Long userId;
    private String serviceName;
    private LocalDateTime bookingDate;
    private String description;
}
