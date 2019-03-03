package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class is a specific class that implements interface Dictionary. It makes use of Array List structure
 * to store the data.
 *
 * When this class is used to run on signature "444", the result is as follows:
 * 444 : gig hih iii ghi ihi
 * Time: 50177701 ns
 *
 * @author Haojie Chen
 * @version 2019-02-11
 */
public class ListDictionary implements Dictionary {

    private ArrayList<WordSig> pairs;

    /**
     * Constructor of the class, upon initiating an instance, the dictionary is read into the array list,
     * with an argument - the path to the dictionary file, being passed to the constructor
     * @param path  type String, the path to the dictionary file
     */
    public ListDictionary(String path){
        this.pairs = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()){
                String str = scanner.nextLine().toLowerCase();
                if (PredictivePrototype.isValidWord(str)) {
                    WordSig newElement = new WordSig(PredictivePrototype.wordToSignature(str), str);
                    pairs.add(newElement);
                }
            }
            scanner.close();
            Collections.sort(pairs);
        }
        catch (FileNotFoundException e){
            e.printStackTrace(); }
    }

    /**
     * This method overrides itself in the dictionary interface, using the array list to look up the signature
     * @param signature type String, the signature to be converted
     * @return  Set</String>, a set that contains all the words that have the same signature as the one passed
     */
    @Override
    public Set<String> signatureToWords(String signature) throws IllegalArgumentException{
        Set<String> set = new HashSet<>();
        for (int i = 0; i < signature.length(); i++){
            if (signature.charAt(i) < '2' || signature.charAt(i) > '9')
                throw new IllegalArgumentException();
        }
        int t = Collections.binarySearch(pairs, new WordSig(signature));
        int t1 = t;
        if (t < 0)
            return new HashSet<>();
        else {
            set.add(pairs.get(t).getWord());
            for (t = t + 1; pairs.get(t).getSignature().equals(signature) && t < pairs.size(); t++){
                set.add(pairs.get(t).getWord());
            }
            for(t1 = t1 -1; pairs.get(t1).getSignature().equals(signature) && t1 >= 0; t1--){
                set.add(pairs.get(t1).getWord());
            }
        }
        return set;
    }
}
