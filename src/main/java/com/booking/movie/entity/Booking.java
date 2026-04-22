package com.booking.movie.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private Long showId;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private BigDecimal discount;
    private BigDecimal finalAmount;
}
