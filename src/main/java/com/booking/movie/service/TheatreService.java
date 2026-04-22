package com.booking.movie.service;

import com.booking.movie.entity.Theatre;

import java.util.List;

public interface TheatreService {

    Theatre saveTheatre(Theatre theatre);

    List<Theatre> getAllTheatres();

    List<Theatre> getByCity(String city);
}

