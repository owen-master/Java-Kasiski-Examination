import java.util.HashMap;

/**
 * This class is provides utility functions for the
 * project.
 *
 * @author  Owen Herbert
 */
public class Utils {

    /**
     * Removes new line characters from a string.
     */
    public static String removeNewLineCharacters(String originalString) {

        String newString = "";

        for (int i = 0; i < originalString.length(); i++) {

            char cyphertextCharacter = originalString.charAt(i); // get the character at the index position

            if (Character.isAlphabetic(cyphertextCharacter)) {
                newString = newString + cyphertextCharacter;
            }
        }

        return newString;
    }

    /**
     * Counts the characters that occur in the given string
     * and returns a HashMap of the character counts.
     */
    public static HashMap<Character, Integer> countCharacters(String string) {
        HashMap<Character, Integer> characterHashmap = new HashMap<>();

        for (int i = 0; i < string.length(); i++) {

            char c = string.charAt(i);

            if (characterHashmap.containsKey(c)) {
                characterHashmap.put(c, characterHashmap.get(c) + 1);
            } else {
                characterHashmap.put(c, 1);
            }
        }

        return characterHashmap;
    }
}
