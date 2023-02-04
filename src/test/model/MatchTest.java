package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchTest {
    Match match;

    @BeforeEach
    public void setup() {
        ArrayList<String> teamLineup;
        teamLineup = new ArrayList<>();
        teamLineup.addAll(Arrays.asList("Messi","Ter Stegen"));
        match = new Match("Barcelona", "Cadiz", 3, 1, teamLineup);
    }

    @Test
    public void testConstructor(){
//        assertEquals(1, match.getTeamLineup());
        assertEquals("Barcelona", match.getHomeTeam());
        assertEquals("Cadiz", match.getAwayTeam());
        assertEquals(3, match.getHomeScore());
        assertEquals(1, match.getAwayScore());
    }

    @Test
    public void testChangeAwayScoreAt0() {
        match.changeAwayScore(0);
        assertEquals(0, match.getAwayScore());
    }

    @Test
    public void testChangeAwayScoreAtAnyValue() {
        match.changeAwayScore(5);
        assertEquals(5, match.getAwayScore());
    }

    @Test
    public void testChangeAwayScoreMultipleTimes() {
        match.changeAwayScore(0);
        match.changeAwayScore(5);
        assertEquals(5, match.getAwayScore());
    }

    @Test
    public void testChangeHomeScoreAt0() {
        match.changeHomeScore(0);
        assertEquals(0, match.getHomeScore());
    }

    @Test
    public void testChangeHomeScoreAtAnyValue() {
        match.changeHomeScore(5);
        assertEquals(5, match.getHomeScore());
    }

    @Test
    public void testChangeHomeScoreMultipleTimes() {
        match.changeHomeScore(3);
        match.changeHomeScore(190);
        assertEquals(190, match.getHomeScore());
    }

    @Test
    public void testChangeHomeTeam() {
        match.changeHomeTeam("Real Madrid");
        assertEquals("Real Madrid", match.getHomeTeam());
    }

    @Test
    public void testChangeAwayTeam() {
        match.changeAwayTeam("Real Madrid");
        assertEquals("Real Madrid", match.getAwayTeam());
    }

//    @Test
//    public void testUpdateLineup() {
        //   stub
//    }



}