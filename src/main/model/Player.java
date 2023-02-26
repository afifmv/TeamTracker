package model;

import java.util.ArrayList;

// Player is a class that represents a football player with their attributes
public class Player {
    private String name;
    private int physicality;
    private int defending;
    private int attacking;
    private int pace;


    // REQUIRES: All stats stay within the range 0 <= stat <= 99.
    public Player(String name, int physicality, int defending, int attacking, int pace) {
        this.name = name;
        this.physicality = physicality;
        this.defending = defending;
        this.attacking = attacking;
        this.pace = pace;
    }

    // REQUIRES: physicality >= 0
    // MODIFIES: this
    // EFFECTS: changes the physicality stat to the given stat
    public void changePhysicality(int physicality) {
        this.physicality = physicality;
    }

    // REQUIRES: defending >= 0
    // MODIFIES: this
    // EFFECTS: changes the home score to the given score
    public void changeDefending(int defending) {
        this.defending = defending;
    }

    // REQUIRES: attacking >= 0
    // MODIFIES: this
    // EFFECTS: changes the attacking stats to the given stat
    public void changeAttacking(int attacking) {
        this.attacking = attacking;
    }

    // REQUIRES: pace >= 0
    // MODIFIES: this
    // EFFECTS: changes the pace name to the given team name
    public void changePace(int pace) {
        this.pace = pace;
    }

    // REQUIRES: name != "" or " "
    // MODIFIES: this
    // EFFECTS: changes the pace name to the given team name
    public void changeName(String name) {
        this.name = name;
    }



    public String getName() {
        return this.name;
    }

    public int getPhysicality() {
        return this.physicality;
    }

    public int getDefending() {
        return this.defending;
    }

    public int getAttacking() {
        return this.attacking;
    }

    public int getPace() {
        return this.pace;
    }

    public int getOverallStats() {
        return getPace() + getDefending() + getAttacking() + getPhysicality();
    }

}
