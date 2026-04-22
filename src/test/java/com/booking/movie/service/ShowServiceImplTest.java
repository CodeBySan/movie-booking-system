package com.booking.movie.service;

import com.booking.movie.entity.Show;
import com.booking.movie.exception.ResourceNotFoundException;
import com.booking.movie.repository.ShowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShowServiceImplTest {

    @Mock
    private ShowRepository showRepository;

    @InjectMocks
    private ShowServiceImpl showService;

    private Show show;

    @BeforeEach
    void setUp() {
        show = new Show();
        show.setId(1L);
        show.setMovieId(1L);
        show.setTheatreId(1L);
        show.setShowDate(LocalDate.now());
        show.setShowTime(LocalTime.of(18, 0));
        show.setPrice(BigDecimal.valueOf(150));
    }

    @Test
    void saveShow_Success() {
        when(showRepository.save(any(Show.class))).thenReturn(show);
        
        Show savedShow = showService.saveShow(show);
        
        assertNotNull(savedShow);
        assertEquals(1L, savedShow.getId());
        verify(showRepository).save(show);
    }

    @Test
    void updateShow_Success() {
        Show updateRequest = new Show();
        updateRequest.setMovieId(2L);
        updateRequest.setTheatreId(2L);
        updateRequest.setPrice(BigDecimal.valueOf(200));

        when(showRepository.findById(1L)).thenReturn(Optional.of(show));
        when(showRepository.save(any(Show.class))).thenReturn(show); // Using the modified show

        Show updatedShow = showService.updateShow(1L, updateRequest);

        assertNotNull(updatedShow);
        assertEquals(2L, updatedShow.getMovieId());
        assertEquals(BigDecimal.valueOf(200), updatedShow.getPrice());
        verify(showRepository).save(show);
    }

    @Test
    void updateShow_NotFound() {
        Show updateRequest = new Show();
        when(showRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> showService.updateShow(1L, updateRequest));
        verify(showRepository, never()).save(any(Show.class));
    }

    @Test
    void deleteShow_Success() {
        when(showRepository.findById(1L)).thenReturn(Optional.of(show));

        showService.deleteShow(1L);

        verify(showRepository).delete(show);
    }

    @Test
    void deleteShow_NotFound() {
        when(showRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> showService.deleteShow(1L));
        verify(showRepository, never()).delete(any(Show.class));
    }
}