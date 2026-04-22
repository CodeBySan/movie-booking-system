package com.booking.movie.controller;

import com.booking.movie.entity.Movie;
import com.booking.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Movie APIs", description = "Manage movies")
@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @Operation(summary = "Add new movie")
    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {

        return movieService.saveMovie(movie);
    }

    @Operation(summary = "Get all movies")
    @GetMapping
    public List<Movie> getAllMovies() {

        return movieService.getAllMovies();
    }

    @Operation(summary = "Get movie by id")
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {

        return movieService.getMovieById(id);
    }
}
