package com.booking.movie.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "booking_seat")
@Data
public class BookingSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookingId;
    private Long showId;
    private String seatNumber;
}
