package src.minions;

public abstract class Minion {

    private String name;
    private int health;

    // ============================================================================================[ Constructor ]>>>
    public Minion(String name, int health) {
        this.name = name;
        this.health = health;
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
