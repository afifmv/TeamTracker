package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    Player player;
    Player testPlayer;

    @BeforeEach
    public void setup() {
        ArrayList<String> teamLineup;
        teamLineup = new ArrayList<>();
        teamLineup.addAll(Arrays.asList("Messi","Ter Stegen"));
        player = new Player("Afif",98,78,34,22);
    }

    @Test
    public void testConstructorAtRandomStats(){
//        assertEquals(1, match.getTeamLineup());
        assertEquals("Afif", player.getName());
        assertEquals(98, player.getPhysicality());
        assertEquals(78, player.getDefending());
        assertEquals(34, player.getAttacking());
        assertEquals(22, player.getPace());
        assertEquals(98+78+34+22,player.getOverallStats());
    }

    @Test
    public void testConstructorAt0(){
//        assertEquals(1, match.getTeamLineup());
        testPlayer = new Player("Martha",0,0,0,0);
        assertEquals("Martha", testPlayer.getName());
        assertEquals(0, testPlayer.getPhysicality());
        assertEquals(0, testPlayer.getDefending());
        assertEquals(0, testPlayer.getAttacking());
        assertEquals(0, testPlayer.getPace());
        assertEquals(0, testPlayer.getOverallStats());
    }

    @Test
    public void testConstructorAt99(){
//        assertEquals(1, match.getTeamLineup());
        testPlayer = new Player("Martha",99,99,99,99);
        assertEquals("Martha", testPlayer.getName());
        assertEquals(99, testPlayer.getPhysicality());
        assertEquals(99, testPlayer.getDefending());
        assertEquals(99, testPlayer.getAttacking());
        assertEquals(99, testPlayer.getPace());
        assertEquals(99*4, testPlayer.getOverallStats());
    }

    @Test
    public void testChangePhysicalityAt0() {
        player.changePhysicality(0);
        assertEquals(0,player.getPhysicality());
    }

    @Test
    public void testChangePhysicalityAtAnyValue() {
        player.changePhysicality(10);
        assertEquals(10,player.getPhysicality());
    }

    @Test
    public void testChangePhysicalityMultipleTimes() {
        player.changePhysicality(10);
        player.changePhysicality(44);
        assertEquals(44,player.getPhysicality());
    }

    @Test
    public void testChangeAttackingAt0() {
        player.changeAttacking(0);
        assertEquals(0,player.getAttacking());
    }

    @Test
    public void testChangeAttackingAtAnyValue() {
        player.changeAttacking(10);
        assertEquals(10,player.getAttacking());
    }

    @Test
    public void testChangeAttackingMultipleTimes() {
        player.changeAttacking(10);
        player.changeAttacking(44);
        assertEquals(44,player.getAttacking());
    }

    @Test
    public void testChangePaceAt0() {
        player.changePace(0);
        assertEquals(0,player.getPace());
    }

    @Test
    public void testChangePaceAtAnyValue() {
        player.changePace(10);
        assertEquals(10,player.getPace());
    }

    @Test
    public void testChangePaceMultipleTimes() {
        player.changePace(10);
        player.changePace(44);
        assertEquals(44,player.getPace());
    }

    @Test
    public void testChangeDefendingAt0() {
        player.changeDefending(0);
        assertEquals(0,player.getDefending());
    }

    @Test
    public void testChangeDefendingAtAnyValue() {
        player.changeDefending(10);
        assertEquals(10,player.getDefending());
    }

    @Test
    public void testChangeDefendingMultipleTimes() {
        player.changeDefending(10);
        player.changeDefending(44);
        assertEquals(44,player.getDefending());
    }

    @Test
    public void testChangePlayerName() {
        player.changeName("Azim");
        assertEquals("Azim", player.getName());
    }










}