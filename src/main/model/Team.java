package model;


import java.util.LinkedList;

// Team is a class that consists of multiple Player, i.e a collection of Player
public class Team {
    LinkedList<Player> players;

    // EFFECTS: instantiates a new team with an empty list of players
    public Team() {
        this.players = new LinkedList<>();
    }

    // REQUIRES: Player that does not exist already in team
    // MODIFIES: this
    // EFFECTS: adds player and returns true
    public boolean addPlayer(Player requiredPlayer) {
        players.add(requiredPlayer);
        return true;
    }

    // REQUIRES: Non-empty team
    // EFFECTS: returns the first player name with the highest physicality in the team.
    public String returnHighestPhysicalityPlayer() {
        String returnPlayer = "";
        int highestPhysicality = 0;
        for (Player player: this.players) {
            if (player.getPhysicality() > highestPhysicality) {
                highestPhysicality = player.getPhysicality();
                returnPlayer = player.getName();
            }
        }
        return returnPlayer;
    }

    // REQUIRES: Non-empty team
    // EFFECTS: returns the first player name with the highest pace in the team.
    public String  returnHighestPacePlayer() {
        String returnPlayer = "";
        int highestPace = 0;
        for (Player player: this.players) {
            if (player.getPace() > highestPace) {
                highestPace = player.getPace();
                returnPlayer = player.getName();
            }
        }
        return returnPlayer;
    }

    // REQUIRES: Non-empty team
    // EFFECTS: returns the first player name with the highest attacking in the team.
    public String returnHighestAttackingPlayer() {
        String returnPlayer = "";
        int highestAttacking = 0;
        for (Player player: this.players) {
            if (player.getAttacking() > highestAttacking) {
                highestAttacking = player.getAttacking();
                returnPlayer = player.getName();
            }
        }
        return returnPlayer;
    }

    // REQUIRES: Non-empty team
    // EFFECTS: returns the first player name with the highest defending in the team.
    public String returnHighestDefendingPlayer() {
        String returnPlayer = "";
        int highestDefending = 0;
        for (Player player: this.players) {
            if (player.getDefending() > highestDefending) {
                highestDefending = player.getDefending();
                returnPlayer = player.getName();
            }
        }
        return returnPlayer;
    }

    // EFFECTS: returns the first player name with the highest overall stats in the team.
    public String returnBestPlayer() {
        String returnPlayer = "";
        int highestOverallRating = 0;
        int currentRating;
        for (Player player : this.players) {
            currentRating = player.getOverallStats();
            if (currentRating > highestOverallRating) {
                highestOverallRating = currentRating;
                returnPlayer = player.getName();
            }
        }
        return returnPlayer;
    }


    // REQUIRES: team.size >= 1
    // EFFECTS: returns the players names
    public LinkedList<String> returnsNamesOfTeam() {
        LinkedList<String> returnList = new LinkedList<>();
        for (Player player: this.players) {
            returnList.add(player.getName());
        }
        return returnList;
    }

    // REQUIRES: player does exist in Array
    // EFFECTS: returns all the players in the team
    public LinkedList<Player> returnsPlayers() {
        LinkedList<Player> returnList = new LinkedList<>(this.players);
        return returnList;
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public int getSize() {
        return players.size();
    }

    // REQUIRES: Player exists in team
    // MODIFIES: this
    // EFFECTS: removes player from team and returns true
    public boolean removePlayer(int index) {
        this.players.remove(index);
        return true;
    }


}
