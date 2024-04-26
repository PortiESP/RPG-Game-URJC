package tests.minions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import src.minions.Devil;

public class Test_Devil {

    @Test
    public void TestDevil() {
        Devil devil = new Devil("TestDevil", 100, "Lorem Ipsum");
        assertEquals("TestDevil", devil.getName());
        assertEquals(100, devil.getHealth());
        assertEquals("Lorem Ipsum", devil.getCovenant());
    }

    @Test
    public void testDevilLoadFromArray() {

        String[][] devilArr1 = {
            { "TestDevil1", "1", "Covenant1"},
            { "TestDevil2", "2", "Covenant2"}, 
            { "TestDevil3", "3", "Covenant3"} };

        String[][] devilArr2 = {
            { "TestDevil1", "1", "Covenant1" }, 
            { "TestDevil2", "2", "Covenant2"}, 
            { "TestDevil3", "3", "Covenant3" }, 
            { "TestDevil4", "4", "Covenant4" } };
        
        String[][] devilArr3 = {
            { "TestDevil1", "1", "Covenant1"  },
            { "TestDevil2", "2", "Covenant2" },
            { "TestDevil3", "3", "Covenant3"  },
            { "TestDevil4", "4", "Covenant4" },
            { "TestDevil5", "5", "Covenant5" },
        };

        String[][] devilArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(devilArr1, devilArr2, devilArr3, devilArr4));

        for (String[][] devilArr : dataInput) {
            ArrayList<Devil> devil = Devil.loadFromArray(devilArr);

            for (int i = 0; i < devilArr.length; i++) {
                assertEquals(devilArr[i][0], devil.get(i).getName());
                assertEquals(Integer.parseInt(devilArr[i][1]), devil.get(i).getHealth());
                assertEquals(devilArr[i][2], devil.get(i).getCovenant());
                
            }
        }
    }
}
