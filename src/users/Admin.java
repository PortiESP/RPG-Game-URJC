package src.users;

import java.util.List;
import src.challenges.Challenge;
import src.characters.CharacterSelection;
import src.characters.*;
import src.equipment.*;
import utils.MenuBuilder;

public class Admin extends User {

    // ============================================================================================[ Constructor ]>>>

    // Zero Constructor
    public Admin() {}

    // Constructor with parameters
    public Admin(String name, String nick, String password) {
        super(name, nick, password);
    }

    // ============================================================================================[ Public Methods ]>>>

    // Method to get the score
    public int getScore() {
        return 0;
    }

    // Method to show the user information
    public void showInfo() {
        // Generate the data to show
        String[] data = { "Nick: " + this.getNick(), "Name: " + this.getName() };

        // Show the data
        MenuBuilder.doc("Player: " + this.getName(), data);
    }

    // Method to manage a challenge
    public void manageChallenge(Challenge challenge, List<Armor> armors, List<Weapon> weapons) {
        boolean opt = MenuBuilder.askYesNo(
            String.format("Do you want to manage the challenge beween %s and %s?", challenge.getChallengerPlayer().getName(), challenge.getChallengedPlayer().getName())
        );
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
        this.manageRules(challenge, player1, player2, armors, weapons);

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

    // Method to manage the rules of the challenge
    private void manageRules(Challenge challenge, Player player1, Player player2, List<Armor> armors, List<Weapon> weapons) {
        String[] options = { "Manage Player 1", "Manage Player 2", "Exit" };
        int opt = MenuBuilder.menu("Manage Challenge", options);

        if (opt == 1) {
            this.managePlayer(player1, armors, weapons);
        } else if (opt == 2) {
            this.managePlayer(player2, armors, weapons);
        } else {
            return;
        }
    }

    // Method to manage a player
    private void managePlayer(Player player, List<Armor> armors, List<Weapon> weapons) {
        String[] options = { "Manage Equipment", "Modify Character", "Exit" };
        int opt = MenuBuilder.menu("Manage Player", options);

        if (opt == 1) {
            player.manageEquipment(armors, weapons);
        } else if (opt == 2) {
            this.modifyCharacter(player);
        } else {
            return;
        }
    }

    // Method to modify a character given a player
    private void modifyCharacter(Player player) {
        CharacterSelection character = player.getCurrentCharacter();
        if (character == CharacterSelection.LYCANTHROPE) {
            Lycanthrope.modifyAttributes();
        } else if (character == CharacterSelection.VAMPIRE) {
            Vampire.modifyAttributes();
        } else {
            Hunter.modifyAttributes();
        }
    }
}