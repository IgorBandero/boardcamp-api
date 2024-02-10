package com.boardcamp.api.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.exceptions.CompletedRentalException;
import com.boardcamp.api.exceptions.CustomerNotFoundException;
import com.boardcamp.api.exceptions.GameNotFoundException;
import com.boardcamp.api.exceptions.GameOutOfStockException;
import com.boardcamp.api.exceptions.RentalNotFoundException;
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

    public RentalModel finishRental(Long rentalId){
        RentalModel rent = rentalsRepository.findById(rentalId).orElseThrow(
            () -> new RentalNotFoundException("Rental not found!"));
        
        if(rent.getReturnDate() != null){
            throw new CompletedRentalException("Game returned already!");
        }
        
        int compareDates = LocalDate.now().compareTo(rent.getRentDate().plusDays(rent.getDaysRented()));
        
        long days = ChronoUnit.DAYS.between(LocalDate.now(), rent.getRentDate().plusDays(rent.getDaysRented()));

        if (compareDates > 0){
            rent.setDelayFee(-1 * days * rent.getGame().getPricePerDay());
        }

        RentalModel updatedRent = new RentalModel();

        updatedRent.setRentDate(rent.getRentDate());
        updatedRent.setDaysRented(rent.getDaysRented());
        updatedRent.setReturnDate(LocalDate.now());
        updatedRent.setOriginalPrice(rent.getOriginalPrice());
        updatedRent.setDelayFee(rent.getDelayFee());
        updatedRent.setCustomerId(rent.getCustomerId());
        updatedRent.setGameId(rent.getGameId());
        updatedRent.setCustomer(rent.getCustomer());
        updatedRent.setGame(rent.getGame());
        updatedRent.setId(rentalId);
        return rentalsRepository.save(updatedRent);
    }
}
