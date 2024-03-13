package src.equipment;

public abstract class Equipment {
    private String name;
    private int attackModifier;
    private int defenseModifier;

    // ============================================================================================[ Constructor ]>>>
    public Equipment(String name, int attackModifier, int defenseModifier) {
        this.name = name;
        this.attackModifier = attackModifier;
        this.defenseModifier = defenseModifier;
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(int attackModifier) {
        this.attackModifier = attackModifier;
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    public void setDefenseModifier(int defenseModifier) {
        this.defenseModifier = defenseModifier;
    }
}
