package src.challenges;

// Import statements
import src.users.Player;

public class Fight {
    private int rounds;
    private String date;
    private Player winner;
    private String[] log;

    // ============================================================================================[ Constructor ]>>>
    public Fight() {
    }

    // Method to generate a fight between two players and return the winner
    public Fight(Player player1, Player player2) {
        // Fight logic
        // If there is a tie, winner = null
        // If player1 wins, winner = player1
        // If player2 wins, winner = player2
        // TODO: Implement the fight logic

    }

    // ============================================================================================[ Public Methods ]>>>
    public Player getWinner() {
        return winner;
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
