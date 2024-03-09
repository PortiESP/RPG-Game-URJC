package tests;

import org.junit.Test;

import utils.IOManager;

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
    public void testInput() {
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
