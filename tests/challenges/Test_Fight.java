package tests.challenges;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import src.challenges.Fight;
import src.users.Player;
import src.characters.Character;
import utils.MenuBuilder;


public class Test_Fight {

    @Test
    public void testGetRounds() {
        Fight fight = new Fight();
        fight.setRounds(5);
        Assertions.assertEquals(5, fight.getRounds());
    }

    @Test
    public void testGetDate() {
        Fight fight = new Fight();
        fight.setDate("2022-01-01");
        Assertions.assertEquals("2022-01-01", fight.getDate());
    }

    @Test
    public void testGetWinner() {
        Fight fight = new Fight();
        Player winner = new Player();
        fight.setWinner(winner);
        Assertions.assertEquals(winner, fight.getWinner());
    }

    @Test
    public void testGetLog() {
        Fight fight = new Fight();
        ArrayList<String> log = new ArrayList<>();
        log.add("Round 1");
        log.add("Round 2");
        fight.setLog(log);
        Assertions.assertEquals(log, fight.getLog());
    }

    @Test
    public void testGetC1() {
        Fight fight = new Fight();
        Character c1 = new Character();
        fight.setC1(c1);
        Assertions.assertEquals(c1, fight.getC1());
    }

    @Test
    public void testGetC2() {
        Fight fight = new Fight();
        Character c2 = new Character();
        fight.setC2(c2);
        Assertions.assertEquals(c2, fight.getC2());
    }

    @Test
    public void testGetPlayers() {
        Fight fight = new Fight();
        Player[] players = new Player[2];
        players[0] = new Player();
        players[1] = new Player();
        fight.setPlayers(players);
        Assertions.assertArrayEquals(players, fight.getPlayers());
    }

    @Test
    public void testSetRounds() {
        Fight fight = new Fight();
        fight.setRounds(10);
        Assertions.assertEquals(10, fight.getRounds());
    }

    @Test
    public void testSetDate() {
        Fight fight = new Fight();
        fight.setDate("2022-02-01");
        Assertions.assertEquals("2022-02-01", fight.getDate());
    }

    @Test
    public void testSetWinner() {
        Fight fight = new Fight();
        Player winner = new Player();
        fight.setWinner(winner);
        Assertions.assertEquals(winner, fight.getWinner());
    }

    @Test
    public void testSetLog() {
        Fight fight = new Fight();
        ArrayList<String> log = new ArrayList<>();
        log.add("Round 1");
        log.add("Round 2");
        fight.setLog(log);
        Assertions.assertEquals(log, fight.getLog());
    }

    @Test
    public void testSetC1() {
        Fight fight = new Fight();
        Character c1 = new Character();
        fight.setC1(c1);
        Assertions.assertEquals(c1, fight.getC1());
    }

    @Test
    public void testSetC2() {
        Fight fight = new Fight();
        Character c2 = new Character();
        fight.setC2(c2);
        Assertions.assertEquals(c2, fight.getC2());
    }

    @Test
    public void testSetPlayers() {
        Fight fight = new Fight();
        Player[] players = new Player[2];
        players[0] = new Player();
        players[1] = new Player();
        fight.setPlayers(players);
        Assertions.assertArrayEquals(players, fight.getPlayers());
    }
}
