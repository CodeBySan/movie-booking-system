package com.booking.movie.service.strategy;

import com.booking.movie.entity.Show;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ThirdTicketDiscountStrategy implements DiscountStrategy {

    @Override
    public BigDecimal calculateDiscount(Show show, int seatCount, BigDecimal totalAmount) {
        if (seatCount >= 3) {
            return show.getPrice().multiply(BigDecimal.valueOf(0.50));
        }
        return BigDecimal.ZERO;
    }
}
