package tests.characters;

import org.junit.Test;

import src.characters.CharacterSelection;

public class Test_CharacterSelection {

    @Test
    public void testToString() {
        CharacterSelection characterSelection;

        // Test Vampire
        characterSelection = CharacterSelection.VAMPIRE;
        assert(characterSelection.toString().equals("Vampire"));

        // Test Hunter
        characterSelection = CharacterSelection.HUNTER;
        assert(characterSelection.toString().equals("Hunter"));

        // Test lycanthrope
        characterSelection = CharacterSelection.LYCANTHROPE;
        assert(characterSelection.toString().equals("Lycanthrope"));
    }

    @Test
    public void allToString() {
        CharacterSelection[] characterSelections = CharacterSelection.values();
        String[] expected = {"Lycanthrope", "Vampire", "Hunter"};
        String[] output = CharacterSelection.allToString();

        for (CharacterSelection characterSelection : characterSelections) {
            assert(output[characterSelection.ordinal()].equals(expected[characterSelection.ordinal()]));
        }
    }
}