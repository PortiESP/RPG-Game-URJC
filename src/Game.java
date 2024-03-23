package src;

// Import statements
import java.util.*;
import src.users.*;
import src.challenges.*;
import src.equipment.*;
import src.characters.*;
import utils.*;

/**
 * Main class of the game.
 * 
 * <p>
 * Its main purpose is to create a new Game object and call the {@Code play()} method to start the game.
 * </p>
 */
public class Game {
    private List<User> users = new ArrayList<>();
    private User loggedUser = null;
    private List<Challenge> challenges = new ArrayList<>();
    private List<Armor> armorsAvailable = new ArrayList<>();
    private List<Weapon> weaponsAvailable = new ArrayList<>();
    private String lastId = null;

    // ============================================================================================[ Constructor ]>>>
    public Game() {
    }

    // ============================================================================================[ Public Methods ]>>>

    /**
     * Method to play the game.
     * 
     * <p>
     * This method is the main loop of the game. Firstly, it will load the game and then start a loop where it will print the main, and save the game.
     * </p>
     * 
     * @see #load()
     * @see #menu()
     * @see #save()
     */
    public void play() {
        // Load the game
        this.load();
        System.out.println("Playing...");

        // Main Loop
        while (true) {
            // If the user is a player, manage the notifications
            if (this.loggedUser instanceof Player) {
                Player player = (Player) this.loggedUser;
                player.manageNotifications();
            }

            // Print the menu
            this.menu();

            // Save the game
            this.save();
        }
    }

    // ============================================================================================[ Private Methods ]>>>

    /**
     * Method to load the game
     * 
     * <p>
     * This method will read the game from a file determined by the {@Code Const.DATA_PATH} constant. 
     * If the file does not exist, it will create a new game. If the file exists, it will replace the game settings.
     * </p>
     * 
     * @see FileManager#readFile(String)
     * @see #replaceSettings(Game)
     * 
     */
    private void load() {
        // Load the game from the file
        Game game = FileManager.readFile(Const.DATA_PATH);

        // Replace the game settings
        if (game != null) {
            this.replaceSettings(game);
        } else {
            // Load the default game settings
            this.loadDefaultSettings();
        }
    }

    /**
     * Method to load the default game settings
     * 
     * <p>
     * This method will load the default game settings. It will create the default armors and weapons available.
     * </p>
     * 
     * @see #loadArmors()
     * @see #loadWeapons()
     */

    private void loadDefaultSettings() {
        // Load the default armors and weapons
        this.armorsAvailable = Const.ARMORS;
        this.weaponsAvailable = Const.WEAPONS;
    }

    /**
     * Method to replace the game settings
     * 
     * <p>
     * This method will replace the game settings with the settings of the game passed as a parameter.
     * This method is used after loading the game from a file, this method will take the {@Code game} object 
     * readed from the file and replace the settings of the current game with the settings of the readed game.
     * </p>
     * 
     * @param game The game to replace the settings with.
     * 
     * @see #setUsers(List)
     * @see #setChallenges(List)
     * @see #setArmorsAvailable(List)
     * @see #setWeaponsAvailable(List)
     */
    private void replaceSettings(Game game) {
        // Set the game attributes
        this.users = game.users;
        this.challenges = game.challenges;
        this.armorsAvailable = game.armorsAvailable;
        this.weaponsAvailable = game.weaponsAvailable;
        this.lastId = game.lastId;
    }

    /**
     * Method to save the game
     * 
     * <p>
     * This method will save the game to a file determined by the {@Code Const.DATA_PATH} constant.
     * </p>
     * 
     * @see FileManager#saveFile(Game)
     *
     */
    private void save() {
        // Save the game to the file
        FileManager.saveFile(this);
    }

    /**
     * Method to print the main menu
     * 
     * <p>
     * This method will print the main menu of the game. The options of the menu will depend on the user type.
     * </p>
     * 
     * @see #notLoggedMenu()
     * @see #loggedPlayerMenu()
     * @see #loggedAdminMenu()
     * 
     */
    private void menu() {
        if (this.loggedUser == null)
            this.notLoggedMenu();
        else if (this.loggedUser instanceof Player)
            this.loggedPlayerMenu();
        else if (this.loggedUser instanceof Admin)
            this.loggedAdminMenu();
    }

    /**
     * Method to print the not logged menu options
     * 
     * <p>
     * This method will print the menu for users not currently logged in. The options are: Login, Register, and Exit.
     * </p>
     * 
     * @see MenuBuilder#menu(String, String[])
     * @see #login()
     * @see #register()
     * @see System#exit(int)
     * 
     */
    private void notLoggedMenu() {
        // Prepare the options, print the menu and get the answer
        String[] options = { "Login", "Register", "Exit" };
        MenuBuilder.setConfigLastAsZero(true);
        int answer = MenuBuilder.menu("Welcome to RPG Game", options);

        // Determine the action to take depending on the answer
        if (answer == 1) {
            this.login();
            if (loggedUser instanceof Player)
                ((Player) loggedUser).manageNotifications();
        } else if (answer == 2) {
            this.register();
        } else {
            System.exit(0);
        }
    }

    /**
     * Method to print the logged player menu options
     * 
     * <p>
     * This method will print the menu for players currently logged in. <br/>
     * The options are:
     * <ul>
     * <li>{@link #challenge() Challenge}</li>
     * <li>{@link #modifyActiveEquipment() Modify Active Equipment}</li>
     * <li>{@link #changeCharacter() Change Character}</li>
     * <li>{@link #checkBattleHistory() Battle History}</li>
     * <li>{@link #checkRanking() Ranking}</li>
     * <li>{@link #manageAccount() Manage Account}</li>
     * <li>{@link #logOut() Log Out}</li>
     * </ul>
     * </p>
     * 
     */
    private void loggedPlayerMenu() {
        // Prepare the options, print the menu and get the answer
        String[] options = { "Challenge", "Modify Active Equipment", "Change Character", "Battle History", "Ranking",
                "Manage Account", "Log Out" };
        String nickName = this.loggedUser.getNick();
        MenuBuilder.setConfigLastAsZero(true);
        int answer = MenuBuilder.menu(String.format("Menu [%s]", nickName), options);

        // Determine the action to take depending on the answer
        if (answer == 1)
            this.challenge();
        else if (answer == 2)
            this.modifyActiveEquipment();
        else if (answer == 3)
            this.changeCharacter();
        else if (answer == 4)
            this.checkBattleHistory();
        else if (answer == 5)
            this.checkRanking();
        else if (answer == 6)
            this.manageAccount();
        else
            this.logOut();
    }

    /**
     * Method to print the logged admin menu options
     * 
     * <p>
     * This method will print the menu for admins currently logged in. <br/>
     * The options are:
     * <ul>
     * <li>{@link #managePlayers() Manage Players}</li>
     * <li>{@link #manageEquipment() Manage Equipment}</li>
     * <li>{@link #manageChallenges() Manage Challenges}</li>
     * <li>{@link #checkRanking() Ranking}</li>
     * <li>{@link #manageAccount() Manage Account}</li>
     * <li>{@link #logOut() Log Out}</li>
     * </ul>
     * </p>
     */
    private void loggedAdminMenu() {
        // Prepare the options, print the menu and get the answer
        String[] options = { "Manage Players", "Manage Equipment", "Manage Challenges", "Check Ranking",
                "Manage Account", "Log Out" };
        String nickName = this.loggedUser.getNick();
        MenuBuilder.setConfigLastAsZero(true);
        int answer = MenuBuilder.menu(String.format("Menu [%s]", nickName), options);

        // Determine the action to take depending on the answer
        if (answer == 1)
            this.managePlayers();
        else if (answer == 2)
            this.manageEquipment();
        else if (answer == 3)
            this.manageChallenges();
        else if (answer == 4)
            this.checkRanking();
        else if (answer == 5)
            this.manageAccount();
        else
            this.logOut();
    }

    // ============================================================================================[ Login Methods ]>>>

    /**
     * Method to login the user by credentials
     * 
     * <p>
     * This method will ask the user for the credentials and then retrieve the user by credentials. <br/>
     * If the credentials are invalid, it will alert the user and ask if they want to try again. <br/>
     * If the credentials are valid, it will set the attribute {@Code loggedUser} with the user retrieved.
     * </p>
     * 
     * @see #getUserCredentials()
     * @see #retrUser(String, String)
     * @see #validateUser(User, String, String)
     * @see MenuBuilder#alert(String, String)
     * @see MenuBuilder#askYesNo(String)
     * 
     */
    private void login() {
        // Loop until the user is logged
        while (this.loggedUser == null) {
            // Get the user credentials
            String[] credentials = this.getUserCredentials();

            // Retrieve the user by credentials
            User user = this.retrUser(credentials[0], credentials[1]);

            // Check the user credentials
            if (user == null) { // Alert the user and ask if they want to try again
                MenuBuilder.alert("Invalid Credentials", "The username or password are invalid. Please try again.");
                boolean answer = MenuBuilder.askYesNo("Do you want to try again?");
                if (!answer) {
                    return;
                }

            }
            // If the user has been banned, alert the user and return
            else if (user instanceof Player && ((Player) user).isBanned()) {
                String msg = "You have been banned. Please contact the admin for more information.";
                MenuBuilder.alert("Banned User", msg);
                return;
            }
            // Set the logged user
            else {
                this.setLoggedUser(user);
            }
        }
    }

    /**
     * Method to read the user credentials
     * 
     * <p>
     * This method will ask the user for the credentials, <em>username</em> and <em>password</em>, and return them as an array of strings.
     * </p>
     * 
     * @return An array of strings with the user credentials: {@Code [username, password]}.
     */
    private String[] getUserCredentials() {
        String[] labels = { "Username", "Password" };
        return MenuBuilder.form("Login", labels);
    }

    /**
     * Method to retrieve the logged user by its credentials
     * 
     * <p>
     * This method will look for the user by its credentials. If the user is found, it will return the user. If the user is not found, it will return null.
     * </p>
     * 
     * @param username Username of the user we want to retrieve
     * @param password Password of the user we want to retrieve
     * @return The user retrieved by its credentials. If the user is not found, it will return null.
     * @see #validateUser(User, String, String)
     */
    private User retrUser(String username, String password) {
        for (User user : this.users) {
            if (validateUser(user, username, password)) {
                return user;
            }
        }

        return null;
    }

    /**
     * Method to validate the user credentials
     * 
     * <p>
     * This method will validate the user credentials. It will check if the username and password entered by the input, match the given user credentials.
     * </p>
     * 
     * @param user The user to validate
     * @param username The username to validate against the user
     * @param password The password to validate against the user
     * @return True if the username and password match the user credentials. False otherwise.
     */
    private boolean validateUser(User user, String username, String password) {
        return user.getName().equals(username) && user.getPassword().equals(password);
    }

    // ============================================================================================[ Register Methods ]>>>

    /**
     * Method to register a new user
     * 
     * <p>
     * This method will ask the user for the data to register a new user. <br/>
     * It will check if the username is already taken. If the username is already taken, it will alert the user and ask for the data again. <br/>
     * If the username is not taken, it will ask for the user type, create the user, and add it to the users list.
     * </p>
     * 
     * @see #isUsernameTaken(String)
     * @see #createUser(String[], int)
     * @see #generatePlayerId()
     * @see #readUserData()
     * @see #readUserType()
     * @see MenuBuilder#alert(String, String)
     * @see MenuBuilder#form(String, String[])
     * @see MenuBuilder#menu(String, String[])
     * 
     */
    private void register() {
        // Read the new user data from the input
        String[] userData = this.readUserData();

        // Check if the username is already taken, if so, ask for the data again
        while (this.isUsernameTaken(userData[0])) {
            MenuBuilder.alert("Username Taken", "The username is already taken. Please try again.");
            userData = this.readUserData();
        }

        // Ask for the new user type: Player (1) || Admin (2)
        int userType = this.readUserType();

        // Create the new user
        User user = this.createUser(userData, userType);

        // Auto-login the user
        this.setLoggedUser(user);

        // If the user is a player, ask the user to choose the character
        if (user instanceof Player) {
            this.changeCharacter();
        }

        // Add the new user to the users list
        this.users.add(user);

        // Log the user in
        this.setLoggedUser(user);
    }

    /**
     * Method print and validate the form to register a new user
     * 
     * <p>
     * This method will print a form to register a new user and then validate the data entered by the user if the password and confirm password match.
     * </p>
     * @return An array of strings with the user data: {@Code [username, nick, password, confirm password]}.
     */
    private String[] readUserData() {
        // Get basic user data
        String[] labels = { "Username", "Nick", "Password", "Confirm Password" };
        String[] data = MenuBuilder.form("Register", labels);

        // Validate the password
        while (!data[2].equals(data[3])) {
            MenuBuilder.alert("Invalid Password", "The passwords do not match. Please try again.");
            data = MenuBuilder.form("Register", labels);
        }

        return data;
    }

    /**
     * Method to check if the username is already taken
     * 
     * <p>
     * This method will check if the username is already taken. It will look for the username in the users list.
     * </p>
     * 
     * @param username The username to check if it is already taken
     * @return True if the username is already taken. False otherwise.
     */
    private boolean isUsernameTaken(String username) {
        for (User user : this.users) {
            if (user.getName().equals(username))
                return true;
        }

        return false;
    }

    /**
     * Method to print a menu to select the user type
     * 
     * <p>
     * This method will print a menu to select the user type among two options: Player (1) or Admin (2), and return the answer.
     * </p>
     * 
     * @return The user type selected: Player (1) or Admin (2).
     */
    // Method to get the user type Player (1) || Admin (2)
    private int readUserType() {
        String[] options = { "Player", "Admin" };
        return MenuBuilder.menu("Select User Type", options);
    }

    /**
     * Method to create a new user
     * 
     * <p>
     * This method will create a new user depending on the user type selected. <br/>
     * If the user type is 1, it will create a new player. If the user type is 2, it will create a new admin.
     * </p>
     * 
     * @param userData The user data to create the user {@Code [username, nick, password]}
     * @param userType The user type to create the user {@Code 1: Player, 2: Admin}
     * @return The new user created.
     * @see #generatePlayerId()
     */
    private User createUser(String[] userData, int userType) {
        if (userType == 1)
            return new Player(userData[0], userData[1], userData[2], this.generatePlayerId());
        else
            return new Admin(userData[0], userData[1], userData[2]);
    }

    /**
     * Method to generate the player id
     * 
     * <p>
     * This method will generate the player id. The id format is: LNLLN (L: Letter, N: Number).
     * </p>
     * 
     * @return The new player id generated.
     */
    private String generatePlayerId() {

        // If the lastId is null (any users created yet), set the lastId to "A0AA0" and return it
        if (this.lastId == null) {
            this.lastId = "A0AA0";
            return this.lastId;
        }

        // Split the lastId into an array of strings
        String[] parts = this.lastId.split("");

        // Generate an array of integers from the parts array using the ASCII code of the characters
        int[] partsInt = new int[5];
        for (int i = 0; i < parts.length; i++) {
            partsInt[i] = parts[i].codePointAt(0);
        }

        // Increment Id parts
        // Increment the last part of the id, if it is greater than 9, set it to 0 and increment the previous part
        partsInt[4]++;
        if (partsInt[4] > 57) { // 5th part exceeds 9
            partsInt[4] = 48; // Set the 5th part to 0 and increment the 4th part
            partsInt[3]++;
            if (partsInt[3] > 90) { // 4th part exceeds Z
                partsInt[3] = 65; // Set the 4th part to A and increment the 3rd part
                partsInt[2]++;
                if (partsInt[2] > 90) { // 3rd part exceeds Z
                    partsInt[2] = 65; // Set the 3rd part to A and increment the 2nd part
                    partsInt[1]++;
                    if (partsInt[1] > 57) { // 2nd part exceeds 9
                        partsInt[1] = 48; // Set the 2nd part to 0 and increment the 1st part
                        partsInt[0]++;
                        if (partsInt[0] > 90) { // 1st part exceeds Z
                            throw new RuntimeException("ID limit reached");
                        }
                    }
                }
            }
        }

        // Convert the partsInt to a string array using the ASCII code of the integers
        for (int i = 0; i < partsInt.length; i++) {
            parts[i] = String.valueOf((char) partsInt[i]);
        }

        // Convert the partsInt to a string and set the lastId to the new id
        String newId = String.format("%s%s%s%s%s", parts[0], parts[1], parts[2], parts[3], parts[4]);
        this.lastId = newId;

        return newId;

    }

    // ============================================================================================[ Logged Player Methods ]>>>

    // Method to challenge another player
    private void challenge() {
        Player currPlayer = (Player) this.loggedUser;
        Player[] players = this.getPlayers();

        // Set the menu title and options
        String title = "Choose a player to challenge";
        String[] options = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            options[i] = players[i].getNick() + " #" + players[i].getId();
        }
        int answer = MenuBuilder.menu(title, options) - 1;

        // Get the opponent selected from the menu
        Player opponent = (Player) players[answer];

        // Ask the user the ammount of gold to bet
        int gold = MenuBuilder.readInt("Enter the ammount of gold to bet");
        // Check if the user has enough gold to bet
        if (!currPlayer.canAfford(gold)) {
            MenuBuilder.alert("Invalid Gold", "You do not have enough gold to bet.");
            return;
        }

        // Verifications before challenging the opponent
        Challenge challenge = new Challenge(currPlayer, opponent, gold);
        if (!challenge.isValid(currPlayer, opponent)) {
            MenuBuilder.alert("Challenge warning", "The challenge was not created. Please try again.");
            return;
        }

        // Ask the user to choose the equipment
        this.modifyActiveEquipment();

        // Create the new challenge
        currPlayer.setPendingChallenge(challenge);

        // Alert the user that the challenge has been created
        MenuBuilder.alert("Challenge Created", "The challenge has been created successfully.");

        // Add the challenge to the challenges list
        this.challenges.add(challenge);
    }

    private Player[] getPlayers() {
        List<Player> players = new ArrayList<>();
        for (User user : this.users) {
            if (user instanceof Player) {
                players.add((Player) user);
            }
        }
        return players.toArray(new Player[players.size()]);
    }

    // Method to modify the active equipment
    private void modifyActiveEquipment() {
        Player currPlayer = (Player) this.loggedUser;
        currPlayer.manageEquipment(this.armorsAvailable, this.weaponsAvailable);
    }

    /**
     * Method to change the character
     * 
     * <p>
     * This method will change the character of the player. It will print the current character selection and then print a menu to select the new character. <br/>
     * </p>
     * 
     */
    // Method to change the character
    private void changeCharacter() {
        // Get the current player object
        Player player = (Player) this.loggedUser;

        // Generate the character options [LYCANTHROPE, VAMPIRE, HUNTER]
        String[] options = new String[CharacterSelection.values().length];
        for (int i = 0; i < CharacterSelection.values().length; i++) {
            options[i] = CharacterSelection.values()[i].toString();
        }
        // Set menu title
        String title = "Change Character";
        // Print the character selection menu and get the answer
        int answer = MenuBuilder.menu(title, options);

        // Get the character selected from the menu
        CharacterSelection selectedCharacter = CharacterSelection.values()[answer - 1];

        // Inform the user of the character selected
        String output = "%s has been selected.";
        output = String.format(output, selectedCharacter.toString());

        // Print an alert message with the result of the operation
        MenuBuilder.alert(title, output);

        // Set the new character selection
        player.setCurrentCharacter(selectedCharacter);
    }

    /**
     * Method to check the battle history
     * 
     * <p>
     * This method will check the battle history of the player. It will print the battle history of the player.
     * </p>
     * 
     * @see Player#hasChallenges()
     * @see Player#getChallenges()
     * @see Challenge#getOpponent(Player)
     * @see Challenge#getWinner()
     * @see MenuBuilder#doc(String, String[])
     * 
     */
    private void checkBattleHistory() {
        // Get the current player object
        Player player = (Player) this.loggedUser;

        // Check if the user has at least one battle, if not, alert the user and return
        if (!player.hasChallenges()) {
            MenuBuilder.alert("No Battle History", "You have not participated in any battle yet.");
            return;
        }

        // Create the battle history report
        String[] data = new String[player.getChallenges().size()];
        // Fill the data array with the battle history
        for (int i = 0; i < player.getChallenges().size(); i++) {
            // Get a certain challenge
            Challenge challenge = player.getChallenges().get(i);

            // Get the result of the challenge
            String result = "TIE";
            if (challenge.getResult() != null)
                result = challenge.getWinner() == player ? "WIN" : "LOSE";
            data[i] = challenge.getOpponent(player).getNick() + " --> " + result;
        }

        // Print the battle history
        MenuBuilder.doc("Battle History", data);
    }

    // ============================================================================================[ Logged Admin Methods ]>>>
    // Method to manage the players
    private void managePlayers() {
        while (true) {
            Player[] players = this.getPlayers();
            String[] playersData = new String[players.length];
            for (int i = 0; i < players.length; i++) {
                playersData[i] = players[i].getNick() + " #" + players[i].getId();
            }
            int answer = MenuBuilder.menu("Select a Player", playersData) - 1;

            String[] options = new String[] { "Ban Player", "Unban Player", "Show Player Info", "Back" };
            MenuBuilder.setConfigLastAsZero(true);
            int action = MenuBuilder.menu("Manage Player", options);

            if (action == 1) {
                this.banPlayer(answer, players);
            } else if (action == 2) {
                this.unbanPlayer(answer, players);
            } else if (action == 3) {
                this.showPlayerInfo(answer, players);
            } else {
                break;
            }
        }
    }

    // Method to ban a user
    private void banPlayer(int index, Player[] players) {
        boolean confirm = MenuBuilder.askYesNo("Are you sure you want to ban this player?");
        if (confirm) {
            Player player = players[index];
            player.ban();
            MenuBuilder.alert("Player Banned", "The player has been banned.");
        } else {
            MenuBuilder.alert("Operation Canceled", "The player has not been banned.");
        }
    }

    // Method to unban a user
    private void unbanPlayer(int index, Player[] players) {
        boolean confirm = MenuBuilder.askYesNo("Are you sure you want to unban this player?");
        if (confirm) {
            Player player = players[index];
            player.unban();
            MenuBuilder.alert("Player Unbanned", "The player has been unbanned.");
        } else {
            MenuBuilder.alert("Operation Canceled", "The player has not been unbanned.");
        }
    }

    // Method to show the user info
    private void showPlayerInfo(int index, Player[] players) {
        Player player = players[index];
        player.showInfo();
    }

    // Method to manage the equipment
    private void manageEquipment() {
        while (true) {
            String[] options = new String[] { "Manage Armors", "Manage Weapons", "Back" };
            MenuBuilder.setConfigLastAsZero(true);
            int answer = MenuBuilder.menu("Manage Equipment", options);
    
            if (answer == 1) {
                this.manageArmors();
            } else if (answer == 2) {
                this.manageWeapons();
            } else {
                break;
            }
        }
    }

    // Method to manage the armors
    private void manageArmors() {
        while (true) {
            String[] options = new String[] { "Add Armor", "Remove Armor", "Show Armors", "Back" };
            MenuBuilder.setConfigLastAsZero(true);
            int answer = MenuBuilder.menu("Manage Armors", options);

            if (answer == 1)
                this.addArmor();
            else if (answer == 2)
                this.removeArmor();
            else if (answer == 3)
                this.showArmors();
            else
                break;
        }
    }

    // Method to add an armor
    private void addArmor() {
        String[] labels = { "Name", "Defense Modifier", "Attack Modifier" };
        String[] dataInput = MenuBuilder.form("Add Armor", labels);

        int defenseModifier = 0;
        int attackModifier = 0;

        // Check if the defense and attack modifiers are integers
        try {
            defenseModifier = Integer.parseInt(dataInput[1]);
            attackModifier = Integer.parseInt(dataInput[2]);
            
            // Create the new armor
            Armor armor = new Armor(dataInput[0], defenseModifier, attackModifier);
    
            // Ask for user confirmation
            boolean answer = MenuBuilder.askYesNo("Are you sure you want to add this armor?");
    
            // If the user confirms, add the new armor to the armors available
            if (answer)
                this.armorsAvailable.add(armor);
            else
                MenuBuilder.alert("Operation Canceled", "The armor has not been added.");

        // If the defense and attack modifiers are not integers, alert the user and ask for the data again
        } catch (NumberFormatException e) {
            MenuBuilder.alert("Invalid Input", "The defense and attack modifiers must be integers.");
            this.addArmor();
        }
    }

    // Method to remove an armor
    private void removeArmor() {
        // Prepare the options, print the menu and get the answer
        String[] options = new String[this.armorsAvailable.size()];
        for (int i = 0; i < this.armorsAvailable.size(); i++) {
            options[i] = this.armorsAvailable.get(i).getName();
        }
        int answer = MenuBuilder.menu("Remove Armor", options) - 1;

        // Ask for user confirmation
        boolean confirm = MenuBuilder.askYesNo("Are you sure you want to remove this armor?");

        // If the user confirms, remove the armor from the armors available
        if (confirm) this.armorsAvailable.remove(answer);
        else MenuBuilder.alert("Operation Canceled", "The armor has not been removed.");        
    }

    // Method to show the armors
    private void showArmors() {
        // Create the armors data table
        String[] data = new String[this.armorsAvailable.size()];
        
        // Fill the data array with the armors
        for (int i = 0; i < this.armorsAvailable.size(); i++) {
            Armor armor = this.armorsAvailable.get(i);
            data[i] = armor.getName() + " --> Defense: " + armor.getDefense() + " | Attack: " + armor.getAttack();
        }

        // Print the armors
        MenuBuilder.doc("Armors", data);
    }

    // Method to manage the weapons
    private void manageWeapons() {
        while (true) {
            String[] options = new String[] { "Add Weapon", "Remove Weapon", "Show Weapons", "Back" };
            MenuBuilder.setConfigLastAsZero(true);
            int answer = MenuBuilder.menu("Manage Weapons", options);

            if (answer == 1)
                this.addWeapon();
            else if (answer == 2)
                this.removeWeapon();
            else if (answer == 3)
                this.showWeapons();
            else
                break;
        }
    }

    // Method to add a weapon
    private void addWeapon() {
        String[] labels = { "Name", "Defense Modifier", "Attack Modifier", "Hands Required" };
        String[] dataInput = MenuBuilder.form("Add Weapon", labels);

        int defenseModifier = 0;
        int attackModifier = 0;
        int handsRequired = 0;

        // Check if the defense, attack modifiers and hands required are integers
        try {
            defenseModifier = Integer.parseInt(dataInput[1]);
            attackModifier = Integer.parseInt(dataInput[2]);
            handsRequired = Integer.parseInt(dataInput[3]);

            // Create the new weapon
            Weapon weapon = new Weapon(dataInput[0], defenseModifier, attackModifier, handsRequired);
    
            // Ask for user confirmation
            boolean answer = MenuBuilder.askYesNo("Are you sure you want to add this weapon?");
    
            // If the user confirms, add the new weapon to the weapons available
            if (answer) this.weaponsAvailable.add(weapon);
            else MenuBuilder.alert("Operation Canceled", "The weapon has not been added.");

        // If the defense, attack modifiers and hands required are not integers, alert the user and ask for the data again
        } catch (NumberFormatException e) {
            MenuBuilder.alert("Invalid Input", "The defense, attack modifiers and hands required must be integers.");
            this.addWeapon();
        }
    }

    // Method to remove a weapon
    private void removeWeapon() {
        // Prepare the options, print the menu and get the answer
        String[] options = new String[this.weaponsAvailable.size()];
        for (int i = 0; i < this.weaponsAvailable.size(); i++) {
            options[i] = this.weaponsAvailable.get(i).getName();
        }
        int answer = MenuBuilder.menu("Remove Weapon", options) - 1;

        // Ask for user confirmation
        boolean confirm = MenuBuilder.askYesNo("Are you sure you want to remove this weapon?");

        // If the user confirms, remove the weapon from the weapons available
        if (confirm) this.weaponsAvailable.remove(answer);
        else MenuBuilder.alert("Operation Canceled", "The weapon has not been removed.");
    }

    // Method to show the weapons
    private void showWeapons() {
        // Create the weapons data table
        String[] data = new String[this.weaponsAvailable.size()];

        // Fill the data array with the weapons
        for (int i = 0; i < this.weaponsAvailable.size(); i++) {
            Weapon weapon = this.weaponsAvailable.get(i);
            data[i] = weapon.getName() + " --> Defense: " + weapon.getDefense() + " | Attack: " + weapon.getAttack() + " | Hands Required: " + weapon.getHandsRequired();
        }

        // Print the weapons
        MenuBuilder.doc("Weapons", data);
    }

    // Method to manage the challenges
    private void manageChallenges() {
        for (Challenge challenge : this.challenges) {
            if (!challenge.isApproved()) {
                Admin admin = (Admin) this.loggedUser;
                admin.manageChallenge(challenge, this.armorsAvailable, this.weaponsAvailable);
            }
        }

        MenuBuilder.alert("Challenges Manager", "All challenges have been managed successfully");
    }

    // ============================================================================================[ General Logged Methods ]>>> 

    // Method to check the ranking
    private void checkRanking() {
        // Sort the users by their score
        this.users.sort((u1, u2) -> u2.getScore() - u1.getScore());

        // Fill the data array with the ranking
        Player[] players = this.getPlayers();

        // Create the ranking data table
        String[] data = new String[players.length];

        for (int i = 0; i < players.length; i++) {
            Player player = players[i];

            // If the user is a player, print the user Nick and score as: `Nick#Id --> Score`
            String playerData = player.getNick() + "#" + player.getId();

            // If the player is banned, print the user data with the BANNED tag, otherwise, print the user data with the score
            if (player.isBanned()) {
                playerData += " --> BANNED";
            } else {
                playerData += " --> " + player.getScore();
            }

            data[i] = playerData;
        }

        // Print the ranking
        MenuBuilder.doc("Ranking", data);
    }

    /**
     * Method to manage the account settings
     * 
     * <p>
     * This method will print a menu to manage the account settings. The options are: Change Nick, Change Password, Delete Account, and Back.
     * </p>
     * 
     * @see #changeNick()
     * @see #changePassword()
     * @see #deleteAccount()
     * @see MenuBuilder#menu(String, String[])
     * 
     */
    private void manageAccount() {
        int answer = 0;

        while (answer != 4 && this.loggedUser != null) {
            String[] options = { "Change Nick", "Change Password", "Delete Account", "Back" };
            MenuBuilder.setConfigLastAsZero(true);
            answer = MenuBuilder.menu("Account Settings", options);
            if (answer == 1)
                this.changeNick();
            else if (answer == 2)
                this.changePassword();
            else if (answer == 3)
                this.deleteAccount();
        }
    }

    /**
     * Method to change the nick of the user
     * 
     * <p>
     * This method will change the nick of the user. It will ask the user for the new nick and then set the new nick to the user.
     * </p>
     */
    private void changeNick() {
        String data = MenuBuilder.readString("Change Nick");

        if (data != null) {
            this.loggedUser.setNick(data);
            MenuBuilder.alert("Nick Changed", "Your nick has been changed successfully.");
        }
    }

    /**
     * Method to change the password of the user
     * 
     * <p>
     * This method will change the password of the user. It will ask the user for the new password and then set the new password to the user.
     * </p>
     */
    private void changePassword() {
        String[] labels = { "New Password", "Confirm Password" };
        String[] data = MenuBuilder.form("Change Password", labels);

        if (data[0].equals(data[1])) {
            this.loggedUser.setPassword(data[0]);
            MenuBuilder.alert("Password Changed", "Your password has been changed successfully.");

        } else {
            MenuBuilder.alert("Invalid Password", "The passwords do not match. Please try again.");
            this.changePassword();
        }
    }

    /**
     * Method to delete the account
     * 
     * <p>
     * This method will delete the account of the user. It will ask the user if they are sure they want to delete the account. <br/>
     * If the user is sure, it will remove the user from the users list and then log out the user.
     * </p>
     * 
     * @see #logOut()
     */
    private void deleteAccount() {
        boolean answer = MenuBuilder.askYesNo("Are you sure you want to delete your account?");

        if (answer) {
            this.users.remove(this.loggedUser);
            this.logOut();
            MenuBuilder.alert("Account Deleted", "Your account has been deleted successfully.");
        }
    }

    /**
     * Method to log out the user
     * 
     * <p>
     * This method will log out the user. It will set the attribute {@Code loggedUser} to null.
     * </p>
     */
    private void logOut() {
        this.loggedUser = null;
    }

    // ============================================================================================[ Getters & Setters ]>>>
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

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }
}