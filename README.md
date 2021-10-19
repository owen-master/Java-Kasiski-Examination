# Java-Kasiski-Examination
A Java project created in attempt to cryptoanalyse a polyalphabetic cypher

# Class Explanation:
## The Main class:
The **Main** class is responsible for several actions, these include:
1. Searching the cyphertext for possible quadgram occurrences (patterns in the cyphertext).
2. Saving the indexes of each of the quadgram occurrences.
3. Calculating the GCF (greatest common factor) of the unique distances.
4. Breaking the original Vigenere cypher into "K" many cryptograms.
5. Counting how many times characters appear in each of those "K" cryptograms.

## The VigenereDecoder class:
The **VigenereDecoder** class is responsible for decoding the polyalphabetic cypher using the keystream and cypthertext provided:
1. The class creates a two-dimensional array of characters that represent a tabula recta table.
2. A lookup is done on each of the cyphertext characters to find its decoded plaintext counterpart.
3. The result is outputted to the user.

# Example Output:
![image](https://user-images.githubusercontent.com/60369890/138002019-1763d0f4-5000-4fec-93f5-440d171ad179.png)
![image](https://user-images.githubusercontent.com/60369890/138002044-8b909ab3-9b66-42b6-a1e1-512fd8bd1bb8.png)
