package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.services.GamesServices;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/games")
public class GamesController {

    final GamesServices gamesServices;
    GamesController(GamesServices gamesServices){
        this.gamesServices = gamesServices;
    }

    @PostMapping
    public ResponseEntity<GameModel> createGame(@RequestBody @Valid GameDTO body) {        
        GameModel game = gamesServices.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    @GetMapping
    public ResponseEntity<List<GameModel>> getGames() {
        List<GameModel> games = gamesServices.getGames();
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }
    
}
