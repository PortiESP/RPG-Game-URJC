package src.characters;

//Import Statements
import src.abilities.*;

public class Hunter extends Character {
    private int willpower;
    private Talent talent;

    // CONSTANTS
    public final int MAX_WILLPOWER = 3;

    // ============================================================================================[ Constructor ]>>>
    public Hunter() {
        super();
        willpower = MAX_WILLPOWER;
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
        this.special = new Talent();
    }

    // ============================================================================================[ Getters & Setters ]>>>

    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }

    public Talent getTalent() {
        return talent;
    }

    public void setTalent(Talent talent) {
        this.talent = talent;
    }

}
