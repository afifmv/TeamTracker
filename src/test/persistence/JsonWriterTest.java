package persistence;

import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// JsonSerializationDemo. (2021). Retrieved March 13, 2023, from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterOnInvalidFileName() {
        try {
            Team team = new Team();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was not raised");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyTeam() {
        try {
            String source = "./data/testWriterEmptyTeam.json";
            Team team = new Team();

            JsonWriter jsonWriter = new JsonWriter(source);

            jsonWriter.open();
            jsonWriter.write(team);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader(source);
            team = jsonReader.read();
            assertEquals(0, team.getSize());

        } catch (FileNotFoundException e) {
            fail("File not found");
        } catch (IOException e) {
            fail("Unable to open file");
        }


    }

    @Test
    public void testWriterNormalTeam() {
        try {
            String source = "./data/testWriterNormalTeam.json";
            Team team = new Team();
            Player player1 = new Player("Lionel", 45, 23, 34, 56);
            Player player2 = new Player("Afif", 56, 23, 34, 78);
            team.addPlayer(player1);
            team.addPlayer(player2);

            JsonWriter jsonWriter = new JsonWriter(source);

            jsonWriter.open();
            jsonWriter.write(team);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader(source);
            team = jsonReader.read();
            LinkedList<Player> checkList = team.getPlayers();
            assertEquals(2, team.getSize());
            checkPlayer("Lionel", 45, 23, 34, 56, checkList.get(0));
            checkPlayer("Afif", 56, 23, 34, 78, checkList.get(1));

        } catch (FileNotFoundException e) {
            fail("File not found");
        } catch (IOException e) {
            fail("Unable to open file");
        }


    }

}
