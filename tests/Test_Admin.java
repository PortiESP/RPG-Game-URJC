package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import src.users.Admin;

public class Test_Admin {

    @Test
    public void getScore() {
        Admin admin = new Admin();
        assertEquals(0, admin.getScore());
    }

    @Test
    public void manageChallenge() {
        // TODO Implement this test 

    }
}