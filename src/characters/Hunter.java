package src.characters;

import src.Game;
//Import Statements
import src.abilities.*;
import src.equipment.Equipment;
import src.minions.Minion;
import src.modifiers.Modifier;
import utils.MenuBuilder;

/**
 * Class that represents the Hunter character.
 */
public class Hunter extends Character {

    private int willpower;
    private Talent talent;

    // CONSTANTS
    public static int MAX_WILLPOWER = 3;
    public static int MAX_HEALTH = 5;
    public static int MAX_POWER = 5;
    public static int MIN_POWER = 1;
    public static int INIT_MINIONS = 3;

    // ============================================================================================[ Constructor ]>>>
    public Hunter() {
        super();
        this.loadSpecial();
        this.loadMinions();
    }

    // ============================================================================================[ Public Methods ]>>>

    /**
     * Load the minions for the hunter.
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
     * Load the special ability of the hunter, the talent.
     */
    @Override
    public void loadSpecial() {
        this.special = new Talent();
    }

    /**
     * Load the initial values of the hunter.
     */
    @Override
    public void loadInitialValues() {
        // Common attributes
        this.setHealth(MAX_HEALTH);
        this.setPower(MAX_POWER);
        this.setModifiers(new Modifier[2]);
        this.setMinions(new Minion[INIT_MINIONS]);
        this.setEquipment(new Equipment[3]);

        // Hunter specific attributes
        this.setWillpower(MAX_WILLPOWER);
    }

    /**
     * Modify the attributes of the hunter. This method is used by the admin to alter the hunter's attributes.
     */
    public static void modifyAttributes() {
        // Print the menu until the user decides to exit
        while (true) {
            // Print the current values of the attributes
            showAttributes();
            
            // Prepare the menu options and ask the user to select an option
            String[] options = { "Alter Max Health", "Alter Max Power", "Alter Max Willpower", "Alter Initial Minions", "Exit" };
            int opt = MenuBuilder.menu("Modify Hunter", options);

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
        String[] attributes = { "Max Health", "Max Power", "Max Willpower", "Initial Minions" };
        // Ask the user to enter a new value for the attribute selected
        String msg = "Enter the new value for the " + attributes[opt] + "(Positive Value)";
        int value = MenuBuilder.readInt(msg, 0, 1000);

        // Alter the attribute selected by the user with the new value entered
        switch (opt) {
            case 1 -> MAX_HEALTH = value;
            case 2 -> MAX_POWER = value;
            case 3 -> MAX_WILLPOWER = value;
            case 4 -> INIT_MINIONS = value;
        }
    }

    /**
     * Show the attributes of the hunter.
     */
    public static void showAttributes() {
        String[] attributes = {
                "Max Health: " + MAX_HEALTH,
                "Max Power: " + MAX_POWER,
                "Max Willpower: " + MAX_WILLPOWER,
                "Initial Minions: " + INIT_MINIONS
        };

        MenuBuilder.doc("Hunter Attributes", attributes);
    }

    // ============================================================================================[ Getters & Setters ]>>>
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
