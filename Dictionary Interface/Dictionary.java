package predictive;

import java.util.Set;

/**
 * This is a Dictionary interface implemented by all other dictionaries.
 *
 * @author Haojie Chen
 * @version 2019-02-11
 */
public interface Dictionary {

    public Set<String> signatureToWords(String signature);

}
