package src.modifiers;

/**
 * Class that represents the Weakness modifier.
 */
public class Weakness extends Modifier {

    private int sensitivity;

    // ============================================================================================[ Constructor ]>>>
    public Weakness() {}

    public Weakness(String name, int sensitivity) {
        super(name);
        this.sensitivity = sensitivity;
    }

    public String toString() {
        return String.format("%s (-%d)", this.getName(), this.sensitivity);
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public int getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(int sensitivity) {
        this.sensitivity = sensitivity;
    }
}
