package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.services.RentalsServices;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/rentals")
public class RentalsController {
    
    final RentalsServices rentalsServices;
    RentalsController(RentalsServices rentalsServices){
        this.rentalsServices = rentalsServices;
    }

    @PostMapping
    public ResponseEntity<RentalModel> newRent(@RequestBody @Valid RentalDTO body) {
        RentalModel rent = rentalsServices.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(rent);
    }
    
}
