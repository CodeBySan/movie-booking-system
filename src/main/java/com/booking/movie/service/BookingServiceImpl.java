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
import com.booking.movie.mapper.BookingMapper;
import com.booking.movie.repository.BookingRepository;
import com.booking.movie.repository.BookingSeatRepository;
import com.booking.movie.repository.ShowRepository;
import com.booking.movie.service.strategy.DiscountStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final ShowRepository showRepository;
    private final BookingRepository bookingRepository;
    private final BookingSeatRepository bookingSeatRepository;
    private final List<DiscountStrategy> discountStrategies;

    @Override
    @Transactional
    public BookingResponse bookTicket(BookingRequest request) {

        log.info("Booking started for user {}", request.getUserName());

        Show show = showRepository.findById(request.getShowId()).orElseThrow(() -> new ResourceNotFoundException(AppConstants.SHOW_NOT_FOUND));

        List<BookingSeat> lockedSeats = bookingSeatRepository.lockBookedSeats(request.getShowId(), request.getSeats());

        log.info("Locked {} seats for showId {}", lockedSeats.size(),  request.getShowId());

        for (String seat : request.getSeats()) {
            boolean booked = bookingSeatRepository.existsByShowIdAndSeatNumber(request.getShowId(), seat);

            if (booked) {
                log.error("Seat already booked {}", seat);
                throw new SeatAlreadyBookedException(AppConstants.SEAT_ALREADY_BOOKED + seat);
            }
        }

        int seatCount = request.getSeats().size();
        BigDecimal totalAmount = show.getPrice().multiply(BigDecimal.valueOf(seatCount));
        
        BigDecimal discount = discountStrategies.stream()
                .map(strategy -> strategy.calculateDiscount(show, seatCount, totalAmount))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal finalAmount = totalAmount.subtract(discount);
        Booking booking = new Booking();
        booking.setUserName(request.getUserName());
        booking.setShowId(request.getShowId());
        booking.setTotalAmount(finalAmount);
        booking.setStatus(BookingStatus.BOOKED.name());
        booking.setCreatedAt(LocalDateTime.now());

        booking = bookingRepository.save(booking);

        for (String seat : request.getSeats()) {

            BookingSeat bs = new BookingSeat();
            bs.setBookingId(booking.getId());
            bs.setShowId(request.getShowId());
            bs.setSeatNumber(seat);

            bookingSeatRepository.save(bs);
        }

        log.info("Booking success for showId {}", request.getShowId());

        return BookingMapper.mapToResponse(booking);
    }
}