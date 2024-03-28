package src.abilities;

import java.util.ArrayList;

import src.Game;

/**
 * Class that represents a Discipline special ability of a character.
 */
public class Discipline extends SpecialAbility {

    private int cost;

    // ============================================================================================[ Constructors ]>>>
    public Discipline() {
        super();
    }

    public Discipline(String name, int attack, int defense, int cost) {
        super(name, attack, defense);
        this.cost = cost;
    }

    // ============================================================================================[ Static Methods ]>>>
    /**
     * Load disciplines from a multidimensional array
     *
     * @param disciplinesArr 2D array with disciplines data as strings (name, attack, defense, cost)
     * @return ArrayList of Discipline objects
     */
    public static ArrayList<Discipline> loadFromArray(String[][] disciplinesArr) {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        for (String[] discip : disciplinesArr) {
            disciplines.add(new Discipline(discip[0], Integer.parseInt(discip[1]), Integer.parseInt(discip[2]), Integer.parseInt(discip[3])));
        }

        return disciplines;
    }

    public String toString() {
        return super.toString() + String.format(" ( Cost: %d )", this.cost);
    }

    public static String[] listAvailableDisciplines() {
        String[] disciplines = new String[Game.disciplinesAvailable.size()];

        for (int i = 0; i < Game.disciplinesAvailable.size(); i++) {
            Discipline discipline = Game.disciplinesAvailable.get(i);
            disciplines[i] = discipline.toString();
        }

        return disciplines;
    }

    // Getters & Setters ==================================================================================================
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
