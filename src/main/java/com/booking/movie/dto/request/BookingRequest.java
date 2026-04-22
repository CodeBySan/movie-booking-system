package com.booking.movie.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {

    @NotBlank(message = "User name required")
    private String userName;

    @NotNull(message = "Show id required")
    private Long showId;

    @NotEmpty(message = "Seats required")
    private List<String> seats;
}
