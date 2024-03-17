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
    public Player() {
    }

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
    public void addChallenge(Challenge challenge) {
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

    // Manage the equipment
    public void manageEquipment() {
        // TODO: Implement
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

        // If the player has a pending challenge, ask if he wants to accept it
        String message = "You have a pending challenge. Do you want to accept it?";
        boolean yORn = utils.MenuBuilder.askYesNo(message);
        if (yORn) {
            this.pendingChallenge.accept();
            this.pendingChallenge.startFight();
        } else {
            String msg = "The challenge has been rejected. You will have to pay a 10% fee of the bet.";
            MenuBuilder.alert("Challenge warning", msg);
            this.pendingChallenge.reject();
        }

    }

    public void goldTransaction(int amount, Player player) {
        this.gold += amount;
        player.gold -= amount;
    }

    public boolean canAfford(int amount) {
        return this.gold >= amount;
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
