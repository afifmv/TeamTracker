package ui;

import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.awt.Font.PLAIN;

public class TeamTrackerGUI extends JFrame {
    private static final int width = 600;
    private static final int height = 600;

    private JPanel loadingMenu = new JPanel();
    private JPanel mainMenu = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel();
    private JPanel addPlayerPanel = new JPanel(new FlowLayout());

    private Player player;
    private Team team = new Team();

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/team.json";


    public TeamTrackerGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        
        setTitle("Team Tracker");
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());
        
        createLoadingPanel();
        createMainMenu();
        createAddPlayerMenu();

        loadingMenu.setVisible(true);
        addPlayerPanel.setVisible(false);
    }

    private void createMainMenu() {
        displayButtonsOnTheSide();
        add(mainMenu);
        mainMenu.setVisible(false);
    }

    private void createLoadingPanel() {
        JLabel welcomeMessage = new JLabel("Welcome to Team Tracker");
        welcomeMessage.setFont(new Font("Times New Roman", PLAIN, 20));
        welcomeMessage.setBorder(BorderFactory.createEmptyBorder(0, (width / 3) + 10,0,0));

        loadingMenu.setLayout(new BorderLayout());
        loadingMenu.add(welcomeMessage, BorderLayout.NORTH);

        JButton button = new JButton("Click Me!");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadingMenu.setVisible(false);
                mainMenu.setVisible(true);
            }
        });

        loadingMenu.add(button, BorderLayout.PAGE_END);
        add(loadingMenu);

        loadingMenu.setVisible(false);
    }

    private void displayButtonsOnTheSide() {
        int buttonXcoordinate = 10;

        JButton addPlayer = getjButton(buttonXcoordinate, 10, "Add Player");
        JButton viewTeam = getjButton(buttonXcoordinate, 60, "View Team");
        JButton saveTeam = getjButton(buttonXcoordinate, 110, "Save Team");
        JButton loadTeam = getjButton(buttonXcoordinate, 160, "Load Team");


//        buttonPanel.setLayout(new GridLayout());
        buttonPanel.add(Box.createVerticalStrut(10));

        setButtonActions(addPlayer, viewTeam, saveTeam, loadTeam, buttonPanel);

        mainMenu.add(buttonPanel, BorderLayout.CENTER);

    }

    private void setButtonActions(JButton addPlayer, JButton viewTeam, JButton saveTeam,
                                  JButton loadTeam, JPanel buttonPanel) {
        addPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.setVisible(false);
                addPlayer.setVisible(true);
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

        buttonPanel.add(addPlayer, BorderLayout.CENTER);
        buttonPanel.add(viewTeam, BorderLayout.CENTER);
        buttonPanel.add(saveTeam, BorderLayout.CENTER);
        buttonPanel.add(loadTeam, BorderLayout.CENTER);
    }

    private void createAddPlayerMenu() {
        JTextField nameField = displayInputsForAddingPlayer(addPlayerPanel, "Name: ");
        JTextField defendingField = displayInputsForAddingPlayer(addPlayerPanel, "Defending: ");
        JTextField physicalityField = displayInputsForAddingPlayer(addPlayerPanel, "Physicality: ");
        JTextField attackingField = displayInputsForAddingPlayer(addPlayerPanel, "Attacking: ");
        JTextField paceField = displayInputsForAddingPlayer(addPlayerPanel, "Pace: ");

        JButton submitPlayer = createPlayerButton(nameField, defendingField, physicalityField,
                attackingField, paceField);

        addPlayerPanel.add(submitPlayer);
        add(addPlayerPanel);
        addPlayerPanel.setVisible(false);
    }

    private JButton createPlayerButton(JTextField nameField, JTextField defendingField,
                                       JTextField physicalityField, JTextField attackingField, JTextField paceField) {
        JButton submitPlayer = new JButton("Add Player!");
        submitPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int defending = Integer.parseInt(defendingField.getText());
                int physicality = Integer.parseInt(physicalityField.getText());
                int attacking = Integer.parseInt(attackingField.getText());
                int pace = Integer.parseInt(paceField.getText());

                player = new Player(name, physicality, defending, attacking, pace);
                team.addPlayer(player);

            }
        });
        return submitPlayer;
    }

    private JTextField displayInputsForAddingPlayer(JPanel panel, String input) {
        JLabel inputLabel = new JLabel(input);
        JTextField inputTextField = new JTextField(5);

        panel.add(inputLabel, BorderLayout.WEST);
        panel.add(inputTextField,  BorderLayout.WEST);

        return inputTextField;
    }

    private JButton getjButton(int buttonXcoordinate, int ycoordinate, String foo) {
        JButton addPlayer = new JButton(foo);
        addPlayer.setPreferredSize(new Dimension(400, 50));
        addPlayer.setBounds(buttonXcoordinate, ycoordinate, 100, 50);
        return addPlayer;
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
