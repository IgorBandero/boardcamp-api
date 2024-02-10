package com.boardcamp.api.models;

import java.time.LocalDate;

import com.boardcamp.api.dtos.RentalDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="rentals")
public class RentalModel {

    public RentalModel(RentalDTO dto, GameModel game, CustomerModel customer){
        
        this.rentDate = LocalDate.now();
        this.daysRented = dto.getDaysRented(); 
        this.returnDate = null;
        this.originalPrice = (dto.getDaysRented() * game.getPricePerDay());
        this.delayFee = 0;
        this.customerId = dto.getCustomerId();
        this.gameId = dto.getGameId(); 
        this.customer = customer;
        this.game = game;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonIgnore
    @Column(nullable = false)
    private Long customerId;

    @JsonIgnore
    @Column(nullable = false)
    private Long gameId;

    // Data atual
    @Column
    private LocalDate rentDate;

    @Column(nullable = false)
    private Long daysRented;

    // null
    @Column(nullable = true)
    private LocalDate returnDate;

    // originalPrice: daysRented * gameId(pricePerDay)
    @Column
    private double originalPrice;

    // delayFee: 0
    @Column
    private double delayFee;
    
    @ManyToOne
    @JoinColumn(name = "customer")
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "game")
    private GameModel game;
}
