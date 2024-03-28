package src.users;

// Import statements
import java.util.*;
import src.Game;
import src.abilities.Discipline;
import src.abilities.Don;
import src.abilities.SpecialAbility;
import src.abilities.Talent;
import src.challenges.Challenge;
import src.characters.CharacterSelection;
import src.equipment.Armor;
import src.equipment.Weapon;
import src.modifiers.Modifier;
import utils.Const;
import utils.MenuBuilder;

/**
 * Class that represents the Player user.
 */
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
    private Modifier[] modifiers = new Modifier[2];
    private SpecialAbility specialAbility;
    private List<Challenge> challenges = new ArrayList<>();

    // ============================================================================================[ Constructor ]>>>

    // Zero Constructor
    public Player() {}

    // Constructor with parameters
    public Player(String name, String nick, String password, String id) {
        super(name, nick, password);
        this.id = id;
        this.pendingNotification = false;
        this.banned = false;
        this.gold = Const.INITIAL_GOLD;
    }

    // ============================================================================================[ Public Methods ]>>>

    /**
     * Adds a challenge to the history of the player. The history of challenges is stored in <code>challenges</code> attribute.
     * @param challenge Challenge to manage
     */
    public void addChallengeToHistory(Challenge challenge) {
        this.challenges.add(challenge);
    }

    /**
     * Checks if the player has challenges in the history.
     * @return <code>true</code> if the player has challenges, <code>false</code> otherwise.
     */
    public boolean hasChallenges() {
        return !this.challenges.isEmpty();
    }

    /**
     * Gets the player's score. The score is the amount of gold the player has.
     *
     * @return The amount of gold the player has.
     */
    public int getScore() {
        return this.gold;
    }

    /**
     * Shows the player's information.
     *
     * <p>The information shown is:
     * <ul>
     *  <li>Nick</li>
     *  <li>Name</li>
     *  <li>Gold</li>
     *  <li>Banned</li>
     *  <li>Character</li>
     *  <li>Armor</li>
     *  <li>Weapon 1</li>
     *  <li>Weapon 2</li>
     *  <li>Pending challenge</li>
     * </ul>
     * </p>
     */
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
            "Modifier 1" + (this.modifiers[0] != null ? this.modifiers[0].getName() : "None"),
            "Modifier 2" + (this.modifiers[1] != null ? this.modifiers[1].getName() : "None"),
            "Special Ability" + (this.specialAbility != null ? this.specialAbility.getName() : "None"),
            "Pending challenge: " + (this.pendingChallenge != null ? "Yes" : "No"),
        };

        // Show the data
        MenuBuilder.doc("Player: " + this.getName(), data);
    }

    /**
     * Checks if the player has been defeated recently. A player is considered to have been defeated recently if the time elapsed since the last lost fight is less than 24 hours.
     * @return <code>true</code> if the player has been defeated recently, <code>false</code> otherwise.
     */
    public boolean defeatedRecently() {
        long dayInMillis = 24 * 60 * 60 * 1000;
        return (System.currentTimeMillis() - this.lastLostFight) < dayInMillis;
    }

    /**
     * Bans the player.
     */
    public void ban() {
        this.banned = true;

        // Remove all pending challenges and notifications (just in case)
        this.pendingChallenge = null;
        this.pendingNotification = false;
    }

    /**
     * Remove the player's ban.
     */
    public void unban() {
        this.banned = false;
    }

    /**
     * Manages the player's modifiers. This method is called when the player wants to manage his modifiers.
     */
    public void manageModifiers() {
        // Show the current modifiers
        this.showModifiers();

        // Ask the user what he wants to do
        String[] options = { "Change modifier 1", "Change modifier 2", "Exit" };

        // Loop until the user wants to exit
        while (true) {
            MenuBuilder.setConfigLastAsZero(true);
            int option = MenuBuilder.menu("Modifiers", options);

            // Manage the option
            if (option == 1) {
                this.changeModifier(0);
            } else if (option == 2) {
                this.changeModifier(1);
            } else {
                break;
            }
        }
    }

    /**
     * Changes the modifier of the player in the specified index.
     */
    public void changeModifier(int modifierIndex) {
        List<Modifier> modifiersAvailable = Game.modifiersAvailable;

        // Show the available modifiers
        String[] options = new String[modifiersAvailable.size()];
        for (int i = 0; i < modifiersAvailable.size(); i++) {
            options[i] = modifiersAvailable.get(i).toString();
        }

        // Ask the user what modifier he wants
        int option = MenuBuilder.menu("Choose a modifier", options) - 1;
        Modifier modifierSelected = modifiersAvailable.get(option);

        // Equip the modifier
        this.modifiers[modifierIndex] = modifierSelected;

        // Show confirmation message
        String message = "You have equipped the " + modifierSelected.getName() + " modifier.";
        MenuBuilder.alert("Modifier equipped", message);
    }

    /**
     * Prints the active modifiers of the player.
     */
    public void showModifiers() {
        // Generate the data to show
        String[] data = {
            "Modifier 1: " + (this.modifiers[0] != null ? this.modifiers[0].getName() : "None"),
            "Modifier 2: " + (this.modifiers[1] != null ? this.modifiers[1].getName() : "None")
        };

        // Show the data
        MenuBuilder.doc("Modifiers", data);
    }

    /**
     * Changes the special ability of the player in the specified index.
     */
    public void changeSpecialAbility() {
        String[] options;
        int characterAbility;

        // Get the available special abilities depending on the character
        if (this.currentCharacter == CharacterSelection.VAMPIRE) {
            options = Discipline.listAvailableDisciplines();
            characterAbility = 0;
        } else if (this.currentCharacter == CharacterSelection.LYCANTHROPE) {
            options = Don.listAvailableDones();
            characterAbility = 1;
        } else {
            options = Talent.listAvailableTalents();
            characterAbility = 2;
        }

        // Ask the user what special ability he wants
        int option = MenuBuilder.menu("Choose a special ability", options) - 1;

        // Get the special ability selected
        SpecialAbility specialAbilitySelected;
        if (characterAbility == 0) {
            specialAbilitySelected = Game.disciplinesAvailable.get(option);
        } else if (characterAbility == 1) {
            specialAbilitySelected = Game.donesAvailable.get(option);
        } else {
            specialAbilitySelected = Game.talentsAvailable.get(option);
        }

        // Equip the special ability
        this.specialAbility = specialAbilitySelected;

        // Show confirmation message
        String message = "You have equipped the " + specialAbilitySelected.getName() + " special ability.";
        MenuBuilder.alert("Special Ability equipped", message);
    }

    /**
     * Prints the active special abilities of the player.
     */
    public void showSpecialAbilities() {
        // Generate the data to show
        String[] data = {
            "Special Ability:" + (this.specialAbility != null ? this.specialAbility.getName() : "None"),
        };

        // Show the data
        MenuBuilder.doc("Special Abilities", data);
    }

    /**
     * Prints a menu to manage the player's equipment.
     *
     * <p>The options are:
     * <ul>
     * <li>Change armor
     * <li>Change weapon 1
     * <li>Change weapon 2
     * <li>Exit
     * </ul>
     * </p>
     * <br>
     */
    public void manageEquipment() {
        // Show the current equipment
        this.showEquipment();

        // Ask the user what he wants to do
        String[] options = {
                        "Change armor", "Change weapon 1", "Change weapon 2", "Change Special Ability",
                        "Change Modifiers", "Exit"
                    };

        // Loop until the user wants to exit
        while (true) {
            MenuBuilder.setConfigLastAsZero(true);
            int option = MenuBuilder.menu("Equipment", options);

            // Manage the option
            if (option == 1) {
                this.changeArmor();
            } else if (option == 2) {
                this.changeWeapon(0);
            } else if (option == 3) {
                this.changeWeapon(1);
            } else if (option == 4) {
                this.changeSpecialAbility();
            } else if (option == 5) {
                this.manageModifiers();
            } else {
                break;
            }
        }
    }

    /**
     * Shows the equipment of the player.
     *
     * <p>The equipment shown is:
     * <ul>
     *  <li>Armor</li>
     *  <li>Weapon 1</li>
     *  <li>Weapon 2</li>
     * </ul>
     * </p>
     */
    public void showEquipment() {
        // Generate the data to show
        String[] data = {
            "Armor: " + (this.armor != null ? this.armor.getName() : "None"),
            "Weapon 1: " + (this.weapons[0] != null ? this.weapons[0].getName() : "None"),
            "Weapon 2: " + (this.weapons[1] != null ? this.weapons[1].getName() : "None"),
            "Special Ability: " + (this.specialAbility != null ? this.specialAbility.getName() : "None"),
            "Modifier 1: " + (this.modifiers[0] != null ? this.modifiers[0].getName() : "None"),
            "Modifier 2: " + (this.modifiers[1] != null ? this.modifiers[1].getName() : "None")
        };

        // Show the data
        MenuBuilder.doc("Equipment", data);
    }

    /**
     * Changes the armor of the player.
     */
    public void changeArmor() {
        List<Armor> armorsAvailable = Game.armorsAvailable;

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

    /**
     * Changes the weapon of the player.
     * @param weaponIndex Index of the weapon to change (0 or 1).
     */
    public void changeWeapon(int weaponIndex) {
        List<Weapon> weaponsAvailable = Game.weaponsAvailable;

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

    /**
     * Notifies the player of a challenge. This method is used once the admin has <i>approved</i> the challenge to let the challenged player know that he has a pending challenge.
     *
     * @param challenge Challenge created by the challenger.
     */
    public void notifyChallenge(Challenge challenge) {
        this.pendingNotification = true;
        this.pendingChallenge = challenge;
    }

    /**
     * Manages the notifications of the player. This method is called when the player logs in.
     * If the player has a pending notification, it will show a message to the player asking if he wants to accept the challenge.
     */
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

    /**
     * Pays gold to another player.
     *
     * @param amount Amount of gold to pay.
     * @param player Player to pay the gold to.
     */
    public void payGoldTo(int amount, Player player) {
        this.gold -= amount;
        player.gold += amount;
    }

    /**
     * Checks if the player can afford a certain amount of gold.
     *
     * @param amount Amount of gold to check.
     * @return <code>true</code> if the player can afford the amount, <code>false</code> otherwise.
     */
    public boolean canAfford(int amount) {
        return this.gold >= amount;
    }

    /**
     * Accepts the pending challenge. This method is called when the player decides to <strong>accept</strong> the challenge.
     * The challenge is accepted, the fight is managed (<em>started, runned and finished</em>) and the result is shown to the player.
     */
    public void acceptChallenge() {
        // Get the pending challenge
        Challenge challenge = this.pendingChallenge;

        // Accept the challenge
        challenge.accept();
        // Manage the fight
        challenge.manageFight();

        // Show the result
        Player winner = challenge.getWinner();
        String msg = "";
        if (winner == challenge.getChallengedPlayer()) {
            msg = "You have won the fight!";
        } else {
            msg = "You have lost the fight!";
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

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public boolean hasPendingChallenge() {
        return this.pendingChallenge != null;
    }

    public Modifier[] getModifiers() {
        return this.modifiers;
    }

    public void setModifiers(Modifier[] modifiers) {
        this.modifiers = modifiers;
    }

    public SpecialAbility getSpecialAbility() {
        return this.specialAbility;
    }

    public void setSpecialAbilities(SpecialAbility specialAbility) {
        this.specialAbility = specialAbility;
    }
}
