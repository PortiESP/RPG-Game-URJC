package tests;

import org.junit.Test;

import static org.junit.Assert.*;

class Test_Weapon {

    @Test
    void testToString() {
    }

    @Test
    public void loadFromArray() {
        String[][] ArmorArr1 = {
            {"TestWeapon", "1", "1"},
            {"TestWeapon2", "2", "2"},
            {"TestWeapon3", "3", "3"}
        };
        
        String[][] ArmorArr2 = {
            {"TestWeapon1", "1", "1"},
            {"TestWeapon2", "2", "2"},
            {"TestWeapon3", "3", "3"},
            {"TestWeapon4", "4", "4"}
        };

        String[][] ArmorArr3 = {
            {"TestWeapon1", "1", "1"},
            {"TestWeapon2", "2", "2"},
            {"TestWeapon3", "3", "3"},
            {"TestWeapon4", "4", "4"},
            {"TestWeapon5", "5", "5"}
        };

        String[][] ArmorArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(
            List.of(WeaponArr1, WeaponArr2, WeaponArr3, WeaponArr4)
        );

        for (String[][] weaponArr : dataInput) {
            ArrayList<Weapon> weapons = Weapon.loadFromArray(weaponArr);

            for (int i = 0; i < weaponArr.length; i++) {
                assertEquals(weaponArr[i][0], weapon.get(i).getName());
                assertEquals(Integer.parseInt(weaponArr[i][1]), weapon.get(i).getAttack());
                assertEquals(Integer.parseInt(weaponArr[i][2]), weapon.get(i).getDefense());
            }
        }        
    }

    @Test
    public void getHandsRequired() {
        Weapon weapon = new Weapon("Sword", 10, 5, 2);
        int expectedHandsRequired = 2;
        int actualHandsRequired = weapon.getHandsRequired();
        assertEquals(expectedHandsRequired, actualHandsRequired, "Incorrect hands required value");
    }

    @Test
    public void testSetHandsRequired() {
        Weapon weapon = new Weapon();
        weapon.setHandsRequired(2);
        assertEquals(2, weapon.getHandsRequired());
    }
}