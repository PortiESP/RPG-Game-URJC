package tests.users;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

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

    @BeforeEach
    public static void init() {
        Game g = new Game();
        g.loadDefaultSettings();        
    }

    @Test
    public void testPlayer() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Assert that the player is not null
        assertNotNull(player);

        // Assert data is set correctly
        assertEquals("Name", player.getName());
        assertEquals("Nick", player.getNick());
        assertEquals("Password", player.getPassword());
        assertEquals("A11A1", player.getId());
    }

    @Test
    public void testAddChallengeToHistory() {
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
    public void testHasChallenges() {
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
    public void testGetScore() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getScore method
        int score = player.getScore();

        // Assert that the score is the initial gold
        assertEquals(Const.INITIAL_GOLD, score);
    }

    @Test
    public void testShowInfo() {
        TestingUtils.setInput("");

        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Check if the methond throws an exception when the current character is null
        assertThrows(NullPointerException.class, () -> player.showInfo());

    }

    @Test
    public void testDefeatedRecently() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Check if the methond throws an exception when the current character is null
        boolean b = player.defeatedRecently();

        // Assert that the player has not been defeated recently
        assertFalse(b);

        // Set the last lost fight to the current time
        player.setLastLostFight(System.currentTimeMillis());

        // Check if the methond throws an exception when the current character is null
        b = player.defeatedRecently();

        // Assert that the player has been defeated recently
        assertTrue(b);
    }

    @Test
    public void testBan() {
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
    public void testUban() {
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

    public void testManageModifiers() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Check if the methond throws an exception when the current character is null
        assertDoesNotThrow(() -> player.manageModifiers());
    }

    public void testChangeModifier() {
        TestingUtils.setInput("0");
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Check if the methond throws an exception when the current character is null
        assertDoesNotThrow(() -> player.changeModifier(1));
    }

    public void testChangeSpecialAbility() {
        TestingUtils.setInput("0");
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Check if the methond throws an exception when the current character is null
        assertDoesNotThrow(() -> player.changeSpecialAbility());
    }

    // @Test
    public void testShowSpecialAbilities() {
        TestingUtils.setInput("");

        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Check if the methond throws an exception when the current character is null
        assertDoesNotThrow(() -> player.showSpecialAbilities());
    }

    public void testManageEquipment() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Check if the methond throws an exception when the current character is null
        assertDoesNotThrow(() -> player.manageEquipment());
    }

    public void testChangeArmor() {
        TestingUtils.setInput("1", "");

        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");
        Game g = new Game();
        g.loadDefaultSettings();

        // Check if the methond throws an exception when the current character is null
        assertDoesNotThrow(() -> player.changeArmor());
    }

    /**
     * ⚠️ This test may fail when executed in a batch with other tests, but will pass when executed alone.
     * This is due to the fact that the method uses the Scanner class to read input from the console.
     */
    public void testChangeWeapon() {
        TestingUtils.setInput("1", "");

        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");
        Game g = new Game();
        g.loadDefaultSettings();

        // Check if the methond throws an exception when the current character is null
        assertDoesNotThrow(() -> player.changeWeapon(1));
    }

    @Test
    public void testNotifyChallenge() {
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
    public void testManageNotifications() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the manageNotifications method
        player.manageNotifications();

        // Assert that the player has no pending notification
        assertFalse(player.isPendingNotification());
    }

    @Test
    public void testPayGoldTo() {
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
    public void testCanAfford() {
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
    public void testAcceptChallenge() {
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
    public void testGetId() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getId method
        String id = player.getId();

        // Assert that the id is the initial id
        assertEquals("A11A1", id);
    }

    @Test
    public void testSetId() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Set the id and call the getId method
        player.setId("A11A2");
        String id = player.getId();

        // Assert that the id is the set id
        assertEquals("A11A2", id);
    }

    @Test
    public void testGetPendingChallenge() {
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
    public void testSetPendingChallenge() {
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
    public void testIsPendingNotification() {
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
    public void testSetPendingNotification() {
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
    public void testIsBanned() {
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
    public void testSetBanned() {
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
    public void testGetGold() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getGold method
        int gold = player.getGold();

        // Assert that the gold is the initial gold
        assertEquals(Const.INITIAL_GOLD, gold);
    }

    @Test
    public void testSetGold() {
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
    public void testGetLastLostFight() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getLastLostFight method
        long lastLostFight = player.getLastLostFight();

        // Assert that the last lost fight is the initial time
        assertEquals(0, lastLostFight);
    }

    @Test
    public void testSetLastLostFight() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Set the last lost fight and call the getLastLostFight method
        player.setLastLostFight(9999999L);

        // Assert that the last lost fight is the current time
        assertNotNull(player.getLastLostFight());
    }

    @Test
    public void testGetCurrentCharacter() {
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
    public void testSetCurrentCharacter() {
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
    public void testGetArmor() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getArmor method
        Armor armor = player.getArmor();

        // Assert that the armor is the initial armor
        assertEquals(null, armor);
    }

    @Test
    public void testSetArmor() {
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
    public void testGetWeapons() {
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
    public void testSetWeapons() {
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
    public void testGetChallenges() {
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
    public void testSetChallenges() {
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
    public void testHasPendingChallenge() {
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
    public void testGetModifiers() {
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
    public void testSetModifiers() {
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
    public void testGetSpecialAbility() {
        // Create an instance of User
        Player player = new Player("Name", "Nick", "Password", "A11A1");

        // Call the getSpecialAbility method
        SpecialAbility specialAbility = player.getSpecialAbility();

        // Assert that the special ability is the initial special ability
        assertEquals(null, specialAbility);
    }

    @Test
    public void testSetSpecialAbilities() {
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
