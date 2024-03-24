package src.characters;

//Import Statements
import src.abilities.*;
import src.equipment.Equipment;
import src.minions.Minion;
import src.modifiers.Modifier;

public class Vampire extends Character {

    private int age;
    private int blood;
    private Discipline discipline;

    // CONSTANTS
    public final int MAX_BLOOD = 10;
    public final int MIN_BLOOD = 1;
    public static int MAX_HEALTH = 5;
    public static int MAX_POWER = 5;
    public static int MIN_POWER = 1;
    public static int INIT_MINIONS = 3;

    // ============================================================================================[ Constructor ]>>>
    public Vampire() {
        super();
        this.loadSpecial();
        this.loadMinions();
    }

    // ============================================================================================[ Public Methods ]>>>

    @Override
    public int calcAttackPower() {
        if (blood >= 5) {
            return calcBaseAttackPower() + 2;
        } else {
            return calcBaseAttackPower();
        }
    }

    @Override
    public void loadMinions() {
        // TODO Auto-generated method stub

    }

    @Override
    public void loadSpecial() {
        this.special = new Discipline();
    }

    @Override
    public void loadInitialValues() {
        this.setHealth(MAX_HEALTH);
        this.setPower(MAX_POWER);
        this.setModifiers(new Modifier[2]);
        this.setMinions(new Minion[INIT_MINIONS]);
        this.setEquipment(new Equipment[3]);
        this.setAge(0);
        this.setBlood(MAX_BLOOD);
    }

    // Method to modify the attributes of the vampire
    public static void modifyAttributes() {
        // TODO Auto-generated method stub

    }

    // ============================================================================================[ Getters & Setters ]>>>

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
