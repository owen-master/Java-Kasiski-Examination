import java.util.ArrayList;

/**
 * This class provides math functions to the
 * project.
 *
 * @author  Owen Herbert
 */
public class GCF {

    /**
     * Returns the GCF greatest common factor of
     * numOne and numTwo.
     */
    static int gcf(int numOne, int numTwo)
    {
        if (numOne == 0) return numTwo;

        return gcf(numTwo % numOne, numOne);
    }

    /**
     * Calculates the GCF greatest common factor from an
     * array list of integers.
     */
    static int calculateGcf(ArrayList<Integer> arrayOfIntegers)
    {
        int result = 0;
        for (int element: arrayOfIntegers){
            result = gcf(result, element);

            if (result == 1) return 1;
        }

        return result;
    }
}