package ui;

import model.Player;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import persistence.JsonReader;
import persistence.JsonWriter;

import static java.awt.Font.PLAIN;

public class MyFrame extends JFrame {
    private static final int width = 600;
    private static final int height = 600;

    private Player player;
    private Team team = new Team();

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/team.json";


    public MyFrame() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setTitle("Team Tracker");
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayLoadingMenu();

    }

    private void displayLoadingMenu() {
        JLabel welcomeMessage = new JLabel("Welcome to Team Tracker");
        welcomeMessage.setFont(new Font("Times New Roman", PLAIN, 20));
        welcomeMessage.setBorder(BorderFactory.createEmptyBorder(0, (width / 3),0,0));
        add(welcomeMessage, BorderLayout.CENTER);

        JButton button = new JButton("Click Me!");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMainMenu();
            }
        });
        add(button, BorderLayout.PAGE_END);
    }

    private void showMainMenu() {
        hideComponents();
        displayButtonsOnTheSide();
        displayHeaderAtTop();
    }

    private void displayHeaderAtTop() {
        JPanel fooFrame = new JPanel();
        JLabel headerMessage = new JLabel("TeamTracker");

        fooFrame.add(headerMessage, BorderLayout.NORTH);
        add(fooFrame, BorderLayout.NORTH);
    }

    private void displayButtonsOnTheSide() {
        int buttonXcoordinate = 10;

        JButton addPlayer = getjButton(buttonXcoordinate, 10, "Add Player");
        JButton viewTeam = getjButton(buttonXcoordinate, 60, "View Team");
        JButton saveTeam = getjButton(buttonXcoordinate, 110, "Save Team");
        JButton loadTeam = getjButton(buttonXcoordinate, 160, "Load Team");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setLayout(null);

        buttonPanel.add(Box.createVerticalStrut(10));

        buttonPanel.add(addPlayer, BorderLayout.WEST);
        setButtonActions(addPlayer, viewTeam, saveTeam, loadTeam, buttonPanel);

        this.add(buttonPanel);


    }

    private void setButtonActions(JButton addPlayer, JButton viewTeam, JButton saveTeam,
                                  JButton loadTeam, JPanel buttonPanel) {
        addPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayerMenu();
            }
        });

        buttonPanel.add(viewTeam);
        buttonPanel.add(saveTeam);
        saveTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTeam();
            }
        });

        buttonPanel.add(loadTeam);
        loadTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTeam();
            }
        });
    }

    private JButton getjButton(int buttonXcoordinate, int ycoordinate, String foo) {
        JButton addPlayer = new JButton(foo);
        addPlayer.setPreferredSize(new Dimension(400, 50));
        addPlayer.setBounds(buttonXcoordinate, ycoordinate, 100, 50);
        return addPlayer;
    }

    private void addPlayerMenu() {
        hideComponents();


        this.setLayout(new FlowLayout());
        JPanel panel = new JPanel(new GridLayout(5, 5));

        JTextField nameField = displayInputsForAddingPlayer(panel, "Name: ");
        JTextField defendingField = displayInputsForAddingPlayer(panel, "Defending: ");
        JTextField physicalityField = displayInputsForAddingPlayer(panel, "Physicality: ");
        JTextField attackingField = displayInputsForAddingPlayer(panel, "Attacking: ");
        JTextField paceField = displayInputsForAddingPlayer(panel, "Pace: ");

        JButton submitPlayer = createPlayerButton(nameField, defendingField, physicalityField,
                attackingField, paceField);

        add(submitPlayer);
        add(panel);
//
//        add(namePanel, BorderLayout.NORTH);

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

                showMainMenu();
            }
        });
        return submitPlayer;
    }

    private JTextField displayInputsForAddingPlayer(JPanel panel, String input) {
        JLabel inputLabel = new JLabel(input);
        JTextField inputTextField = new JTextField(5);

        panel.add(inputLabel);
        panel.add(inputTextField);

        return inputTextField;
    }

    private void hideComponents() {
        Container components = this.getContentPane();
//        getContentPane().removeAll();
//        this.revalidate();
//        this.repaint();
//
        for (int i = 0; i < components.getComponentCount();i++) {
            Component component = components.getComponent(i);
            component.setVisible(false);
        }
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
