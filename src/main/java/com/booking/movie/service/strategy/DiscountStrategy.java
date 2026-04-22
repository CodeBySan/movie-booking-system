package com.booking.movie.service.strategy;

import com.booking.movie.entity.Show;
import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal calculateDiscount(Show show, int seatCount, BigDecimal totalAmount);
}
