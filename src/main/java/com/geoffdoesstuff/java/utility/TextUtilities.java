package com.geoffdoesstuff.java.utility;

import java.util.Objects;

/**
 * Basic utility class providing some handy methods for working with text.
 */
public class TextUtilities {

    /**
     * Utility method to help test for something that is either null, empty. A string with whitespace in is
     * not empty.
     * @param text input String object
     * @return boolean
     */
    public static boolean isNullOrEmpty(String text) {
        return ( Objects.isNull(text) || text.isEmpty() );
    }

    /**
     * Utility method to help test for something that is either null, empty or pure whitespace.
     * @param text input String object
     * @return boolean
     */
    public static boolean isNullOrBlank(String text) {
        return ( Objects.isNull(text) || text.isBlank() );
    }
}
