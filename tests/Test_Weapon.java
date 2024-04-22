package tests;

import org.junit.Test;

import src.equipment.Weapon;

import static org.junit.Assert.*;

import java.util.ArrayList;

class Test_Weapon {

    @Test
    public void testToString() {
        return super.toString() + " (Hands Required: " + this.handsRequired + ")";
    }

    @Test
    public static ArrayList<Weapon> loadFromArray(String[][] arr) {
        ArrayList<Weapon> weapons = new ArrayList<>();
        for (String[] weapon : arr) {
            weapons.add(new Weapon(weapon[0], Integer.parseInt(weapon[1]), Integer.parseInt(weapon[2]), Integer.parseInt(weapon[3])));
        }

        return weapons;
    }

    @Test
    public int getHandsRequired() {
        return handsRequired;
    }

    @Test
    public void setHandsRequired(int handsRequired) {
        this.handsRequired = handsRequired;
    }
}