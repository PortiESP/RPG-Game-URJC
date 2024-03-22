package utils;

import src.equipment.*;
import java.util.List;

public class Const {

    // Constants for the file paths
    public static final String DATA_PATH = "/data/game.xml";

    // Constants for the game rules
    public static final int INITIAL_GOLD = 500;

    // Default Available Armors
    public static final List<Armor> ARMORS = List.of(
            new Armor("Leather Armor", 0, 10),
            new Armor("Chainmail Armor", 10, 20),
            new Armor("Plate Armor", 15, 30),
            new Armor("Dragon Armor", 20, 40)
    );

    // Default Available Weapons
    public static final List<Weapon> WEAPONS = List.of(
            new Weapon("Dagger", 0, 10, 1),
            new Weapon("Sword", 10, 20, 1),
            new Weapon("Axe", 15, 30, 2),
            new Weapon("Dragon Slayer", 20, 40, 2)
    );
}
