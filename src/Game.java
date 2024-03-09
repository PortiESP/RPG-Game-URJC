package src;

// Import statements
import java.util.*;
import src.users.*;
import src.challenges.*;
import src.equipment.*;
import utils.*;

public class Game {
    private List<User> users = new ArrayList<>();
    private User loggedUser = null;
    private List<Challenge> challenges = new ArrayList<>();
    private List<Armor> armorsAvailable = new ArrayList<>();
    private List<Weapon> weaponsAvailable = new ArrayList<>();

    // Constructor ========================================================================================================
    public Game() {}

    // Public Methods =====================================================================================================

    // Method to play the game
    public void play() {
        // Load the game
        this.load();
        System.out.println("Playing...");

        // Main Loop
        // while (true) {
            // Print the main menu
            this.menu();
        
            // Save the game
            this.save();
        // }
    }

    // Private Methods ====================================================================================================

    // Method to load the game
    private void load() {
        // Load the game from the file
        Game game = FileManager.readFile(Const.DATA_PATH);

        // Replace the game settings
        if (game != null) this.replaceSettings(game);
    }

    // Method to replace the game settings
    private void replaceSettings(Game game) {
        // Set the game attributes
        this.setUsers(game.getUsers());
        this.setLoggedUser(game.getLoggedUser());
        this.setChallenges(game.getChallenges());
        this.setArmorsAvailable(game.getArmorsAvailable());
        this.setWeaponsAvailable(game.getWeaponsAvailable());
    }

    // Method to save the game
    private void save() {
        // Save the game to the file
        FileManager.saveFile(this);
    }

    // Method to print the main menu
    private void menu() {
        if (this.loggedUser == null)
            this.notLoggedMenu();
        else if (this.loggedUser instanceof Player)
            this.loggedPlayerMenu();
        else if (this.loggedUser instanceof Admin)
            this.loggedAdminMenu();
    }
    
    // Method to print the not logged menu options
    private void notLoggedMenu() {
        System.out.println("Not Logged Menu");
    }

    // Method to print the logged admin menu options
    private void loggedPlayerMenu() {
        System.out.println("Logged Player Menu");
    }
    
    // Method to print the logged player menu options
    private void loggedAdminMenu() {
        System.out.println("Logged Admin Menu");
    }

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