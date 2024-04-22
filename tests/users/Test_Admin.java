package tests.users;

import static org.junit.Assert.*;

import org.junit.Test;
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
