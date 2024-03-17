package src.characters;

//Import Statements
import src.abilities.*;
<<<<<<< HEAD
import src.equipment.*;
import src.minions.Minion;
import src.modifiers.Modifier;
=======
>>>>>>> challenge

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

<<<<<<< HEAD
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
=======
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
>>>>>>> challenge
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
