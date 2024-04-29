package tests.equipment;

import org.junit.Test;

import src.equipment.Armor;

public class Test_Equipment {

    @Test
    public void testToString() {
        Armor armor = new Armor("TestArmor", 100, 100);
        assert(armor.toString().equals("TestArmor ( Attack: 100, Defense: 100 )"));
    }

    @Test
    public void getName() {
        Armor armor = new Armor("TestArmor", 100, 100);
        assert(armor.getName().equals("TestArmor"));
    }

    @Test
    public void setName() {
        Armor armor = new Armor("TestArmor", 100, 100);
        armor.setName("TestArmor2");
        assert(armor.getName().equals("TestArmor2"));
    }

    @Test
    public void getAttack() {
        Armor armor = new Armor("TestArmor", 100, 100);
        assert(armor.getAttack() == 100);
    }

    @Test
    public void setAttack() {
        Armor armor = new Armor("TestArmor", 100, 100);
        armor.setAttack(200);
        assert(armor.getAttack() == 200);
    }

    @Test
    public void getDefense() {
        Armor armor = new Armor("TestArmor", 100, 100);
        assert(armor.getDefense() == 100);
    }


    @Test
    public void setDefense() {
        Armor armor = new Armor("TestArmor", 100, 100);
        armor.setDefense(200);
        assert(armor.getDefense() == 200);
    }

    @Test
    public void testArmor() {
        Armor armor = new Armor("TestArmor", 100, 100);
        assert(armor.getName().equals("TestArmor"));
        assert(armor.getAttack() == 100);
        assert(armor.getDefense() == 100);
    }
}
