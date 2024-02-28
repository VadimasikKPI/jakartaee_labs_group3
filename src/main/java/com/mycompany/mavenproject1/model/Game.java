package com.mycompany.mavenproject1.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_date")
    private Date date;

    @ManyToMany
    private List<Team> teams;

    public Game() {
    }

    public Game(Date date, List<Team> teams) {
        this.date = date;
        this.teams = teams;
    }

    public Game(Date gameDate) {
        this.date = gameDate;
        this.teams = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public int getTeamsSize() {
        return this.teams.size();
    }
}
