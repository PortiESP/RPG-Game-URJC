package src.abilities;

import java.util.ArrayList;

import src.Game;

/**
 * Class that represents a Don special ability of a character.
 */
public class Don extends SpecialAbility {

    private int minRage;

    // ============================================================================================[ Constructors ]>>>
    public Don() {
        super();
    }

    public Don(String name, int attack, int defense, int minRage) {
        super(name, attack, defense);
        this.minRage = minRage;
    }

    // ============================================================================================[ Static Methods ]>>>
    /**
     * Load dons from a multidimensional array
     *
     * @param donsArr 2D array with dons data as strings (name, attack, defense, minRage)
     * @return ArrayList of Don objects
     */
    public static ArrayList<Don> loadFromArray(String[][] donsArr) {
        ArrayList<Don> dons = new ArrayList<>();
        for (String[] don : donsArr) {
            dons.add(new Don(don[0], Integer.parseInt(don[1]), Integer.parseInt(don[2]), Integer.parseInt(don[3])));
        }

        return dons;
    }

    public String toString() {
        return super.toString() + String.format(" ( Min Rage: %d )", this.minRage);
    }

    public static String[] listAvailableDones() {
        String[] dones = new String[Game.donesAvailable.size()];

        for (int i = 0; i < Game.donesAvailable.size(); i++) {
            Don don = Game.donesAvailable.get(i);
            dones[i] = don.toString();
        }

        return dones;
    }

    // Getters & Setters ==================================================================================================
    public int getMinRage() {
        return minRage;
    }

    public void setMinRage(int minRage) {
        this.minRage = minRage;
    }
}
