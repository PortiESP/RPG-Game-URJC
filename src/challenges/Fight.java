package src.challenges;

// Import statements
import src.users.Player;

public class Fight {
    private int rounds;
    private String date;

    // ============================================================================================[ Constructor ]>>>
    public Fight() {
    }

    // ============================================================================================[ Public Methods ]>>>

    // Method to generate a fight between two players and return the winner
    public Player generateFight(Player player1, Player player2) {
        // Fight logic
        // If there is a tie, return null
        // If player1 wins, return player1
        // If player2 wins, return player2

        return null;
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
