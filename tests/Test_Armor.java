package tests;

import org.junit.Test;

import src.equipment.Armor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class Test_Armor {

    @Test
    public void loadFromArray() {
        String[][] ArmorArr1 = {
            {"TestDiscipline1", "1", "1"},
            {"TestDiscipline2", "2", "2"},
            {"TestDiscipline3", "3", "3"}
        };
        
        String[][] ArmorArr2 = {
            {"TestDiscipline1", "1", "1"},
            {"TestDiscipline2", "2", "2"},
            {"TestDiscipline3", "3", "3"},
            {"TestDiscipline4", "4", "4"}
        };

        String[][] ArmorArr3 = {
            {"TestDiscipline1", "1", "1"},
            {"TestDiscipline2", "2", "2"},
            {"TestDiscipline3", "3", "3"},
            {"TestDiscipline4", "4", "4"},
            {"TestDiscipline5", "5", "5"}
        };

        String[][] ArmorArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(
            List.of(ArmorArr1, ArmorArr2, ArmorArr3, ArmorArr4)
        );

        for (String[][] armorArr : dataInput) {
            ArrayList<Armor> armors = Armor.loadFromArray(armorArr);

            for (int i = 0; i < armorArr.length; i++) {
                assertEquals(armorArr[i][0], armors.get(i).getName());
                assertEquals(Integer.parseInt(armorArr[i][1]), armors.get(i).getAttack());
                assertEquals(Integer.parseInt(armorArr[i][2]), armors.get(i).getDefense());
            }
        }        
    }
}
