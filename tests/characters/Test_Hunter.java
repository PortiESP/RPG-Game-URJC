package tests.characters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

import src.Game;
import src.abilities.Talent;
import src.characters.*;
import src.minions.*;
import src.users.Player;
import tests.TestingUtils;

public class Test_Hunter {

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
    public void testHunter() {
        // Assert an exception is thrown when the player is null
        assertThrows(NullPointerException.class, () -> new Hunter(null));
        
        // Assert attributes are set correctly when the player is not null
        Hunter hunter = new Hunter(new Player("Player 1", "P1", "1234", "ID-F1"));

        assertNull(hunter.getTalent());
        assertTrue(hunter.getMinionsHealth() > 0);
        assertNotNull(hunter.getMinions());
    }

    @Test
    public void loadMinions() {
        Hunter hunter = new Hunter(new Player("Player 1", "P1", "1234", "ID-F1"));
        hunter.loadMinions();

        assertTrue(hunter.getMinionsHealth() > 0);
        assertNotNull(hunter.getMinions());
    }

    @Test
    public void loadInitialValues() {
        // Assert attributes are set correctly from static values
        Hunter hunter = new Hunter(new Player("Player 1", "P1", "1234", "ID-F1"));
        hunter.loadInitialValues();

        assertTrue(hunter.getHealth() == Hunter.MAX_HEALTH);
        assertTrue(hunter.getPower() == Hunter.MAX_POWER);
        assertTrue(hunter.getModifiers().length == 2);
        assertTrue(hunter.getMinions().length == Hunter.INIT_MINIONS);
        assertTrue(hunter.getEquipment().length == 3);
        assertTrue(hunter.getWillpower() == Hunter.MAX_WILLPOWER);
    }

    // @Test
    public void modifyAttributes() {
        // Set the input for the test
        TestingUtils.setInput(" ", "1", "-5", " ", "200", " ", "5");

        // Call the method
        Hunter.modifyAttributes();

        // Assert the attributes are modified correctly
        assertEquals(200, Hunter.MAX_HEALTH);
    }

    // @Test
    public void alterAttr() {
        // Set the input for the test
        int opt = 2;
        int value = 5;

        TestingUtils.setInput(String.valueOf(value));

        // Call the method
        Hunter.alterAttr(opt);

        // Assert the attributes are modified correctly
        assertEquals(value, Hunter.MAX_POWER);
    }

    // @Test
    public void showAttributes() {
        // Set the input for the test
        TestingUtils.setInput(" ");

        // Call the method and assert it does not throw any exceptions
        assertDoesNotThrow(() -> Hunter.showAttributes());
    }

    @Test
    public void getWillpower() {
        Hunter hunter = new Hunter(new Player("Player 1", "P1", "1234", "ID-F1"));
        assertEquals(Hunter.MAX_WILLPOWER, hunter.getWillpower());
    }

    @Test
    public void setWillpower() {
        Hunter hunter = new Hunter(new Player("Player 1", "P1", "1234", "ID-F1"));
        hunter.setWillpower(5);
        assertEquals(5, hunter.getWillpower());
    }

    @Test
    public void getTalent() {
        Hunter hunter = new Hunter(new Player("Player 1", "P1", "1234", "ID-F1"));
        assertNull(hunter.getTalent());
    }

    @Test
    public void setTalent() {
        Hunter hunter = new Hunter(new Player("Player 1", "P1", "1234", "ID-F1"));
        hunter.setTalent(new Talent());
        assertNotNull(hunter.getTalent());
    }
}
