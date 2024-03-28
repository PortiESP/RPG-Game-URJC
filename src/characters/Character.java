package src.characters;

import src.abilities.SpecialAbility;
import src.equipment.*;
import src.minions.*;
// Import statements
import src.modifiers.*;
import src.users.Player;

/**
 * Abstract class that represents a character in the game. (Hunter, Lycanthrope, Vampire)
 */
public abstract class Character {

    private String name;
    private int health;
    private int power;
    private Modifier[] modifiers;
    private Minion[] minions;
    private int minionsHealth;
    protected Equipment[] equipment;
    protected SpecialAbility special;

    // ============================================================================================[ Constructor ]>>>
    public Character() {
        this.loadInitialValues();
        this.loadModifiers();
    }

    // ============================================================================================[ Abstract Methods ]>>>

    // Load initial values for the character
    public abstract void loadInitialValues();

    // Load the character's special ability
    public abstract void loadSpecial();

    // Load the character's minions
    public abstract void loadMinions();

    // Modify the character's attributes --> To be implemented in subclasses
    public static void modifyAttributes() {}

    // ============================================================================================[ Public Methods ]>>>

    /**
     * Receive a hit from another character.
     *
     * <p>The damage will be calculated based on the attacker's attack power and the character's defense power.<p>
     *
     * @param attacker Character that is attacking
     * @return Damage received
     */
    public int getHit(Character attacker) {
        // Get the attack power of the attacker and the defense power of the character
        int damage = attacker.getAttackPower();
        int defense = getDefensePower();

        // Calculate the final attack value
        int finalAttackValue = damage - defense;

        // If the damage about to be received is negativo or zero (aka: defense is higher than the damage), the character will receive no damage
        if (finalAttackValue <= 0) {
            return 0;
        }

        // Remove minions health before removing character's health
        if (minionsHealth > 0) {
            if (minionsHealth >= finalAttackValue) {
                minionsHealth -= finalAttackValue;
                finalAttackValue = 0;
            } else {
                finalAttackValue -= minionsHealth;
                minionsHealth = 0;
            }
        }

        // Calculate the remaining health of the character after receiving the damage
        int remainingHealth = health - finalAttackValue;

        // If the remaining health is negative, set the health to 0
        if (remainingHealth < 0) {
            health = 0;
        } else {
            health = remainingHealth;
        }

        // Return the damage received
        return finalAttackValue;
    }

    /**
     * Check if the character is dead.
     * @return True if the character is dead, false otherwise
     */
    public boolean isDead() {
        return health == 0;
    }

    /**
     * Assign equipment to the character.
     *
     * <p>The character will receive the weapons and the armor from the player.</p>
     *
     * @param player Player that is assigning the equipment
     */
    public void assignEquipment(Player player) {
        Equipment[] weapons = player.getWeapons();
        this.equipment[0] = weapons[0];
        this.equipment[1] = weapons[1];
        this.equipment[2] = player.getArmor();
    }

    public void assignModifiers(Player player) {
        this.modifiers = player.getModifiers();
    }

    public void assignSpecial(Player player) {
        this.special = player.getSpecialAbility();
    }

    // ============================================================================================[ Private methods ]>>>

    /**
     * Roll a dice to get a random number between 1 and 6.
     * @return Random number between 1 and 6
     */
    private int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    /**
     * Get the attack power of the character.
     *
     * <p>The attack power is calculated by rolling a dice for each point of attack power the character has. If the roll is 5 or 6, the final attack power is incremented by one.</p>
     * @return Final attack power
     */
    private int getAttackPower() {
        int success = 0;
        int attackPower = this.calcAttackPower();
        for (int i = 0; i < attackPower; i++) {
            int roll = this.rollDice();
            if (roll >= 5) {
                success++;
            }
        }

        return success;
    }

    /**
     * Get the defense power of the character.
     *
     * <p>The defense power is calculated by rolling a dice for each point of defense power the character has. If the roll is 5 or 6, the final defense power is incremented by one.</p>
     * @return Final defense power
     */
    private int getDefensePower() {
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

    /**
     * Load the modifiers for the character.
     */
    private void loadModifiers() {
        // TODO: Implement this method
        Modifier[] mods = { new Strength(), new Weakness() };
        this.modifiers = mods;
    }

    /**
     * Calculate the attack power provided by the equipment.
     * @return Attack power provided by the equipment
     */
    private int calcEquipmentAttack() {
        int cumPower = 0;
        for (Equipment e : this.equipment) {
            if (e == null) {
                continue;
            }
            cumPower += e.getAttack();
        }

        return cumPower;
    }

    /**
     * Calculate the defense power provided by the equipment.
     * @return Defense power provided by the equipment
     */
    private int calcEquipmentDefense() {
        int cumDefense = 0;
        for (Equipment e : this.equipment) {
            if (e == null) {
                continue;
            }
            cumDefense += e.getDefense();
        }

        return cumDefense;
    }

    /**
     * Calculate the attack power provided by the modifiers.
     * @return Attack power provided by the modifiers
     */
    private int calcModifiersAttack() {
        int sum = 0;
        for (Modifier m : this.modifiers) {
            if (m == null) {
                continue;
            }
            if (m instanceof Strength) {
                Strength s = (Strength) m;
                sum += s.getEffectiveness();
            } else if (m instanceof Weakness) {
                Weakness w = (Weakness) m;
                sum -= w.getSensitivity();
            }
        }
        return sum;
    }

    /**
     * Calculate the defense power provided by the minions. The defense power is equal to the combined health of all the minions.
     * @return Defense power provided by the minions
     */
    private int calcMinionsDefense() {
        return this.minionsHealth;
    }

    // ============================================================================================[ Protected Methods ]>>>
    /**
     * Calculate the base attack power of the character. The base attack power is the sum of the attack power provided by the equipment and the modifiers.
     * This <i>base power</i> is calculated the same way for all characters. Then the final attack power is calculated upon value calculated here.
     * @return Base attack power
     */
    protected int calcBaseAttackPower() {
        int cumAtt = 0;

        cumAtt += calcEquipmentAttack();
        cumAtt += calcModifiersAttack();
        cumAtt += this.special.getAttack();

        return cumAtt;
    }

    /**
     * Calculate the base defense power of the character. The base defense power is the sum of the defense power provided by the equipment and the minions.
     * This <i>base power</i> is calculated the same way for all characters. Then the final defense power is calculated upon value calculated here.
     * @return Base defense power
     */
    protected int calcBaseDefensePower() {
        int cumDef = 0;

        cumDef += calcEquipmentDefense();
        cumDef += calcMinionsDefense();
        cumDef += this.special.getDefense();

        return cumDef;
    }

    /**
     * Calculate the total attack power of the character. The total attack power is the combination of the base attack power and additional attack power of each type of character.
     * <p>This method is meant to be overridden by the subclasses to provide additional attack power above the base attack power.</p>
     * @return Total attack power
     */
    protected int calcAttackPower() {
        return calcBaseAttackPower();
    }

    /**
     * Calculate the total defense power of the character. The total defense power is the combination of the base defense power and additional defense power of each type of character.
     * <p>This method is meant to be overridden by the subclasses to provide additional defense power above the base defense power.</p>
     * @return Total defense power
     */
    protected int calcDefensePower() {
        return calcBaseDefensePower();
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

    public SpecialAbility getSpecial() {
        return special;
    }

    public void setSpecial(SpecialAbility special) {
        this.special = special;
    }

    public int getMinionsHealth() {
        return minionsHealth;
    }

    public void setMinionsHealth(int minionsHealth) {
        this.minionsHealth = minionsHealth;
    }
}
