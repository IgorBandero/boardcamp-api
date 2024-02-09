package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.exceptions.GameConflictException;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GamesRepository;

@Service
public class GamesServices {
    
    final GamesRepository gamesRepository;
    GamesServices(GamesRepository gamesRepository){
        this.gamesRepository = gamesRepository;
    }
    
    public GameModel save(GameDTO dto){
        if (gamesRepository.existsByName(dto.getName())){
            throw new GameConflictException("This game already exists!");
        }
        GameModel game = new GameModel(dto);
        return gamesRepository.save(game);
    }

    public List<GameModel> getGames(){
        return gamesRepository.findAll();
    }
}
