package src.modifiers;

import java.util.ArrayList;

/**
 * Abstract class that represents a Modifier.
 */
public abstract class Modifier {

    private String name;

    // ============================================================================================[ Constructor ]>>>
    public Modifier() {}

    public Modifier(String name) {
        this.name = name;
    }

    // ============================================================================================[ Static Methods ]>>>
    /**
     * Load modifiers from two multidimensional arrays
     *
     * @param strengths 2D array with strengths data as strings (name, effectiveness)
     * @param weaknesses 2D array with weaknesses data as strings (name, sensitivity)
     * @return ArrayList of Modifier objects
     */
    public static ArrayList<Modifier> loadFromArray(String[][] strengths, String[][] weaknesses) {
        ArrayList<Modifier> modifiers = new ArrayList<>();
        for (String[] modifier : strengths) {
            modifiers.add(new Strength(modifier[0], Integer.parseInt(modifier[1])));
        }
        for (String[] modifier : weaknesses) {
            modifiers.add(new Weakness(modifier[0], Integer.parseInt(modifier[1])));
        }

        return modifiers;
    }

    public String toString() {
        return this.name;
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
