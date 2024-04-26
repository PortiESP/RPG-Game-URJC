package tests.abilities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import src.Game;
import src.abilities.*;

public class Test_Discipline {

    @Test
    public void testDiscipline() {
        Discipline discipline = new Discipline();
        assertNotNull(discipline);

        Object[][] dataInput = {
            { "Test1", 1, 1, 1 },
            { "     ", 2, 2, 2 },
            { "*****", 3, 3, 3 },
            { "Test4", 4, 4, 4 }
        };

        for (Object[] data : dataInput) {
            discipline = new Discipline((String) data[0], (int) data[1], (int) data[2], (int) data[3]);

            assertEquals(data[0], discipline.getName());
            assertEquals(data[1], discipline.getAttack());
            assertEquals(data[2], discipline.getDefense());
            assertEquals(data[3], discipline.getCost());
        }
    }

    @Test
    public void testDisciplineToString() {
        Discipline discipline = new Discipline();

        Object[][] dataInput = {
            { "Test1", 1, 1, 1 },
            { "     ", 2, 2, 2 },
            { "*****", 3, 3, 3 },
            { "Test4", 4, 4, 4 }
        };

        for (Object[] data : dataInput) {
            discipline = new Discipline((String) data[0], (int) data[1], (int) data[2], (int) data[3]);

            assertEquals(data[0] + " (Attack: " + data[1] + ", Defense: " + data[2] + ") ( Cost: " + data[3] + " )", discipline.toString());
        }
    }

    @Test
    public void testDisciplineAbilityAttack() {
        Discipline discipline = new Discipline();

        Object[][] dataInput = {
            { "Test1", 1, 1, 1 },
            { "     ", 2, 2, 2 },
            { "*****", 3, 3, 3 },
            { "Test4", 4, 4, 4 }
        };

        for (Object[] data : dataInput) {
            discipline = new Discipline((String) data[0], (int) data[1], (int) data[2], (int) data[3]);
            int blood = 3;

            if (blood >= (int) data[3]) {
                assertEquals(data[1], discipline.abilityAttack(blood));
            } else {
                assertEquals(0, discipline.abilityAttack(blood));
            }
        }
    }

    @Test
    public void testDisciplineAbilityDefense() {
        Discipline discipline = new Discipline();

        Object[][] dataInput = {
            { "Test1", 1, 1, 1 },
            { "     ", 2, 2, 2 },
            { "*****", 3, 3, 3 },
            { "Test4", 4, 4, 4 }
        };

        for (Object[] data : dataInput) {
            discipline = new Discipline((String) data[0], (int) data[1], (int) data[2], (int) data[3]);
            assertEquals(data[2], discipline.abilityDefense());
        }
    }

    @Test
    public void testDisciplineLoadFromArray() {
        String[][] disciplinesArr1 = {
            { "TestDiscipline1", "1", "1", "1" },
            { "TestDiscipline2", "2", "2", "2" },
            { "TestDiscipline3", "3", "3", "3" }
        };

        String[][] disciplinesArr2 = {
            { "TestDiscipline1", "1", "1", "1" },
            { "TestDiscipline2", "2", "2", "2" },
            { "TestDiscipline3", "3", "3", "3" },
            { "TestDiscipline4", "4", "4", "4" }
        };

        String[][] disciplinesArr3 = {
            { "TestDiscipline1", "1", "1", "1" },
            { "TestDiscipline2", "2", "2", "2" },
            { "TestDiscipline3", "3", "3", "3" },
            { "TestDiscipline4", "4", "4", "4" },
            { "TestDiscipline5", "5", "5", "5" },
        };

        String[][] disciplinesArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(disciplinesArr1, disciplinesArr2, disciplinesArr3, disciplinesArr4));

        for (String[][] disciplinesArr : dataInput) {
            ArrayList<Discipline> disciplines = Discipline.loadFromArray(disciplinesArr);

            for (int i = 0; i < disciplinesArr.length; i++) {
                assertEquals(disciplinesArr[i][0], disciplines.get(i).getName());
                assertEquals(Integer.parseInt(disciplinesArr[i][1]), disciplines.get(i).getAttack());
                assertEquals(Integer.parseInt(disciplinesArr[i][2]), disciplines.get(i).getDefense());
                assertEquals(Integer.parseInt(disciplinesArr[i][3]), disciplines.get(i).getCost());
            }
        }
    }

    @Test
    public void testDisciplineListAvailableDisciplines() {
        String[][] disciplinesArr1 = {
            { "TestDiscipline1", "1", "1", "1" },
            { "TestDiscipline2", "2", "2", "2" },
            { "TestDiscipline3", "3", "3", "3" }
        };

        String[][] disciplinesArr2 = {
            { "TestDiscipline1", "1", "1", "1" },
            { "TestDiscipline2", "2", "2", "2" },
            { "TestDiscipline3", "3", "3", "3" },
            { "TestDiscipline4", "4", "4", "4" }
        };

        String[][] disciplinesArr3 = {
            { "TestDiscipline1", "1", "1", "1" },
            { "TestDiscipline2", "2", "2", "2" },
            { "TestDiscipline3", "3", "3", "3" },
            { "TestDiscipline4", "4", "4", "4" },
            { "TestDiscipline5", "5", "5", "5" },
        };

        String[][] disciplinesArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(disciplinesArr1, disciplinesArr2, disciplinesArr3, disciplinesArr4));

        for (String[][] disciplinesArr : dataInput) {
            ArrayList<Discipline> disciplines = Discipline.loadFromArray(disciplinesArr);
            Game.disciplinesAvailable = disciplines;
            String[] availableDisciplines = Discipline.listAvailableDisciplines();

            for (int i = 0; i < disciplinesArr.length; i++) {
                assertEquals(
                    disciplinesArr[i][0] + " (Attack: " + disciplinesArr[i][1] + ", Defense: " + disciplinesArr[i][2] + ") ( Cost: " + disciplinesArr[i][3] + " )",
                    availableDisciplines[i]
                );
            }
        }
    }
}
