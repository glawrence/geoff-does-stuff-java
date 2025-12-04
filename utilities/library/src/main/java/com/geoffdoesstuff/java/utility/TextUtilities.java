package com.geoffdoesstuff.java.utility;

import java.util.*;

/**
 * Basic utility class providing some handy methods for working with text.
 */
public class TextUtilities {

    private static final String REGEX_ANY_CHARACTER = ".";

    /**
     * This is here to suppress JavaDoc complaining about not commenting the default constructor
     */
    private TextUtilities() {
    }

    /**
     * Utility method to help test for something that is either null or empty. A string with whitespace in is
     * not empty. See also isNullOrBlank()
     * @param text input String object
     * @return boolean
     */
    public static boolean isNullOrEmpty(String text) {
        return ( Objects.isNull(text) || text.isEmpty() );
    }

    /**
     * Utility method to help test for something that is either null, empty or pure whitespace. See also isNullOrEmpty.
     * @param text input String object
     * @return boolean
     */
    public static boolean isNullOrBlank(String text) {
        return ( Objects.isNull(text) || text.isBlank() );
    }

    /**
     * Convert the input String to be all the same outputChar character. The only validation is null checking.
     * @param input input string to convert
     * @param outputChar single character string
     * @return String
     */
    public static String convertToRepeatedCharacter(String input, Character outputChar) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException("Cannot have a null input string");
        }
        if (Objects.isNull(outputChar)) {
            throw new IllegalArgumentException("The outputChar cannot be null");
        }
        char[] line = new char[input.length()];
        Arrays.fill(line, 0, line.length, outputChar);
        return String.valueOf(line);
    }

    /**
     * Convert input String to be multiple copies of the outputChar to the same length as the input String.
     * The only validation is null checking and outputChar length checking.
     * @param input input string to convert
     * @param outputChar single character string
     * @return String
     */
    public static String convertToRepeatedString(String input, String outputChar) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException("Cannot have a null input string");
        }
        if (Objects.isNull(outputChar)) {
            throw new IllegalArgumentException("The outputChar cannot be null");
        }
        if (outputChar.length() != 1) {
            throw new IllegalArgumentException("The outputChar must be one character long");
        }
        return input.replaceAll(REGEX_ANY_CHARACTER, outputChar);
    }
}
