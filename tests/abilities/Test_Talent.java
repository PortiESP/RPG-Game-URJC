package tests.abilities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import src.Game;
import src.abilities.*;
public class Test_Talent {

    @Test
    public void testTalent() {
        Talent talent = new Talent();

        Object[][] dataInput = {
            { "Test1", 1, 1 },
            { "     ", 2, 2 },
            { "*****", 3, 3 },
            { "Test4", 4, 4 }
        };

        for (Object[] data : dataInput) {
            talent = new Talent((String) data[0], (int) data[1], (int) data[2]);

            assertEquals(data[0], talent.getName());
            assertEquals(data[1], talent.getAttack());
            assertEquals(data[2], talent.getDefense());
        }
    }

    @Test
    public void testTalentToString() {
        Talent talent = new Talent();

        Object[][] dataInput = {
            { "Test1", 1, 1 },
            { "     ", 2, 2 },
            { "*****", 3, 3 },
            { "Test4", 4, 4 }
        };

        for (Object[] data : dataInput) {
            talent = new Talent((String) data[0], (int) data[1], (int) data[2]);

            assertEquals(data[0] + " (Attack: " + data[1] + ", Defense: " + data[2] + ")", talent.toString());
        }
    }

    @Test
    public void testTalentAbilityAttack() {
        Talent talent = new Talent();

        Object[][] dataInput = {
            { "Test1", 1, 1 },
            { "     ", 2, 2 },
            { "*****", 3, 3 },
            { "Test4", 4, 4 }
        };

        for (Object[] data : dataInput) {
            talent = new Talent((String) data[0], (int) data[1], (int) data[2]);
            assertEquals(data[1], talent.abilityAttack());
        }
    }

    @Test
    public void testTalentLoadFromArray() {
        String[][] talentsArr1 = {
            { "Test1", "1", "1", "1" },
            { "Test2", "2", "2", "2" },
            { "Test3", "3", "3", "3" }
        };

        String[][] talentsArr2 = {
            { "Test1", "1", "1", "1" },
            { "Test2", "2", "2", "2" },
            { "Test3", "3", "3", "3" },
            { "Test4", "4", "4", "4" }
        };

        String[][] talentsArr3 = {
            { "Test1", "1", "1", "1" },
            { "Test2", "2", "2", "2" },
            { "Test3", "3", "3", "3" },
            { "Test4", "4", "4", "4" },
            { "Test5", "5", "5", "5" },
        };

        String[][] talentsArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(talentsArr1, talentsArr2, talentsArr3, talentsArr4));

        for (String[][] talentsArr : dataInput) {
            ArrayList<Talent> talents = Talent.loadFromArray(talentsArr);

            for (int i = 0; i < talentsArr.length; i++) {
                assertEquals(talentsArr[i][0], talents.get(i).getName());
                assertEquals(Integer.parseInt(talentsArr[i][1]), talents.get(i).getAttack());
                assertEquals(Integer.parseInt(talentsArr[i][2]), talents.get(i).getDefense());
            }
        }
    }

    @Test
    public void testDisciplineListAvailableTalents() {
        String[][] talentsArr1 = {
            { "Test1", "1", "1", "1" },
            { "Test2", "2", "2", "2" },
            { "Test3", "3", "3", "3" }
        };

        String[][] talentsArr2 = {
            { "Test1", "1", "1", "1" },
            { "Test2", "2", "2", "2" },
            { "Test3", "3", "3", "3" },
            { "Test4", "4", "4", "4" }
        };

        String[][] talentsArr3 = {
            { "Test1", "1", "1", "1" },
            { "Test2", "2", "2", "2" },
            { "Test3", "3", "3", "3" },
            { "Test4", "4", "4", "4" },
            { "Test5", "5", "5", "5" },
        };

        String[][] talentsArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(talentsArr1, talentsArr2, talentsArr3, talentsArr4));

        for (String[][] talentsArr : dataInput) {
            ArrayList<Talent> talents = Talent.loadFromArray(talentsArr);
            Game.talentsAvailable = talents;
            String[] availabletalents = Talent.listAvailableTalents();

            for (int i = 0; i < talentsArr.length; i++) {
                assertEquals(
                    talentsArr[i][0] + " (Attack: " + talentsArr[i][1] + ", Defense: " + talentsArr[i][2] + ")",
                    availabletalents[i]
                );
            }
        }
    }
}
