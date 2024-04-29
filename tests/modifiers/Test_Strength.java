package tests.modifiers;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import src.modifiers.Strength;


public class Test_Strength {

    @Test 
    public void testStrength() {
        Strength strength = new Strength();
        assertEquals(null, strength.getName());
        assertEquals(0, strength.getEffectiveness());
    }


    @Test
    public void testStrengthToString() {
    
        Strength strength = new Strength();

        Object[][] dataInput = {
            { "Test1", 1 },
            { "               ", 2 },
            { "***************", 3 },
            { "Test4", 4 } 
        };

        for (Object[] data : dataInput) {
            strength = new Strength((String) data[0], (int) data[1]);
            assertEquals(String.format("%s (+%d)", data[0], data[1]), strength.toString());
        }
    }

    @Test
    public void testStrengthGetEffectiveness() {
        Strength strength = new Strength("Test", 1);
        assertEquals(1, strength.getEffectiveness());
    }

    @Test
    public void testStrengthGetName() {
        Strength strength = new Strength("Test", 1);
        assertEquals("Test", strength.getName());
    }
}
