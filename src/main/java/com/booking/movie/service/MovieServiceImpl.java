package com.booking.movie.service;

import com.booking.movie.constants.AppConstants;
import com.booking.movie.entity.Movie;
import com.booking.movie.exception.ResourceNotFoundException;
import com.booking.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.MOVIE_NOT_FOUND));
    }
}
