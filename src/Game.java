package src;

// Import statements
import java.util.*;
import src.users.*;
import src.challenges.*;
import src.equipment.*;

public class Game {
    private List<User> users = new ArrayList<>();
    private User loggedUser;
    private List<Challenge> challenges = new ArrayList<>();
    private List<Armor> armorsAvailable = new ArrayList<>();
    private List<Weapon> weaponsAvailable = new ArrayList<>();

    // Constructor ========================================================================================================
    public Game() {

    }

    // Public Methods =====================================================================================================

    // Method to play the game
    public void play() {
        System.out.println("Playing...");
    }

    // Private Methods ====================================================================================================

    // Getters & Setters ==================================================================================================
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public User getLoggedUser() {
        return loggedUser;
    }
    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
    public List<Challenge> getChallenges() {
        return challenges;
    }
    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }
    public List<Armor> getArmorsAvailable() {
        return armorsAvailable;
    }
    public void setArmorsAvailable(List<Armor> armorsAvailable) {
        this.armorsAvailable = armorsAvailable;
    }
    public List<Weapon> getWeaponsAvailable() {
        return weaponsAvailable;
    }
    public void setWeaponsAvailable(List<Weapon> weaponsAvailable) {
        this.weaponsAvailable = weaponsAvailable;
    }
}