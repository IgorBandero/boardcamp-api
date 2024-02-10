package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.exceptions.CustomerNotFoundException;
import com.boardcamp.api.exceptions.GameNotFoundException;
import com.boardcamp.api.exceptions.GameOutOfStockException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;

@Service
public class RentalsServices {

    final RentalsRepository rentalsRepository;
    final GamesRepository gamesRepository;    
    final CustomersRepository customersRepository;

    RentalsServices(RentalsRepository rentalsRepository, GamesRepository gamesRepository, CustomersRepository customersRepository){
        this.rentalsRepository = rentalsRepository;
        this.gamesRepository = gamesRepository;
        this.customersRepository = customersRepository;
    }

    public RentalModel save(RentalDTO dto){

        GameModel game = gamesRepository.findById(dto.getGameId()).orElseThrow(
            () -> new GameNotFoundException("Game not found!"));
        CustomerModel customer = customersRepository.findById(dto.getCustomerId()).orElseThrow(
            () -> new CustomerNotFoundException("Customer not found!"));
        if (game.getStockTotal()==0){
            throw new GameOutOfStockException("Game out of stock!");
        }
        RentalModel rent = new RentalModel(dto, game, customer);
        return rentalsRepository.save(rent);
    }

    public List<RentalModel> getRentals(){
        return rentalsRepository.findAll();
    }
}
