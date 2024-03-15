package src.challenges;

// Import statements
import src.users.Player;
import src.characters.Character;
import src.characters.CharacterSelection;
import src.characters.Hunter;
import src.characters.Lycanthrope;
import src.characters.Vampire;

public class Fight {
    private int rounds;
    private String date;
    private Player winner;
    private String[] log;
    private Character c1;
    private Character c2;

    // ============================================================================================[ Constructor ]>>>
    public Fight(Player player1, Player player2) {
        this.c1 = createCharacter(player1);
        this.c2 = createCharacter(player2);

        this.start();
    }

    // ============================================================================================[ Public Methods ]>>>
    public Player getWinner() {
        return winner;
    }

    // ============================================================================================[ Private Methods ]>>>
    // Method to generate a fight between two players and return the winner
    private void start() {
        // Fight logic
        // If there is a tie, winner = null
        // If player1 wins, winner = player1
        // If player2 wins, winner = player2

    }

    private Character createCharacter(Player player) {
        Character character;

        // Type of character
        CharacterSelection cs = player.getCurrentCharacter();
        if (cs == CharacterSelection.HUNTER) {
            character = new Hunter();
        } else if (cs == CharacterSelection.LYCANTHROPE) {
            character = new Lycanthrope();
        } else if (cs == CharacterSelection.VAMPIRE) {
            character = new Vampire();
        } else {
            throw new IllegalArgumentException("Invalid character type");
        }

        // Get the character attributes from the player
        character.setName(player.getName());

        return character;
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
