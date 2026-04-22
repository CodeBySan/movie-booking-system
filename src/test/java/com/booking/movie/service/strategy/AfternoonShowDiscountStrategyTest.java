package com.booking.movie.service.strategy;

import com.booking.movie.entity.Show;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AfternoonShowDiscountStrategyTest {

    private final AfternoonShowDiscountStrategy strategy = new AfternoonShowDiscountStrategy();

    @Test
    void calculateDiscount_At1PM_ShouldApplyDiscount() {
        Show show = new Show();
        show.setShowTime(LocalTime.of(13, 0)); // 1 PM

        BigDecimal totalAmount = BigDecimal.valueOf(1000);
        BigDecimal discount = strategy.calculateDiscount(show, 2, totalAmount);

        // 20% of 1000 = 200
        assertEquals(0, BigDecimal.valueOf(200).compareTo(discount));
    }

    @Test
    void calculateDiscount_At11AM_ShouldNotApplyDiscount() {
        Show show = new Show();
        show.setShowTime(LocalTime.of(11, 0)); // 11 AM

        BigDecimal totalAmount = BigDecimal.valueOf(1000);
        BigDecimal discount = strategy.calculateDiscount(show, 2, totalAmount);

        assertEquals(0, BigDecimal.ZERO.compareTo(discount));
    }

    @Test
    void calculateDiscount_At4PM_ShouldNotApplyDiscount() {
        Show show = new Show();
        show.setShowTime(LocalTime.of(16, 0)); // 4 PM

        BigDecimal totalAmount = BigDecimal.valueOf(1000);
        BigDecimal discount = strategy.calculateDiscount(show, 2, totalAmount);

        assertEquals(0, BigDecimal.ZERO.compareTo(discount));
    }
}