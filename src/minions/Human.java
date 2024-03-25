package src.minions;

import java.util.ArrayList;

/**
 * Class that represents the Human minion.
 */
public class Human extends Minion {

    private LoyaltyEnum loyalty;

    // ============================================================================================[ Constructor ]>>>
    public Human(String name, int health, LoyaltyEnum loyalty) {
        super(name, health);
        this.loyalty = loyalty;
    }

    // ============================================================================================[ Static Methods ]>>>
    /**
     * Load humans from a multidimensional array
     *
     * @param humansArr 2D array with humans data as strings (name, health, loyalty)
     * @return ArrayList of Human objects
     */
    public static ArrayList<Human> loadFromArray(String[][] humansArr) {
        ArrayList<Human> humans = new ArrayList<>();
        for (String[] human : humansArr) {
            humans.add(new Human(human[0], Integer.parseInt(human[1]), LoyaltyEnum.valueOf(human[2])));
        }
        return humans;
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public LoyaltyEnum getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(LoyaltyEnum loyalty) {
        this.loyalty = loyalty;
    }
}
