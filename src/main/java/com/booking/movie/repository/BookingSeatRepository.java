package com.booking.movie.repository;

import com.booking.movie.entity.BookingSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {

    boolean existsByShowIdAndSeatNumber(Long showId, String seatNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
                select b from BookingSeat b
                where b.showId = :showId
                and b.seatNumber in :seats
            """)
    List<BookingSeat> lockBookedSeats(Long showId, List<String> seats);

}
