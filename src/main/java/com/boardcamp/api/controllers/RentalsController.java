package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.services.RentalsServices;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




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

    @GetMapping
    public ResponseEntity<List<RentalModel>> getRentals() {
        List<RentalModel> rentals = rentalsServices.getRentals();
        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }
    
    @PutMapping("/{id}/return")
    public ResponseEntity<RentalModel> finishRent(@PathVariable Long id) {
        RentalModel rent = rentalsServices.finishRental(id);
        return ResponseEntity.status(HttpStatus.OK).body(rent);     
    }
    
}
