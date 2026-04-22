package com.booking.movie.service;

import com.booking.movie.dto.request.BookingRequest;
import com.booking.movie.dto.response.BookingResponse;

public interface BookingService {
    BookingResponse bookTicket(BookingRequest request);
}
