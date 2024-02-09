package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.services.CustomersServices;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/customers")
public class CustomersController {
    
    final CustomersServices customersServices;
    CustomersController(CustomersServices customersServices){
        this.customersServices = customersServices;
    }

    @PostMapping
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody @Valid CustomerDTO body) {        
        CustomerModel customer = customersServices.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    
}
