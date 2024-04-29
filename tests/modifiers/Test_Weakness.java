package tests.modifiers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import src.modifiers.Weakness;

public class Test_Weakness {

    @Test 
    public void testWeakness() {
        Weakness weakness = new Weakness();
        assertEquals(null, weakness.getName());
        assertEquals(0, weakness.getSensitivity());
    }


    @Test
    public void testWeaknessToString() {
    
        Weakness weakness = new Weakness();

        Object[][] dataInput = {
            { "Test1", 1 },
            { "               ", 2 },
            { "***************", 3 },
            { "Test4", 4 } 
        };

        for (Object[] data : dataInput) {
            weakness = new Weakness((String) data[0], (int) data[1]);
            assertEquals(String.format("%s (-%d)", data[0], data[1]), weakness.toString());
        }
    }

    @Test
    public void testWeaknessGetSensitivity() {
        Weakness weakness = new Weakness("Test", 1);
        assertEquals(1, weakness.getSensitivity());
    }

    @Test
    public void testWeaknessGetName() {
        Weakness weakness = new Weakness("Test", 1);
        assertEquals("Test", weakness.getName());
    }
}
