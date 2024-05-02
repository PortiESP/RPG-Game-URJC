package tests.characters;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import src.Game;
import src.abilities.Discipline;
import src.minions.*;
import src.characters.Vampire;
import src.users.Player;
import tests.TestingUtils;

public class Test_Vampire {

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
    public void testCalcAttackPower() {
        Vampire vampire = new Vampire(new Player("Player 1", "P1", "1234", "ID-F1"));
        vampire.setBlood(5);
        assertEquals(2, vampire.calcAttackPower());
    }

    @Test
    public void testLoadMinions() {
        Vampire vampire = new Vampire(new Player("Player 1", "P1", "1234", "ID-F1"));
        vampire.loadMinions();

        assertTrue(vampire.getMinionsHealth() > 0);
        assertNotNull(vampire.getMinions());
    }

    @Test
    public void loadInitialValues() {
        // Assert attributes are set correctly from static values
        Vampire vampire = new Vampire(new Player("Player 1", "P1", "1234", "ID-F1"));
        vampire.loadInitialValues();

        assertTrue(vampire.getHealth() == Vampire.MAX_HEALTH);
        assertTrue(vampire.getPower() == Vampire.MAX_POWER);
        assertTrue(vampire.getModifiers().length == 2);
        assertTrue(vampire.getMinions().length == Vampire.INIT_MINIONS);
        assertTrue(vampire.getEquipment().length == 3);
        assertTrue(vampire.getAge() == 0);
    }

    // @Test
    public void modifyAttributes() {
        // Set the input for the test
        TestingUtils.setInput(" ", "1", "-5", " ", "200", " ", "5");

        // Call the method
        Vampire.modifyAttributes();

        // Assert the attributes are modified correctly
        assertEquals(200, Vampire.MAX_HEALTH);
    }

    @Test
    public void alterAttr() {
        // Set the input for the test
        int opt = 2;
        int value = 5;

        TestingUtils.setInput(String.valueOf(value));

        // Call the method
        Vampire.alterAttr(opt);

        // Assert the attributes are modified correctly
        assertEquals(value, Vampire.MAX_POWER);
    }

    // @Test
    public void showAttributes() {
        // Set the input for the test
        TestingUtils.setInput(" ");

        // Call the method and assert it does not throw any exceptions
        assertDoesNotThrow(() -> Vampire.showAttributes());
    }

    @Test
    public void successAtack() {
        // Set the input for the test
        Vampire vampire = new Vampire(new Player("Player 1", "P1", "1234", "ID-F1"));
        Vampire.MAX_BLOOD = 10;
        vampire.setBlood(5);

        // Call the method
        vampire.successAtack();

        // Assert the attributes are modified correctly
        assertEquals(9, vampire.getBlood());
    }

    @Test
    public void getAge() {
        Vampire vampire = new Vampire(new Player("Player 1", "P1", "1234", "ID-F1"));
        assertEquals(0, vampire.getAge());
    }

    @Test
    public void setAge() {
        Vampire vampire = new Vampire(new Player("Player 1", "P1", "1234", "ID-F1"));
        vampire.setAge(5);
        assertEquals(5, vampire.getAge());
    }

    @Test
    public void getBlood() {
        Vampire vampire = new Vampire(new Player("Player 1", "P1", "1234", "ID-F1"));
        assertEquals(Vampire.MIN_BLOOD, vampire.getBlood());
    }

    @Test
    public void setBlood() {
        Vampire vampire = new Vampire(new Player("Player 1", "P1", "1234", "ID-F1"));
        vampire.setBlood(5);
        assertEquals(5, vampire.getBlood());
    }

    @Test
    public void getDiscipline() {
        Vampire vampire = new Vampire(new Player("Player 1", "P1", "1234", "ID-F1"));
        assertNull(vampire.getDiscipline());
    }

    @Test
    public void setDiscipline() {
        Vampire vampire = new Vampire(new Player("Player 1", "P1", "1234", "ID-F1"));
        vampire.setDiscipline(null);
        assertNull(vampire.getDiscipline());

        Discipline discipline = new Discipline("Discipline 1", 1, 1, 1);
        vampire.setDiscipline(discipline);
        assertNotNull(vampire.getDiscipline());
        assertEquals(discipline, vampire.getDiscipline());
    }
}
