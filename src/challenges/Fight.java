package src.challenges;

// Import statements
import src.users.Player;

import java.util.ArrayList;

import src.characters.Character;
import src.characters.CharacterSelection;
import src.characters.Hunter;
import src.characters.Lycanthrope;
import src.characters.Vampire;

public class Fight {
    private int rounds;
    private String date;
    private Player winner;
    private ArrayList<String> log = new ArrayList<String>();
    private Character c1;
    private Character c2;
    private Player[] players;

    // ============================================================================================[ Constructor ]>>>
    public Fight(Player player1, Player player2) {
        this.players = new Player[2];
        this.players[0] = player1;
        this.players[1] = player2;

        this.c1 = createCharacter(player1);
        this.c2 = createCharacter(player2);

        this.start();
    }

    public Fight() {
    }

    // ============================================================================================[ Public Methods ]>>>
    public Player getWinner() {
        return winner;
    }

    // ============================================================================================[ Private Methods ]>>>
    // Method to generate a fight between two players and return the winner
    private void start() {
        int round = 0;

        while (checkFightEnd()) {
            // Get the damage from each character
            int damage1 = c1.attack(c2);
            int damage2 = c2.attack(c1);

            // Log the fight
            String msg1 = String.format("Round [%d]", round, c1.getName(), c1.getHealth());
            String msg2 = String.format("%s attacks %s inflicting (%d) points of damage",
                    c1.getName(), c2.getName(), damage1);
            String msg3 = String.format("%s attacks %s inflicting (%d) points of damage",
                    c2.getName(), c1.getName(), damage2);
            String msg4 = String.format("Round %d status: [%s](%d HP) vs [%s](%d HP)", round, c1.getName(),
                    c1.getHealth(), c2.getName(), c2.getHealth());

            log.add(msg1);
            log.add(msg2);
            log.add(msg3);
            log.add(msg4);

            // Increment the round counter
            round++;
        }

        chooseWinner();
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

    private boolean checkFightEnd() {
        if (c1.isDead() || c2.isDead()) {
            return true;
        } else {
            return false;
        }
    }

    private void chooseWinner() {
        if (c1.isDead() && c2.isDead()) {
            winner = null;
        } else if (c1.isDead()) {
            winner = players[1];
        } else {
            winner = players[0];
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
