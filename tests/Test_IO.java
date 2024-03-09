package tests;

import org.junit.Test;

import utils.IOManager;
import utils.MenuBuilder;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
        setInput("0", "1", "hola");

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

    @Test
    /*
     * Test the MenuBuilder
     */
    public void testMenuBuilder_Menu() {
        // Inputs are read from the standard input
        setInput("1", "0", "", "2");

        // Test menu
        String options[] = { "Option 1", "Option 2", "Option 3" };
        int opt = MenuBuilder.menu("TITLE", options);
        assertEquals(1, opt);

        // Test menu with an invalid option
        int opt2 = MenuBuilder.menu("TITLE", options);
        assertEquals(2, opt2);
    }

    //  --------------------- Utils ---------------------

    public void setInput(String input) {
        input += "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    public void setInput(String... input) {
        String buffString = "";
        for (String str : input) {
            buffString += str;
            buffString += "\n";
        }
        InputStream in = new ByteArrayInputStream(buffString.getBytes());
        System.setIn(in);
    }

}
