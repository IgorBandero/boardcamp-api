package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RentalDTO {
    
    @NotNull(message = "Customer id is mandatory!")
    private Long customerId;

    @NotNull(message = "Game id is mandatory!")
    private Long gameId;

    @NotNull(message = "Days rented is mandatory!")
    @Positive(message = "Days rented must be greater than zero.")
    private Long daysRented;
}
