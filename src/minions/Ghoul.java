package src.minions;

import java.util.ArrayList;

/**
 * Class that represents the Ghoul minion.
 */
public class Ghoul extends Minion {

    private int dependency;

    // ============================================================================================[ Constructor ]>>>
    public Ghoul() {
        super();
    }

    public Ghoul(String name, int health, int dependency) {
        super(name, health);
        this.dependency = dependency;
    }

    // ============================================================================================[ Static Methods ]>>>
    /**
     * Load ghouls from a multidimensional array
     *
     * @param ghoulsArr 2D array with ghouls data as strings (name, health, dependency)
     * @return ArrayList of Ghoul objects
     */
    public static ArrayList<Ghoul> loadFromArray(String[][] ghoulsArr) {
        ArrayList<Ghoul> ghouls = new ArrayList<>();
        for (String[] ghoul : ghoulsArr) {
            ghouls.add(new Ghoul(ghoul[0], Integer.parseInt(ghoul[1]), Integer.parseInt(ghoul[2])));
        }
        return ghouls;
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public int getDependency() {
        return dependency;
    }

    public void setDependency(int dependency) {
        this.dependency = dependency;
    }
}
