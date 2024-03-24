package src.minions;

public class Human extends Minion {

    private LoyaltyEnum loyalty;

    // ============================================================================================[ Constructor ]>>>
    public Human(String name, int health, LoyaltyEnum loyalty) {
        super(name, health);
        this.loyalty = loyalty;
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public LoyaltyEnum getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(LoyaltyEnum loyalty) {
        this.loyalty = loyalty;
    }
}
