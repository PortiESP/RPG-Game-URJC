package src.abilities;

public class Don extends SpecialAbility {
    private int minRage;

    // ============================================================================================[ Constructors ]>>>
    public Don() {
        super();
    }

    public Don(String name, int attack, int defense, int minRage) {
        super(name, attack, defense);
        this.minRage = minRage;
    }

    // Getters & Setters ==================================================================================================
    public int getMinRage() {
        return minRage;
    }

    public void setMinRage(int minRage) {
        this.minRage = minRage;
    }  
}
