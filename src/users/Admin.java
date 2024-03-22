package src.users;

import src.challenges.Challenge;
import utils.MenuBuilder;

public class Admin extends User {

    // ============================================================================================[ Constructor ]>>>

    // Zero Constructor
    public Admin() {
    }

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
        String[] data = {
                "Nick: " + this.getNick(),
                "Name: " + this.getName(),
        };

        // Show the data
        MenuBuilder.doc("Player: " + this.getName(), data);
    }

    // Method to manage a challenge
    public void manageChallenge(Challenge challenge) {
        boolean opt = MenuBuilder.askYesNo(String.format("Do you want to manage the challenge beween %s and %s?",
                challenge.getChallengerPlayer().getName(), challenge.getChallengedPlayer().getName()));
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
        player1.manageEquipment();
        player2.manageEquipment();

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
}
