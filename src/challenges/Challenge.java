package src.challenges;

import java.util.ArrayList;
import src.users.Player;
import utils.MenuBuilder;

/**
 * Class that represents a challenge between two players.
 *
 * <ol>
 *  <li>A player can challenge another player to a fight. The challenger will set the gold amount for the challenge.</li>
 *  <li>An admin must approve the challenge in order to be valid. The challenged player will be notified.</li>
 *  <li>The challenged player can accept or reject the challenge. If accepted, a fight will be started.</li>
 * </ol>
 *
 * <ul>
 *  <li>The winner will receive the gold from the challenge.</li>
 *  <li>The loser will pay a fee to the winner.</li>
 *  <li>The challenge will be added to the history of both players.</li>
 * </ul>
 */
public class Challenge {

    private int gold;
    private boolean accepted;
    private boolean approved;
    private Player[] players = new Player[2];
    private Fight result;
    private Player winner;

    // ============================================================================================[ Constructor ]>>>
    public Challenge() {}

    public Challenge(Player player1, Player player2, int gold) {
        this.gold = gold;
        this.accepted = false;
        this.approved = false;
        this.players[0] = player1;
        this.players[1] = player2;
        this.result = null;
        this.winner = null;
    }

    // ============================================================================================[ Public Methods ]>>>

    /**
     * Get the opponent of a given player in the challenge.
     *
     * @param player Player to get the opponent from
     * @return Opponent player
     */
    public Player getOpponent(Player player) {
        if (player.equals(this.players[0])) {
            return this.players[1];
        } else {
            return this.players[0];
        }
    }

    /**
     * Check if the challenge is valid. If the challenge is not valid, an alert will be shown.
     *
     * <ul>
     * <li>The opponent is not the same as the logged user.</li>
     * <li>The opponent has not been challenged.</li>
     * <li>The opponent is not banned.</li>
     * <li>The opponent has not recently battled.</li>
     * </ul>
     *
     * @param loggedUser Player that is logged in
     * @param opponent Player that is being challenged
     * @return True if the challenge is valid, false otherwise
     */
    public boolean isValid(Player loggedUser, Player opponent) {
        // Check if the opponent is the same as the logged user
        if (opponent == loggedUser) {
            MenuBuilder.alert("Invalid Opponent", "You cannot challenge yourself.");
            return false;
        }
        // Check if the opponent has already been challenged
        else if (opponent.hasPendingChallenge()) {
            MenuBuilder.alert("Invalid Opponent", "The opponent has already been challenged.");
            return false;
        }
        // Check if the opponent is banned
        else if (opponent.isBanned()) {
            MenuBuilder.alert("Invalid Opponent", "The opponent is banned.");
            return false;
        }
        // Check if the opponent has recently battled
        else if (opponent.defeatedRecently()) {
            boolean yORn = MenuBuilder.askYesNo("The opponent has recently lost a battle, are you sure you want to continue?");
            if (!yORn) {
                return false;
            }
        }

        return true;
    }

    /**
     * Get the challenged player.
     *
     * @return Challenged player
     */
    public Player getChallengedPlayer() {
        return this.players[1];
    }

    /**
     * Get the challenger player.
     *
     * @return Challenger player
     */
    public Player getChallengerPlayer() {
        return this.players[0];
    }

    /**
     * Set the approved status of the challenge to true and set the pending challenge of the challenged player. This method is called by an admin.
     */
    public void approve() {
        this.approved = true;
        this.getChallengedPlayer().setPendingChallenge(this);
    }

    /**
     * Set the accepted status of the challenge to true. This method is called by the challenged player.
     */
    public void accept() {
        this.accepted = true;
    }

    /**
     * Set the accepted status of the challenge to false and set the winner to the challenger player.
     * The challenged player will pay a fee to the challenger player.
     * This method is called by the challenged player.
     */
    public void reject() {
        this.accepted = false;
        this.winner = this.getChallengerPlayer();
        Player loser = this.getChallengedPlayer();
        int fee = (int) (this.gold * 0.1);

        // Check if the loser can afford the fee
        // Can afford -> Pay the fee
        // Cannot afford -> Pay all the gold (this scenario should not happen, but it's here just in case)
        if (loser.canAfford(fee)) {
            loser.payGoldTo(fee, this.winner);
        } else {
            String msg = "The challenged player does not have enough funds to pay the fee. The player will be banned.";
            MenuBuilder.alert("Insufficient funds", msg);
            int gold = loser.getGold();
            loser.payGoldTo(gold, this.winner);
        }
    }

    /**
     * Manage the fight between the players. The fight will be started, the result will be printed, and the challenge will be finished.
     * This method is called by the challenged player after calling the {@Code accept()} method.
     */
    public void manageFight() {
        this.startFight();
        this.printResult();

        this.finishChallenge();
    }

    // ============================================================================================[ Private Methods ]>>>
    /**
     * Instantiate a fight between the players. This action will trigger the fight.
     * The winner will be set and the result of the fight will be stored.
     */
    private void startFight() {
        this.result = new Fight(this.players[0], this.players[1]);
        this.winner = this.result.getWinner();
    }

    /**
     * Print the log of the fight.
     */
    private void printResult() {
        ArrayList<String> log = this.result.getLog();
        String[] fixedLog = log.toArray(String[]::new);

        MenuBuilder.doc("Challenge Result", fixedLog);
    }

    /**
     * This method will be called after the fight is finished.
     *
     * The following actions will be performed:
     * <ul>
     * <li>Reset the pending challenges of the players.</li>
     * <li>Add the fight to the history of both players.</li>
     * <li>Update the gold of the players.</li>
     * </ul>
     */
    private void finishChallenge() {
        Player p1 = this.players[0];
        Player p2 = this.players[1];

        // Reset the pending challenges
        p1.setPendingChallenge(null);
        p2.setPendingChallenge(null);

        // Add the fight to the history
        p1.addChallengeToHistory(this);
        p2.addChallengeToHistory(this);

        // Update the gold
        if (this.winner == p1) {
            p2.payGoldTo(this.gold, p1);
        } else {
            p1.payGoldTo(this.gold, p2);
        }
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Fight getResult() {
        return result;
    }

    public void setResult(Fight result) {
        this.result = result;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
