package src.challenges;

import java.util.ArrayList;
import src.characters.Character;
import src.characters.CharacterSelection;
import src.characters.Hunter;
import src.characters.Lycanthrope;
import src.characters.Vampire;
// Import statements
import src.users.Player;

public class Fight {

    private int rounds;
    private String date;
    private Player winner;
    private ArrayList<String> log = new ArrayList<String>();
    private Character c1;
    private Character c2;
    private Player[] players;

    // ============================================================================================[ Constructor ]>>>
    public Fight() {}

    public Fight(Player player1, Player player2) {
        this.players = new Player[2];
        this.players[0] = player1;
        this.players[1] = player2;

        this.c1 = createCharacter(player1);
        this.c2 = createCharacter(player2);

        this.start();
    }

    // ============================================================================================[ Private Methods ]>>>
    /**
     * Create a character based on the player's data.
     *
     * @param player Player to create the character from
     * @return Character created
     */
    private Character createCharacter(Player player) {
        Character character;

        // Instantiate the character based on the player's current character
        CharacterSelection cs = player.getCurrentCharacter();
        if (cs == CharacterSelection.HUNTER) {
            character = new Hunter(player);
        } else if (cs == CharacterSelection.LYCANTHROPE) {
            character = new Lycanthrope(player);
        } else if (cs == CharacterSelection.VAMPIRE) {
            character = new Vampire(player);
        } else {
            throw new IllegalArgumentException("Invalid character type");
        }

        // Get the character attributes from the player
        character.setName(player.getName());

        return character;
    }

    /**
     * Start the fight between the two players.
     *
     * <p>The workflow of the fight is as follows:
     * <ol>
     * <li>Get the damage from each character.</li>
     * <li>Log the actions made on this round.</li>
     * <li>Increment the round counter.</li>
     * </ol>
     * </p>
     *
     * <p>The fight will continue until one of the characters is dead.</p>
     */
    private void start() {
        int round = 1;

        log.add("Fight started between " + c1.getName() + " and " + c2.getName());
        log.add("");

        while (!checkFightEnd()) {
            // Get the damage from each character
            int damage1 = c2.getHit(c1);
            int damage2 = c1.getHit(c2);

            // Log the fight
            String msg1 = String.format("Round [%d]", round, c1.getName(), c1.getHealth());
            String msg2 = String.format(" | %s attacks %s inflicting (%d) points of damage", c1.getName(), c2.getName(), damage1);
            String msg3 = String.format(" | %s attacks %s inflicting (%d) points of damage", c2.getName(), c1.getName(), damage2);
            String msg4 = String.format(" └ Fight status: [%s](%d HP) vs [%s](%d HP)", c1.getName(), c1.getHealth(), c2.getName(), c2.getHealth());

            log.add(msg1);
            log.add(msg2);
            log.add(msg3);
            log.add(msg4);
            log.add("");

            // Increment the round counter
            round++;
        }

        chooseWinner();
    }

    /**
     * Check if the fight has ended.
     *
     * @return True if the fight has ended, false otherwise
     */
    private boolean checkFightEnd() {
        return c1.isDead() || c2.isDead();
    }

    /**
     * Choose the winner of the fight.
     *
     * <p>The winner will be stored in the <code>winner</code> attribute. If the fight ended in a draw, the winner will be null.</p>
     */
    private void chooseWinner() {
        log.add("");
        if (c1.isDead() && c2.isDead()) {
            winner = null;
            log.add("The fight ended in a draw");
        } else if (c1.isDead()) {
            winner = players[1];
            log.add("The winner is " + c2.getName());
        } else {
            winner = players[0];
            log.add("The winner is " + c1.getName());
        }
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

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public ArrayList<String> getLog() {
        return log;
    }

    public void setLog(ArrayList<String> log) {
        this.log = log;
    }

    public Character getC1() {
        return c1;
    }

    public void setC1(Character c1) {
        this.c1 = c1;
    }

    public Character getC2() {
        return c2;
    }

    public void setC2(Character c2) {
        this.c2 = c2;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
