package src.abilities;

public abstract class SpecialAbility {

    private String name;
    private int attack;
    private int defense;

    // ============================================================================================[ Constructor ]>>>
    public SpecialAbility() {}

    public SpecialAbility(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    // Getters & Setters ==================================================================================================
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
