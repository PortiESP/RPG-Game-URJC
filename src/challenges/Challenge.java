package src.challenges;

// Import statements
import src.users.Player;
import utils.MenuBuilder;

public class Challenge {
    private int gold;
    private boolean accepted;
    private boolean approved;
    private Player[] players = new Player[2];
    private Fight result;
    private Player winner;

    // ============================================================================================[ Constructor ]>>>
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

    // Method to get the opponent
    public Player getOpponent(Player player) {
        if (player.equals(this.players[0])) {
            return this.players[1];
        } else {
            return this.players[0];
        }
    }

    // Verify if the challenge is valid
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
            boolean yORn = MenuBuilder
                    .askYesNo("The opponent has recently lost a battle, are you sure you want to continue?");
            if (!yORn) {
                return false;
            }
        }

        return true;
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
