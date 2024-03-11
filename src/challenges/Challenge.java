package src.challenges;

// Import statements
import src.users.Player;

public class Challenge {
    private int gold;
    private boolean accepted;
    private boolean approved;
    private Player [] players = new Player[2];
    private Fight result;
    private Player winner;

    // Constructor ========================================================================================================
    public Challenge(Player player1, Player player2, int gold) {
        this.gold = gold;
        this.accepted = false;
        this.approved = false;
        this.players[0] = player1;
        this.players[1] = player2;
        this.result = null;
        this.winner = null;
    }

    // Public Methods =====================================================================================================

    // Method to get the opponent
    public Player getOpponent(Player player) {
        if (player.equals(this.players[0])) {
            return this.players[1];
        } else {
            return this.players[0];
        }
    }

    // Getters & Setters ==================================================================================================
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
