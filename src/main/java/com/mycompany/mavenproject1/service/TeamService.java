package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.model.Team;
import com.mycompany.mavenproject1.repository.TeamRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


import jakarta.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
@Transactional
public class TeamService {
    @Inject
    private TeamRepository teamRepository;

//    public TeamService() {
//        System.out.println("TeamService.TeamService");
//        this.teamRepository = new TeamRepository();
//    }

    public void saveTeam(String teamName){
        Team team = new Team(teamName);
        teamRepository.save(team);
    }

    public List<Team> getTeamsFilteredByName(String teamName){
        return teamRepository.getTeamsFilteredByName(teamName);
    }
//    public Team getTeamByName(TeamDTO teamDTO){
//        Team team = teamRepository.findByName(teamDTO.getTeamName());
//        return team;
//    }

    public Team updateTeamById(Long id, String newName){
        Team team = teamRepository.findById(id);
        if(team != null){
            team.setTeamName(newName);
            teamRepository.update(team);
        }
        return team;
    }

    public void deleteTeam(Long id){
        Team team = teamRepository.findById(id);
        if(team != null){
            teamRepository.delete(id);
        }

    }

    public List<Team> findAll(){
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(long l) {
        return Optional.ofNullable(teamRepository.findById(l));
    }
}
