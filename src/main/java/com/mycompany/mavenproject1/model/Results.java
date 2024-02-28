package com.mycompany.mavenproject1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "result")
public class Results {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Game game;

    private String score;

    @OneToOne
    private Team winnerName;

    public Results() {
    }

    public Results(Game game, String score, Team winnerName) {
        this.game = game;
        this.score = score;
        this.winnerName = winnerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Team getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(Team winnerName) {
        this.winnerName = winnerName;
    }
}
