package src.minions;

import java.util.ArrayList;

public class Devil extends Minion {

    private String covenant;
    private ArrayList<Minion> minions = new ArrayList<>();

    // ============================================================================================[ Constructor ]>>>
    public Devil(String name, int health, String covenant) {
        super(name, health);
        this.covenant = covenant;
        generateRecursiveMinions();
    }

    // ============================================================================================[ Private Methods ]>>>
    private void generateRecursiveMinions() {
        int randomNum = (int) (Math.random() * 10);
        if (randomNum > 8) {
            this.minions.add(new Devil("Devil Minion", 1, "Covenant"));
        }
    }

    // ============================================================================================[ Static Methods ]>>>
    public static ArrayList<Devil> loadFromArray(String[][] devilsArr) {

        ArrayList<Devil> devils = new ArrayList<>();
        for (String[] devil : devilsArr) {
            devils.add(new Devil(devil[0], Integer.parseInt(devil[1]),devil[2]));
        }
      return devils;
    }
    // ============================================================================================[ Getters & Setters ]>>>
    public String getCovenant() {
        return covenant;
    }

    public void setCovenant(String covenant) {
        this.covenant = covenant;
    }

    public ArrayList<Minion> getMinions() {
        return minions;
    }

    public void setMinions(ArrayList<Minion> minions) {
        this.minions = minions;
    }

    @Override
    public int getHealth() {
        int cumHealth = super.getHealth();
        for (Minion minion : minions) {
            cumHealth += minion.getHealth();
        }
        return cumHealth;
    }
}
