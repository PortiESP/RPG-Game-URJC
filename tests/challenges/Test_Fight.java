package tests.challenges;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import tests.TestingUtils;

import src.challenges.Fight;
import src.users.Player;
import src.characters.*;
import src.minions.*;
import src.equipment.*;
import src.modifiers.*;
import src.abilities.*;
import src.Game;


public class Test_Fight {

    @Test
    public void testFight() {
        Player player1 = new Player("player1", "p1", "123456789", "X-999");
        player1.setCurrentCharacter(CharacterSelection.VAMPIRE);
        player1.setArmor(new Armor("test", 1, 5));
        player1.setWeapons(new Weapon[] { new Weapon("test", 1, 5, 1), new Weapon("test2", 2, 6, 2)});
        player1.setModifiers(new Modifier[] { new Strength("test", 1) });
        player1.setSpecialAbilities(new Discipline("test", 1, 2, 3));

        Player player2 = new Player("player2", "p2", "987654321", "X-998");
        player2.setCurrentCharacter(CharacterSelection.VAMPIRE);
        player2.setArmor(new Armor("test", 1, 5));
        player2.setWeapons(new Weapon[] { new Weapon("test", 1, 5, 1), new Weapon("test2", 2, 6, 2)});
        player2.setModifiers(new Modifier[] { new Strength("test", 1) });
        player2.setSpecialAbilities(new Discipline("test", 1, 2, 3));

        Game.ghoulsAvailable = new ArrayList<>(List.of(new Ghoul("test", 1, 5), new Ghoul("test2", 2, 6)));
        Game.devilsAvailable = new ArrayList<>(List.of(new Devil("test", 1, "test"), new Devil("test", 2, "test")));

        TestingUtils.setInput(" ");

        Fight fight = new Fight(player1, player2);

        // Expecting to reach the end of the fight and log at least one round
        Assert.assertNotNull(fight);
        Assert.assertTrue(fight.getLog().size() > 0);
        Assert.assertNotNull(fight.getC1());
        Assert.assertNotNull(fight.getC2());
    }
}
