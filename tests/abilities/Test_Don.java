package tests.abilities;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import src.Game;
import src.abilities.Don;

public class Test_Don {

    @Test
    public void loadFromArray() {
        String[][] donesArr1 = {  
            { "TestDon1", "1", "1", "1" }, 
            { "TestDon2", "2", "2", "2" }, 
            { "TestDon3", "3", "3", "3" } 
        };

        String[][] donesArr2 = {
            { "TestDon1", "1", "1", "1" }, 
            { "TestDon2", "2", "2", "2" },
            { "TestDon3", "3", "3", "3" },
            { "TestDon4", "4", "4", "4" } 
        };

        String[][] donesArr3 = {
            { "TestDon1", "1", "1", "1" },
            { "TestDon2", "2", "2", "2" },
            { "TestDon3", "3", "3", "3" },
            { "TestDon4", "4", "4", "4" },
            { "TestDon5", "5", "5", "5" },
        };

        String[][] donesArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(donesArr1, donesArr2, donesArr3,donesArr4));

        for (String[][] donesArr : dataInput) {
            ArrayList<Don> dones = Don.loadFromArray(donesArr);
            Game.donesAvailable = dones;
            String[] availableDones = Don.listAvailableDones();

            for (int i = 0; i < donesArr.length; i++) {
                assertEquals(donesArr[i][0] + " (Attack: " + donesArr[i][1] + ", Defense: " + donesArr[i][2] + ") ( Cost: " + donesArr[i][3] + " )", availableDones[i]);
            }
        }
    }

    @Test
    @Disabled
    void testToString() {}

    @Test
    @Disabled
    void listAvailableDones() {}

    @Test
    @Disabled
    void getMinRage() {}

    @Test
    @Disabled
    void setMinRage() {}
}
