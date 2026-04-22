package com.booking.movie.service;

import com.booking.movie.entity.Show;
import com.booking.movie.exception.ResourceNotFoundException;
import com.booking.movie.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;

    @Override
    public Show saveShow(Show show) {
        return showRepository.save(show);
    }

    @Override
    public Show updateShow(Long id, Show request) {

        Show show = showRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Show not found"));

        show.setMovieId(request.getMovieId());
        show.setTheatreId(request.getTheatreId());
        show.setShowDate(request.getShowDate());
        show.setShowTime(request.getShowTime());
        show.setPrice(request.getPrice());

        return showRepository.save(show);
    }

    @Override
    public void deleteShow(Long id) {

        Show show = showRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Show not found"));

        showRepository.delete(show);
    }

    @Override
    public List<Show> getShowsByMovieAndDate(Long movieId, LocalDate date) {

        log.info("Finding shows by MovieId {} and date {}", movieId, date);

        return showRepository.findByMovieIdAndShowDate(movieId, date);
    }

    @Override
    public List<Show> getShowsByTheatreAndDate(Long theatreId, LocalDate date) {

        log.info("Finding shows by Theatre {} and date {}", theatreId, date);

        return showRepository.findByTheatreIdAndShowDate(theatreId, date);
    }

    @Override
    public Page<Show> searchShows(Long movieId, String city, LocalDate date, Pageable pageable) {

        log.info("Searching shows movieId {} city {}", movieId, city);

        return showRepository.searchShows(movieId, city, date, pageable);
    }
}
