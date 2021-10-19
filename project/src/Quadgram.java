import java.util.ArrayList;

/**
 * This class represents a quadgram that occurs
 * in a Vigenere cypher.
 *
 * @author  Owen Herbert
 */
public class Quadgram {

    private final String quadgram;
    private ArrayList<Integer> indexOfEachOccurence = new ArrayList<>();
    private ArrayList<Integer> distances = new ArrayList<>();

    public Quadgram(String quadgram) {
        this.quadgram = quadgram;
    }

    public String getQuadgram() {
        return quadgram;
    }

    public void calculateDistances() {

        for (int i = 0; i < indexOfEachOccurence.size(); i++) {

            if (i + 1 < indexOfEachOccurence.size()) {
                int distance = indexOfEachOccurence.get(i+1) - indexOfEachOccurence.get(i);
                distances.add(distance);
            }
        }
    }

    public boolean occursMoreThanOnce() {
        return this.indexOfEachOccurence.size() > 1;
    }

    public ArrayList<Integer> getIndexOfEachOccurence() {
        return indexOfEachOccurence;
    }

    public void setIndexOfEachOccurence(ArrayList<Integer> indexOfEachOccurence) {
        this.indexOfEachOccurence = indexOfEachOccurence;
    }

    public ArrayList<Integer> getDistances() {
        return distances;
    }

    public void setDistances(ArrayList<Integer> distances) {
        this.distances = distances;
    }
}
