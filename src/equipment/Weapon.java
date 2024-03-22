package src.equipment;

public class Weapon extends Equipment {
    private int handsRequired;

    // ============================================================================================[ Constructor ]>>>
    public Weapon() {
        super();
    }

    public Weapon(String name, int attack, int defense, int handsRequired) {
        super(name, attack, defense);
        this.handsRequired = handsRequired;
    }

    // ============================================================================================[ Public Methods ]>>>
    @Override
    public String toString() {
        return super.toString() + " ( Hands Required: " + this.handsRequired + ")";
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public int getHandsRequired() {
        return handsRequired;
    }

    public void setHandsRequired(int handsRequired) {
        this.handsRequired = handsRequired;
    }
}
