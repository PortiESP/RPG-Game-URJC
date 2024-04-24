package tests.challenges;

import static org.junit.Assert.*;

import org.junit.Test;

import src.challenges.Challenge;
import src.users.Player;
import utils.TestingUtils;

public class Test_Challenge {

    @Test
    public void testChallenge() {
        Challenge challenge = new Challenge();
        assertNotNull(challenge);

        Object[][] dataInput = {
            { new Player("1", "1", "1", "9999"), new Player("2", "2", "2", "9999"), 1 },
            { new Player("3", "3", "3", "9999"), new Player("4", "4", "4", "9999"), 2 },
            { new Player("5", "5", "5", "9999"), new Player("6", "6", "6", "9999"), 3 },
            { new Player("7", "7", "7", "9999"), new Player("8", "8", "8", "9999"), 4 }
        };

        for (Object[] data : dataInput) {
            Player player1 = (Player) data[0];
            Player player2 = (Player) data[1];
            int gold = (int) data[2];

            challenge = new Challenge(player1, player2, gold);

            assertEquals(gold, challenge.getGold());
            assertEquals(false, challenge.isAccepted());
            assertEquals(false, challenge.isApproved());
            assertEquals(player1, challenge.getPlayers()[0]);
            assertEquals(player2, challenge.getPlayers()[1]);
            assertEquals(null, challenge.getResult());
            assertEquals(null, challenge.getWinner());
        }
    }

    @Test
    public void testGetOpponent() {
        Challenge challenge = new Challenge();

        Object[][] dataInput = {
                { new Player("1", "1", "1", "9999"), new Player("2", "2", "2", "9999"), 1 },
                { new Player("3", "3", "3", "9999"), new Player("4", "4", "4", "9999"), 2 },
                { new Player("5", "5", "5", "9999"), new Player("6", "6", "6", "9999"), 3 },
                { new Player("7", "7", "7", "9999"), new Player("8", "8", "8", "9999"), 4 }
        };

        for (Object[] data : dataInput) {
            Player player1 = (Player) data[0];
            Player player2 = (Player) data[1];
            int gold = (int) data[2];

            challenge = new Challenge(player1, player2, gold);

            assertEquals(player2, challenge.getOpponent(player1));
            assertEquals(player1, challenge.getOpponent(player2));
        }
    }

    @Test
    // TODO: Fix this test
    public void testIsValid() {
        Player auxPlayer = new Player("1", "1", "1", "9999");
        Object[][] dataInput = {
            { new Player("1", "1", "1", "9999"), new Player("2", "2", "2", "9999"), 1, true, false, true },
            { new Player("3", "3", "3", "9999"), new Player("4", "4", "4", "9999"), 2, false, false, false },
            { new Player("5", "5", "5", "9999"), new Player("6", "6", "6", "9999"), 3, true, true, true },
            { auxPlayer, auxPlayer, 4, false, true, false }
        };

        Challenge auxChallenge = new Challenge();

        for (Object[] data : dataInput) {
            Player loggedUser = (Player) data[0];
            Player opponent = (Player) data[1];
            if ((boolean) data[3]) {
                opponent.setPendingChallenge(auxChallenge);
            }
            opponent.setBanned((boolean) data[4]);

            if ((boolean) data[5]) {
                opponent.setLastLostFight(System.currentTimeMillis() - 22 * 60 * 60 * 1000);
            } else {
                opponent.setLastLostFight(System.currentTimeMillis() - 25 * 60 * 60 * 1000);
            }

            int gold = (int) data[2];
            Challenge challenge = new Challenge(loggedUser, opponent, gold);

            boolean result = true;
            result = result && !((Player) data[0] == (Player) data[1]);
            result = result && !opponent.hasPendingChallenge();
            result = result && !opponent.isBanned();
            result = result && !opponent.defeatedRecently();

            if (!result) {
                if (opponent.defeatedRecently()) {
                    TestingUtils.setInput("1");
                } else {
                    TestingUtils.setInput(" ");
                }
            }

            assertEquals(result, challenge.isValid(loggedUser, opponent));
        }
    }

    // @Test
    // void getChallengedPlayer() {}

    // @Test
    // void getChallengerPlayer() {}

    // @Test
    // void approve() {}

    // @Test
    // void accept() {}

    // @Test
    // void reject() {}

    // @Test
    // void manageFight() {}

    // @Test
    // void getGold() {}

    // @Test
    // void setGold() {}

    // @Test
    // void isAccepted() {}

    // @Test
    // void setAccepted() {}

    // @Test
    // void isApproved() {}
}
