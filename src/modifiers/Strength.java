package src.modifiers;

/**
 * Class that represents the Strength modifier.
 */
public class Strength extends Modifier {

    private int effectiveness;

    // ============================================================================================[ Constructor ]>>>
    public Strength() {}

    public Strength(String name, int effectiveness) {
        super(name);
        this.effectiveness = effectiveness;
    }

    public String toString() {
        return String.format("%s (+%d)", this.getName(), this.effectiveness);
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public int getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(int effectiveness) {
        this.effectiveness = effectiveness;
    }
}
