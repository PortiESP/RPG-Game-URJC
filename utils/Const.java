package utils;

public class Const {

  // Constants for the file paths
  public static final String DATA_PATH = "/data/game.xml";

  // Constants for the game rules
  public static final int INITIAL_GOLD = 500;

  // Default Available Armors
  public static final String[][] ARMORS = {
    // Name, Attack Modifier, Defense Modifier
    { "Leather Armor", "0", "10" },
    { "Chainmail Armor", "10", "20" },
    { "Plate Armor", "15", "30" },
    { "Dragon Armor", "20", "40" },
  };

  // Default Available Weapons
  public static final String[][] WEAPONS = {
    // Name, Attack Modifier, Defense Modifier, Hands
    { "Dagger", "0", "10", "1" },
    { "Sword", "10", "20", "1" },
    { "Axe", "15", "30", "2" },
    { "Dragon Slayer", "20", "40", "2" },
  };
}
