package tests.equipment;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import src.equipment.Weapon;

public class Test_Weapon {

    @Test
    @Disabled
    public void testToString() {}

    @Test
    public void loadFromArray() {
        String[][] weaponArr1 = { { "TestWeapon", "1", "1", "1" }, { "TestWeapon2", "2", "2", "2" }, { "TestWeapon3", "3", "3", "3" } };

        ArrayList<Weapon> weapons = Weapon.loadFromArray(weaponArr1);

        for (int i = 0; i < weaponArr1.length; i++) {
            assertEquals(weaponArr1[i][0], weapons.get(i).getName());
            assertEquals(Integer.parseInt(weaponArr1[i][1]), weapons.get(i).getAttack());
            assertEquals(Integer.parseInt(weaponArr1[i][2]), weapons.get(i).getDefense());
            assertEquals(Integer.parseInt(weaponArr1[i][3]), weapons.get(i).getHandsRequired());
        }
    }

    @Test
    public void getHandsRequired() {
        Weapon weapon = new Weapon("Sword", 10, 5, 2);
        int actualHandsRequired = weapon.getHandsRequired();
        assertEquals(2, actualHandsRequired, "Incorrect hands required value");
    }

    @Test
    public void testSetHandsRequired() {
        Weapon weapon = new Weapon();
        weapon.setHandsRequired(2);
        assertEquals(2, weapon.getHandsRequired());
    }
}
