package src.characters;

//Import Statements
import src.abilities.*;
import src.equipment.*;
import src.minions.Minion;
import src.modifiers.Modifier;

public class Lycanthrope extends Character {
    private int rage;
    private Don don;

    // ============================================================================================[ Constructor ]>>>
    public Lycanthrope(String name, int health, int power, Modifier[] modifiers, Minion[] minions,
            Equipment[] equipment, int rage, Don don) {
        super(name, health, power, modifiers, minions, equipment);
        this.rage = rage;
        this.don = don;
    }

    // ============================================================================================[ Private Methods ]>>>
    int calculateAttackPower(SpecialAbility don, Weapon weapons, Armor armor){
        int totalAttack = don.getAttack() + rage + getPower() + 
                        weapons.getAttackModifier() + armor.getAttackModifier() ;
        return totalAttack;
    }
    int calculateDefensePower(SpecialAbility don, Weapon weapons, Armor armor){
        int totalDefense = don.getDefense() + rage + getPower() + 
                        weapons.getDefenseModifier() + armor.getDefenseModifier() ;
        return totalDefense;
    }
    // Getters & Setters ==================================================================================================
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
