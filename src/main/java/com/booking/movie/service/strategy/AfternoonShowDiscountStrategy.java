package com.booking.movie.service.strategy;

import com.booking.movie.entity.Show;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;

@Component
public class AfternoonShowDiscountStrategy implements DiscountStrategy {

    @Override
    public BigDecimal calculateDiscount(Show show, int seatCount, BigDecimal totalAmount) {
        LocalTime showTime = show.getShowTime();
        if (!showTime.isBefore(LocalTime.NOON) && showTime.isBefore(LocalTime.of(16, 0))) {
            return totalAmount.multiply(BigDecimal.valueOf(0.20));
        }
        return BigDecimal.ZERO;
    }
}
