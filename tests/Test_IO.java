package tests;

import org.junit.Test;

import utils.IOManager;
import utils.MenuBuilder;

import static org.junit.Assert.*;

public class Test_IO {

    @Test
    /*
     * This test is used to check if the setup of the IO menus is working.
     */
    public void testPrints() {
        IOManager.print("holaaa\n");
        IOManager.setPRINT_PADDING("*****");
        IOManager.print("holaaa\n");

        assertTrue(true);
    }

    @Test
    /*
     * Test input methods
     */
    public void testIOManager() {
        // Inputs are read from the standard input
        TestingUtils.setInput("0", "1", "hola");

        // Read the inputs
        // 0
        int a = IOManager.readInt();
        assertEquals(0, a);
        // 1
        int b = IOManager.readInt();
        assertEquals(1, b);
        // hola
        String str = IOManager.readString();
        assertEquals("hola", str);
    }

    /*
     * Test the MenuBuilder
     */
    public void testMenuBuilder_Menu() {
        // Inputs are read from the standard input
        TestingUtils.setInput("1", "0", "", "2", "");

        // Test menu
        String options[] = { "Option 1", "Option 2", "Option 3" };
        int opt = MenuBuilder.menu("TITLE", options);
        assertEquals(1, opt);

        // Test menu with an invalid option
        int opt2 = MenuBuilder.menu("TITLE", options);
        assertEquals(2, opt2);
    }
}