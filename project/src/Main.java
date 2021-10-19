import java.util.ArrayList;

/**
 * This class is designed to find several cyphertexts from
 * a given Vigenere Cypher. It uses the Kasiski examination
 * method.
 *
 * @author  Owen Herbert
 */
public class Main {

    // the original cyphertext to be cryptoanalysed
    private static String ORIGINAL_CYPHERTEXT = "omqisespiakgwfvoangvkuvqdiqimfsrtklowuiakgwfvzprpzljmekzbnbusyqmprgkjo\n" +
            "ljweagvqhymeuhgkxggnbruucahtwxzrggwtlvbxbubusjmiwaryqmprgkjoliwtsxmlol\n" +
            "kzqbbjixmccgcriewekxgchfoxicvplamgckpkqegzuvtrqfuvcgokquvnzimwcvfvukvg\n" +
            "gditgfqymsmfvrdkjrsexxwccjmjnbftwtlhqkqtofstcxmiwtsxmlolkzqbbjitmaucqy\n" +
            "pnitbowawjilqegkxxqpsfxkvowuiakgwfvzprpzlgubiebyiesbvueahfirtcoibomfoe\n" +
            "lhqqrvzyighvuvbgcfczjvrviipbhymxbusyqmprgkjolqsinuznuzdkvgwdmucgdvzowq\n" +
            "kzvyiardcybcopitizclvzmdirtzwgvveovaweohqqoemtoywjpgcphzwtqfhymswfhnmr\n" +
            "txbfetbldvwlihqkquvnbuqyevrvtecfsunuzesrtkagokmnwjsmmxqgzvayiqogbgjysk\n" +
            "wuvywemyqgirbowagtwsxnfvlzwfsrtklowuiakgwfvylhskwobffvtoiaqvwtzrocbour\n" +
            "diqimhduizmfseorqfvrvjdvqbzkgnitbowagrzkkbbjqjmesuiyifqvvjqauuckbbhymh\n" +
            "qqrvzyjvruqtohdkpkarhktkurbkxxqps";

    // the alphabet...
    private static final char[] ALPHABET = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w',
            'x','y','z'};

    public static void main(String[] args) {

        // remove new line characters from the cyphertext
        String newCyphertext = Utils.removeNewLineCharacters(ORIGINAL_CYPHERTEXT);

        // create a new array list that will hold Quadgrams that occur more than once
        ArrayList<Quadgram> quadgrams = new ArrayList<>();

        // search for all possible quadgrams - not the most efficient solution but it works...
        for (char a : ALPHABET) {
            for (char b : ALPHABET) {
                for (char c : ALPHABET) {
                    for (char d : ALPHABET) {

                        // create quadgram string
                        String quadgramString = String.valueOf(a) + String.valueOf(b) + String.valueOf(c) + String.valueOf(d);

                        Quadgram quadgram = new Quadgram(quadgramString);
                        quadgram.setIndexOfEachOccurence(findPositionsOfOccurences(newCyphertext, quadgramString));
                        quadgram.calculateDistances();

                        // add quadgram to hashmap if it occurs more than once
                        if (quadgram.occursMoreThanOnce()) quadgrams.add(quadgram);
                    }
                }
            }
        }

        // print the table header
        System.out.format("%27s%27s%27s%n","QUADGRAM", "INDEX OF EACH OCCURENCE", "DISTANCES");
        ArrayList<Integer> allDistances = new ArrayList<>();

        // print out each occurrence in table format
        for (Quadgram quadgram : quadgrams) {
            for (int i : quadgram.getDistances()) {

                boolean found = false;
                for (int j : allDistances) {

                    if (i == j) {
                        found = true;
                        break;
                    }
                }

                if (!found) allDistances.add(i); // add if not already in arraylist
            }

            System.out.format("%27s%27s%27s%n", quadgram.getQuadgram(), quadgram.getIndexOfEachOccurence().toString(),
                    quadgram.getDistances().toString());
        }

        int gcf = GCF.calculateGcf(allDistances);

        System.out.println("Cyphertext Length: " + newCyphertext.length());
        System.out.println("Unique Distances:" + allDistances);
        System.out.println("Greatest Common Factor of Distances: " + gcf);

        if (gcf == 1) {
            System.out.println("GCF is 1. Cannot break up cryptograms!");
            System.exit(1);
        }

        // split the cryptograms into the GCF number
        ArrayList<String> subCryptograms = new ArrayList<>(gcf);
        for (int i = 1; i < gcf + 1; i++) {

            int iteration = 0;
            String subCryptogram = "";

            while (true) {

                int characterIndex = (i + (iteration * gcf)) - 1;

                try {

                    subCryptogram = subCryptogram + newCyphertext.charAt(characterIndex);

                } catch (IndexOutOfBoundsException exception) {
                    break;
                }

                iteration++;
            }

            subCryptograms.add(subCryptogram);
        }

        // print out all of the sub cryptograms along with their letter counts
        System.out.println("--CRYPTOGRAMS--");
        for (int i = 0; i < subCryptograms.size(); i++) {
            int subcryptogramNumber = i + 1;

            String cryptogram = subCryptograms.get(i);

            System.out.println("CRYPTOGRAM #" + subcryptogramNumber + ": " + cryptogram);
            System.out.println("CHAR COUNT: " + Utils.countCharacters(cryptogram));
            System.out.println();
        }
    }

    /**
     * Finds the positions of quadgram occurrences in the cyphertext.
     * Returns the results as an array list.
     */
    public static ArrayList<Integer> findPositionsOfOccurences(String str, String substring) {
        final boolean ignoreCase = true;
        int substringLength = substring.length();
        int stringLength = str.length();

        ArrayList<Integer> occurrenceArray = new ArrayList<Integer>();

        for(int i = 0; i < stringLength - substringLength + 1; i++) {
            if(str.regionMatches(ignoreCase, i, substring, 0, substringLength))  {
                occurrenceArray.add(i);
            }
        }

        return occurrenceArray;
    }
}
