package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import src.Game;
import src.challenges.Challenge;
import src.users.Player;
import src.users.User;

public class Test_Game {

    @Test
    public void testLoadDefaultSettings() {
        Game game = new Game();
        game.loadDefaultSettings();
        
        // Assert that the armors and weapons are loaded correctly
        assertNotNull(Game.armorsAvailable);
        assertNotNull(Game.weaponsAvailable);
        
        // Assert that the modifiers, talents, dones, disciplines, ghouls, humans, and devils are loaded correctly
        assertNotNull(Game.modifiersAvailable);
        assertNotNull(Game.talentsAvailable);
        assertNotNull(Game.donesAvailable);
        assertNotNull(Game.disciplinesAvailable);
        assertNotNull(Game.ghoulsAvailable);
        assertNotNull(Game.humansAvailable);
        assertNotNull(Game.devilsAvailable);
    }

    @Test
    public void testPassword() {
        String password;

        // Test password with 8 characters
        password = "password";
        assertEquals(true, Game.isValidPassword(password));

        // Test password with 7 characters
        password = "passwor";
        assertEquals(false, Game.isValidPassword(password));

        // Test password with 9 characters
        password = "password1";
        assertEquals(true, Game.isValidPassword(password));

        // Test password with 16 characters
        password = "password123456789";
        assertEquals(false, Game.isValidPassword(password));
    }

    @Test
    public void getUsers() {
        Game game = new Game();
        assertNotNull(game.getUsers());
    }

    @Test
    public void setUsers() {
        Game game = new Game();
        List<User> users = new ArrayList<>();
        game.setUsers(users);
        assertNotNull(game.getUsers());
    }

    @Test
    public void getLoggedUser() {
        Game game = new Game();
        game.setLoggedUser(new Player("Name", "Nick", "Password", "A11A1"));
        assertNotNull(game.getLoggedUser());
    }

    @Test
    public void setLoggedUser() {
        Game game = new Game();
        game.setLoggedUser(new Player("Name", "Nick", "Password", "A11A1"));
        assertNotNull(game.getLoggedUser());
    }

    @Test
    public void getChallenges() {
        Game game = new Game();
        assertNotNull(game.getChallenges());
    }

    @Test
    public void setChallenges() {
        Game game = new Game();
        List<Challenge> challenges = new ArrayList<>();
        game.setChallenges(challenges);
        assertNotNull(game.getChallenges());
    }

    @Test
    public void getLastId() {
        Game game = new Game();
        assertNull(game.getLastId());
        game.setLastId("A11A1");
        assertEquals("A11A1", game.getLastId());
    }

    @Test
    public void setLastId() {
        Game game = new Game();
        game.setLastId("A11A1");
        assertEquals("A11A1", game.getLastId());
    }
}
