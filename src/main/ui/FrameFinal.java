package ui;

import model.Event;
import model.EventLog;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// a class
public class FrameFinal extends JFrame {
    private static final int width = 600;
    private static final int height = 600;


    private Player player;
    private Team team = new Team();

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/team.json";

    private ImageIcon image = new ImageIcon("./fcm_rgbf_s.png");
    private EventLog eventLog = EventLog.getInstance();


    // MODIFIES: this
    // EFFECTS: begins the GUI application
    public FrameFinal() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                for (Event event: eventLog) {
                    System.out.println(event);
                }

                System.exit(0);
            }
        });



        setTitle("Team Tracker");
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        createMainMenu();
    }


    // MODIFIES: this
    // EFFECTS: creates a main menu by adding the appropriate buttons
    private void createMainMenu() {
        createAndAddButtons();
    }


    // MODIFIES: this
    // EFFECTS: creates the buttons for the main menu and adds to the main frame
    private void createAndAddButtons() {
        JButton addPlayer = new JButton("Add Player");
        JButton viewPlayer = new JButton("View Players");
        JButton bestPlayer = new JButton("Best Player");
        JButton saveTeam = new JButton("Save Team");
        JButton loadTeam = new JButton("Load team");

        add(viewPlayer);
        add(addPlayer);
        add(bestPlayer);
        add(saveTeam);
        add(loadTeam);

        setActions(addPlayer, viewPlayer, saveTeam, loadTeam, bestPlayer);
    }


    // MODIFIES: this
    // EFFECTS: gives the main menu buttons the appropriate actions
    private void setActions(JButton addPlayer, JButton viewPlayer, JButton saveTeam, JButton loadTeam,
                            JButton bestPlayer) {
        addPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayerActionListener();
            }
        });

        saveTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTeam();
            }
        });

        loadTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTeam();
            }
        });

        setActionsExtension(viewPlayer, bestPlayer);

    }

    // EFFECTS: sets the remaining button actions on the main menu
    private void setActionsExtension(JButton viewPlayer, JButton bestPlayer) {
        viewPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTeamActionListener();
            }
        });

        bestPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bestPlayerActionListener();
            }
        });
    }


    // EFFECTS: creates a pop up frame to display the best player for each stat
    private void bestPlayerActionListener() {
        JFrame bestPlayerPopUp = createPopUpFrame();
        bestPlayerPopUp.setSize(width, height * 2);

        JButton bestPhysicality = new JButton("Best Physicality");
        JButton bestDefending = new JButton("Best Defending");
        JButton bestAttacking = new JButton("Best Attacking");
        JButton bestPace = new JButton("Best Pace");
        JLabel imageLabel = new JLabel(image);

        bestPlayerPopUp.add(bestPhysicality);
        bestPlayerPopUp.add(bestDefending);
        bestPlayerPopUp.add(bestAttacking);
        bestPlayerPopUp.add(bestPace);
        bestPlayerPopUp.add(imageLabel);

        setBestPlayerForSpecificStatButtonAction(bestPhysicality, bestDefending, bestAttacking,
                bestPace);

        bestPlayerPopUp.setVisible(true);
    }


    // EFFECTS: sets the button actions on the best player popup frame
    private void setBestPlayerForSpecificStatButtonAction(JButton bestPhysicality, JButton bestDefending,
                                                          JButton bestAttacking, JButton bestPace) {

        bestPhysicality.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame displayPlayerFrame = createPopUpFrame();
                displayPlayerFrame.setSize(new Dimension(width / 4, height / 4));
                String name = team.returnHighestPhysicalityPlayer();
                displayLabelOnPopUpForBestStat(name, displayPlayerFrame);
            }
        });
        bestDefending.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame displayPlayerFrame = createPopUpFrame();
                displayPlayerFrame.setSize(new Dimension(width / 4, height / 4));
                String name = team.returnHighestDefendingPlayer();
                displayLabelOnPopUpForBestStat(name, displayPlayerFrame);
            }
        });
        setBestPlayerForSpecificStatButtonActionExtension(bestAttacking, bestPace);
    }


    // EFFECTS: sets the remaining button actions on the best player popup frame
    private void setBestPlayerForSpecificStatButtonActionExtension(JButton bestAttacking, JButton bestPace) {
        bestAttacking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame displayPlayerFrame = createPopUpFrame();
                displayPlayerFrame.setSize(new Dimension(width / 4, height / 4));
                String name = team.returnHighestAttackingPlayer();
                displayLabelOnPopUpForBestStat(name, displayPlayerFrame);
            }
        });
        bestPace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame displayPlayerFrame = createPopUpFrame();
                displayPlayerFrame.setSize(new Dimension(width / 4, height / 4));
                String name = team.returnHighestPacePlayer();
                displayLabelOnPopUpForBestStat(name, displayPlayerFrame);
            }
        });
    }

    // EFFECTS: displays a label on the displayPlayerFrame
    private void displayLabelOnPopUpForBestStat(String name, JFrame displayPlayerFrame) {
        JLabel nameLabel = new JLabel(name);
        displayPlayerFrame.add(nameLabel);
        displayPlayerFrame.setVisible(true);
    }

    // EFFECTS: creates a pop up frame and makes it visible
    private void viewTeamActionListener() {
        JFrame viewPlayerPopUp = createPopUpFrame();

        displayPlayers(viewPlayerPopUp);

        viewPlayerPopUp.setVisible(true);
    }


    // EFFECTS: returns the players all listen on label on the given frame
    private void displayPlayers(JFrame viewPlayerPopUp) {
        LinkedList<Player> players = team.returnsPlayers();

        for (Player currentPlayer: players) {
            JLabel playerName = new JLabel(currentPlayer.getName());
            viewPlayerPopUp.add(playerName);
        }
    }

    // EFFECTS: the functionality of the add Player main menu option,
    // adds a players to the team with the given stats in the text boxes
    private void addPlayerActionListener() {
        JFrame addPlayerPopUp = createPopUpFrame();

        JTextField nameField = createLabelAndTextField(addPlayerPopUp, "Name");
        JTextField physicalityField = createLabelAndTextField(addPlayerPopUp, "Physicality");
        JTextField defendingField = createLabelAndTextField(addPlayerPopUp, "Defending");
        JTextField attackingField = createLabelAndTextField(addPlayerPopUp, "Attacking");
        JTextField paceField = createLabelAndTextField(addPlayerPopUp, "Pace");

        JLabel empty = new JLabel();
        JButton submitAddPlayer = new JButton("Add Player");
        submitAddPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPlayerAndAddToTeam(nameField, physicalityField, defendingField, attackingField, paceField);
                addPlayerPopUp.setVisible(false);
            }
        });



        addPlayerPopUp.add(empty);
        addPlayerPopUp.add(submitAddPlayer);

        addPlayerPopUp.setVisible(true);
    }

    // EFFECTS: creates a pop up frame, sets it up with the appropriate settings and returns it
    private JFrame createPopUpFrame() {
        JFrame addPlayerPopUp = new JFrame();
        addPlayerPopUp.getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        addPlayerPopUp.setLayout(new GridLayout(6, 2, 0, 0));
        addPlayerPopUp.setSize(width, height / 2);
        return addPlayerPopUp;
    }

    // MODIFIES: this
    // EFFECTS: creates a player with the given inputs from the fields and adds to team
    private void createPlayerAndAddToTeam(JTextField nameField, JTextField physicalityField, JTextField defendingField,
                                          JTextField attackingField, JTextField paceField) {
        String name = nameField.getText();
        int physicality = Integer.parseInt(physicalityField.getText());
        int defending = Integer.parseInt(defendingField.getText());
        int attacking = Integer.parseInt(attackingField.getText());
        int pace = Integer.parseInt(paceField.getText());

        player = new Player(name, physicality, defending, attacking, pace);
        team.addPlayer(player);


    }

    // EFFECTS: creates a label with the given a message and adds it to the given frame
    private JTextField createLabelAndTextField(JFrame addPlayerPopUp, String message) {
        JLabel inputLabel = new JLabel(message);
        JTextField inputTextField = new JTextField(5);

        addPlayerPopUp.add(inputLabel);
        addPlayerPopUp.add(inputTextField);

        return inputTextField;
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
