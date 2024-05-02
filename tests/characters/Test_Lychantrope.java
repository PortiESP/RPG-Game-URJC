package tests.characters;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import src.Game;
import src.abilities.Don;
import src.characters.Lycanthrope;
import src.minions.*;
import src.users.Player;
import tests.TestingUtils;

public class Test_Lychantrope {

    @Before
    public void setUp() {
        ArrayList<Ghoul> ghoulsAvailable = new ArrayList<>();
        ghoulsAvailable.add(new Ghoul("Ghoul 1", 1, 1));
        ghoulsAvailable.add(new Ghoul("Ghoul 2", 2, 2));
        ghoulsAvailable.add(new Ghoul("Ghoul 3", 3, 3));

        ArrayList<Human> humansAvailable = new ArrayList<>();
        humansAvailable.add(new Human("Human 1", 1, LoyaltyEnum.HIGH));
        humansAvailable.add(new Human("Human 2", 2, LoyaltyEnum.LOW));
        humansAvailable.add(new Human("Human 3", 3, LoyaltyEnum.REGULAR));

        ArrayList<Devil> devilsAvailable = new ArrayList<>();
        devilsAvailable.add(new Devil("Devil 1", 1, "Covenant 1"));
        devilsAvailable.add(new Devil("Devil 2", 2, "Covenant 2"));
        devilsAvailable.add(new Devil("Devil 3", 3, "Covenant 3"));

        Game.ghoulsAvailable = ghoulsAvailable;
        Game.humansAvailable = humansAvailable;
        Game.devilsAvailable = devilsAvailable;
    }

    @Test
    public void testLycanthrope() {
        // Assert an exception is thrown when the player is null
        assertThrows(NullPointerException.class, () -> new Lycanthrope(null));
        
        // Assert attributes are set correctly when the player is not null
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Player 1", "P1", "1234", "ID-F1"));

        assertNull(lycanthrope.getDon());
        assertTrue(lycanthrope.getMinionsHealth() > 0);
        assertNotNull(lycanthrope.getMinions());
    }

    @Test
    public void loadMinions() {
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Player 1", "P1", "1234", "ID-F1"));
        lycanthrope.loadMinions();

        assertTrue(lycanthrope.getMinionsHealth() > 0);
        assertNotNull(lycanthrope.getMinions());
    }

    @Test
    public void loadInitialValues() {
        // Assert attributes are set correctly from static values
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Player 1", "P1", "1234", "ID-F1"));
        lycanthrope.loadInitialValues();

        assertTrue(lycanthrope.getHealth() == Lycanthrope.MAX_HEALTH);
        assertTrue(lycanthrope.getPower() == Lycanthrope.MAX_POWER);
        assertTrue(lycanthrope.getModifiers().length == 2);
        assertTrue(lycanthrope.getMinions().length == Lycanthrope.INIT_MINIONS);
        assertTrue(lycanthrope.getEquipment().length == 3);
        assertTrue(lycanthrope.getRage() == Lycanthrope.MAX_RAGE);
    }

    // @Test
    public void modifyAttributes() {
        // Set the input for the test
        TestingUtils.setInput(" ", "1", "-5", " ", "200", " ", "5");

        // Call the method
        Lycanthrope.modifyAttributes();

        // Assert the attributes are modified correctly
        assertEquals(200, Lycanthrope.MAX_HEALTH);
    }

    // @Test
    public void alterAttr() {
        // Set the input for the test
        int opt = 2;
        int value = 5;

        TestingUtils.setInput(String.valueOf(value));

        // Call the method
        Lycanthrope.alterAttr(opt);

        // Assert the attributes are modified correctly
        assertEquals(value, Lycanthrope.MAX_POWER);
    }

    // @Test
    public void showAttributes() {
        // Set the input for the test
        TestingUtils.setInput(" ");

        // Call the method and assert it does not throw any exceptions
        assertDoesNotThrow(() -> Lycanthrope.showAttributes());
    }

    @Test
    public void getRage() {
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Player 1", "P1", "1234", "ID-F1"));
        lycanthrope.setRage(1);
        assertEquals(1, lycanthrope.getRage());
    }

    @Test
    public void setRage() {
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Player 1", "P1", "1234", "ID-F1"));
        lycanthrope.setRage(1);
        assertEquals(1, lycanthrope.getRage());
    }

    @Test
    public void getDon() {
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Player 1", "P1", "1234", "ID-F1"));
        assertNull(lycanthrope.getDon());
    }

    @Test
    public void setDon() {
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Player 1", "P1", "1234", "ID-F1"));
        lycanthrope.setDon(new Don());
        assertNotNull(lycanthrope.getDon());
    }
}
