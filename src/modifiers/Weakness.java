package src.modifiers;

public class Weakness extends Modifier {
    private int sensitivity;

    // ============================================================================================[ Constructor ]>>>
    public Weakness() {
    }

    public Weakness(String name, int sensitivity) {
        super(name);
        this.sensitivity = sensitivity;
    }

    // ============================================================================================[ Getters & Setters ]>>>
    public int getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(int sensitivity) {
        this.sensitivity = sensitivity;
    }

}
