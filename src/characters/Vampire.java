package src.characters;

//Import Statements
import src.abilities.*;
import src.equipment.*;
import src.minions.Minion;
import src.modifiers.Modifier;

public class Vampire extends Character {
    private int age;
    private int blood;
    private Discipline discipline;

    // ============================================================================================[ Constructor ]>>>
    public Vampire(String name, int health, int power, Modifier[] modifiers, Minion[] minions, Equipment[] equipment,
            int age, int blood, Discipline discipline) {
        super(name, health, power, modifiers, minions, equipment);
        this.age = age;
        this.blood = blood;
        this.discipline = discipline;
    }
    // ============================================================================================[ Private Methods ]>>>
    int calculateAttackPower(SpecialAbility discipline, Weapon weapons, Armor armor){
        int totalAttack;
        if (blood >= 5) {
            totalAttack = discipline.getAttack() + getPower() + 
                        weapons.getAttackModifier() + armor.getAttackModifier() + 2;
        }else{
            totalAttack = discipline.getAttack() + getPower() + 
                        weapons.getAttackModifier() + armor.getAttackModifier();
        }
        return totalAttack;
    }
    int calculateDefensePower(SpecialAbility discipline, Weapon weapons, Armor armor){
        int totalDefense;
        if (blood >= 5) {
            totalDefense = discipline.getAttack() + getPower() + 
                        weapons.getAttackModifier() + armor.getAttackModifier() + 2;
        }else{
            totalDefense = discipline.getAttack() + getPower() + 
                        weapons.getAttackModifier() + armor.getAttackModifier();
        }
        return totalDefense;
    }
    // Getters & Setters ==================================================================================================

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
