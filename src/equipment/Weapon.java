package src.equipment;

public class Weapon extends Equipment {
    private int handsRequired;

    // Constructor ========================================================================================================
    public Weapon(String name, int attackModifier, int defenseModifier, int handsRequired) {
        super(name, attackModifier, defenseModifier);
        this.handsRequired = handsRequired;
    }

    // Getters & Setters ==================================================================================================
    public int getHandsRequired() {
        return handsRequired;
    }

    public void setHandsRequired(int handsRequired) {
        this.handsRequired = handsRequired;
    }
}
