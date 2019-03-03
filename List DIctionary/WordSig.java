package predictive;

/**
 * This class is a class that pairs two elements - word and signature. Besides, it specifies how objects
 * of this class can be compared by implementing the interface Comparable<WordSig></WordSig> and overriding
 * the method compareTo()
 *
 * @author Haojie Chen
 * @version 2019-02-10
 */
public class WordSig implements Comparable<WordSig>{

    private String word;
    private String signature;

    /**
     * First constructor of this class, with two arguments - signature and word
     * @param signature type String
     * @param word      type String
     */
    public WordSig(String signature, String word){
        this.signature = signature;
        this.word = word;
    }

    /**
     * Second constructor of this class, with only one arugument - signature
     * @param signature type String
     */
    public WordSig(String signature){
        this.signature = signature;
        this.word = null;
    }

    /**
     * Getter for field variable word
     * @return  type String, word of the object
     */
    public String getWord(){
        return word;
    }

    /**
     * Getter for field variable signature
     * @return  type String, signature of the object
     */
    public String getSignature(){
        return signature;
    }

    /**
     * This compareTo() method compares the signature of each WordSig object.
     * @param ws    Object WordSig, the object to be compared with
     * @return      -1 if this object is smaller than the one compared to, 1 if greater than it, and 0 if they
     *              are equal.
     */
    @Override
    public int compareTo(WordSig ws){
        if (this.signature.compareTo(ws.getSignature()) < 0)
            return -1;
        else if (this.signature.compareTo(ws.getSignature()) > 0)
            return 1;
        else {
            return 0;
        }
    }
}
