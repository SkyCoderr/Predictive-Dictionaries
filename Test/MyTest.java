package predictive;

import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * This class tests classes in package predictive. It makes use of the dictionary file "words". And except for class
 * PredictivePrototype, which is a static class that  have the path already specified, other classes need an argument
 * String path, in order to create an object of them.
 *
 * @author Haojie Chen
 * @version 2019-02-15
 */
public class MyTest {

    public String[] words4663 = {"gome", "gond", "gone", "good", "goof", "home", "hond", "hone", "hood", "hoof", "inne", "ione", "ioof"};
    public String[] trim4663 = {"gnof", "gome", "gond", "gone", "gonf", "good", "gooe", "goof", "home", "hond", "hone", "honf", "hood", "hooe", "hoof", "imme", "inme", "inne", "inod", "inoe", "inof", "ione", "ioof"};
    public Set<String> expectedSet = new HashSet<>(){{
        for (int i = 0; i < words4663.length; i++){
            add(words4663[i]); }
    }};
    public Set<String> trimExpectedSet = new HashSet<>(){{
        for (int i = 0; i < trim4663.length; i++){
            add(trim4663[i]); }
    }};

    @Test
    // This is the test for class PredictivePrototype
    public void test1(){
        // This tests the isValidWord method
        assertEquals(false, PredictivePrototype.isValidWord(""));
        assertEquals(false, PredictivePrototype.isValidWord("DFGH"));
        assertEquals(false, PredictivePrototype.isValidWord("gh$jk.i"));
        // This tests the wordToSignature method
        assertEquals("44892", PredictivePrototype.wordToSignature("gityc"));
        assertEquals("287437", PredictivePrototype.wordToSignature("BURGER"));
        assertEquals("287 437", PredictivePrototype.wordToSignature("Bur$ger"));
        // This tests the signatureToWords method
        assertEquals(expectedSet, PredictivePrototype.signatureToWords("4663"));
        assertEquals(new HashSet<String>(), PredictivePrototype.signatureToWords(""));
        try { PredictivePrototype.signatureToWords("4663abc"); }
        catch (IllegalArgumentException e){System.out.println("Invalid signature passed.");}
    }

    @Test
    // This is the test for class ListDictionary
    public void test2(){
        // This tests the signatureToWords method
        ListDictionary ld = new ListDictionary("words");
        assertEquals(expectedSet, ld.signatureToWords("4663"));
        assertEquals(new HashSet<String>(), ld.signatureToWords(""));
        try {ld.signatureToWords("4663abc"); }
        catch (IllegalArgumentException e){ System.out.println("Invalid signature passed.");}
    }

    @Test
    // This is the test for class WordSig
    public void test3(){
        WordSig wg1 = new WordSig("2744486377", "brightness");
        WordSig wg2 = new WordSig("32756377", "darkness");
        // This tests the getSignature method
        assertEquals("2744486377", wg1.getSignature());
        assertEquals("32756377", wg2.getSignature());
        // This tests the getWord method
        assertEquals("brightness", wg1.getWord());
        assertEquals("darkness", wg2.getWord());
        // This test the compareTo method
        assertEquals(-1, wg1.compareTo(wg2));
    }

    @Test
    // This is the test for class MapDictionary
    public void test4(){
        MapDictionary md = new MapDictionary("words");
        // This tests the add method
        Map<String, Set<String>> map1 = md.getMap();
        Map<String, Set<String>> map2 = md.getMap();
        Set<String> set = map1.get("22");
        set.add("test");
        map1.put("22", set);
        md.add(map2, "22", "test");
        assertEquals(map1.get("22"), md.getMap().get("22"));
        // This tests the signatureToWords method
        assertEquals(expectedSet, md.signatureToWords("4663"));
        assertEquals(new HashSet<String>(), md.signatureToWords(""));
        try {md.signatureToWords("4663abc"); }
        catch (IllegalArgumentException e){ System.out.println("Invalid signature passed.");}
    }

    @Test
    // This is the test for class TreeDictionary
    public void test5(){
        TreeDictionary td = new TreeDictionary("words");
        // This tests the trim method
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        set1.add("test");
        set2.add("tes");
        set1 = td.trim(set1, 3);
        assertEquals(set2, set1);
        // This tests the signatureToWords method
        assertEquals(trimExpectedSet, td.signatureToWords("4663"));
        assertEquals(new HashSet<>(), td.signatureToWords(""));
        try {td.signatureToWords("4663abc"); }
        catch (IllegalArgumentException e){ System.out.println("Invalid signature passed.");}
    }


}
