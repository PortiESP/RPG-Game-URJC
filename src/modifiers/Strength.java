package src.modifiers;

public class Strength extends Modifier {
    private int effectiveness;

    // ============================================================================================[ Constructor ]>>>
    public Strength() {
    }

    public Strength(String name, int effectiveness) {
        super(name);
        this.effectiveness = effectiveness;
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public int getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(int effectiveness) {
        this.effectiveness = effectiveness;
    }

}
