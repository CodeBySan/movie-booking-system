package com.booking.movie.service;

import com.booking.movie.entity.Theatre;
import com.booking.movie.repository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreServiceImpl implements TheatreService{
    private final TheatreRepository theatreRepository;

    @Override
    public Theatre saveTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @Override
    public List<Theatre> getByCity(String city) {
        return theatreRepository.findByCityIgnoreCase(city);
    }
}
