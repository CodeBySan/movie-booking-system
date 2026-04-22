package com.booking.movie.mapper;

import com.booking.movie.dto.response.BookingResponse;
import com.booking.movie.entity.Booking;

public class BookingMapper {
    private BookingMapper() {
    }

    public static BookingResponse mapToResponse(Booking booking) {
        if (booking == null) {
            return null;
        }

        return BookingResponse.builder()
                .bookingId(booking.getId())
                .status(booking.getStatus())
                .totalAmount(booking.getTotalAmount())
                .discount(booking.getDiscount())
                .finalAmount(booking.getFinalAmount())
                .build();
    }
}
