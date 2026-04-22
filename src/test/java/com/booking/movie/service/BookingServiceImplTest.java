package com.booking.movie.service;

import com.booking.movie.constants.AppConstants;
import com.booking.movie.dto.request.BookingRequest;
import com.booking.movie.dto.response.BookingResponse;
import com.booking.movie.entity.Booking;
import com.booking.movie.entity.BookingSeat;
import com.booking.movie.entity.Show;
import com.booking.movie.enums.BookingStatus;
import com.booking.movie.exception.ResourceNotFoundException;
import com.booking.movie.exception.SeatAlreadyBookedException;
import com.booking.movie.repository.BookingRepository;
import com.booking.movie.repository.BookingSeatRepository;
import com.booking.movie.repository.ShowRepository;
import com.booking.movie.service.strategy.DiscountStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private ShowRepository showRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingSeatRepository bookingSeatRepository;

    @Mock
    private DiscountStrategy discountStrategy;

    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        // Initialize the service to inject the list of strategies
        bookingService = new BookingServiceImpl(
                showRepository,
                bookingRepository,
                bookingSeatRepository,
                Collections.singletonList(discountStrategy)
        );
    }

    @Test
    void bookTicket_Success() {
        BookingRequest request = new BookingRequest();
        request.setUserName("SantoshBehera");
        request.setShowId(1L);
        request.setSeats(Arrays.asList("A1", "A2"));

        Show show = new Show();
        show.setId(1L);
        show.setPrice(BigDecimal.valueOf(100));
        show.setShowTime(LocalTime.of(18, 0)); // Evening show

        Booking savedBooking = new Booking();
        savedBooking.setId(100L);
        savedBooking.setUserName("SantoshBehera");
        savedBooking.setShowId(1L);
        savedBooking.setTotalAmount(BigDecimal.valueOf(200));
        savedBooking.setStatus(BookingStatus.BOOKED.name());

        when(showRepository.findById(1L)).thenReturn(Optional.of(show));
        when(bookingSeatRepository.lockBookedSeats(eq(1L), anyList())).thenReturn(Collections.emptyList());
        when(bookingSeatRepository.existsByShowIdAndSeatNumber(eq(1L), anyString())).thenReturn(false);
        
        // Mocking discount calculation
        when(discountStrategy.calculateDiscount(any(Show.class), eq(2), any(BigDecimal.class)))
                .thenReturn(BigDecimal.ZERO);

        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

        // Act
        BookingResponse response = bookingService.bookTicket(request);

        // Assert
        assertNotNull(response);
        assertEquals(100L, response.getBookingId());
        assertEquals(BookingStatus.BOOKED.name(), response.getStatus());

        verify(showRepository).findById(1L);
        verify(bookingSeatRepository, times(2)).existsByShowIdAndSeatNumber(eq(1L), anyString());
        verify(bookingRepository).save(any(Booking.class));
        verify(bookingSeatRepository, times(2)).save(any(BookingSeat.class));
    }

    @Test
    void bookTicket_ShowNotFound() {
        // Arrange
        BookingRequest request = new BookingRequest();
        request.setShowId(99L);

        when(showRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> bookingService.bookTicket(request)
        );
        assertEquals(AppConstants.SHOW_NOT_FOUND, exception.getMessage());
    }

    @Test
    void bookTicket_SeatAlreadyBooked() {
        // Arrange
        BookingRequest request = new BookingRequest();
        request.setUserName("SantoshBehera");
        request.setShowId(1L);
        request.setSeats(Arrays.asList("A1", "A2"));

        Show show = new Show();
        show.setId(1L);

        when(showRepository.findById(1L)).thenReturn(Optional.of(show));
        when(bookingSeatRepository.lockBookedSeats(eq(1L), anyList())).thenReturn(Collections.emptyList());
        when(bookingSeatRepository.existsByShowIdAndSeatNumber(1L, "A1")).thenReturn(true);

        // Act & Assert
        SeatAlreadyBookedException exception = assertThrows(
                SeatAlreadyBookedException.class,
                () -> bookingService.bookTicket(request)
        );
        assertTrue(exception.getMessage().contains(AppConstants.SEAT_ALREADY_BOOKED));
    }
}
