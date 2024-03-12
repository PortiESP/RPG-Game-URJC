package src.modifiers;

public abstract class Modifier {
    private String name;

    // ============================================================================================[ Constructor ]>>>
    public Modifier(String name) {
        this.name = name;
    }

    // Getters & Setters ==================================================================================================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
