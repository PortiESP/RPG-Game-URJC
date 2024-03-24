package src.abilities;

import java.util.ArrayList;

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
    public static ArrayList<Discipline> loadFromArray(String[][] disciplinesArr) {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        for (String[] discip : disciplinesArr) {
            disciplines.add(new Discipline(discip[0], Integer.parseInt(discip[1]), Integer.parseInt(discip[2]), Integer.parseInt(discip[3])));
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
