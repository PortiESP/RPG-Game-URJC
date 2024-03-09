package src.characters;

//Import Statements
import src.abilities.*;
import src.equipment.Equipment;
import src.minions.Minion;
import src.modifiers.Modifier;

public class Hunter extends Character {
    private int willpower;
    private Talent talent;

    // Constructor ========================================================================================================
    public Hunter(String name, int health, int power, Modifier[] modifiers, Minion[] minions, Equipment[] equipment,
            int willpower, Talent talent) {
        super(name, health, power, modifiers, minions, equipment);
        this.willpower = willpower;
        this.talent = talent;
    }
    // Getters & Setters ==================================================================================================

    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }

    public Talent getTalent() {
        return talent;
    }

    public void setTalent(Talent talent) {
        this.talent = talent;
    }
}
