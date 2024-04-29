package tests.users;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import src.Game;
import src.abilities.Don;
import src.abilities.SpecialAbility;
import src.abilities.Talent;
import src.challenges.Challenge;
import src.characters.CharacterSelection;
import src.equipment.Armor;
import src.equipment.Weapon;
import src.modifiers.Modifier;
import src.modifiers.Strength;
import src.users.Player;
import tests.TestingUtils;
import utils.Const;

public class Test_Player {

    @Test
    public void addChallengeToHistory() {
        // Create an instance of User
        Player player1 = new Player("Name1", "Nick1", "Password1", "A11A1");
        Player player2 = new Player("Name2", "Nick2", "Password2", "A11A2");

        // Create an instance of Challenge
        Challenge challenge = new Challenge(player1, player2, 100);

        // Call the addChallengeToHistory method
        player1.addChallengeToHistory(challenge);

        // Assert that the challenge is in the history
        assertTrue(player1.getChallenges().contains(challenge));
    }

    @Test
    public void hasChallenges() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Assert that the player has no challenges
        assertFalse(player.hasChallenges());

        // Create an instance of Challenge and add the challenge to the player's history
        Challenge challenge = new Challenge();
        player.addChallengeToHistory(challenge);

        // Assert that the player has challenges
        assertTrue(player.hasChallenges());
    }

    @Test
    public void getScore() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getScore method
        int score = player.getScore();

        // Assert that the score is the initial gold
        assertEquals(Const.INITIAL_GOLD, score);
    }

    @Test
    public void showInfo() {
        TestingUtils.setInput("");

        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Check if the methond throws an exception when the current character is null
        assertThrows(NullPointerException.class, () -> player.showInfo());

    }

    @Test
    @Disabled
    public void defeatedRecently() {}

    @Test
    public void ban() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Assert that the player is not banned
        assertFalse(player.isBanned());

        // Call the ban method
        player.ban();

        // Assert that the player is banned
        assertTrue(player.isBanned());
    }

    @Test
    public void unban() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the ban method
        player.ban();

        // Assert that the player is banned
        assertTrue(player.isBanned());

        // Call the unban method
        player.unban();

        // Assert that the player is not banned
        assertFalse(player.isBanned());
    }

    // @Test
    // @Disabled
    public void manageModifiers() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Check if the methond throws an exception when the current character is null
        assertDoesNotThrow(() -> player.manageModifiers());
    }

    @Test
    @Disabled
    public void changeModifier() {}

    @Test
    @Disabled
    public void changeSpecialAbility() {}

    @Test
    public void showSpecialAbilities() {
        TestingUtils.setInput("");

        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Check if the methond throws an exception when the current character is null
        assertDoesNotThrow(() -> player.showSpecialAbilities());
    }

    @Test
    @Disabled
    public void manageEquipment() {}

    @Test
    @Disabled
    public void changeArmor() {}

    /**
     * ⚠️ This test may fail when executed in a batch with other tests, but will pass when executed alone.
     * This is due to the fact that the method uses the Scanner class to read input from the console.
     */
    @Test
    public void changeWeapon() {
        TestingUtils.setInput("1", "");

        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");
        Game g = new Game();
        g.loadDefaultSettings();

        // Check if the methond throws an exception when the current character is null
        assertDoesNotThrow(() -> player.changeWeapon(1));
    }

    @Test
    public void notifyChallenge() {
        // Create an instance of User
        Player player1 = new Player("Name1", "Nick1", "Password1", "A11A1");
        Player player2 = new Player("Name2", "Nick2", "Password2", "A11A2");

        // Create an instance of Challenge
        Challenge challenge = new Challenge(player1, player2, 100);

        // Call the notifyChallenge method
        player1.notifyChallenge(challenge);

        // Assert that the player has a pending notification
        assertTrue(player1.isPendingNotification());
    }

    @Test
    public void manageNotifications() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the manageNotifications method
        player.manageNotifications();

        // Assert that the player has no pending notification
        assertFalse(player.isPendingNotification());
    }

    @Test
    public void payGoldTo() {
        // Create an instance of User
        Player player1 = new Player("Name1", "Nick1", "Password1", "A11A1");
        Player player2 = new Player("Name2", "Nick2", "Password2", "A11A2");

        // Call the payGoldTo method
        player1.payGoldTo(50, player2);

        // Assert that the player1 has less gold
        assertEquals(Const.INITIAL_GOLD - 50, player1.getGold());

        // Assert that the player2 has more gold
        assertEquals(Const.INITIAL_GOLD + 50, player2.getGold());
    }

    @Test
    public void canAfford() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Assert that the player can afford the gold
        assertTrue(player.canAfford(100));

        // Assert that the player cannot afford the gold
        assertFalse(player.canAfford(1000));
    }

    /**
     * ⚠️ This test may fail when executed in a batch with other tests, but will pass when executed alone.
     * This is due to the fact that the method uses the Scanner class to read input from the console.
     */
    @Test
    public void acceptChallenge() {
        TestingUtils.setInput(" ", " ", " ", " ");

        // Create an instance of User
        Player player1 = new Player("Name1", "Nick1", "Password1", "A11A1");
        player1.setCurrentCharacter(CharacterSelection.LYCANTHROPE);
        player1.setSpecialAbilities(new Don("SpecialAbility", 10, 10, 10));
        Player player2 = new Player("Name2", "Nick2", "Password2", "A11A2");
        player2.setCurrentCharacter(CharacterSelection.LYCANTHROPE);
        player2.setSpecialAbilities(new Don("SpecialAbility", 10, 10, 10));
        Game g = new Game();
        g.loadDefaultSettings();

        // Create an instance of Challenge
        Challenge challenge = new Challenge(player1, player2, 100);

        // Set the pending challenge
        player2.setPendingChallenge(challenge);

        // Call the acceptChallenge method
        player2.acceptChallenge();

        // Assert that the challenge is accepted
        assertTrue(challenge.isAccepted());
    }

    @Test
    public void getId() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getId method
        String id = player.getId();

        // Assert that the id is the initial id
        assertEquals("A11A1", id);
    }

    @Test
    public void setId() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Set the id and call the getId method
        player.setId("A11A2");
        String id = player.getId();

        // Assert that the id is the set id
        assertEquals("A11A2", id);
    }

    @Test
    public void getPendingChallenge() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Assert that the player has no pending challenge
        assertNull(player.getPendingChallenge());

        // Create an instance of Challenge
        Challenge challenge = new Challenge();

        // Set the pending challenge and call the getPendingChallenge method
        player.setPendingChallenge(challenge);
        Challenge pendingChallenge = player.getPendingChallenge();

        // Assert that the pending challenge is the challenge
        assertEquals(challenge, pendingChallenge);
    }

    @Test
    public void setPendingChallenge() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Create an instance of Challenge
        Challenge challenge = new Challenge();

        // Set the pending challenge and call the getPendingChallenge method
        player.setPendingChallenge(challenge);
        Challenge pendingChallenge = player.getPendingChallenge();

        // Assert that the pending challenge is the challenge
        assertEquals(challenge, pendingChallenge);
    }

    @Test
    public void isPendingNotification() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Assert that the player has no pending notification
        assertFalse(player.isPendingNotification());

        // Call the setPendingNotification method
        player.setPendingNotification(true);

        // Assert that the player has a pending notification
        assertTrue(player.isPendingNotification());
    }

    @Test
    public void setPendingNotification() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Assert that the player has no pending notification
        assertFalse(player.isPendingNotification());

        // Call the setPendingNotification method
        player.setPendingNotification(true);

        // Assert that the player has a pending notification
        assertTrue(player.isPendingNotification());
    }

    @Test
    public void isBanned() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Assert that the player is not banned
        assertFalse(player.isBanned());

        // Call the ban method
        player.ban();

        // Assert that the player is banned
        assertTrue(player.isBanned());
    }

    @Test
    public void setBanned() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Assert that the player is not banned
        assertFalse(player.isBanned());

        // Call the setBanned method
        player.setBanned(true);

        // Assert that the player is banned
        assertTrue(player.isBanned());
    }

    @Test
    public void getGold() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getGold method
        int gold = player.getGold();

        // Assert that the gold is the initial gold
        assertEquals(Const.INITIAL_GOLD, gold);
    }

    @Test
    public void setGold() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the setGold method
        player.setGold(100);

        // Call the getGold method
        int gold = player.getGold();

        // Assert that the gold is the set gold
        assertEquals(100, gold);
    }

    @Test
    public void getLastLostFight() {}

    @Test
    @Disabled
    public void setLastLostFight() {}

    @Test
    public void getCurrentCharacter() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getCurrentCharacter method
        CharacterSelection currentCharacter = player.getCurrentCharacter();

        // Assert that the current character is the initial character
        assertNull(currentCharacter);

        // Create an instance of CharacterSelection
        CharacterSelection character = CharacterSelection.LYCANTHROPE;

        // Set the current character and call the getCurrentCharacter method
        player.setCurrentCharacter(character);
        currentCharacter = player.getCurrentCharacter();

        // Assert that the current character is the character
        assertEquals(character, currentCharacter);
    }

    @Test
    public void setCurrentCharacter() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Create an instance of CharacterSelection
        CharacterSelection character = CharacterSelection.LYCANTHROPE;

        // Set the current character and call the getCurrentCharacter method
        player.setCurrentCharacter(character);
        CharacterSelection currentCharacter = player.getCurrentCharacter();

        // Assert that the current character is the character
        assertEquals(character, currentCharacter);
    }

    @Test
    public void getArmor() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getArmor method
        Armor armor = player.getArmor();

        // Assert that the armor is the initial armor
        assertEquals(null, armor);
    }

    @Test
    public void setArmor() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Create an instance of Armor
        Armor armor = new Armor("Armor", 100, 10);

        // Set the armor and call the getArmor method
        player.setArmor(armor);
        Armor currentArmor = player.getArmor();

        // Assert that the current armor is the armor
        assertEquals(armor, currentArmor);
    }

    @Test
    public void getWeapons() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getWeapons method
        Weapon[] weapons = player.getWeapons();

        // Assert 
        assertNull(weapons[0]);
        assertNull(weapons[1]);

        // Create an instance of Weapon
        Weapon weapon = new Weapon("Weapon", 100, 10, 2);

        // Set the weapons and call the getWeapons method
        player.setWeapons(new Weapon[] { weapon, weapon });
        weapons = player.getWeapons();

        // Assert that the weapons are the weapons
        assertEquals(weapon, weapons[0]);
        assertEquals(weapon, weapons[1]);
    }

    @Test
    public void setWeapons() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Create an instance of Weapon
        Weapon weapon = new Weapon("Weapon", 100, 10, 2);

        // Set the weapons and call the getWeapons method
        player.setWeapons(new Weapon[] { weapon, weapon });
        Weapon[] weapons = player.getWeapons();

        // Assert that the weapons are the weapons
        assertEquals(weapon, weapons[0]);
        assertEquals(weapon, weapons[1]);
    }

    @Test
    public void getChallenges() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getChallenges method
        List<Challenge> challenges = player.getChallenges();

        // Assert that the challenges are empty
        assertEquals(0, challenges.size());

        // Create an instance of Challenge
        Challenge challenge = new Challenge();
        player.addChallengeToHistory(challenge);

        // Call the getChallenges method
        challenges = player.getChallenges();

        // Assert that the challenges contain the challenge
        assertEquals(1, challenges.size());
        assertEquals(challenge, challenges.get(0));
    }

    @Test
    public void setChallenges() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Create an instance of Challenge
        Challenge challenge = new Challenge();

        // Set the challenges and call the getChallenges method
        player.setChallenges(List.of(challenge));
        List<Challenge> challenges = player.getChallenges();

        // Assert that the challenges contain the challenge
        assertEquals(1, challenges.size());
        assertEquals(challenge, challenges.get(0));
    }

    @Test
    public void hasPendingChallenge() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Assert that the player has no pending challenge
        assertFalse(player.hasPendingChallenge());

        // Create an instance of Challenge and set the pending challenge
        Challenge challenge = new Challenge();
        player.setPendingChallenge(challenge);

        // Assert that the player has a pending challenge
        assertTrue(player.hasPendingChallenge());
    }

    @Test
    public void getModifiers() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getModifiers method
        Modifier[] modifiers = player.getModifiers();

        // Assert that the modifiers are the initial modifiers
        assertEquals(2, modifiers.length);
        assertEquals(null, modifiers[0]);
        assertEquals(null, modifiers[1]);
    }

    @Test
    public void setModifiers() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Create an instance of Modifier
        Modifier modifier = new Strength("Modifier", 10);

        // Set the modifiers and call the getModifiers method
        player.setModifiers(new Modifier[] { modifier, modifier });
        Modifier[] modifiers = player.getModifiers();

        // Assert that the modifiers are the modifiers
        assertEquals(modifier, modifiers[0]);
        assertEquals(modifier, modifiers[1]);
    }


    @Test
    public void getSpecialAbility() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getSpecialAbility method
        SpecialAbility specialAbility = player.getSpecialAbility();

        // Assert that the special ability is the initial special ability
        assertEquals(null, specialAbility);
    }

    @Test
    @Disabled
    public void setSpecialAbilities() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Create an instance of SpecialAbility
        SpecialAbility specialAbility = new Talent("SpecialAbility", 10, 10);

        // Set the special abilities and call the getSpecialAbilities method
        player.setSpecialAbilities(specialAbility);
        SpecialAbility specialAbilities = player.getSpecialAbility();

        // Assert that the special abilities are the special abilities
        assertEquals(specialAbility, specialAbilities);
    }
}
