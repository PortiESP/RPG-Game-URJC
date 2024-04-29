package tests.abilities;

import org.junit.Test;

import src.abilities.Don;
import src.abilities.SpecialAbility;

import static org.junit.Assert.*;

public class SpecialAbilityTest {

    @Test
    public void testToString() {
        SpecialAbility ability = new Don("Fireball", 10, 5, 2);
        String expected = "Fireball (Attack: 10, Defense: 5) ( Min Rage: 2 )";
        assertEquals(expected, ability.toString());
    }

    @Test
    public void testAbilityAttack() {
        SpecialAbility ability = new Don("Fireball", 10, 5, 10);
        int expected = 10;
        assertEquals(expected, ability.abilityAttack());
    }

    @Test
    public void testAbilityDefense() {
        SpecialAbility ability = new Don("Fireball", 10, 5, 10);
        int expected = 5;
        assertEquals(expected, ability.abilityDefense());
    }

    @Test
    public void testGettersAndSetters() {
        SpecialAbility ability = new Don();
        ability.setName("Fireball");
        ability.setAttack(10);
        ability.setDefense(5);

        assertEquals("Fireball", ability.getName());
        assertEquals(10, ability.getAttack());
        assertEquals(5, ability.getDefense());
    }
}