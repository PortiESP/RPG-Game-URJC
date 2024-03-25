package src.characters;

/**
 * Enum that represents the possible character selections.
 *
 * <ul>
 * <li>Lycanthrope</li>
 * <li>Vampire</li>
 * <li>Hunter</li>
 * </ul>
 */
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

    /**
     * Get all character selections as an array of strings.
     *
     * @return Array of character names. Example <code>{"Lycanthrope", "Vampire", "Hunter"}</code>
     */
    public static String[] allToString() {
        CharacterSelection[] characters = CharacterSelection.values();
        String[] characterNames = new String[characters.length];

        for (int i = 0; i < characters.length; i++) {
            characterNames[i] = characters[i].toString();
        }

        return characterNames;
    }
}
