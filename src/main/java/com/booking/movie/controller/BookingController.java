package com.booking.movie.controller;

import com.booking.movie.dto.request.BookingRequest;
import com.booking.movie.dto.response.BookingResponse;
import com.booking.movie.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Booking APIs", description = "Book movie tickets")
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @Operation(summary = "Book tickets with offers")
    @PostMapping
    public BookingResponse bookTicket(@Valid @RequestBody BookingRequest request) {

        return bookingService.bookTicket(request);
    }
}
