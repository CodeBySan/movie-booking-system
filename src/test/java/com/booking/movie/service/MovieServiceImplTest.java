package com.booking.movie.service;

import com.booking.movie.entity.Movie;
import com.booking.movie.exception.ResourceNotFoundException;
import com.booking.movie.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    void getAllMovies_Success() {
        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setTitle("Movie 1");
        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setTitle("Movie 2");

        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie1, movie2));

        List<Movie> movies = movieService.getAllMovies();

        assertEquals(2, movies.size());
        verify(movieRepository).findAll();
    }

    @Test
    void getMovieById_Success() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test Movie");

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        Movie foundMovie = movieService.getMovieById(1L);

        assertNotNull(foundMovie);
        assertEquals("Test Movie", foundMovie.getTitle());
    }

    @Test
    void getMovieById_NotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> movieService.getMovieById(1L));
    }
}