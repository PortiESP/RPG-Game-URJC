package tests.minions;

import static org.junit.Assert.*;

import org.junit.Test;

import src.minions.Human;
import src.minions.LoyaltyEnum;


public class Test_Minion {

    @Test
    public void getName() {
        Human human = new Human("TestHuman", 100, LoyaltyEnum.HIGH);
        assertEquals("TestHuman", human.getName());
    }

    @Test
    public void setName() {
        Human human = new Human("TestHuman", 100, LoyaltyEnum.HIGH);
        human.setName("TestHuman2");
        assertEquals("TestHuman2", human.getName());
    }

    @Test
    public void getHealth() {
        Human human = new Human("TestHuman", 100, LoyaltyEnum.HIGH);
        assertEquals(100, human.getHealth());
    }

    @Test
    public void setHealth() {
        Human human = new Human("TestHuman", 100, LoyaltyEnum.HIGH);
        human.setHealth(200);
        assertEquals(200, human.getHealth());
    }
}
