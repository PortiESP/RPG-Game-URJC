package src.abilities;

import java.util.ArrayList;

public class Talent extends SpecialAbility {

    // ============================================================================================[ Constructor ]>>>
    public Talent() {
        super();
    }
    public Talent(String name, int attack, int defense) {
        super(name, attack, defense);
    }

    // ============================================================================================[ Static Methods ]>>>
    public static ArrayList<Talent> loadFromArray(String[][] talentsArr) {
        ArrayList<Talent> talents = new ArrayList<>();
        for (String[] talent : talentsArr) {
            talents.add(new Talent(talent[0], Integer.parseInt(talent[1]), Integer.parseInt(talent[2])));
        }

        return talents;
    }

}
