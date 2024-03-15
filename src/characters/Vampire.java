package src.characters;

//Import Statements
import src.abilities.*;

public class Vampire extends Character {
    private int age;
    private int blood;
    private Discipline discipline;

    // CONSTANTS
    public final int MAX_BLOOD = 10;
    public final int MIN_BLOOD = 1;

    // ============================================================================================[ Constructor ]>>>
    public Vampire() {
        super();
        age = 0;
        blood = MAX_BLOOD;
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
