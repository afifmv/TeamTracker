package persistence;

import model.Player;
import model.Team;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// JsonSerializationDemo. (2021). Retrieved March 13, 2023, from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads team from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads team from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Team read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeam(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses team from JSON object and returns it
    private Team parseTeam(JSONObject jsonObject) {
        Team team = new Team();
        addPlayers(team, jsonObject);
        return team;
    }

    // MODIFIES: team
    // EFFECTS: parses players from JSON object and adds them to workroom
    private void addPlayers(Team team, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(team, nextPlayer);
        }
    }

    // MODIFIES: team
    // EFFECTS: parses player from JSON object and adds it to workroom
    private void addPlayer(Team team, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int physicality = jsonObject.getInt("physicality");
        int attacking = jsonObject.getInt("attacking");
        int defending = jsonObject.getInt("defending");
        int pace = jsonObject.getInt("pace");
        Player player = new Player(name, physicality, defending, attacking, pace);
        team.addPlayer(player);
    }
}
