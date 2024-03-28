package src.characters;

import src.Game;
//Import Statements
import src.abilities.*;
import src.equipment.Equipment;
import src.minions.Minion;
import src.modifiers.Modifier;
import utils.MenuBuilder;

/**
 * Class that represents the Vampire character.
 */
public class Vampire extends Character {

    private int age;
    private int blood;
    private Discipline discipline;

    // CONSTANTS
    public static int MAX_BLOOD = 10;
    public static int MIN_BLOOD = 1;
    public static int MAX_HEALTH = 5;
    public static int MAX_POWER = 5;
    public static int MIN_POWER = 1;
    public static int INIT_MINIONS = 3;

    // ============================================================================================[ Constructor ]>>>
    public Vampire() {
        super();
        this.loadSpecial();
        this.loadMinions();
    }

    // ============================================================================================[ Public Methods ]>>>

    /**
     * Calculate the attack power of the vampire. This method overrides the parent's method to add a bonus if the vampire has more than 5 blood points.
     */
    @Override
    public int calcAttackPower() {
        if (blood >= 5) {
            return calcBaseAttackPower() + 2;
        } else {
            return calcBaseAttackPower();
        }
    }

    /**
     * Load the minions for the vampire. The vampire will can't have humans as minions.
     */
    @Override
    public void loadMinions() {
        int health = 0;
        Minion[] minions = new Minion[INIT_MINIONS];

        for (int i = 0; i < INIT_MINIONS; i++) {
            int index = i % 2;
            if (index == 0) {
                minions[i] = Game.ghoulsAvailable.get(i % Game.ghoulsAvailable.size());
            } else {
                minions[i] = Game.devilsAvailable.get(i % Game.devilsAvailable.size());
            }

            health += minions[i].getHealth();
        }

        this.setMinionsHealth(health);
        this.setMinions(minions);
    }

    /**
     * Load the special ability of the vampire, the discipline.
     */
    @Override
    public void loadSpecial() {
        this.special = new Discipline();
    }

    /**
     * Load the initial values of the vampire.
     */
    @Override
    public void loadInitialValues() {
        this.setHealth(MAX_HEALTH);
        this.setPower(MAX_POWER);
        this.setModifiers(new Modifier[2]);
        this.setMinions(new Minion[INIT_MINIONS]);
        this.setEquipment(new Equipment[3]);
        this.setAge(0);
        this.setBlood(MAX_BLOOD);
    }

    /**
     * Modify the attributes of the vampire. This method is used by the admin to alter the vampire's attributes.
     */
    public static void modifyAttributes() {
        // Print the menu until the user decides to exit
        while (true) {
            // Print the current values of the attributes
            showAttributes();

            // Prepare the menu options and ask the user to select an option
            String[] options = { "Alter Max Health", "Alter Max Power", "Alter Max Blood", "Alter Initial Minions", "Exit" };
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
        String[] attributes = { "Max Health", "Max Power", "Max Blood", "Initial Minions" };
        // Ask the user to enter a new value for the attribute selected
        String msg = "Enter the new value for the " + attributes[opt] + "(Positive Value)";
        int value = MenuBuilder.readInt(msg, 0, 1000);

        // Alter the attribute selected by the user with the new value entered
        switch (opt) {
            case 1 -> MAX_HEALTH = value;
            case 2 -> MAX_POWER = value;
            case 3 -> MAX_BLOOD = value;
            case 4 -> INIT_MINIONS = value;
        }
    }

    /**
     * Show the attributes of the vampire.
     */
    public static void showAttributes() {
        String[] attributes = {
                "Max Health: " + MAX_HEALTH,
                "Max Power: " + MAX_POWER,
                "Max Blood: " + MAX_BLOOD,
                "Initial Minions: " + INIT_MINIONS
        };

        MenuBuilder.doc("Vampire Attributes", attributes);
    }

    // ============================================================================================[ Getters & Setters ]>>>

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
