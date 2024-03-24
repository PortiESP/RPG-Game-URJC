package src.users;

// Import statements
import java.util.*;
import src.challenges.Challenge;
import src.characters.CharacterSelection;
import src.equipment.Armor;
import src.equipment.Weapon;
import utils.Const;
import utils.MenuBuilder;

public class Player extends User {

    private String id;
    private Challenge pendingChallenge;
    private boolean pendingNotification;
    private boolean banned;
    private int gold;
    private long lastLostFight;
    private CharacterSelection currentCharacter;
    private Armor armor;
    private Weapon[] weapons = new Weapon[2];
    private List<Challenge> challenges = new ArrayList<>();

    // ============================================================================================[ Constructor ]>>>

    // Zero Constructor
    public Player() {}

    // Constructor with parameters
    public Player(String name, String nick, String password, String id) {
        super(name, nick, password);
        this.id = id;
        this.pendingChallenge = null;
        this.pendingNotification = false;
        this.banned = false;
        this.gold = Const.INITIAL_GOLD;
        this.lastLostFight = 0;
        this.currentCharacter = null;
        this.armor = null;
        this.weapons[0] = null;
        this.weapons[1] = null;
    }

    // ============================================================================================[ Public Methods ]>>>

    // Method to add a challenge
    public void addChallengeToHistory(Challenge challenge) {
        this.challenges.add(challenge);
    }

    // Method to check if the player has at least one challenge
    public boolean hasChallenges() {
        return !this.challenges.isEmpty();
    }

    // Method to get the score
    public int getScore() {
        return this.gold;
    }

    // Method to show the user information
    public void showInfo() {
        // Generate the data to show
        String[] data = {
            "Nick: " + this.getNick(),
            "Name: " + this.getName(),
            "Gold: " + this.gold,
            "Banned: " + (this.banned ? "Yes" : "No"),
            "Character: " + this.currentCharacter.name().toLowerCase(),
            "Armor: " + (this.armor != null ? this.armor.getName() : "None"),
            "Weapon 1: " + (this.weapons[0] != null ? this.weapons[0].getName() : "None"),
            "Weapon 2: " + (this.weapons[1] != null ? this.weapons[0].getName() : "None"),
            "Pending challenge: " + (this.pendingChallenge != null ? "Yes" : "No"),
        };

        // Show the data
        MenuBuilder.doc("Player: " + this.getName(), data);
    }

    // Return if the user has been defeated in the last 24 hours
    public boolean defeatedRecently() {
        long dayInMillis = 24 * 60 * 60 * 1000;
        return (System.currentTimeMillis() - this.lastLostFight) < dayInMillis;
    }

    // Ban player
    public void ban() {
        this.banned = true;

        // Remove all pending challenges and notifications
        this.pendingChallenge = null;
        this.pendingNotification = false;
    }

    // Unban player
    public void unban() {
        this.banned = false;
    }

    // Manage the equipment
    public void manageEquipment(List<Armor> armorsAvailable, List<Weapon> weaponsAvailable) {
        // Show the current equipment
        this.showEquipment();

        // Ask the user what he wants to do
        String[] options = { "Change armor", "Change weapon 1", "Change weapon 2", "Exit" };

        // Loop until the user wants to exit
        while (true) {
            MenuBuilder.setConfigLastAsZero(true);
            int option = MenuBuilder.menu("Equipment", options);

            // Manage the option
            if (option == 1) {
                this.changeArmor(armorsAvailable);
            } else if (option == 2) {
                this.changeWeapon(0, weaponsAvailable);
            } else if (option == 3) {
                this.changeWeapon(1, weaponsAvailable);
            } else {
                break;
            }
        }
    }

    // Method to show the equipment
    public void showEquipment() {
        // Generate the data to show
        String[] data = {
            "Armor: " + (this.armor != null ? this.armor.getName() : "None"),
            "Weapon 1: " + (this.weapons[0] != null ? this.weapons[0].getName() : "None"),
            "Weapon 2: " + (this.weapons[1] != null ? this.weapons[1].getName() : "None"),
        };

        // Show the data
        MenuBuilder.doc("Equipment", data);
    }

    // Method to change the armor
    public void changeArmor(List<Armor> armorsAvailable) {
        // Show the available armors
        String[] options = new String[armorsAvailable.size()];
        for (int i = 0; i < armorsAvailable.size(); i++) {
            options[i] = armorsAvailable.get(i).toString();
        }

        // Ask the user what armor he wants
        int option = MenuBuilder.menu("Choose an armor", options) - 1;
        this.armor = armorsAvailable.get(option);

        // Show confirmation message
        String message = "You have equipped the " + this.armor.getName() + " armor.";
        MenuBuilder.alert("Armor equipped", message);
    }

    // Method to change a weapon
    public void changeWeapon(int weaponIndex, List<Weapon> weaponsAvailable) {
        // Show the available weapons
        String[] options = new String[weaponsAvailable.size()];
        for (int i = 0; i < weaponsAvailable.size(); i++) {
            options[i] = weaponsAvailable.get(i).toString();
        }

        // Ask the user what weapon he wants
        int option = MenuBuilder.menu("Choose a weapon", options) - 1;
        Weapon weaponSelected = weaponsAvailable.get(option);

        // Check if the total hands required are less than or equal to the hands available
        if (this.weapons[1 - weaponIndex] != null && this.weapons[1 - weaponIndex].getHandsRequired() + weaponSelected.getHandsRequired() > 2) {
            MenuBuilder.alert("Error", "You need to unequip the other weapon first to equip this one.");
            return;
        }

        // Equip the weapon
        this.weapons[weaponIndex] = weaponSelected;
        // Show confirmation message
        String message = "You have equipped the " + weaponSelected.getName() + " weapon.";
        MenuBuilder.alert("Weapon equipped", message);
    }

    // Notify the player about a challenge
    public void notifyChallenge(Challenge challenge) {
        this.pendingNotification = true;
        this.pendingChallenge = challenge;
    }

    // Method to manage the notifications
    public void manageNotifications() {
        // If the player has no pending notifications, do nothing
        if (!this.pendingNotification) {
            return;
        }

        // If the player has a pending challenge:

        // Get the opponent
        Player opponent = this.pendingChallenge.getOpponent(this);

        // Show the notification
        String message = "You have a pending challenge from " + opponent.getName();
        MenuBuilder.alert("Challenge Notification", message);

        String[] challengeData = { "Opponent: " + opponent.getName(), "Gold: " + this.pendingChallenge.getGold() };
        MenuBuilder.doc("Challenge", challengeData);

        // Ask if he wants to accept it
        message = "Do you want to accept the challenge from " + opponent.getName() + "?";
        boolean yORn = MenuBuilder.askYesNo(message);

        if (yORn) {
            this.acceptChallenge();
        } else {
            String msg = "The challenge has been rejected. You will have to pay a 10% fee of the bet.";
            MenuBuilder.alert("Challenge Warning", msg);
            this.pendingChallenge.reject();
        }

        // Reset the notification
        this.pendingNotification = false;
    }

    public void payGoldTo(int amount, Player player) {
        this.gold -= amount;
        player.gold += amount;
    }

    public boolean canAfford(int amount) {
        return this.gold >= amount;
    }

    public void acceptChallenge() {
        Challenge challenge = this.pendingChallenge;

        challenge.accept();
        challenge.manageFight();

        Player winner = challenge.getWinner();
        String msg = "";
        if (winner == challenge.getChallengedPlayer()) {
            msg = "You have won the fight!";
        } else {
            msg = "You have lost the fight!";
            this.lastLostFight = System.currentTimeMillis();
        }
        MenuBuilder.alert("Fight Result", msg);
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Challenge getPendingChallenge() {
        return pendingChallenge;
    }

    public void setPendingChallenge(Challenge pendingChallenge) {
        this.pendingChallenge = pendingChallenge;
    }

    public boolean isPendingNotification() {
        return pendingNotification;
    }

    public void setPendingNotification(boolean pendingNotification) {
        this.pendingNotification = pendingNotification;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public long getLastLostFight() {
        return lastLostFight;
    }

    public void setLastLostFight(long lastLostFight) {
        this.lastLostFight = lastLostFight;
    }

    public CharacterSelection getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(CharacterSelection currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Weapon[] getWeapons() {
        return weapons;
    }

    public void setWeapons(Weapon[] weapons) {
        this.weapons = weapons;
    }

    // TODO: Implement as an iterator
    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public boolean hasPendingChallenge() {
        return this.pendingChallenge != null;
    }
}
