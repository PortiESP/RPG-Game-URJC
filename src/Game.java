package src;

// Import statements
import java.util.*;
import src.abilities.*;
import src.challenges.*;
import src.characters.*;
import src.equipment.*;
import src.minions.*;
import src.modifiers.*;
import src.users.*;
import utils.*;

/**
 * Main class of the game.
 *
 * <p>
 * Its main purpose is to create a new Game object and call the <code>play()</code> method to start the game.
 * </p>
 */
public class Game {

    private List<User> users = new ArrayList<>();
    private User loggedUser = null;
    private String lastId = null;
    private List<Challenge> challenges = new ArrayList<>();
    public static List<Armor> armorsAvailable = new ArrayList<>();
    public static List<Weapon> weaponsAvailable = new ArrayList<>();
    public static List<Modifier> modifiersAvailable = new ArrayList<>();
    public static List<Talent> talentsAvailable = new ArrayList<>();
    public static List<Don> donesAvailable = new ArrayList<>();
    public static List<Discipline> disciplinesAvailable = new ArrayList<>();
    public static List<Ghoul> ghoulsAvailable = new ArrayList<>();
    public static List<Human> humansAvailable = new ArrayList<>();
    public static List<Devil> devilsAvailable = new ArrayList<>();

    // ============================================================================================[ Constructor ]>>>
    public Game() {}

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

        // Save the game for the first time
        this.save();

        // Main Loop
        boolean exit = false;
        while (!exit) {
            // If the user is a player, manage the notifications
            if (this.loggedUser instanceof Player) {
                Player player = (Player) this.loggedUser;
                player.manageNotifications();
            }

            // Print the menu and get the exit status
            exit = this.menu();

            // Save the game
            this.save();
        }
    }

    // ============================================================================================[ Private Methods ]>>>

    /**
     * Method to load the game
     *
     * <p>
     * This method will read the game from a file determined by the <code>Const.DATA_PATH</code> constant.
     * If the file does not exist, it will create a new game. If the file exists, it will replace the game settings.
     * </p>
     *
     * @see FileManager#readFile(String)
     * @see #replaceSettings(Game)
     *
     */
    private void load() {
        // Load the game from the file
        Game game = (Game) FileManager.readFile(Const.DATA_PATH);

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
        Game.armorsAvailable = Armor.loadFromArray(Const.ARMORS);
        Game.weaponsAvailable = Weapon.loadFromArray(Const.WEAPONS);
        Game.modifiersAvailable = Modifier.loadFromArray(Const.STRENGHTS, Const.WEAKNESSES);
        Game.talentsAvailable = Talent.loadFromArray(Const.TALENTS);
        Game.donesAvailable = Don.loadFromArray(Const.DONES);
        Game.disciplinesAvailable = Discipline.loadFromArray(Const.DISCIPLINES);
        Game.ghoulsAvailable = Ghoul.loadFromArray(Const.GHOULS);
        Game.humansAvailable = Human.loadFromArray(Const.HUMANS);
        Game.devilsAvailable = Devil.loadFromArray(Const.DEVILS);
    }

    /**
     * Method to create the game static state
     * <p>
     * This method will create the game static state. The game static state is a map with the game static attributes.
     * </p>
     * @return The game static state
     */
    private Map<String, Object> createStaticState() {
        Map<String, Object> state = new HashMap<>();
        state.put("armorsAvailable", Game.armorsAvailable);
        state.put("weaponsAvailable", Game.weaponsAvailable);
        state.put("modifiersAvailable", Game.modifiersAvailable);
        state.put("talentsAvailable", Game.talentsAvailable);
        state.put("donesAvailable", Game.donesAvailable);
        state.put("disciplinesAvailable", Game.disciplinesAvailable);
        state.put("ghoulsAvailable", Game.ghoulsAvailable);
        state.put("humansAvailable", Game.humansAvailable);
        state.put("devilsAvailable", Game.devilsAvailable);
        return state;
    }

    /**
     * Method to retrieve the game static state
     * <p>
     * This method will retrieve the game static state from a file determined by the <code>Const.STATE_PATH</code> constant.
     * </p>
     * @return The game static state
     * @see FileManager#readFile(String)
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> retrieveStaticState() {
        return (Map<String, Object>) FileManager.readFile(Const.STATE_PATH);
    }

    /**
     * Method to replace the game settings
     *
     * <p>
     * This method will replace the game settings with the settings of the game passed as a parameter.
     * This method is used after loading the game from a file, this method will take the <code>game</code> object
     * readed from the file and replace the settings of the current game with the settings of the readed game.
     * </p>
     *
     * @param game The game to replace the settings with.
     *
     * @see #setUsers(List)
     * @see #setChallenges(List)
     */
    @SuppressWarnings("unchecked")
    private void replaceSettings(Game game) {
        // Set the game attributes
        this.users = game.users;
        this.lastId = game.lastId;
        this.challenges = game.challenges;

        // Set the game static state
        Map<String, Object> state = game.retrieveStaticState();
        Game.armorsAvailable = (List<Armor>) state.get("armorsAvailable");
        Game.weaponsAvailable = (List<Weapon>) state.get("weaponsAvailable");
        Game.modifiersAvailable = (List<Modifier>) state.get("modifiersAvailable");
        Game.talentsAvailable = (List<Talent>) state.get("talentsAvailable");
        Game.donesAvailable = (List<Don>) state.get("donesAvailable");
        Game.disciplinesAvailable = (List<Discipline>) state.get("disciplinesAvailable");
        Game.ghoulsAvailable = (List<Ghoul>) state.get("ghoulsAvailable");
        Game.humansAvailable = (List<Human>) state.get("humansAvailable");
        Game.devilsAvailable = (List<Devil>) state.get("devilsAvailable");
    }

    /**
     * Saves the current state of the game in the <code>game.xml</code> file.
     */
    private void save() {
        // Save the game to the file
        FileManager.saveFile(this, Const.DATA_PATH);

        // Save the game state to the file
        FileManager.saveFile(this.createStaticState(), Const.STATE_PATH);
    }

    /**
     * Prints the corresponding menu depending on the user logged in.
     */
    private boolean menu() {
        boolean exit = false;

        if (this.loggedUser == null) {
            exit = this.notLoggedMenu();
        } else if (this.loggedUser instanceof Player) {
            this.loggedPlayerMenu();
        } else if (this.loggedUser instanceof Admin) {
            this.loggedAdminMenu();
        }

        return exit;
    }

    /**
     * Prints the menu options for users not logged in
     */
    private boolean notLoggedMenu() {
        // Prepare the options, print the menu and get the answer
        String[] options = { "Login", "Register", "Exit" };
        MenuBuilder.setConfigLastAsZero(true);
        int answer = MenuBuilder.menu("Welcome to RPG Game", options);

        // Menu options:
        // 1 -> Login
        // 2 -> Register
        // 0 -> Exit
        if (answer == 1) {
            this.login();
            if (loggedUser instanceof Player) {
                ((Player) loggedUser).manageNotifications();
            }
        } else if (answer == 2) {
            this.register();
        } else {
            return true;
        }

        return false;
    }

    /**
     * Method to print the logged player menu options
     */
    private void loggedPlayerMenu() {
        // Prepare the options, print the menu and get the answer
        String[] options = { "Challenge", "Modify Active Equipment", "Change Character", "Battle History", "Ranking", "Manage Account", "Log Out" };
        String nickName = this.loggedUser.getNick();
        MenuBuilder.setConfigLastAsZero(true);
        int answer = MenuBuilder.menu(String.format("Menu [%s]", nickName), options);

        // Logged Player Menu Options:
        // 1 -> Challenge another player
        // 2 -> Modify Active Equipment
        // 3 -> Change Character (Lycanthrope, Vampire, Hunter)
        // 4 -> Battle History
        // 5 -> Ranking
        // 6 -> Manage Account (Change Password, Delete Account, ...)
        // 0 -> Log Out
        if (answer == 1) {
            this.challenge();
        } else if (answer == 2) {
            this.modifyActiveEquipment();
        } else if (answer == 3) {
            this.changeCharacter();
        } else if (answer == 4) {
            this.checkBattleHistory();
        } else if (answer == 5) {
            this.checkRanking();
        } else if (answer == 6) {
            this.manageAccount();
        } else {
            this.logOut();
        }
    }

    /**
     * Method to print the logged admin menu options
     */
    private void loggedAdminMenu() {
        // Prepare the options, print the menu and get the answer
        String[] options = { "Manage Players", "Manage Equipment", "Manage Challenges", "Check Ranking", "Manage Account", "Log Out" };
        String nickName = this.loggedUser.getNick();
        MenuBuilder.setConfigLastAsZero(true);
        int answer = MenuBuilder.menu(String.format("Menu [%s]", nickName), options);

        // Logged Admin Menu Options:
        // 1 -> Manage Players
        // 2 -> Manage Equipment
        // 3 -> Manage Challenges
        // 4 -> Check Ranking
        // 5 -> Manage Account (Change Password, Delete Account, ...)
        // 0 -> Log Out
        if (answer == 1) {
            this.managePlayers();
        } else if (answer == 2) {
            this.manageEquipment();
        } else if (answer == 3) {
            this.manageChallenges();
        } else if (answer == 4) {
            this.checkRanking();
        } else if (answer == 5) {
            this.manageAccount();
        } else {
            this.logOut();
        }
    }

    // ============================================================================================[ Login Methods ]>>>

    /**
     * Main loop for the logging in process. The user will be asked to enter their credentials until they are logged in.
     */
    private void login() {
        // Loop until the user is logged
        while (this.loggedUser == null) {
            // Print the user a menu to enter his credentials
            String[] credentials = this.askUserCredentials();

            // Retrieve the user from the users list by the provided credentials
            User user = this.retrUser(credentials[0], credentials[1]);

            // Check the user credentials
            // User is null -> Alert the user and ask if they want to try again
            // User is a player and is banned -> Alert the user and return
            // User is a player -> Set the logged user
            if (user == null) {
                MenuBuilder.alert("Invalid Credentials", "The username or password are invalid. Please try again.");
                boolean answer = MenuBuilder.askYesNo("Do you want to try again?");
                if (!answer) {
                    return;
                }
            } else if (user instanceof Player && ((Player) user).isBanned()) {
                String msg = "You have been banned. Please contact the admin for more information.";
                MenuBuilder.alert("Banned User", msg);
                return;
            } else {
                this.setLoggedUser(user);
            }
        }
    }

    /**
     * Print the user a menu to enter their credentials
     *
     * @return An array of strings with the user credentials: <code>[username, password]</code>.
     */
    private String[] askUserCredentials() {
        String[] labels = { "Username", "Password" };
        return MenuBuilder.form("Login", labels);
    }

    /**
     * Look for the user from the users list that matches the provided credentials and retrieve the user object. If the user is not found, it will return null.
     *
     * @param username Username of the user we want to retrieve
     * @param password Password of the user we want to retrieve
     * @return The user retrieved by its credentials. If the user is not found, it will return null.
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
     * Check if the username and password match the user credentials
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
     * Print the register form to the user and after some validations, it will create the new user.
     *
     * If the user is a player, it will ask the user to choose the character.
     * Then it will add the new user to the users list and log the user in.
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
    }

    /**
     * Prints the user a form where he must enter his registration credentials
     *
     * @return An array of strings with the user data: <code>[username, nick, password, confirm password]</code>.
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
     * Check if the username is already taken by another user
     *
     * @param username The username to check if it is already taken
     * @return True if the username is already taken. False otherwise.
     */
    private boolean isUsernameTaken(String username) {
        for (User user : this.users) {
            if (user.getName().equals(username)) return true;
        }

        return false;
    }

    /**
     * Prints a menu to select the user type among two options: Player (1) or Admin (2), and return the answer.
     *
     * @return The user type selected: Player (1) or Admin (2).
     */
    private int readUserType() {
        String[] options = { "Player", "Admin" };
        return MenuBuilder.menu("Select User Type", options);
    }

    /**
     * Create a the user object with the provided data and user typed selected.
     *
     * @param userData The user data to create the user <code>[username, nick, password]</code>
     * @param userType The user type to create the user <code>1: Player, 2: Admin</code>
     * @return The new user created.
     * @see #generatePlayerId()
     */
    private User createUser(String[] userData, int userType) {
        if (userType == 1) {
            return new Player(userData[0], userData[1], userData[2], this.generatePlayerId());
        } else {
            return new Admin(userData[0], userData[1], userData[2]);
        }
    }

    /**
     * Generate the player id. The id format is: LNLLN (L: Letter, N: Number).
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

    /**
     * Prints a list of players to the user and asks the user to select an opponent to challenge.
     *
     * @return The player selected from the list.
     */
    private Player askPlayerFromPlayersList() {
        // Get the players available to challenge
        Player[] players = this.getPlayers();

        // Set the menu title and options
        String title = "Choose a player to challenge";
        String[] options = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            options[i] = players[i].getNick() + " #" + players[i].getId();
        }
        int answer = MenuBuilder.menu(title, options) - 1;

        // Get the opponent selected from the menu
        return (Player) players[answer];
    }

    /**
     * Handle the process of challenging another player. From the process of selecting the opponent to the process of creating the challenge.
     */
    private void challenge() {
        // Get the current player object
        Player currPlayer = (Player) this.loggedUser;

        // Ask the user to select the opponent
        Player opponent = this.askPlayerFromPlayersList();

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

    /**
     * Retrieves all the players from the users list and returns them in an array.
     *
     * @return An array of players.
     */
    private Player[] getPlayers() {
        List<Player> players = new ArrayList<>();
        for (User user : this.users) {
            if (user instanceof Player) {
                players.add((Player) user);
            }
        }

        return players.toArray(new Player[players.size()]);
    }

    /**
     * Prints the manage equipment menu of current player.
     */
    private void modifyActiveEquipment() {
        Player currPlayer = (Player) this.loggedUser;
        currPlayer.manageEquipment();
    }

    /**
     * Change the character of the player. It will print the current character selection and then print a menu to select the new character.
     */
    private void changeCharacter() {
        // Get the current player object
        Player player = (Player) this.loggedUser;

        // Print the character selection menu and get the answer.
        String title = "Change Character";
        String[] options = CharacterSelection.allToString(); // Generate the character options [LYCANTHROPE, VAMPIRE, HUNTER]
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
     * Prints the history of the battles of the current player.
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
            if (challenge.getResult() != null) {
                result = challenge.getWinner() == player ? "WIN" : "LOSE";
            }

            String name1 = challenge.getChallengerPlayer().getName();
            String name2 = challenge.getChallengedPlayer().getName();
            String msg = String.format("- [%d] %s VS %s ==> YOU %s!", i + 1, name1, name2, result);
            data[i] = msg;
        }

        // Print the battle history
        MenuBuilder.doc("Battle History", data);
    }

    // ============================================================================================[ Logged Admin Methods ]>>>
    /**
     * Prints a list of players to the user and asks the user to select a player to manage. Then it will print the manage menu for that player
     */
    private void managePlayers() {
        while (true) {
            Player player = this.askPlayerFromPlayersList();

            String[] options = new String[] { "Ban Player", "Unban Player", "Show Player Info", "Back" };
            MenuBuilder.setConfigLastAsZero(true);
            int action = MenuBuilder.menu("Manage Player", options);

            if (action == 1) {
                this.banPlayer(player);
            } else if (action == 2) {
                this.unbanPlayer(player);
            } else if (action == 3) {
                this.showPlayerInfo(player);
            } else {
                break;
            }
        }
    }

    /**
     * Prints a verification message before banning a player.
     *
     * @param player The player to ban.
     */
    private void banPlayer(Player player) {
        boolean confirm = MenuBuilder.askYesNo("Are you sure you want to ban this player?");
        if (confirm) {
            player.ban();
            MenuBuilder.alert("Player Banned", "The player has been banned.");
        } else {
            MenuBuilder.alert("Operation Canceled", "The player has not been banned.");
        }
    }

    /**
     * Prints a verification message before unbanning a player.
     *
     * @param player The player to unban.
     */
    private void unbanPlayer(Player player) {
        boolean confirm = MenuBuilder.askYesNo("Are you sure you want to unban this player?");
        if (confirm) {
            player.unban();
            MenuBuilder.alert("Player Unbanned", "The player has been unbanned.");
        } else {
            MenuBuilder.alert("Operation Canceled", "The player has not been unbanned.");
        }
    }

    /**
     * Prints the player information to the user.
     *
     * @param player The player to show the information.
     */
    private void showPlayerInfo(Player player) {
        player.showInfo();
    }

    /**
     * Prints the manage equipment (1=Armors, 2=Weapons) menu for the current user.
     */
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

    /**
     * Prints the manage armors menu for the admin.
     */
    private void manageArmors() {
        while (true) {
            String[] options = new String[] { "Add Armor", "Remove Armor", "Show Armors", "Back" };
            MenuBuilder.setConfigLastAsZero(true);
            int answer = MenuBuilder.menu("Manage Armors", options);

            if (answer == 1) {
                this.addArmor();
            } else if (answer == 2) {
                this.removeArmor();
            } else if (answer == 3) {
                this.showArmors();
            } else {
                break;
            }
        }
    }

    /**
     * Add an armor to the armors available.
     */
    private void addArmor() {
        String[] labels = { "Name", "Defense Modifier", "Attack Modifier" };
        String[] dataInput = MenuBuilder.form("Add Armor", labels);

        int defenseModifier = 0;
        int attackModifier = 0;

        // Check if the defense and attack modifiers are integers
        // If the defense and attack modifiers are integers -> Create the new armor and add it to the armors available
        // If the defense and attack modifiers are not integers -> Alert the user and ask for the data again
        try {
            defenseModifier = Integer.parseInt(dataInput[1]);
            attackModifier = Integer.parseInt(dataInput[2]);

            // Create the new armor
            Armor armor = new Armor(dataInput[0], defenseModifier, attackModifier);

            // Ask for user confirmation
            boolean answer = MenuBuilder.askYesNo("Are you sure you want to add this armor?");

            // If the user confirms, add the new armor to the armors available
            if (answer) {
                Game.armorsAvailable.add(armor);
            } else {
                MenuBuilder.alert("Operation Canceled", "The armor has not been added.");
            }
        } catch (NumberFormatException e) {
            MenuBuilder.alert("Invalid Input", "The defense and attack modifiers must be integers.");
            this.addArmor(); // Recursive call to ask for the data again
        }
    }

    /**
     * Remove an armor from the armors available.
     */
    private void removeArmor() {
        // Prepare the options, print the menu and get the answer
        String[] options = new String[Game.armorsAvailable.size()];
        for (int i = 0; i < Game.armorsAvailable.size(); i++) {
            options[i] = Game.armorsAvailable.get(i).getName();
        }
        int answer = MenuBuilder.menu("Remove Armor", options) - 1;

        // Ask for user confirmation
        boolean confirm = MenuBuilder.askYesNo("Are you sure you want to remove this armor?");

        // If the user confirms, remove the armor from the armors available
        if (confirm) {
            Game.armorsAvailable.remove(answer);
        } else {
            MenuBuilder.alert("Operation Canceled", "The armor has not been removed.");
        }
    }

    /**
     * Print the armors available to the user.
     */
    private void showArmors() {
        // Create the armors data table
        String[] data = new String[Game.armorsAvailable.size()];

        // Fill the data array with the armors
        for (int i = 0; i < Game.armorsAvailable.size(); i++) {
            Armor armor = Game.armorsAvailable.get(i);
            data[i] = armor.getName() + " --> Defense: " + armor.getDefense() + " | Attack: " + armor.getAttack();
        }

        // Print the armors
        MenuBuilder.doc("Armors", data);
    }

    /**
     * Prints the manage weapons menu for the admin.
     */
    private void manageWeapons() {
        while (true) {
            String[] options = new String[] { "Add Weapon", "Remove Weapon", "Show Weapons", "Back" };
            MenuBuilder.setConfigLastAsZero(true);
            int answer = MenuBuilder.menu("Manage Weapons", options);

            if (answer == 1) {
                this.addWeapon();
            } else if (answer == 2) {
                this.removeWeapon();
            } else if (answer == 3) {
                this.showWeapons();
            } else {
                break;
            }
        }
    }

    /**
     * Add a weapon to the weapons available.
     */
    private void addWeapon() {
        String[] labels = { "Name", "Defense Modifier", "Attack Modifier", "Hands Required" };
        String[] dataInput = MenuBuilder.form("Add Weapon", labels);

        int defenseModifier = 0;
        int attackModifier = 0;
        int handsRequired = 0;

        // Check if the defense, attack modifiers and hands required are integers
        // If the defense, attack modifiers and hands required are integers -> Create the new weapon and add it to the weapons available
        // If the defense, attack modifiers and hands required are not integers -> alert the user and ask for the data again
        try {
            defenseModifier = Integer.parseInt(dataInput[1]);
            attackModifier = Integer.parseInt(dataInput[2]);
            handsRequired = Integer.parseInt(dataInput[3]);

            // Create the new weapon
            Weapon weapon = new Weapon(dataInput[0], defenseModifier, attackModifier, handsRequired);

            // Ask for user confirmation
            boolean answer = MenuBuilder.askYesNo("Are you sure you want to add this weapon?");

            // If the user confirms, add the new weapon to the weapons available
            if (answer) {
                Game.weaponsAvailable.add(weapon);
            } else {
                MenuBuilder.alert("Operation Canceled", "The weapon has not been added.");
            }
        } catch (NumberFormatException e) {
            MenuBuilder.alert("Invalid Input", "The defense, attack modifiers and hands required must be integers.");
            this.addWeapon();
        }
    }

    /**
     * Remove a weapon from the weapons available.
     */
    private void removeWeapon() {
        // Prepare the options, print the menu and get the answer
        String[] options = new String[Game.weaponsAvailable.size()];
        for (int i = 0; i < Game.weaponsAvailable.size(); i++) {
            options[i] = Game.weaponsAvailable.get(i).getName();
        }
        int answer = MenuBuilder.menu("Remove Weapon", options) - 1;

        // Ask for user confirmation
        boolean confirm = MenuBuilder.askYesNo("Are you sure you want to remove this weapon?");

        // If the user confirms, remove the weapon from the weapons available
        if (confirm) {
            Game.weaponsAvailable.remove(answer);
        } else {
            MenuBuilder.alert("Operation Canceled", "The weapon has not been removed.");
        }
    }

    /**
     * Print the weapons available to the user.
     */
    private void showWeapons() {
        // Create the weapons data table
        String[] data = new String[Game.weaponsAvailable.size()];

        // Fill the data array with the weapons
        for (int i = 0; i < Game.weaponsAvailable.size(); i++) {
            Weapon weapon = Game.weaponsAvailable.get(i);
            data[i] = weapon.getName() + " --> Defense: " + weapon.getDefense() + " | Attack: " + weapon.getAttack() + " | Hands Required: " + weapon.getHandsRequired();
        }

        // Print the weapons
        MenuBuilder.doc("Weapons", data);
    }

    /**
     * Iterate over the challenges and print ask the admin if he wants to manage each challenge.
     */
    private void manageChallenges() {
        Admin admin = (Admin) this.loggedUser;

        for (Challenge challenge : this.challenges) {
            // If the challenge is not approved yet, print the manage the challenge
            if (!challenge.isApproved()) {
                admin.manageChallenge(challenge);
            }
        }

        MenuBuilder.alert("Challenges Manager", "All challenges have been managed successfully");
    }

    // ============================================================================================[ General Logged Methods ]>>>

    /**
     * Print the ranking of the players.
     */
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
     * Print the manage account menu for the current user. The menu will allow the user to change the nick, password or delete the account.
     */
    private void manageAccount() {
        int answer = -1;

        while (answer != 0 && this.loggedUser != null) {
            String[] options = { "Change Nick", "Change Password", "Delete Account", "Back" };
            MenuBuilder.setConfigLastAsZero(true);
            answer = MenuBuilder.menu("Account Settings", options);

            if (answer == 1) {
                this.changeNick();
            } else if (answer == 2) {
                this.changePassword();
            } else if (answer == 3) {
                this.deleteAccount();
            }
        }
    }

    /**
     * Print a string input prompt to the user to change the nick of the user.
     */
    private void changeNick() {
        String data = MenuBuilder.readString("Change Nick");

        if (data != null) {
            this.loggedUser.setNick(data);
            MenuBuilder.alert("Nick Changed", "Your nick has been changed successfully.");
        }
    }

    /**
     * Print a password input prompt to the user to change the password of the user.
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
     * Print a verification message before deleting the account.
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
     * Log out the current user by setting the logged user to null.
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

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }
}
