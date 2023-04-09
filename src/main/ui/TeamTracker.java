package ui;

import model.Event;
import model.EventLog;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TeamTracker {
    private static final String JSON_STORE = "./data/team.json";
    private Scanner input;
    private Team team;
    private Player selectedPlayer;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private EventLog eventLog = EventLog.getInstance();

    // EFFECTS: runs the team application
    public TeamTracker() throws FileNotFoundException {
        runTeamTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTeamTracker() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        for (Event e: eventLog) {
            System.out.println(e);
        }

        System.out.println("\nGoodbye! Hope you enjoyed your time at TeamTracker");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            displayAddMenu();

        } else if (command.equals("v")) {
            displayViewMenu();
        } else if (command.equals("c")) {
            displayChangeMenu();
        } else if (command.equals("r")) {
            displayRemoveMenu();
        } else if (command.equals("t")) {
            displayBestStatMenu();
        } else if (command.equals("z")) {
            displaySpecificPlayerStatsMenu();
        } else if (command.equals("s")) {
            saveTeam();
        } else if (command.equals("l")) {
            loadTeam();
        } else {
            System.out.println("Invalid Selection");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMainMenu() {
        System.out.println("\nWelcome to TeamTracker, an efficient way to manage your "
                + "team and get the player for each stat!");
        System.out.println("To add a player, click a");
        System.out.println("To view existing players, click v");
        System.out.println("To change a player's stats, click c");
        System.out.println("To remove a player, click r");
        System.out.println("To view the stats of a specific player, click z");
        System.out.println("To return the best player for a specific stat, click t");
        System.out.println("To save team to file, type s");
        System.out.println("To load work from file, l");
        System.out.println("To quit, click q");
    }

    // EFFECTS: displays the add player menu to user
    private void displayAddMenu() {
        String command;

        System.out.println("Enter the player's name: ");
        command = input.next();
        String name = command;

        System.out.println("Enter the player's defending: ");
        int defending = Integer.parseInt(input.next());

        System.out.println("Enter the player's attacking: ");
        int attacking = Integer.parseInt(input.next());

        System.out.println("Enter the player's physicality: ");
        int physicality = Integer.parseInt(input.next());

        System.out.println("Enter the player's pace: ");
        int pace = Integer.parseInt(input.next());

        selectedPlayer = new Player(name, physicality, defending, attacking, pace);

        System.out.println("Successfully added " + selectedPlayer.getName() +  " to team!");
        this.team.addPlayer(selectedPlayer);
    }

    // EFFECTS: displays all the players names numbered
    private void displayViewMenu() {
        List<String> listOfPlayers = this.team.returnsNamesOfTeam();
        int count = 1;
        for (String name: listOfPlayers) {
            System.out.println(count + ". " + name);
            count++;
        }
    }

    // EFFECTS: displays the menu for changing a player's stats
    private void displayChangeMenu() {
        String command;

        System.out.println("Please choose the player you wish to change the stats of by entering the corresponding "
                + "number of the player.");
        List<Player> listOfPlayers = this.team.returnsPlayers();
        int count = 1;
        for (Player player: listOfPlayers) {
            System.out.println(count + ". " + player.getName());
            count++;
        }
        command = input.next();
        int indexToRetrieve = Integer.parseInt(command) - 1;
        selectedPlayer = listOfPlayers.get(indexToRetrieve);

        System.out.println("Choose the number for what you wish to change: \n1. Defending "
                +
                "\n2. Attacking \n3. Physicality \n4. Pace \n5. Name ");

        command = input.next();
        displayChangePlayerSubMenu(command);

    }

    // EFFECTS: displays the menu displaying the choice to choose a specific stat to be changed
    private void displayChangePlayerSubMenu(String command) {
        if (command.equals("1")) {
            selectedPlayer.changeDefending(statToChangePrompt());
        } else if (command.equals("2")) {
            int statToChange = statToChangePrompt();
            selectedPlayer.changeDefending((statToChange));
            System.out.println("Successfully changed player stat to " + statToChange);
        } else if (command.equals("3")) {
            int statToChange = statToChangePrompt();
            selectedPlayer.changeDefending(statToChange);
            System.out.println("Successfully changed player stat to " + statToChange);
        } else if (command.equals("4")) {
            int statToChange = statToChangePrompt();
            selectedPlayer.changeDefending(statToChange);
            System.out.println("Successfully changed player stat to " + statToChange);
        } else if (command.equals("5")) {
            System.out.println("Enter the name you wish to change to");
            command = input.next();
            selectedPlayer.changeName(command);
            System.out.println("Successfully changed player name to" + command);
        }
    }

    // EFFECTS: displays the menu for removing a player from team
    private void displayRemoveMenu() {
        displayViewMenu();
        System.out.println("Please choose the player you wish to remove by entering the corresponding "
                + "number of the player.");

        int command = Integer.parseInt(input.next()) - 1;
        this.team.removePlayer(command);
    }

    // EFFECTS: displays the menu for changing a player's stats
    private void displayBestStatMenu() {
        System.out.println("Enter the corresponding for the best stat for each player");
        System.out.println("1. Defending");
        System.out.println("2. Attacking");
        System.out.println("3. Physicality");
        System.out.println("4. Pace");
        String command = input.next();

        if (command.equals("1")) {
            String bestPlayer = team.returnHighestDefendingPlayer();
            System.out.println(bestPlayer + " is the best defender");
        } else if (command.equals("2")) {
            String bestPlayer = team.returnHighestAttackingPlayer();
            System.out.println(bestPlayer + " is the best attacker");
        } else if (command.equals("3")) {
            String bestPlayer = team.returnHighestPhysicalityPlayer();
            System.out.println(bestPlayer + " is the strongest player");
        } else if (command.equals("4")) {
            String bestPlayer = team.returnHighestPacePlayer();
            System.out.println(bestPlayer + " is the fastest player");
        }
    }

    // EFFECTS: displays the menu for choosing to display the stats of a specific player
    private void displaySpecificPlayerStatsMenu() {
        String command;

        System.out.println("Please choose the player you wish to view the stats of by entering the corresponding "
                + "number of the player.");
        List<Player> listOfPlayers = this.team.returnsPlayers();
        int count = 1;
        for (Player player: listOfPlayers) {
            System.out.println(count + ". " + player.getName());
            count++;
        }
        command = input.next();
        int indexToRetrieve = Integer.parseInt(command) - 1;
        selectedPlayer = listOfPlayers.get(indexToRetrieve);

        System.out.println(selectedPlayer.getName() + " stats are as follows,");
        System.out.println("Defending: " + selectedPlayer.getDefending());
        System.out.println("Attacking: " + selectedPlayer.getAttacking());
        System.out.println("Physicality: " + selectedPlayer.getPhysicality());
        System.out.println("Pace: " + selectedPlayer.getPace());
    }

    // EFFECTS: returns an int that is used for changing the stat
    private int statToChangePrompt() {
        String command;
        System.out.println("Enter the number you wish to change to");
        command = input.next();
        return Integer.parseInt(command);
    }

    // MODIFIES: this
    // EFFECTS: initializes a new team
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        team = new Team();
    }

    // EFFECTS: saves the team to file
    private void saveTeam() {
        try {
            jsonWriter.open();
            jsonWriter.write(team);
            jsonWriter.close();
            System.out.println("Saved Team succesfully to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads team from file
    private void loadTeam() {
        try {
            team = jsonReader.read();
            System.out.println("Loaded Team successfully from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
