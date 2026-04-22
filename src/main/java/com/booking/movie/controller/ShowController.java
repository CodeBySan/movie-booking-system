package com.booking.movie.controller;

import com.booking.movie.entity.Show;
import com.booking.movie.service.ShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Show APIs", description = "Manage movie shows")
@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {
    private final ShowService showService;

    @Operation(summary = "Create show")
    // Future enhancement:
    // @PreAuthorize("hasRole('THEATRE_ADMIN')")
    @PostMapping
    public Show saveShow(@RequestBody Show show) {

        return showService.saveShow(show);
    }

    @Operation(summary = "Update show")
    // Future enhancement:
    // @PreAuthorize("hasRole('THEATRE_ADMIN')")
    @PutMapping("/{id}")
    public Show update(@PathVariable Long id, @RequestBody Show show) {

        return showService.updateShow(id, show);
    }

    @Operation(summary = "Delete show")
    // Future enhancement:
    // @PreAuthorize("hasRole('THEATRE_ADMIN')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        showService.deleteShow(id);
        return "Show deleted successfully";
    }

    @Operation(summary = "Browse shows by movie city and date")
    @GetMapping("/search")
    public Page<Show> search(@RequestParam Long movieId, @RequestParam String city, @RequestParam String date, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return showService.searchShows(movieId, city, LocalDate.parse(date), pageable);
    }

    @Operation(summary = "Finding shows by movie and date")
    @GetMapping("/movie")
    public List<Show> getByMovie(@RequestParam Long movieId, @RequestParam String date) {

        return showService.getShowsByMovieAndDate(movieId, LocalDate.parse(date));
    }

    @Operation(summary = "Finding shows by Theatre and date")
    @GetMapping("/theatre")
    public List<Show> getByTheatre(@RequestParam Long theatreId, @RequestParam String date) {

        return showService.getShowsByTheatreAndDate(theatreId, LocalDate.parse(date));
    }
}
