package com.boardcamp.api.dtos;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GameDTO {
    
    @NotBlank(message = "Game name is mandatory!")
    private String name;

    @URL(message = "Invalid image URL!")
    private String image;

    @NotNull(message = "Game stock is mandatory!")
    @Positive(message = "Stock total must be greater than zero.")
    private Long stockTotal;
    
    @NotNull(message = "Game price is mandatory!")
    @Positive(message = "Price per day must be greater than zero")
    private double pricePerDay;
}
