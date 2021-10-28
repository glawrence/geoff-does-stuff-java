package com.geoffdoesstuff.java.utility;

import java.util.Objects;

/**
 * Basic utility class providing some handy methods for working with text.
 */
public class TextUtilities {

    /**
     * Utility method to help test for something that is either null, empty or pure whitespace.
     * @param text input String object
     * @return boolean
     */
    public static boolean isNullOrEmpty(String text) {
        return ((Objects.isNull(text)) || text.trim().isEmpty());
    }
}
