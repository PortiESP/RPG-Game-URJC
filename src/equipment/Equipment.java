package src.equipment;

/**
 * Class that represents the Equipment.
 */
public abstract class Equipment {

    private String name;
    private int attack;
    private int defense;

    // ============================================================================================[ Constructor ]>>>
    public Equipment() {}

    public Equipment(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    public String toString() {
        return this.name + " ( Attack: " + this.attack + ", Defense: " + this.defense + " )";
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
