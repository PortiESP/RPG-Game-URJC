package tests.minions;

import org.junit.jupiter.api.*;

import src.minions.Devil;
import src.minions.Ghoul;
import src.minions.Minion;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Test_Devil {

    @Test
    public void testDevilLoadFromArray() {

        String[][] devilArr = {
            { "TestDevil1", "1", "Covenant1"},
            { "TestDevil2", "2", "Covenant2"}, 
            { "TestDevil3", "3", "Covenant3"} };

        
        ArrayList<Devil> devil_list = Devil.loadFromArray(devilArr);

        for (int i = 0; i < devilArr.length; i++) {
            assertEquals(devilArr[i][0], devil_list.get(i).getName());
            assertEquals(Integer.parseInt(devilArr[i][1]), devil_list.get(i).getHealth());
            assertEquals(devilArr[i][2], devil_list.get(i).getCovenant());
            
        }
    }

    @Test
    public void testGetCovenant() {
        Devil devil = new Devil();
        ArrayList<Minion> minions = new ArrayList<>();
        minions.add(new Ghoul("Minion1", 100, 10));
        minions.add(new Ghoul("Minion2", 200, 10));

        devil.setCovenant("Covenant1");
        assertEquals("Covenant1", devil.getCovenant());
    }

    @Test
    public void testSetCovenant() {
        Devil devil = new Devil();
        ArrayList<Minion> minions = new ArrayList<>();
        minions.add(new Ghoul("Minion1", 100, 10));
        minions.add(new Ghoul("Minion2", 200, 10));

        devil.setCovenant("Covenant2");
        assertEquals("Covenant2", devil.getCovenant());
    }

    @Test
    public void testGetMinions() {
        Devil devil = new Devil();
        ArrayList<Minion> minions = new ArrayList<>();
        minions.add(new Ghoul("Minion1", 100, 10));
        minions.add(new Ghoul("Minion2", 200, 10));

        devil.setMinions(minions);
        assertEquals(minions, devil.getMinions());
    }

    @Test
    public void testSetMinions() {
        Devil devil = new Devil();
        ArrayList<Minion> minions = new ArrayList<>();
        minions.add(new Ghoul("Minion1", 100, 10));
        minions.add(new Ghoul("Minion2", 200, 10));

        devil.setMinions(minions);
        assertEquals(minions, devil.getMinions());
    }

    @Test
    public void testGetHealth() {
        Devil devil = new Devil();
        ArrayList<Minion> minions = new ArrayList<>();
        minions.add(new Ghoul("Minion1", 100, 10));
        minions.add(new Ghoul("Minion2", 200, 10));

        devil.setHealth(500);
        devil.setMinions(minions);
        assertEquals(800, devil.getHealth());
    }
}