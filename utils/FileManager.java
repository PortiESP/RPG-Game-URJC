package utils;

import java.beans.*;
import java.io.*;
/*
    Auxiliary class containing the methods to save and read files in XML format
    - saveFile: Method to save a data in a file
    - readFile: Method to read a data from a file
*/

public class FileManager {

    public static void saveFile(Object data, String filePath) {
        // Create finalPath with actualDate
        String finalPath = getFilePath(filePath);

        // Try to save the file
        try {
            // Create the file if it doesn't exist
            File file = new File(finalPath);
            if (!file.exists()) file.createNewFile();

            // Configure the encoder
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(finalPath)));

            // Save the object
            encoder.writeObject(data);
            encoder.close();
        } catch (IOException e) {
            System.out.println("Error trying to save the file");
        }
    }

    public static Object readFile(String filePath) {
        // Create finalPath with fileName
        String finalPath = getFilePath(filePath);

        // Try to read the file
        try {
            // Configure the decoder
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(finalPath)));

            // Read the object
            Object data = decoder.readObject();
            decoder.close();
            return data;

        } catch (IOException e) {
            return null;
        }
    }

    public static String getFilePath(String relativePath) {
        String absolutePath = System.getProperty("user.dir");
        return absolutePath + relativePath;
    }
}
