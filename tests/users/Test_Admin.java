package tests.users;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.mockito.Mockito;
import src.users.Admin;

public class Test_Admin {

    @Test
    public void getScore() {
        Admin admin = new Admin();
        assertEquals(0, admin.getScore());
    }

    @Test
    public void testManageChallenge() { //TODO modificar estos tests

        // Create a mock challenge
        Challenge challenge = Mockito.mock(Challenge.class);

        // Create a mock player
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);

        // Create an instance of Admin
        Admin admin = new Admin();

        // Mock the behavior of MenuBuilder.askYesNo
        Mockito.when(MenuBuilder.askYesNo(Mockito.anyString())).thenReturn(true);

        // Mock the behavior of player2.defeatedRecently
        Mockito.when(player2.defeatedRecently()).thenReturn(true);

        // Mock the behavior of MenuBuilder.askYesNo
        Mockito.when(MenuBuilder.askYesNo(Mockito.anyString())).thenReturn(true);

        // Mock the behavior of player1.canAfford
        Mockito.when(player2.canAfford(Mockito.anyInt())).thenReturn(false);
        Mockito.when(player2.getGold()).thenReturn(100);

        // Mock the behavior of MenuBuilder.menu
        Mockito.when(MenuBuilder.menu(Mockito.anyString(), Mockito.any())).thenReturn(1);

        // Mock the behavior of player1.manageEquipment
        Mockito.doNothing().when(player1).manageEquipment();

        // Mock the behavior of this.modifyCharacter
        Mockito.doNothing().when(admin).modifyCharacter(Mockito.any(CharacterSelection.class));

        // Mock the behavior of player2.manageModifiers
        Mockito.doNothing().when(player2).manageModifiers();

        // Mock the behavior of player2.changeSpecialAbility
        Mockito.doNothing().when(player2).changeSpecialAbility();

        // Mock the behavior of player2.notifyChallenge
        Mockito.doNothing().when(player2).notifyChallenge(Mockito.any(Challenge.class));

        // Call the method under test
        admin.manageChallenge(challenge);

        // Verify the interactions
        Mockito.verify(player1).ban();
        Mockito.verify(player2).getGold();
        Mockito.verify(challenge).setGold(100);
        Mockito.verify(player2).notifyChallenge(challenge);
    }
}
