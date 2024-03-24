package src.characters;

public enum CharacterSelection {
    LYCANTHROPE,
    VAMPIRE,
    HUNTER;

    // ============================================================================================[ Public Methods ]>>>
    public String toString() {
        switch (this) {
            case LYCANTHROPE:
                return "Lycanthrope";
            case VAMPIRE:
                return "Vampire";
            case HUNTER:
                return "Hunter";
            default:
                return "Unknown";
        }
    }

    public static String[] allToString() {
        CharacterSelection[] characters = CharacterSelection.values();
        String[] characterNames = new String[characters.length];

        for (int i = 0; i < characters.length; i++) {
            characterNames[i] = characters[i].toString();
        }

        return characterNames;
    }
}
