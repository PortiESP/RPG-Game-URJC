package tests.minions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import src.minions.*;

public class Test_Human {

    @Test
    public void testHuman() {
        Human human = new Human("TestHuman", 100, LoyaltyEnum.HIGH);
        assertEquals("TestHuman", human.getName());
        assertEquals(100, human.getHealth());
        assertEquals(LoyaltyEnum.HIGH, human.getLoyalty());
    }

    @Test
    public void testHumanLoadFromArray() {

        String[][] humanArr1 = { 
            { "TestHuman1", "1", "HIGH"},
            { "TestHuman2", "2", "REGULAR"}, 
            { "TestHuman3", "3", "LOW"} };

        String[][] humanArr2 = {
            { "TestHuman1", "1", "HIGH" }, 
            { "TestHuman2", "2", "REGULAR"}, 
            { "TestHuman3", "3", "LOW" }, 
            { "TestHuman4", "4", "LOW" } };

        String[][] humanArr3 = {
            { "TestHuman1", "1", "HIGH"  },
            { "TestHuman2", "2", "REGULAR" },
            { "TestHuman3", "3", "LOW"  },
            { "TestHuman4", "4", "LOW" },
            { "TestHuman5", "5", "HIGH" },
        };

        String[][] humanArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(humanArr1, humanArr2, humanArr3, humanArr4));

        for (String[][] humanArr : dataInput) {
            ArrayList<Human> human = Human.loadFromArray(humanArr);

            for (int i = 0; i < humanArr.length; i++) {
                assertEquals(humanArr[i][0], human.get(i).getName());
                assertEquals(Integer.parseInt(humanArr[i][1]), human.get(i).getHealth());
                assertEquals(LoyaltyEnum.valueOf(humanArr[i][2]), human.get(i).getLoyalty());
                
            }
        }
    }

    // Getters and Setters
    @Test
    public void testGetLoyalty() {
        Human human = new Human("TestHuman", 100, LoyaltyEnum.HIGH);

        human.setLoyalty(LoyaltyEnum.LOW);
        assertEquals(LoyaltyEnum.LOW, human.getLoyalty());
    }

    @Test
    public void testSetLoyalty() {
        Human human = new Human("TestHuman", 100, LoyaltyEnum.HIGH);

        human.setLoyalty(LoyaltyEnum.REGULAR);
        assertEquals(LoyaltyEnum.REGULAR, human.getLoyalty());
    }

}
