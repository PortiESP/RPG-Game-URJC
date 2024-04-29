
package tests.users;

import static org.junit.Assert.*;

import org.junit.Test;
import src.users.Player;
import utils.Const;

public class Test_User {

    @Test
    public void getScoreTest() {
        // Create an instance of User
        Player user = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getScore method
        int score = user.getScore();

        // Assert that the score is the initial gold
        assertEquals(Const.INITIAL_GOLD, score);
    }

    @Test
    public void getName() {
        String name = "Name";
        
        // Create an instance of User
        Player player = new Player(name, "Nick", "Password", "A11A1");

        // Assert that the name is null
        assertEquals(name, player.getName());
    }

    @Test
    public void setName() {
        // Create an instance of User
        Player user = new Player("Name", "Nick", "Password", "A11A1");

        // Call the setName method with a name
        user.setName("John");

        // Assert that the name is "John"
        assertEquals("John", user.getName());
    }

    @Test
    public void getNick() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getNick method
        String nick = player.getNick();

        // Assert that the nick "Nick"
        assertEquals("Nick", nick);
    }

    @Test
    public void setNick() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the setNick method with a nick
        player.setNick("nick");

        // Assert that the nick is "nick"
        assertEquals("nick", player.getNick());
    }

    @Test
    public void getPassword() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getPassword method
        String password = player.getPassword();

        // Assert that the password is "Password"
        assertEquals("Password", password);
    
    }

    @Test
    public void setPassword() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the setPassword method with a password
        player.setPassword("password");

        // Assert that the password is "password"
        assertEquals("password", player.getPassword());
    }

}
