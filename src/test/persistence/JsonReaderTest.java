package persistence;

import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
;import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderFileDoesNotExist() {
        JsonReader jsonReader = new JsonReader("./data/fooFile");
        try {
            Team team = jsonReader.read();
            fail("IO exception expected");
        } catch (IOException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testReaderEmptyTeam() {
        JsonReader jsonReader = new JsonReader("./data/testReaderEmptyTeam.json");
        try {
            Team team = jsonReader.read();
            ArrayList<Player> emptyList = new ArrayList<Player>();
            assertEquals(emptyList, team.getPlayers());
        } catch (IOException e) {
            fail("Unable to read file");
        }
    }

    @Test
    public void testReaderNormalTeam() {
        JsonReader jsonReader = new JsonReader("./data/testReaderNormalTeam");
        try {
            Team team = jsonReader.read();
            LinkedList<Player> checkList = team.getPlayers();
            assertEquals(2, team.getSize());
            checkPlayer("Lionel", 45, 23, 34, 56, checkList.get(0));
            checkPlayer("Afif", 56, 23, 34, 78, checkList.get(1));
        } catch (IOException e) {
            fail("Unable to read file");
        }
    }
}
