package utils;

/*
    Auxiliary class containing the methods to save and read files
    - saveFile: Method to save a game in a file
    - readFile: Method to read a game from a file
*/

import src.Game;
import java.beans.*;
import java.io.*;

public class FileManager {

    public static void saveFile(Game game) {

        // Create finalPath with actualDate
        String filePath = Constants.DATA_PATH;
        String finalPath = getFilePath(filePath);

        // Try to save the file
        try {

            // Create the file if it doesn't exist
            File file = new File(finalPath);
            if (!file.exists())
                file.createNewFile();

            // Configure the encoder
            XMLEncoder encoder = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(finalPath)));

            // Save the object
            encoder.writeObject(game);
            encoder.close();

        } catch (IOException e) {
            System.out.println("Error trying to save the file");
        }
    }

    public static Game readFile(String filePath) {

        // Create finalPath with fileName
        String finalPath = getFilePath(filePath);

        // Try to read the file
        try {

            // Configure the decoder
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(finalPath)));

            // Read the object
            Game game = (Game) decoder.readObject();
            decoder.close();
            return game;

        } catch (IOException e) {
            return null;
        }
    }

    public static String getFilePath(String relativePath) {
        String absolutePath = System.getProperty("user.dir");
        return absolutePath + relativePath;
    }
}