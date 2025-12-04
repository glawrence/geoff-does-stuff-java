package com.geoffdoesstuff.java.utility;

import java.util.List;
import java.util.UUID;

/**
 * Helper class to deal with Collections.
 */
public class CollectionUtilities {

    /**
     * This is here to suppress JavaDoc complaining about not commenting the default constructor
     */
    private CollectionUtilities() {
    }

    /**
     * Test if the list of strings is immutable or not and return true if it is immutable, or in other words
     * return true if the list <strong>cannot</strong> be added to or updated.
     *
     * @param testList list of String to test
     * @return true if immutable
     */
    public static boolean isListImmutable(List<String> testList) {
        boolean result = false;
        try {
            String testElement  = UUID.randomUUID().toString();
            testList.add(testElement);
            testList.remove(testElement);
        } catch (RuntimeException  e) {
            result = true;
        }
        return result;
    }

    /**
     * Test if the list of strings is mutable or not and return true if it is mutable, or in other words
     * return true if the list <strong>can</strong> be added to or updated.
     *
     * @param testList list of String to test
     * @return true if mutable
     */
    public static boolean isListMutable(List<String> testList) {
        return !isListImmutable(testList);
    }
}
