package tests.abilities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import src.Game;
import src.abilities.Don;

public class Test_Don {

    @Test
    public void testDonLoadFromArray() {

        String[][] donesArr1 = { { "TestDon1", "1", "1", "1" }, { "TestDon2", "2", "2", "2" }, { "TestDon3", "3", "3", "3" } };

        String[][] donesArr2 = { { "TestDon1", "1", "1", "1" }, { "TestDon2", "2", "2", "2" }, { "TestDon3", "3", "3", "3" }, { "TestDon4", "4", "4", "4" } };

        String[][] donesArr3 = {
            { "TestDon1", "1", "1", "1" },
            { "TestDon2", "2", "2", "2" },
            { "TestDon3", "3", "3", "3" },
            { "TestDon4", "4", "4", "4" },
            { "TestDon5", "5", "5", "5" },
        };

        String[][] donesArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(donesArr1, donesArr2, donesArr3, donesArr4));

        for (String[][] donesArr : dataInput) {
            ArrayList<Don> dones = Don.loadFromArray(donesArr);

            for (int i = 0; i < donesArr.length; i++) {
                assertEquals(donesArr[i][0], dones.get(i).getName());
                assertEquals(Integer.parseInt(donesArr[i][1]), dones.get(i).getAttack());
                assertEquals(Integer.parseInt(donesArr[i][2]), dones.get(i).getDefense());
                assertEquals(Integer.parseInt(donesArr[i][3]), dones.get(i).getMinRage());
            }
        }
    }

    @Test
    public void testDonToString() {Don don = new Don();

        Object[][] dataInput = { { "TestDon1", 1, 1, 1 }, { "               ", 2, 2, 2 }, { "***************", 3, 3, 3 }, { "TestDon4", 4, 4, 4 } };

        for (Object[] data : dataInput) {
            don = new Don((String) data[0], (int) data[1], (int) data[2], (int) data[3]);

            assertEquals(data[0] + " (Attack: " + data[1] + ", Defense: " + data[2] + ") ( Min Rage: " + data[3] + " )", don.toString());
        }}

    @Test
    public void testDonListAvailableDones() {
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
                assertEquals(donesArr[i][0] + " (Attack: " + donesArr[i][1] + ", Defense: " + donesArr[i][2] + ") ( Min Rage: " + donesArr[i][3] + " )", availableDones[i]);
            }
        }
    }

    // @Test
    // void getMinRage() {
        
    // }

    // @Test
    // void setMinRage() {}
}
