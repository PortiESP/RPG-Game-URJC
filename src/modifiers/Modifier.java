package src.modifiers;

import java.util.ArrayList;

import src.equipment.Armor;

public abstract class Modifier {

    private String name;

    // ============================================================================================[ Constructor ]>>>
    public Modifier() {}

    public Modifier(String name) {
        this.name = name;
    }
    // ============================================================================================[ Static Methods ]>>>
    public static ArrayList<Modifier> loadFromArray(String[][] strengths, String[][] weaknesses) {
        ArrayList<Modifier> modifiers = new ArrayList<>();
        for (String[] modifier : strengths ) {
            modifiers.add(new Strength(modifier[0], Integer.parseInt(modifier[1])));
        }
        for (String[] modifier : weaknesses ) {
            modifiers.add(new Weakness(modifier[0], Integer.parseInt(modifier[1])));
        }

        return modifiers;
    } 
    // ============================================================================================[ Getters & Setters ]>>>
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
