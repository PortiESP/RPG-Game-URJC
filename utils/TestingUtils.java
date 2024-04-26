package utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestingUtils {
    public static void setInput(String input) {
        input += "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    public static void setInput(String... input) {
        String buffString = "";
        for (String str : input) {
            buffString += str;
            buffString += "\n";
        }
        InputStream in = new ByteArrayInputStream(buffString.getBytes());
        System.setIn(in);
    }
}
