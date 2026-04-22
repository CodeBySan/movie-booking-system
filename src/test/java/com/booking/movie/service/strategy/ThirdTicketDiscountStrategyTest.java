package com.booking.movie.service.strategy;

import com.booking.movie.entity.Show;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThirdTicketDiscountStrategyTest {

    private final ThirdTicketDiscountStrategy strategy = new ThirdTicketDiscountStrategy();

    @Test
    void calculateDiscount_WithThreeSeats_ShouldApplyDiscount() {
        Show show = new Show();
        show.setPrice(BigDecimal.valueOf(100));

        BigDecimal discount = strategy.calculateDiscount(show, 3, BigDecimal.valueOf(300));

        // 50% discount on 1 ticket price of 100 = 50
        assertEquals(0, BigDecimal.valueOf(50).compareTo(discount));
    }

    @Test
    void calculateDiscount_WithTwoSeats_ShouldNotApplyDiscount() {
        Show show = new Show();
        show.setPrice(BigDecimal.valueOf(100));

        BigDecimal discount = strategy.calculateDiscount(show, 2, BigDecimal.valueOf(200));

        assertEquals(0, BigDecimal.ZERO.compareTo(discount));
    }
}
