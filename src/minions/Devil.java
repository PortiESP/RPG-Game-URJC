package src.minions;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Devil extends Minion {

    private String covenant;
    private Minion[] minions;

    // ============================================================================================[ Constructor ]>>>
    public Devil(String name, int health, String covenant, Minion[] minions) {
        super(name, health);
        this.covenant = covenant;
        this.minions = minions;
    }

    // ============================================================================================[ Static Methods ]>>>
    public static ArrayList<Devil> loadFromArray(String[][] devilsArr) {
        //TODO - reimplement this method 
        ArrayList<Devil> devils = new ArrayList<>();
        for (String[] devil : devilsArr) {
            // devils.add(new Devil(devil[0], Integer.parseInt(devil[1]), devil[2], Minion.loadFromArray(devil[3])));
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

    public Minion[] getMinions() {
        return minions;
    }

    public void setMinions(Minion[] minions) {
        this.minions = minions;
    }
}
