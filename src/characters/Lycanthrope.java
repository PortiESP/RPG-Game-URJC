package src.characters;

import src.Game;
//Import Statements
import src.abilities.*;
import src.equipment.Equipment;
import src.minions.Minion;
import src.modifiers.Modifier;
import utils.MenuBuilder;

/**
 * Class that represents the Lycanthrope character.
 */
public class Lycanthrope extends Character {

    private int rage;
    private Don don;

    // CONSTANTS
    public static int MAX_RAGE = 3;
    public static int MAX_HEALTH = 5;
    public static int MAX_POWER = 5;
    public static int MIN_POWER = 1;
    public static int INIT_MINIONS = 3;

    // ============================================================================================[ Constructor ]>>>
    public Lycanthrope() {
        super();
        rage = 0;
        this.loadSpecial();
        this.loadMinions();
    }

    // ============================================================================================[ Public Methods ]>>>

    /**
     * Load the minions for the lycanthrope.
     */
    @Override
    public void loadMinions() {
        // Accumulated health of the minions
        int health = 0;
        // Array of minions
        Minion[] minions = new Minion[INIT_MINIONS];

        // Create a different minion for each slot defined by INIT_MINIONS
        for (int i = 0; i < INIT_MINIONS; i++) {
            int index = i % 3;
            if (index == 0) { // Ghouls
                minions[i] = Game.ghoulsAvailable.get(i % Game.ghoulsAvailable.size());
            } else if (index == 1) { // Humans
                minions[i] = Game.humansAvailable.get(i % Game.humansAvailable.size());
            } else { // Devils
                minions[i] = Game.devilsAvailable.get(i % Game.devilsAvailable.size());
            }

            // Accumulate the health of the minions
            health += minions[i].getHealth();
        }

        // Set the health of the minions and the minions array
        this.setMinionsHealth(health);
        this.setMinions(minions);
    }

    /**
     * Load the special ability of the lycanthrope, the don.
     */
    @Override
    public void loadSpecial() {
        this.special = new Don();
    }

    /**
     * Load the initial values of the lycanthrope.
     */
    @Override
    public void loadInitialValues() {
        // Common attributes
        this.setHealth(MAX_HEALTH);
        this.setPower(MAX_POWER);
        this.setModifiers(new Modifier[2]);
        this.setMinions(new Minion[INIT_MINIONS]);
        this.setEquipment(new Equipment[3]);
        this.setRage(MAX_RAGE);
    }

    /**
     * Modify the attributes of the lycanthrope. This method is used by the admin to alter the lycanthrope's attributes.
     */
    public static void modifyAttributes() {
        // Print the menu until the user decides to exit
        while (true) {
            // Print the current values of the attributes
            showAttributes();

            // Prepare the menu options and ask the user to select an option
            String[] options = { "Alter Max Health", "Alter Max Power", "Alter Max Rage", "Alter Initial Minions", "Exit" };
            int opt = MenuBuilder.menu("Modify Lycanthrope", options);

            // Alter the attribute selected by the user, if the user decides to exit, break the loop
            if (opt < options.length) {
                alterAttr(opt);
            } else {
                break;
            }
        }
    }

    /**
     * Prompt the user to enter a new value for the attribute selected.
     * @param opt Option selected by the user.
     */
    public static void alterAttr(int opt) {
        // String array with the attributes to be altered (not for the menu, but for the message to the user)
        String[] attributes = { "Max Health", "Max Power", "Max Rage", "Initial Minions" };
        // Ask the user to enter a new value for the attribute selected
        String msg = "Enter the new value for the " + attributes[opt] + "(Positive Value)";
        int value = MenuBuilder.readInt(msg, 0, 1000);

        // Alter the attribute selected by the user with the new value entered
        switch (opt) {
            case 1 -> MAX_HEALTH = value;
            case 2 -> MAX_POWER = value;
            case 3 -> MAX_RAGE = value;
            case 4 -> INIT_MINIONS = value;
        }
    }

    /**
     * Show the attributes of the lycanthrope.
     */
    public static void showAttributes() {
        String[] attributes = { 
            "Max Health: " + MAX_HEALTH, 
            "Max Power: " + MAX_POWER, 
            "Max Rage: " + MAX_RAGE, 
            "Initial Minions: " + INIT_MINIONS 
        };

        MenuBuilder.doc("Lycanthrope Attributes", attributes);
    }

    // ============================================================================================[ Getters & Setters ]>>>
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
