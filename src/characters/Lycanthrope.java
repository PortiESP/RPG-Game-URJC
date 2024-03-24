package src.characters;

//Import Statements
import src.abilities.*;
import src.equipment.Equipment;
import src.minions.Minion;
import src.modifiers.Modifier;

public class Lycanthrope extends Character {

    private int rage;
    private Don don;

    // CONSTANTS
    public static int MAX_RAGE = 3;
    public static int MAX_HEALTH = 5;
    public static int MAX_POWER = 5;
    public static int MIN_POWER = 1;
    public static int INIT_MINIONS = 3;

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

    @Override
    public void loadInitialValues() {
        this.setHealth(MAX_HEALTH);
        this.setPower(MAX_POWER);
        this.setModifiers(new Modifier[2]);
        this.setMinions(new Minion[INIT_MINIONS]);
        this.setEquipment(new Equipment[3]);
        this.setRage(MAX_RAGE);
    }

    // Method to modify the attributes of the lycanthrope
    public static void modifyAttributes() {
        // TODO Auto-generated method stub

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
