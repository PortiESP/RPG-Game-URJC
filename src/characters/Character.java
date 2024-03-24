package src.characters;

import src.abilities.SpecialAbility;
import src.equipment.*;
import src.minions.*;
// Import statements
import src.modifiers.*;
import src.users.Player;

public abstract class Character {

    private String name;
    private int health;
    private int power;
    private Modifier[] modifiers;
    private Minion[] minions;
    protected Equipment[] equipment;
    protected SpecialAbility special;

    // CONSTANTS
    public final int MAX_HEALTH = 5;
    public final int MAX_POWER = 5;
    public final int MIN_POWER = 1;
    public final int INIT_MINIONS = 3;

    // ============================================================================================[ Constructor ]>>>
    public Character() {
        health = MAX_HEALTH;
        power = MAX_POWER;
        modifiers = new Modifier[2];
        minions = new Minion[INIT_MINIONS];
        equipment = new Equipment[3];
        loadModifiers();
    }

    // ============================================================================================[ Abstract Methods ]>>>

    // Load the character's special ability
    public abstract void loadSpecial();

    // Load the character's minions
    public abstract void loadMinions();

    // ============================================================================================[ Public Methods ]>>>
    public int getAttackPower() {
        int success = 0;
        int attackPower = calcAttackPower();
        for (int i = 0; i < attackPower; i++) {
            int roll = rollDice();
            if (roll >= 5) {
                success++;
            }
        }

        return success;
    }

    public int getDefensePower() {
        int success = 0;
        int defensePower = calcDefensePower();
        for (int i = 0; i < defensePower; i++) {
            int roll = rollDice();
            if (roll >= 5) {
                success++;
            }
        }

        return success;
    }

    public int getHit(Character target) {
        int damage = target.getAttackPower();
        int defense = getDefensePower();
        if (defense > damage) {
            defense = damage;
        }
        int remainingHealth = health - (damage - defense);

        if (remainingHealth < 0) {
            health = 0;
        } else {
            health = remainingHealth;
        }

        return (damage - defense);
    }

    // Get a random number between 1 and 6
    public int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    public boolean isDead() {
        return health == 0;
    }

    // Load the character's modifiers
    public void loadModifiers() {
        Modifier[] mods = { new Strength(), new Weakness() };
        this.modifiers = mods;
    }

    public boolean hasActiveEquipment() {
        return true;
    }

    public int calcEquipmentAttack() {
        int cumPower = 0;
        for (Equipment e : this.equipment) {
            if (e == null) {
                continue;
            }
            cumPower += e.getAttack();
        }

        return cumPower;
    }

    public int calcEquipmentDefense() {
        int cumDefense = 0;
        for (Equipment e : this.equipment) {
            if (e == null) {
                continue;
            }
            cumDefense += e.getDefense();
        }

        return cumDefense;
    }

    public int calcModifiersAttack() {
        Strength s = (Strength) this.modifiers[0];
        return s.getEffectiveness();
    }

    public int calcMinionsDefense() {
        int cumHealth = 0;
        for (Minion m : this.minions) {
            if (m == null) {
                continue;
            }
            cumHealth += m.getHealth();
        }

        return cumHealth;
    }

    public int calcBaseAttackPower() {
        int cumAtt = 0;

        cumAtt += calcEquipmentAttack();
        cumAtt += calcModifiersAttack();
        cumAtt += this.special.getAttack();

        return cumAtt;
    }

    public int calcBaseDefensePower() {
        int cumDef = 0;

        cumDef += calcEquipmentDefense();
        cumDef += calcMinionsDefense();
        cumDef += this.special.getDefense();

        return cumDef;
    }

    // Calculate total attack power of the character
    public int calcAttackPower() {
        return calcBaseAttackPower();
    }

    // Calculate total defense power of the character
    public int calcDefensePower() {
        return calcBaseDefensePower();
    }

    public void assignEquipment(Player player) {
        Equipment[] weapons = player.getWeapons();
        this.equipment[0] = weapons[0];
        this.equipment[1] = weapons[1];
        this.equipment[2] = player.getArmor();
    }

    // ============================================================================================[ Getters & Setters ]>>>
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
