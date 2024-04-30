package tests.users;

import static org.junit.Assert.*;
import org.junit.Test;

import src.users.*;
import src.challenges.Challenge;
import tests.TestingUtils;

public class Test_Admin {

    @Test
    public void testAdmin() {
        Object[][] testCases = {
            { "Test1", "john1", "password" },
            { "Test2", "john2", "password" },
            { "Test3", "john3", "password" },
            { "Test4", "john4", "password" }
        };

        for (Object[] testCase : testCases) {
            Admin admin = new Admin((String) testCase[0], (String) testCase[1], (String) testCase[2]);
            assertNotNull(admin);
            assertEquals((String) testCase[0], admin.getName());
            assertEquals((String) testCase[1], admin.getNick());
            assertEquals((String) testCase[2], admin.getPassword());
        }
    }

    @Test
    public void testGetScore() {
        Admin admin = new Admin();
        assertEquals(0, admin.getScore());
    }

    @Test
    public void manageChallenge() {
        Object[][] testCases = {
            { "Test1.1", "Test1.2", "John", "Jane", "JohnPass", "JanePass", "ID-1", "ID-2", 100},
            { "Test2.1", "Test2.2", "John", "Jane", "JohnPass", "JanePass", "ID-1", "ID-2", 15},
            { "Test3.1", "Test3.2", "John", "Jane", "JohnPass", "JanePass", "ID-1", "ID-2", 200},
            { "Test4.1", "Test4.2", "John", "Jane", "JohnPass", "JanePass", "ID-1", "ID-2", 50}
        };
        
        // Define terminal input
        TestingUtils.setInput("1", "3", "1", "3", "1", "3", "1", "3");

        for (Object[] testCase : testCases) {
            // Create test objects
            Player challenger = new Player((String) testCase[0], (String) testCase[2], (String) testCase[4], (String) testCase[6]);
            Player challenged = new Player((String) testCase[1], (String) testCase[3], (String) testCase[5], (String) testCase[7]);
            Challenge challenge = new Challenge(challenger, challenged, (int) testCase[8]);

            // Create an instance of Admin
            Admin admin = new Admin("Admin", "adminNick", "adminPass");


            // Call the manageChallenge method
            admin.manageChallenge(challenge);

            // Assert that the challenge has been managed correctly
            assertTrue(challenge.isApproved());
            assertTrue(challenged.isPendingNotification());
            assertEquals(challenge, challenged.getPendingChallenge());

        }
    }

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
