package com.booking.movie.controller;

import com.booking.movie.entity.Theatre;
import com.booking.movie.service.TheatreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Theatre APIs", description = "Manage theatres")
@RestController
@RequestMapping("/api/theatres")
@RequiredArgsConstructor
public class TheatreController {
    private final TheatreService theatreService;

    @Operation(summary = "Add theatre")
    @PostMapping
    public Theatre saveTheatre(@RequestBody Theatre theatre) {

        return theatreService.saveTheatre(theatre);
    }

    @Operation(summary = "Get all theatres")
    @GetMapping
    public List<Theatre> getAll() {

        return theatreService.getAllTheatres();
    }

    @Operation(summary = "Get theatres by city")
    @GetMapping("/city/{city}")
    public List<Theatre> getByCity(@PathVariable String city) {

        return theatreService.getByCity(city);
    }
}
