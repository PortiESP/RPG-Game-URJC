package src.equipment;

import java.util.ArrayList;

/**
 * Class that represents the Armor equipment.
 */
public class Armor extends Equipment {

    // ============================================================================================[ Constructor ]>>>
    public Armor() {
        super();
    }

    public Armor(String name, int attackModifier, int defenseModifier) {
        super(name, attackModifier, defenseModifier);
    }

    // ============================================================================================[ Static Methods ]>>>
    /**
     * Load armors from a multidimensional array
     *
     * @param armorsArray 2D array with armors data as strings (name, attack, defense)
     * @return ArrayList of Armor objects
     */
    public static ArrayList<Armor> loadFromArray(String[][] armorsArray) {
        ArrayList<Armor> armors = new ArrayList<>();
        for (String[] armor : armorsArray) {
            armors.add(new Armor(armor[0], Integer.parseInt(armor[1]), Integer.parseInt(armor[2])));
        }

        return armors;
    }
}
