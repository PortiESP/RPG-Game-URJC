package src.characters;

//Import Statements
import src.abilities.*;

public class Lycanthrope extends Character {

    private int rage;
    private Don don;

    // CONSTANTS
    public final int MAX_RAGE = 3;

    // ============================================================================================[ Constructor ]>>>
    public Lycanthrope() {
        super();
        rage = 0;
        this.loadSpecial();
        this.loadMinions();
    }

    // ============================================================================================[ Public Methods ]>>>

    @Override
    public void loadMinions() {
        // TODO Auto-generated method stub

    }

    @Override
    public void loadSpecial() {
        this.special = new Don();
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public Don getDon() {
        return don;
    }

    public void setDon(Don don) {
        this.don = don;
    }
}
