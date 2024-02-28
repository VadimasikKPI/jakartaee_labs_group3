package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.model.Game;
import com.mycompany.mavenproject1.repository.GameRepository;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Stateless
@Transactional
public class GameService {
    @Inject
    private GameRepository gameRepository;
    @Inject
    private TeamService teamService;

//    public GameService() {
//        this.gameRepository = new GameRepository();
//        this.teamService = new TeamService();
//
//    }

    public void saveGame(Date gameDate, String team1Id, String team2Id){
        Game game = new Game(gameDate);
        teamService.getTeamById(Long.parseLong(team1Id)).ifPresent(game::addTeam);
        teamService.getTeamById(Long.parseLong(team2Id)).ifPresent(game::addTeam);
        gameRepository.save(game);
    }
    public Game getGameById(Long id){
        Game game = gameRepository.findById(id);
        return game;
    }

    public Game updateGame(Long id, Date gameDate, String team1Id, String team2Id){
        Game game = gameRepository.findById(id);
        game.setDate(gameDate);
        game.setTeams(new ArrayList<>());
        teamService.getTeamById(Long.parseLong(team1Id)).ifPresentOrElse(game::addTeam, () -> {
            throw new RuntimeException("Team not found");
        });
        teamService.getTeamById(Long.parseLong(team2Id)).ifPresentOrElse(game::addTeam, () -> {
            throw new RuntimeException("Team not found");
        });
        gameRepository.update(game);
        return game;
    }

    public void deleteGame(Long id){
        Game game = gameRepository.findById(id);
        if(game != null){
            gameRepository.delete(id);
        }

    }

    public List<Game> findAll(){
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(long l) {
        return Optional.ofNullable(gameRepository.findById(l));
    }
}
