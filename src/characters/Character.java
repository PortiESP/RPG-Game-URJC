package src.characters;

// Import statements
import src.modifiers.*;
import src.minions.*;
import src.equipment.*;

public abstract class Character {
    private String name;
    private int health;
    private int power;
    private Modifier[] modifiers;
    private Minion[] minions;
    private Equipment[] equipment;

    // Constructor ========================================================================================================
    public Character(String name, int health, int power, Modifier[] modifiers, Minion[] minions, Equipment[] equipment) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.modifiers = modifiers;
        this.minions = minions;
        this.equipment = equipment;
    }
    
    // Public Methods =====================================================================================================
    public void attack(Character Character){}
    public boolean hasActiveEquipment(){
        return true;
    }


    // Private Methods ====================================================================================================
    public int calculateAttackPower(){
        return -1;
    }

    // Getters & Setters ==================================================================================================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Modifier[] getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifier[] modifiers) {
        this.modifiers = modifiers;
    }

    public Minion[] getMinions() {
        return minions;
    }

    public void setMinions(Minion[] minions) {
        this.minions = minions;
    }

    public Equipment[] getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment[] equipment) {
        this.equipment = equipment;
    }
    
       
}
