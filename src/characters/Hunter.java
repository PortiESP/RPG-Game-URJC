package src.characters;

//Import Statements
import src.abilities.*;
import src.equipment.Equipment;
import src.equipment.Weapon;
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

    int calculateAttackPower(SpecialAbility talent, Weapon weapons){
        int totalPower = talent.getAttack() + willpower + getPower() + weapons.getAttackModifier();
        return totalPower;
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
