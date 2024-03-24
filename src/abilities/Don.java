package src.abilities;

import java.util.ArrayList;


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
    public static ArrayList<Don> loadFromArray(String[][] donsArr) {
        ArrayList<Don> dons = new ArrayList<>();
        for (String[] don : donsArr) {
            dons.add(new Don(don[0], Integer.parseInt(don[1]), Integer.parseInt(don[2]), Integer.parseInt(don[3])));
        }

        return dons;
    }

    // Getters & Setters ==================================================================================================
    public int getMinRage() {
        return minRage;
    }

    public void setMinRage(int minRage) {
        this.minRage = minRage;
    }  
}
