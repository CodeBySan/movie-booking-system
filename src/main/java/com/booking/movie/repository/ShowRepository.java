package com.booking.movie.repository;

import com.booking.movie.entity.Show;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovieIdAndShowDate(Long movieId, LocalDate showDate);

    List<Show> findByTheatreIdAndShowDate(Long theatreId, LocalDate showDate);

    @Query(value = """
            SELECT s.*
            FROM shows s
            JOIN theatre t ON s.theatre_id = t.id
            WHERE s.movie_id = :movieId
            AND s.show_date = :date
            AND LOWER(t.city) = LOWER(:city)
            """,
            countQuery = """
                    SELECT count(*)
                    FROM shows s
                    JOIN theatre t ON s.theatre_id = t.id
                    WHERE s.movie_id = :movieId
                    AND s.show_date = :date
                    AND LOWER(t.city) = LOWER(:city)
                    """,
            nativeQuery = true)
    Page<Show> searchShows(
            Long movieId,
            String city,
            LocalDate date,
            Pageable pageable);
}
