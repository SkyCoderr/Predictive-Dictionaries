package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This class is a static class that contains two static methods used in a dictionary.
 *
 * When this class is used to run on signature "444", the result is as follows:
 * 444 : gig hih iii ghi ihi
 * Time: 187908553882360 ns
 *
 * @author Haojie Chen
 * @version 2019-02-10
 */
public class PredictivePrototype {

    /**
     * This two-dimensional array stores the lowercase alphabets in the corresponding index of the
     * array according to their positions on the keyboard.
     */
    public static final char[][] numbers = new char[][]{{}, {'1'}, {'2', 'a', 'b', 'c'},
            {'3', 'd', 'e', 'f'}, {'4', 'g', 'h', 'i'}, {'5', 'j', 'k', 'l'}, {'6', 'm', 'n', 'o'},
            {'7', 'p', 'q', 'r', 's'}, {'8', 't', 'u', 'v'}, {'9', 'w', 'x', 'y', 'z'}};

    /**
     * This method is a helper method that checks if the word entered is an admissible word.
     * @param word  type String, the word to be checked
     * @return      type boolean
     */
    static boolean isValidWord(String word){
        if (word == "")
            return false;
        else {
            for (int i = 0; i < word.length(); i++){
                if (!Character.isLowerCase(word.charAt(i)))
                    return false;
            }
            return true;
        }
    }

    /**
     * This method converts a word to signature, using the two-dimensional array that stores alphabets in
     * the array with the corresponding number.
     * @param word type String, the word to be converted.
     * @return type String, the signature converted from a word
     */
    public static String wordToSignature(String word){
        // The reason why we are using StringBuffer here is that the value of StringBuffer can be changed
        // without having to create a new object. But a String would have to create a new object if its
        // value is to be modified.
        StringBuffer signature = new StringBuffer();
        String tem = word.toLowerCase();
        for (int i =0; i < tem.length(); i++){
            if (!Character.isLowerCase(tem.charAt(i)))
                signature.append(" ");
            else {
                here:
                for (int j = 1; j < 10; j++){
                    for (int k = 1; k < numbers[j].length; k++){
                        if (tem.charAt(i) == numbers[j][k]) {
                            signature.append(numbers[j][0]);
                            break here;
                        }
                    }
                }
            }
        }
        return signature.toString();
    }

    /**
     * This method converts a signature to a word by going through the whole dictionary and check if the
     * signature for each word is the same as the signature passed. If so, then the word would be added to
     * the set.
     * @param signature type String, the signature to be converted to words
     * @return Set</String> containing all the words of type String that have the same signature
     */
    // In this method, the dictionary is not stored in the Java program. Because if it is, then the dictionary
    // would be stored in this method. Therefore, every time we pass a signature to this program, the dictionary
    // reloads again, which could lead to huge waste of resource.
    public static Set<String> signatureToWords(String signature){
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < signature.length(); i++){
            if (signature.charAt(i) < '2' || signature.charAt(i) > '9')
                throw new IllegalArgumentException();
        }
        try { Scanner scanner = new Scanner(new File("words"));
            while (scanner.hasNext()){
                String sb = scanner.nextLine().toLowerCase();
                if (isValidWord(sb)){
                    String tem = sb.toLowerCase();
                    if (wordToSignature(tem).equals(signature))
                        set.add(tem);
                }
            }
            scanner.close();
            return set;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return new HashSet<>(); }
        catch (IllegalArgumentException f){
            f.printStackTrace();
            return new HashSet<>(); }
    }
}
