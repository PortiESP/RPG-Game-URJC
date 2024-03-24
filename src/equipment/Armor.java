package src.equipment;

import java.util.ArrayList;

public class Armor extends Equipment {

    // ============================================================================================[ Constructor ]>>>
    public Armor() {
        super();
    }

    public Armor(String name, int attackModifier, int defenseModifier) {
        super(name, attackModifier, defenseModifier);
    }

    // ============================================================================================[ Static Methods ]>>>
    public static ArrayList<Armor> loadFromArray(String[][] arr) {
        ArrayList<Armor> armors = new ArrayList<>();
        for (String[] armor : arr) {
            armors.add(new Armor(armor[0], Integer.parseInt(armor[1]), Integer.parseInt(armor[2])));
        }

        return armors;
    }
}
