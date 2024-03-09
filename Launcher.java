
/*
 * Main class of the game.
 * It creates a new Game object and calls the play method to start the game.
*/

import src.Game;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}