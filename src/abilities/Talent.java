package src.abilities;

import java.util.ArrayList;

/**
 * Class that represents a Talent special ability of a character.
 */
public class Talent extends SpecialAbility {

    // ============================================================================================[ Constructor ]>>>
    public Talent() {
        super();
    }

    public Talent(String name, int attack, int defense) {
        super(name, attack, defense);
    }

    // ============================================================================================[ Static Methods ]>>>
    /**
     * Load talents from a multidimensional array
     *
     * @param talentsArr 2D array with talents data as strings (name, attack, defense)
     * @return ArrayList of Talent objects
     */
    public static ArrayList<Talent> loadFromArray(String[][] talentsArr) {
        ArrayList<Talent> talents = new ArrayList<>();
        for (String[] talent : talentsArr) {
            talents.add(new Talent(talent[0], Integer.parseInt(talent[1]), Integer.parseInt(talent[2])));
        }

        return talents;
    }
}
