package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    Player player1;
    Player player2;
    Team team;
    Team bigTeam;

    @BeforeEach
    public void setup() {
        player1 = new Player("Afif", 99, 98, 44, 22);
        player2 = new Player("Azim", 97, 34, 32, 21);
        team = new Team();
        bigTeam = new Team();
        bigTeam.addPlayer(player1);
        bigTeam.addPlayer(player2);
    }

    @Test
    public void testConstructor() {
        assertEquals(new ArrayList<>(), team.getPlayers());
    }

    @Test
    public void testAddOnePlayer() {
        assertTrue(team.addPlayer(player1));
        assertEquals(1,team.getSize());
    }

    @Test
    public void testReturnHighestAttribute() {
        assertEquals("Afif", bigTeam.returnHighestPhysicalityPlayer());
        assertEquals("Afif", bigTeam.returnHighestDefendingPlayer());
        assertEquals("Afif", bigTeam.returnHighestAttackingPlayer());
        assertEquals("Afif", bigTeam.returnHighestPacePlayer());
    }

    @Test
    public void testReturnTopPlayer() {
        assertEquals("Afif", bigTeam.returnBestPlayer());
    }

    @Test
    public void testReturnsNamesOfGivenList() {
        LinkedList<String> assertList = new LinkedList<>();
        assertList.add("Afif");
        assertList.add("Azim");
        assertEquals(assertList, bigTeam.returnsNamesOfTeam());
    }

    @Test
    public void testReturnPlayersAt1() {
        LinkedList<Player> assertList = new LinkedList<>();
        assertList.add(player1);
        team.addPlayer(player1);
        assertEquals(assertList, this.team.returnsPlayers());
    }

    @Test
    public void testReturnPlayersAtAnyValue() {
        LinkedList<Player> assertList = new LinkedList<>();
        assertList.add(player1);
        assertList.add(player2);
        assertEquals(assertList, this.bigTeam.returnsPlayers());
    }

    @Test
    public void testRemovePlayer() {
        assertTrue(this.bigTeam.removePlayer(0));
        LinkedList<Player> assertList = new LinkedList<>();
        assertList.add(player2);
        assertEquals(assertList, this.bigTeam.returnsPlayers());
    }








}
