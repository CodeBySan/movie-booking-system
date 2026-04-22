package com.booking.movie.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BookingResponse {
    private Long bookingId;
    private String status;
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private BigDecimal finalAmount;
}
