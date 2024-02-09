package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.exceptions.CustomerConflictException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomersRepository;

@Service
public class CustomersServices {
    final CustomersRepository customersRepository;
    CustomersServices(CustomersRepository customersRepository){
        this.customersRepository = customersRepository;
    }

    public CustomerModel save(CustomerDTO dto){
        if(customersRepository.existsByCpf(dto.getCpf())){
            throw new CustomerConflictException("Customer cpf already exists!");
        }
        CustomerModel customer = new CustomerModel(dto);
        return customersRepository.save(customer);
    }
}
