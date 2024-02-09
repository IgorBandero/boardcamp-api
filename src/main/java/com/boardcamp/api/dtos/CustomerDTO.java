package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
    
    @NotBlank(message = "Customer name is mandatory!")
    private String name;

    @NotBlank(message = "Customer cpf is mandatory!")
    @Size(min = 11, max = 11)
    private String cpf;
}
