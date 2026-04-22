package com.booking.movie.service;

import com.booking.movie.entity.Show;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ShowService {

    Show saveShow(Show show);

    Show updateShow(Long id, Show show);

    void deleteShow(Long id);

    List<Show> getShowsByMovieAndDate(Long movieId, LocalDate date);

    List<Show> getShowsByTheatreAndDate(Long theatreId, LocalDate date);

    Page<Show> searchShows(Long movieId, String city, LocalDate date, Pageable pageable);
}
