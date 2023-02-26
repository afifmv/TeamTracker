package ui;

import model.Player;
import model.Team;

import java.util.List;
import java.util.Scanner;

public class TeamTracker {
    private Scanner input;
    private final Team team = new Team();
    private Player selectedPlayer;

    // EFFECTS: runs the teller application
    public TeamTracker() {
        runTeamTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTeamTracker() {
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

        System.out.println("\nGoodbye! Hope you enjoyed your time at TeamTracker");
    }

    private void processCommand(String command) {
        if (command.equals("a")) {
            displayAddMenu();

        } else if (command.equals("v")) {
            displayViewMenu();
        } else if (command.equals("c")) {
            displayChangeMenu();
        } else if (command.equals("r")) {
            displayRemoveMenu();
        }
    }


    private void displayMainMenu() {
        System.out.println("\nWelcome to TeamTracker, an efficient way to manage your "
                + "team and get the player for each stat!");
        System.out.println("To add a player, click a");
        System.out.println("To view existing players, click v");
        System.out.println("To change a player's stats, click c");
        System.out.println("To remove a player, click r");
        System.out.println("To quit, click q");
    }

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

    private void displayViewMenu() {
        List<String> listOfPlayers = this.team.returnsNamesOfTeam();
        int count = 1;
        for (String name: listOfPlayers) {
            System.out.println(count + ". " + name);
            count++;
        }
    }

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

    private void displayRemoveMenu() {
        displayViewMenu();
        System.out.println("Please choose the player you wish to remove by entering the corresponding "
                + "number of the player.");

        int command = Integer.parseInt(input.next()) - 1;
        this.team.removePlayer(command);
    }

    private int statToChangePrompt() {
        String command;
        System.out.println("Enter the number you wish to change to");
        command = input.next();
        return Integer.parseInt(command);
    }

    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }
}
