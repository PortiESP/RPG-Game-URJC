package src.equipment;

public abstract class Equipment {
    private String name;
    private int attack;
    private int defense;

    // ============================================================================================[ Constructor ]>>>
    public Equipment(String name, int attackModifier, int defenseModifier) {
        this.name = name;
        this.attack = attackModifier;
        this.defense = defenseModifier;
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

    public void setAttack(int attackModifier) {
        this.attack = attackModifier;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defenseModifier) {
        this.defense = defenseModifier;
    }
}
