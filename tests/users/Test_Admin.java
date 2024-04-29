package tests.users;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import src.users.Admin;

public class Test_Admin {

    @Test
    public void getScore() {
        Admin admin = new Admin();
        assertEquals(0, admin.getScore());
    }

    // @Test
    // public void manageChallenge() {
    //     // Create test objects
    //     Player challenger = new Player("John");
    //     Player challenged = new Player("Jane");
    //     Challenge challenge = new Challenge(challenger, challenged);
    //     Admin admin = new Admin();

    //     // Set up test conditions
    //     challenge.setGold(100);
    //     challenged.setGold(50);

    //     // Call the method under test
    //     admin.manageChallenge(challenge);

    //     // Assert the expected outcomes
    //     assertTrue(challenge.isApproved());
    //     assertEquals(50, challenge.getGold());
    //     assertEquals("Do you want to manage the challenge between John and Jane?", MenuBuilder.getLastPrompt());
    //     assertTrue(MenuBuilder.getLastResponse()); // Assuming the admin chooses to manage the challenge
    //     assertFalse(challenger.isBanned());
    //     assertEquals("The bet has been adjusted to 50", MenuBuilder.getLastAlert());
    //     assertEquals(challenge, challenged.getLastNotifiedChallenge());
    // }

    // @Test
    // public void testManageRules() {
    //     // Create an instance of Admin
    //     Admin admin = new Admin();

    //     // Create two players
    //     Player player1 = new Player("Player 1");
    //     Player player2 = new Player("Player 2");

    //     // Create a challenge
    //     Challenge challenge = new Challenge(player1, player2, 100);

    //     // Call the manageRules method
    //     admin.manageRules(challenge, player1, player2);

    //     // Assert that the challenge has been managed correctly
    //     // Add your assertions here
    // }

    // @Test
    // public void managePlayer() {
    //     // Create an instance of Admin
    //     Admin admin = new Admin();

    //     // Create a mock player
    //     Player player = new Player();

    //     // Set up the mock player's initial state
    //     player.setCurrentCharacter(CharacterSelection.LYCANTHROPE);

    //     // Call the managePlayer method
    //     admin.managePlayer(player);

    //     // Assert that the player's state has been modified as expected
    //     // (e.g., equipment managed, character modified, modifiers altered, special ability modified)
    //     // Add your assertions here
    // }

    // @Test
    // public void modifyCharacter() {
    //     Admin admin = new Admin();
    //     CharacterSelection character = CharacterSelection.LYCANTHROPE;

    //     // Test modifying attributes for Lycanthrope character
    //     admin.modifyCharacter(character);
    //     // Add assertions here to verify the modifications made to the character

    //     // Test modifying attributes for Vampire character
    //     character = CharacterSelection.VAMPIRE;
    //     admin.modifyCharacter(character);
    //     // Add assertions here to verify the modifications made to the character

    //     // Test modifying attributes for Hunter character
    //     character = CharacterSelection.HUNTER;
    //     admin.modifyCharacter(character);
    // }
     
}
