package com.booking.movie.service;

import com.booking.movie.entity.Theatre;
import com.booking.movie.repository.TheatreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TheatreServiceImplTest {

    @Mock
    private TheatreRepository theatreRepository;

    @InjectMocks
    private TheatreServiceImpl theatreService;

    @Test
    void getByCity_Success() {
        Theatre theatre1 = new Theatre();
        theatre1.setCity("Bangalore");
        Theatre theatre2 = new Theatre();
        theatre2.setCity("Bangalore");

        when(theatreRepository.findByCityIgnoreCase("Bangalore")).thenReturn(Arrays.asList(theatre1, theatre2));

        List<Theatre> theatres = theatreService.getByCity("Bangalore");

        assertEquals(2, theatres.size());
        verify(theatreRepository).findByCityIgnoreCase("Bangalore");
    }
}