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

    public void testManageChallenge() {
        // Create test objects
        Player challenger = new Player("John");
        Player challenged = new Player("Jane");
        Challenge challenge = new Challenge(challenger, challenged);
        Admin admin = new Admin();

        // Set up test conditions
        challenge.setGold(100);
        challenged.setGold(50);

        // Call the method under test
        admin.manageChallenge(challenge);

        // Assert the expected outcomes
        assertTrue(challenge.isApproved());
        assertEquals(50, challenge.getGold());
        assertEquals("Do you want to manage the challenge between John and Jane?", MenuBuilder.getLastPrompt());
        assertTrue(MenuBuilder.getLastResponse()); // Assuming the admin chooses to manage the challenge
        assertFalse(challenger.isBanned());
        assertEquals("The bet has been adjusted to 50", MenuBuilder.getLastAlert());
        assertEquals(challenge, challenged.getLastNotifiedChallenge());
    }
}
