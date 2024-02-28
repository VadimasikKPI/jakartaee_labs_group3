package com.mycompany.mavenproject1.service;


import com.mycompany.mavenproject1.model.Results;
import com.mycompany.mavenproject1.repository.ResultRepository;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
@Transactional
public class ResultService {
    @Inject
    private ResultRepository resultRepository;
    @Inject
    private TeamService teamService;
    @Inject

    private GameService gameService;

//    public ResultService() {
//        this.resultRepository = new ResultRepository();
//        this.teamService = new TeamService();
//        this.gameService = new GameService();
//    }

    public void saveResult(String gameId, String score, String winnerId){
        Results result = new Results();
        gameService.getGameById(Long.parseLong(gameId)).ifPresent(result::setGame);
        result.setScore(score);
        teamService.getTeamById(Long.parseLong(winnerId)).ifPresent(result::setWinnerName);
        resultRepository.save(result);
    }
//    public Results getResultByTeamWinner(ResultDTO resultDTO){
//        Results result = resultRepository.findByWinner(resultDTO.getWinnerName());
//        return result;
//    }

    public Results getResultById(Long id){
        Results result = resultRepository.findById(id);
        return result;
    }

    public Results updateResult(Long id, String gameId, String score, String winnerId){
        Results result = resultRepository.findById(id);
        gameService.getGameById(Long.parseLong(gameId)).ifPresent(result::setGame);
        result.setScore(score);
        teamService.getTeamById(Long.parseLong(winnerId)).ifPresent(result::setWinnerName);
        resultRepository.update(result);
        return result;
    }

    public void deleteResult(Long id){
        resultRepository.delete(id);
    }

    public List<Results> findAll(){
        return resultRepository.findAll();
    }
}
