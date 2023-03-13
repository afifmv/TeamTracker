package persistence;

import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

// JsonSerializationDemo. (2021). Retrieved March 13, 2023, from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonTest {
    protected void checkPlayer(String name, int physicality, int defending, int attacking, int pace, Player player) {
        assertEquals(name, player.getName());
        assertEquals(physicality, player.getPhysicality());
        assertEquals(defending, player.getDefending());
        assertEquals(attacking, player.getAttacking());
        assertEquals(pace, player.getPace());
    }
}
