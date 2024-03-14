package src.characters;

//Import Statements
import src.abilities.*;
import src.equipment.*;
import src.minions.Minion;
import src.modifiers.Modifier;

public class Hunter extends Character {
    private int willpower;
    private Talent talent;

    // ============================================================================================[ Constructor ]>>>
    public Hunter(String name, int health, int power, Modifier[] modifiers, Minion[] minions, Equipment[] equipment,
            int willpower, Talent talent) {
        super(name, health, power, modifiers, minions, equipment);
        this.willpower = willpower;
        this.talent = talent;
    }
    
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
