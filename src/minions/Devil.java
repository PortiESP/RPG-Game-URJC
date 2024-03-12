package src.minions;

public class Devil extends Minion {
    private String covenant;
    private Minion[] minions;

    // ============================================================================================[ Constructor ]>>>
    public Devil(String name, int health, String covenant, Minion[] minions) {
        super(name, health);
        this.covenant = covenant;
        this.minions = minions;
    }

    // Getters & Setters ==================================================================================================
    public String getCovenant() {
        return covenant;
    }

    public void setCovenant(String covenant) {
        this.covenant = covenant;
    }

    public Minion[] getMinions() {
        return minions;
    }

    public void setMinions(Minion[] minions) {
        this.minions = minions;
    }

}
