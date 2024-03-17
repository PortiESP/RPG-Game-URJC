package src.characters;

//Import Statements
import src.abilities.*;
<<<<<<< HEAD
import src.equipment.*;
import src.minions.Minion;
import src.modifiers.Modifier;
=======
>>>>>>> challenge

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
<<<<<<< HEAD
    
    // ============================================================================================[ Private Methods ]>>>

    int calculateAttackPower(SpecialAbility talent, Weapon weapons, Armor armor){
        int totalPower = talent.getAttack() + willpower + getPower() + 
                        weapons.getAttackModifier() + armor.getAttackModifier();
        return totalPower;
    }

    int calculateDefensePower(SpecialAbility talent, Weapon weapons, Armor armor){
        int totalDefense = talent.getDefense() + willpower + getPower() + 
                        weapons.getDefenseModifier() + armor.getDefenseModifier();
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
        this.special = new Talent();
    }

    // ============================================================================================[ Getters & Setters ]>>>
>>>>>>> challenge

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
