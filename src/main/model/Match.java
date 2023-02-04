package model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class Match {
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private ArrayList<String> teamLineup;

    public static final String barca = "FC Barcelona";
    public static final String barcaStadium = "Camp Nou";

    public Match(String homeTeam, String awayTeam, int homeScore, int awayScore, ArrayList<String> teamLineup) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.teamLineup = teamLineup;
    }

    // REQUIRES: awayScore >= 0
    // MODIFIES: this
    // EFFECTS: changes the away score to the given score
    public void changeAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    // REQUIRES: homeScore >= 0
    // MODIFIES: this
    // EFFECTS: changes the home score to the given score
    public void changeHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    // REQUIRES: homeTeam != "" or " "
    // MODIFIES: this
    // EFFECTS: changes the homeTeam name to the given team name
    public void changeHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    // REQUIRES: awayTeam != "" or " "
    // MODIFIES: this
    // EFFECTS: changes the awayTeam name to the given team name
    public void changeAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    // REQUIRES: awayTeam != "" or " "
    // MODIFIES: this
    // EFFECTS: changes the awayTeam name to the given team name
    public void updateLineup( ArrayList<String> teamLineup) {
        // stub
    }

    public String getHomeTeam() {
        return this.homeTeam;
    }

    public String getAwayTeam() {
        return this.awayTeam;
    }

    public int getHomeScore() {
        return this.homeScore;
    }

    public int getAwayScore() {
        return this.awayScore;
    }

    public List<Object> getTeamLineup() {
        return emptyList();
    }



}
