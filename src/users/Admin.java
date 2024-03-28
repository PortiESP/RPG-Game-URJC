package src.users;

import src.challenges.Challenge;
import src.characters.*;
import src.characters.CharacterSelection;
import utils.MenuBuilder;

/**
 * Class that represents the Admin user.
 */
public class Admin extends User {

    // ============================================================================================[ Constructor ]>>>

    // Zero Constructor
    public Admin() {}

    // Constructor with parameters
    public Admin(String name, String nick, String password) {
        super(name, nick, password);
    }

    // ============================================================================================[ Public Methods ]>>>

    /**
     * Method to get the score of the player. In this case, the admin does not have a score, but since it is needed to sort the users, it is implemented as 0.
     */
    public int getScore() {
        return 0;
    }

    /**
     * Method to manage a challenge between two players.
     *
     * @param challenge Challenge to manage
     */
    public void manageChallenge(Challenge challenge) {
        // Ask the admin if they want to manage the challenge
        String msg1 = String.format("Do you want to manage the challenge beween %s and %s?", challenge.getChallengerPlayer().getName(), challenge.getChallengedPlayer().getName());
        boolean opt = MenuBuilder.askYesNo(msg1);

        // If the admin does not want to manage this challenge, return
        if (!opt) {
            return;
        }

        // Extract the players from the challenge
        Player player1 = challenge.getChallengerPlayer();
        Player player2 = challenge.getChallengedPlayer();

        // Ban the challenger if the challenged player has been defeated recently
        if (player2.defeatedRecently()) {
            String message = "The challenged player has recently lost a battle. The challenger will be banned.";
            boolean yORn = MenuBuilder.askYesNo(message);
            if (yORn) {
                player1.ban();
                return;
            }
        }

        // Edit the equipment of the players
        this.manageRules(challenge, player1, player2);

        // Adjust the gold of the challenge
        if (!player2.canAfford(challenge.getGold())) {
            challenge.setGold(player2.getGold());
            String msg = String.format("The bet has been adjusted to %d", challenge.getGold());
            MenuBuilder.alert("Challenge warning", msg);
        }

        // Approve the challenge
        challenge.approve();

        // Notify the challenged player
        player2.notifyChallenge(challenge);
    }

    /**
     * Prints a menu to the admin to manage the challenge's rules.
     *
     * <p>Options:
     * <ul>
     * <li>Manage Player 1
     * <li>Manage Player 2
     * <li>Exit
     * </ul>
     *
     * @param challenge Challenge to manage
     * @param player1 Player 1
     * @param player2 Player 2
     */
    private void manageRules(Challenge challenge, Player player1, Player player2) {
        String[] options = { "Manage Player 1", "Manage Player 2", "Exit" };
        int opt = MenuBuilder.menu("Manage Challenge", options);

        if (opt == 1) {
            this.managePlayer(player1);
        } else if (opt == 2) {
            this.managePlayer(player2);
        } else {
            return;
        }
    }

    /**
     * Prints a menu to the admin to manage a player.
     *
     * <p>Options:
     * <ul>
     * <li>Manage Equipment
     * <li>Modify Character
     * <li>Alter Modifiers
     * <li>Modify Special Ability
     * <li>Exit
     * </ul>
     *
     * @param player Player to manage
     * @param armors List of armors
     * @param weapons List of weapons
     */
    private void managePlayer(Player player) {
        String[] options = { "Manage Equipment", "Modify Character", "Alter Modifiers", "Modify Special Ability", "Exit" };

        while (true) {
            MenuBuilder.setConfigLastAsZero(true);
            int opt = MenuBuilder.menu("Manage Player", options);
    
            if (opt == 1) {
                player.manageEquipment();
            } else if (opt == 2) {
                this.modifyCharacter(player.getCurrentCharacter());
            } else if (opt == 3) {
                player.manageModifiers();
            } else if (opt == 4) {
                player.changeSpecialAbility();
            } else {
                break;
            }
        }
    }

    /**
     * Modifies the attributes of the player's character (Lycanthrope, Vampire, Hunter). This menu is designed for admins so they can modify the attributes any the character.
     *
     * @param character Character to modify.
     */
    private void modifyCharacter(CharacterSelection character) {
        if (character == CharacterSelection.LYCANTHROPE) {
            Lycanthrope.modifyAttributes();
        } else if (character == CharacterSelection.VAMPIRE) {
            Vampire.modifyAttributes();
        } else {
            Hunter.modifyAttributes();
        }
    }
}
