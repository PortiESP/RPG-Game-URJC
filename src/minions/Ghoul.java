package src.minions;

public class Ghoul extends Minion {

    private int dependency;

    // ============================================================================================[ Constructor ]>>>
    public Ghoul(String name, int health, int dependency) {
        super(name, health);
        this.dependency = dependency;
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public int getDependency() {
        return dependency;
    }

    public void setDependency(int dependency) {
        this.dependency = dependency;
    }
}
