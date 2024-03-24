package src.abilities;

public class Discipline extends SpecialAbility {
    private int cost;

    // ============================================================================================[ Constructors ]>>>
    public Discipline() {
        super();
    }
    public Discipline(String name, int attack, int defense, int cost) {
        super(name, attack, defense);
        this.cost = cost;
    }

    // Getters & Setters ==================================================================================================
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
}
