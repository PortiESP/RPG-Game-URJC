package src.equipment;

import java.util.ArrayList;

public class Weapon extends Equipment {

    private int handsRequired;

    // ============================================================================================[ Constructor ]>>>
    public Weapon() {
        super();
    }

    public Weapon(String name, int attack, int defense, int handsRequired) {
        super(name, attack, defense);
        this.handsRequired = handsRequired;
    }

    // ============================================================================================[ Public Methods ]>>>
    @Override
    public String toString() {
        return super.toString() + " (Hands Required: " + this.handsRequired + ")";
    }

    // ============================================================================================[ Static Methods ]>>>
    /**
     * Load weapons from a multidimensional array
     *
     * @param arr 2D array with weapons data as strings (name, attack, defense, hands required)
     * @return ArrayList of Weapon objects
     */
    public static ArrayList<Weapon> loadFromArray(String[][] arr) {
        ArrayList<Weapon> weapons = new ArrayList<>();
        for (String[] weapon : arr) {
            weapons.add(new Weapon(weapon[0], Integer.parseInt(weapon[1]), Integer.parseInt(weapon[2]), Integer.parseInt(weapon[3])));
        }

        return weapons;
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public int getHandsRequired() {
        return handsRequired;
    }

    public void setHandsRequired(int handsRequired) {
        this.handsRequired = handsRequired;
    }
}
