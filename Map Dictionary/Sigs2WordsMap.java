package predictive;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Haojie Chen
 * @version 2019-02-15
 */
public class Sigs2WordsMap {

    public static void main(String[] args){
        Dictionary dictionary = new MapDictionary("words");
        long startTime = System.nanoTime();
        for (int i = 0; i < args.length; i++){
            String t1 = args[i] + " : ";
            String t2 = "";
            Set<String> set = dictionary.signatureToWords(args[i]);
            Iterator<String> it = set.iterator();
            while (it.hasNext()){
                t2 = t2 + it.next() + " ";
            }
            System.out.println(t1 + t2);
        }
        System.out.println("Time: " + (System.nanoTime() - startTime));
    }

}
