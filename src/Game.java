package src;

// Import statements
import java.util.*;
import src.users.*;
import src.challenges.*;
import src.equipment.*;
import src.characters.*;
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
        while (true) {
            // Print the menu
            this.menu();
        
            // Save the game
            this.save();
        }
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
        String [] options = {"Login", "Register", "Exit"};
        int answer = MenuBuilder.menu("Welcome to RPG Game", options);

        if (answer == 1) this.login();
        else if (answer == 2) this.register();
        else System.exit(0);
    }

    // Method to print the logged admin menu options
    private void loggedPlayerMenu() {
        String [] options = {"Challenge", "Modify Active Equipment", "Change Character", "Battle History", "Ranking", "Manage Account", "Log Out"};
        String nickName = this.loggedUser.getNick();
        int answer = MenuBuilder.menu(String.format("Menu [%s]", nickName), options);

        if (answer == 1) this.challenge();
        else if (answer == 2) this.modifyActiveEquipment();
        else if (answer == 3) this.changeCharacter();
        else if (answer == 4) this.checkBattleHistory();
        else if (answer == 5) this.checkRanking();
        else if (answer == 6) this.manageAccount();
        else this.signOff();
    }

    // Method to print the logged player menu options
    private void loggedAdminMenu() {
        System.out.println("Logged Admin Menu");
    }

    // Login Methods ======================================================================================================

    // Method to login the user by credentials
    private void login() {
        while (this.loggedUser == null) {
            // Get the user credentials
            String [] credentials = this.getUserCredentials();

            // Retrieve the user by credentials
            User user = this.retrUser(credentials[0], credentials[1]);

            // Alert the user if the credentials are invalid || Set the logged user if the credentials are valid
            if (user == null) {
                MenuBuilder.alert("Invalid Credentials", "The username or password are invalid. Please try again.");
                boolean answer = MenuBuilder.askYesNo("Do you want to try again?");
                if (!answer) break;

            } else this.setLoggedUser(user);
        }
    }

    // Method to get the user credentials
    private String[] getUserCredentials() {
        String [] labels = {"Username", "Password"};
        return MenuBuilder.form("Login", labels);
    }

    // Method to retrieve the user by credentials
    private User retrUser(String username, String password) {
        for (User user : this.users) {
            if (validateUser(user, username, password)) return user;
        }

        return null;
    }

    // Method to validate the user credentials
    private boolean validateUser(User user, String username, String password) {
        return user.getName().equals(username) && user.getPassword().equals(password);
    }

    // Register Methods ===================================================================================================

    // Method to register a new user
    private void register() {
        // Get the user data
        String [] userData = this.getUserData();

        // Check if the username is already taken
        while (this.isUsernameTaken(userData[0])) {
            MenuBuilder.alert("Username Taken", "The username is already taken. Please try again.");
            userData = this.getUserData();
        }

        // Ask for the user type Player (1) || Admin (2)
        int userType = this.getUserType();

        // Create the user
        User user = this.createUser(userData, userType);

        // Add the user to the users list
        this.users.add(user);
    }

    // Method to get the user data
    private String[] getUserData() {
        // Get basic user data
        String [] labels = {"Username", "Nick", "Password", "Confirm Password"};
        String [] data = MenuBuilder.form("Register", labels);

        // Validate the password
        while (!data[2].equals(data[3])) {
            MenuBuilder.alert("Invalid Password", "The passwords do not match. Please try again.");
            data = MenuBuilder.form("Register", labels);
        }

        return data;
    }

    // Method to check if the username is already taken
    private boolean isUsernameTaken(String username) {
        for (User user : this.users) {
            if (user.getName().equals(username)) return true;
        }

        return false;
    }

    // Method to get the user type Player (1) || Admin (2)
    private int getUserType() {
        String [] options = {"Player", "Admin"};
        return MenuBuilder.menu("Select User Type", options);
    }

    // Method to create the user
    private User createUser(String[] userData, int userType) {
        if (userType == 1) return new Player(userData[0], userData[1], userData[2], this.generatePlayerId());
        else return new Admin(userData[0], userData[1], userData[2]);
    }

    // Method to generate the plaer id
    private String generatePlayerId() {
        int usersSize = this.users.size();

        // ID Format LNLLN (L: Letter, N: Number)
        return "P" + (usersSize) + "A" + (char) (65 + usersSize) + (char) (65 + usersSize) + (usersSize + 1);
    }

    // Logged Player Methods ==============================================================================================

    // Method to challenge another player
    private void challenge() {
        System.out.println("Challenging...");
        // TODO: Implement the challenge method
    }

    // Method to modify the active equipment
    private void modifyActiveEquipment() {
        System.out.println("Modifying Active Equipment...");
        // TODO: Implement the modifyActiveEquipment method
    }

    // Method to change the character
    private void changeCharacter() {
        Player player = (Player) this.loggedUser;
        
        // Get the character selection
        CharacterSelection characterSelection = player.getCurrentCharacter();

        // Print the current character selection
        String output = "Your current character is " + characterSelection + ".";
        MenuBuilder.alert("Current Character", output);

        // Generate the character options
        String [] options = new String[CharacterSelection.values().length];

        for (int i = 0; i < CharacterSelection.values().length; i++) {
            options[i] = CharacterSelection.values()[i].toString();
        }

        // Set menu title
        String title = "Change Character";

        // Print the character selection menu and get the answer
        int answer = MenuBuilder.menu(title, options);

        // Get the character selected from the menu
        CharacterSelection selection = CharacterSelection.values()[answer - 1];

        // Format the string question
        if (characterSelection == null) {
            output = "%s has been selected.";
            output = String.format(output, selection.toString());

        } else if (characterSelection == selection) {
            output = "Your character has not been changed. You are still %s.";
            output = String.format(output, selection.toString());

        } else {
            output = "Your character has been changed from %s to %s.";
            output = String.format(output, characterSelection, selection.toString());
        }
        
        // Print an alert message with the result of the operation
        MenuBuilder.alert(title, (output));

        // Set the new character selection
        player.setCurrentCharacter(selection);
    }

    // Method to check the battle history
    private void checkBattleHistory() {
        Player player = (Player) this.loggedUser;

        // Check if the user has at least one battle
        if (!player.hasChallenges()) {
            MenuBuilder.alert("No Battle History", "You have not participated in any battle yet.");
            return;
        }

        // Create the battle history data table
        String [] data = new String[player.getChallenges().size()];

        for (int i = 0; i < player.getChallenges().size(); i++) {
            Challenge challenge = player.getChallenges().get(i);
            String result = "TIE";
            if (challenge.getResult() != null)
                result = challenge.getWinner() == player ? "WIN" : "LOSE";
            data[i] = challenge.getOpponent(player).getNick() + " ->> " + result;
        }

        // Print the battle history
        MenuBuilder.doc("Battle History", data);
    }

    // Method to check the ranking
    private void checkRanking() {
        // Sort the users by score
        this.users.sort((u1, u2) -> u2.getScore() - u1.getScore());

        // Create the ranking data table
        String [] data = new String[this.users.size()];

        for (int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);

            if (user instanceof Admin) {
                data[i] = user.getNick() + user.getName() + " ->> ADMIN";

            } else {
                Player player = (Player) user;
                String playerData = player.getNick() + "#" + player.getId();

                if (player.isBanned()) data[i] = playerData + " ->> BANNED";
                else data[i] = playerData + " ->> " + player.getScore();
            }
        }

        // Print the ranking
        MenuBuilder.doc("Ranking", data);
    }

    // Method to manage the account settings
    private void manageAccount() {
        int answer = 0;
        
        while (answer != 4) {
            String [] options = {"Change Nick", "Change Password", "Delete Account", "Back"};
            answer = MenuBuilder.menu("Account Settings", options);
            if (answer == 1) this.changeNick();
            else if (answer == 2) this.changePassword();
            else if (answer == 3) this.deleteAccount();
        }
    }

    // Method to change the nick
    private void changeNick() {
        String [] labels = {"New Nick"};
        String[] data = MenuBuilder.form("Change Nick", labels);

        if (data[0] != null) {
            this.loggedUser.setNick(data[0]);
            MenuBuilder.alert("Nick Changed", "Your nick has been changed successfully.");
        }
    }

    // Method to change the password
    private void changePassword() {
        String [] labels = {"New Password", "Confirm Password"};
        String[] data = MenuBuilder.form("Change Password", labels);

        if (data[0].equals(data[1])) {
            this.loggedUser.setPassword(data[0]);
            MenuBuilder.alert("Password Changed", "Your password has been changed successfully.");

        } else {
            MenuBuilder.alert("Invalid Password", "The passwords do not match. Please try again.");
            this.changePassword();
        }
    }

    // Method to delete the account
    private void deleteAccount() {
        boolean answer = MenuBuilder.askYesNo("Are you sure you want to delete your account?");

        if (answer) {
            this.users.remove(this.loggedUser);
            this.signOff();
            MenuBuilder.alert("Account Deleted", "Your account has been deleted successfully.");
        }
    }

    // Method to sign off
    private void signOff() {
        this.loggedUser = null;
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