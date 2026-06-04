package com.shivacharan.Ipl_dashboard;

import jakarta.persistence.*;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int season;
    private String city;
    private String date;
    private String team1;
    private String team2;
    private String tossWinner;
    private String tossDecision;
    private String result;
    private String winner;
    private String playerOfMatch;
    private String venue;

    public Match() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getSeason() { return season; }
    public void setSeason(int season) { this.season = season; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTeam1() { return team1; }
    public void setTeam1(String team1) { this.team1 = team1; }

    public String getTeam2() { return team2; }
    public void setTeam2(String team2) { this.team2 = team2; }

    public String getTossWinner() { return tossWinner; }
    public void setTossWinner(String tossWinner) { this.tossWinner = tossWinner; }

    public String getTossDecision() { return tossDecision; }
    public void setTossDecision(String tossDecision) { this.tossDecision = tossDecision; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }

    public String getPlayerOfMatch() { return playerOfMatch; }
    public void setPlayerOfMatch(String playerOfMatch) { this.playerOfMatch = playerOfMatch; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
}