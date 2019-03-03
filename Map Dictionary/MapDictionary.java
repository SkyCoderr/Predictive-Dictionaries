package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class implements interface Dictionary. It makes use of Map structure to store the data.
 *
 * When this class is used to run on signature "444", the result is as follows:
 * 444 : gig hih iii ghi ihi
 * Time: 38209202 ns
 *
 * @author Haojie Chen
 * @version 2019-02-15
 */
public class MapDictionary implements Dictionary{

    private Map<String, Set<String>> map;

    /**
     * Constructor of the class, upon initiating an instance, the dictionary is read into the map,
     * with an argument - the path to the dictionary file, being passed to the constructor. And
     * HashMap is used here because compared to TreeMap, it works faster. It has a reference to
     * each key, and the key leads directly to the value, the complexity of which being constant.
     * @param path  type String, the path to the dictionary file
     */
    public MapDictionary(String path){
        this.map = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()){
                String tem = scanner.next().toLowerCase();
                if (PredictivePrototype.isValidWord(tem)){
                    add(map, PredictivePrototype.wordToSignature(tem), tem);
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace(); }
    }

    /**
     * This method is the getter for field variable map
     * @return  Type Map<String, Set<String></String>>  the field variable map
     */
    public Map<String, Set<String>> getMap(){
        return map;
    }

    /**
     * This method is used to add an element to a value in the map, which is a set.
     * @param map   Type Map, the map elements need to be stored in
     * @param key   Type String, the signature of the element
     * @param element   Type String, the word that needs to be stored in the set
     */
    public void add(Map<String, Set<String>> map, String key, String element){
        if (map.containsKey(key)){
            Set<String> set = map.get(key);
            set.add(element);
            map.put(key, set);
        }
        else {
            Set<String> set = new HashSet<>();
            set.add(element);
            map.put(key, set);
        }
    }

    /**
     * This method overrides itself in the dictionary interface, using the Map to look up the signature
     * @param signature type String, the signature to be converted
     * @return Set</String>, a set that contains all the words that have the same signature as the one passed
     */
    @Override
    public Set<String> signatureToWords(String signature) throws IllegalArgumentException{
        Set<String> set = new HashSet<>();
        for (int i = 0; i < signature.length(); i++){
            if (signature.charAt(i) < '2' || signature.charAt(i) > '9')
                throw new IllegalArgumentException();
        }
        if (map.containsKey(signature))
            return map.get(signature);
        else
            return set;

    }
}
