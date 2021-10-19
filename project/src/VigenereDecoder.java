/**
 * This class is designed to take a given
 * key stream and cyphertext and decode it
 * using the Vigenere decoding methods.
 *
 * @author  Owen Herbert
 */
public class VigenereDecoder {

    // the length of the alphabet
    private static final int ALPHABET_LENGTH = 26;

    private static final char[][] tabulaRecta = new char[ALPHABET_LENGTH][ALPHABET_LENGTH]; // create empty tabula recta

    private static final String KEY_STREAM = "ORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIG" +
            "INORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGI" +
            "NORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGIN" +
            "ORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINO" +
            "RIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINOR" +
            "IGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORI" +
            "GINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIG" +
            "INORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGI" +
            "NORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGIN" +
            "ORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINORIGINO" +
            "RIGINORIGINORIGINORIGINORIGINO";

    private static final String ORIGINAL_CYPHERTEXT = "omqisespiakgwfvoangvkuvqdiqimfsrtklowuiakgwfvzprpzljmekzbnbusyqmprgkjo\n" +
            "ljweagvqhymeuhgkxggnbruucahtwxzrggwtlvbxbubusjmiwaryqmprgkjoliwtsxmlol\n" +
            "kzqbbjixmccgcriewekxgchfoxicvplamgckpkqegzuvtrqfuvcgokquvnzimwcvfvukvg\n" +
            "gditgfqymsmfvrdkjrsexxwccjmjnbftwtlhqkqtofstcxmiwtsxmlolkzqbbjitmaucqy\n" +
            "pnitbowawjilqegkxxqpsfxkvowuiakgwfvzprpzlgubiebyiesbvueahfirtcoibomfoe\n" +
            "lhqqrvzyighvuvbgcfczjvrviipbhymxbusyqmprgkjolqsinuznuzdkvgwdmucgdvzowq\n" +
            "kzvyiardcybcopitizclvzmdirtzwgvveovaweohqqoemtoywjpgcphzwtqfhymswfhnmr\n" +
            "txbfetbldvwlihqkquvnbuqyevrvtecfsunuzesrtkagokmnwjsmmxqgzvayiqogbgjysk\n" +
            "wuvywemyqgirbowagtwsxnfvlzwfsrtklowuiakgwfvylhskwobffvtoiaqvwtzrocbour\n" +
            "diqimhduizmfseorqfvrvjdvqbzkgnitbowagrzkkbbjqjmesuiyifqvvjqauuckbbhymh\n" +
            "qqrvzyjvruqtohdkpkarhktkurbkxxqps\n";

    public static void main(String[] a) {

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create a tabula recta table using a two dimensional prim array of chars
        for (int lineIndex = 0; lineIndex < ALPHABET_LENGTH; lineIndex++) {

            for (int columnIndex = 0; columnIndex < alphabet.length(); columnIndex++) {
                tabulaRecta[lineIndex][columnIndex] = alphabet.charAt(columnIndex);
            }

            char beginningChar = alphabet.charAt(0);
            alphabet = alphabet.substring(1, ALPHABET_LENGTH) + String.valueOf(beginningChar);
        }

        // remove new line characters and make uppercase
        String processedCryptogram = Utils.removeNewLineCharacters(ORIGINAL_CYPHERTEXT).toUpperCase();
        String processedKeyStream = Utils.removeNewLineCharacters(KEY_STREAM).toUpperCase();

        // make sure cryptogram length and key stream length match
        if (processedCryptogram.length() != processedKeyStream.length()) {
            System.out.println("Lengths are not identical!");
            System.exit(1);
        }

        // begin decode process
        StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < processedCryptogram.length(); i++) {

            char cryptogramChar = processedCryptogram.charAt(i);
            char keyStreamChar = processedKeyStream.charAt(i);

            // perform lookup on tabula recta
            char decodedCharacter = performLookupOnTabulaRecta(cryptogramChar, keyStreamChar);
            decoded.append(decodedCharacter);
        }

        System.out.println("Plaintext: ");
        System.out.println(decoded);
    }

    /**
     * Performs a lookup on the tabula recta
     * table and returns the plaintext character.
     */
    private static char performLookupOnTabulaRecta(char cryptogramChar, char keyStreamChar) {

        // iterate over columns

        for (int columnIndex = 0; columnIndex < ALPHABET_LENGTH; columnIndex++) {
            if (tabulaRecta[0][columnIndex] == keyStreamChar) {
                for (int rowIndex = 0; rowIndex < ALPHABET_LENGTH; rowIndex++) {
                    if (tabulaRecta[rowIndex][columnIndex] == cryptogramChar) return tabulaRecta[0][rowIndex];
                }
            }
        }

        return ' ';
    }
}
