package utils;

public class Const {

    // Constants for the file paths
    public static final String DATA_PATH = "/data/game.xml";

    // Constants for the game rules
    public static final int INITIAL_GOLD = 500;

    // Default Available Armors
    public static final String[][] ARMORS = {
        // Name, Attack Modifier, Defense Modifier
        { "Leather Armor", "0", "1" },
        { "Chainmail Armor", "0", "2" },
        { "Plate Armor", "0", "3" },
        { "Dragon Armor", "1", "4" },
    };

    // Default Available Weapons
    public static final String[][] WEAPONS = {
        // Name, Attack Modifier, Defense Modifier, Hands
        { "Dagger", "1", "1", "1" },
        { "Sword", "2", "2", "1" },
        { "Axe", "3", "4", "2" },
        { "Dragon Slayer", "6", "1", "2" },
    };
    // TODO DANI 1: Add a constant for the modifiers, minions, special abilities.
    public static final String[][] STRENGHTS = {
        //Name, modifier
        { "Moonlight","4" },
        { "Darkness", "3" },
        { "Fire", "1" },
        { "Magic", "2" }

    };
    public static final String[][] WEAKNESSES = {
        //Name, modifier
        {"Garlic","3"},
        { "Silver", "2" },
        { "Holy Water", "3" },
        { "Sunlight", "4" } 
    };
    public static final String[][] DONES = {
        // Name, Attack Modifier, Defense Modifier, Cost
        { "Claw", "1", "1", "2" },
        { "Bite", "2", "2", "3" },
        { "Tail", "2", "3", "4" },
        { "Roar", "1", "2", "5" }
    };
    public static final String[][] DISCIPLINES = {
        // Name, Attack Modifier, Defense Modifier, Cost
        { "Bloodlust", "1", "3", "2" },
        { "Frenzy", "2", "1", "3" },
        { "Rage", "3", "2", "4" },
        { "Vampirism", "1", "3", "5" }
    };
    public static final String[][] TALENTS = {
        // Name, Attack Modifier, Defense Modifier, Cost
        { "Sneak", "1", "1" },
        { "Hide", "2", "2" },
        { "Backstab", "2", "3" },
        { "Assassinate", "1", "2" }
    };

}
