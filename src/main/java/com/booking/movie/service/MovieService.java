package com.booking.movie.service;

import com.booking.movie.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie saveMovie(Movie movie);

    List<Movie> getAllMovies();

    Movie getMovieById(Long id);
}
