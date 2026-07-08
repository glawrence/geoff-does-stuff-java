package com.geoffdoesstuff.java.utility;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.util.*;

/**
 * Basic utility class providing some handy methods for working with text.
 */
public class TextUtilities {

    /**
     * Match any character, except new-lines, and "return" once per character
     */
    private static final String REGEX_ANY_CHARACTER = ".";

    /**
     * This is here to suppress Javadoc complaining about not commenting the default constructor
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
     * Utility method to help test for something that contains text, in other words it is not null, empty or just
     * whitespace.
     * @param text input String object
     * @return boolean
     */
    public static boolean isNotBlank(String text) {
        return ( ! isNullOrBlank(text) );
    }

    /**
     * Shortcut of call to convertToRepeatedCharacter() with outputChar set to ' ', a simple convenience method.
     * @param input input string to convert
     * @return String
     */
    public static String convertToSpaces(String input) {
        return convertToRepeatedCharacter(input, ' ');
    }

    /**
     * Shortcut of call to convertToRepeatedCharacter() with outputChar set to '-', a simple convenience method.
     * @param input input string to convert
     * @return String
     */
    public static String convertToHyphens(String input) {
        return convertToRepeatedCharacter(input, '-');
    }

    /**
     * Convert the input String to be all the same outputChar character. The only validation is null checking.
     * Processes line terminating characters, but the Windows \r\n will convert to two output characters as
     * technically it is two input characters.
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
     * Will ignore line terminating characters, and thus output them.
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

    /**
     * Helper method to get plurals and non-plurals right. Pass in a number with the unit, and it will return "0 days"
     * or "1 file" or "2 warnings", avoiding the need to use "x apple(s)". This has been coded with an example of using
     * {@link java.text.MessageFormat} and other {@link java.text.Format} classes to implement. Which are much more
     * powerful than this.
     * @param count the number of "items"
     * @param descriptor the base word in singular form
     * @return the number, with descriptor in the correct plural/non-plural form
     */
    public static String getNumberWithWord(long count, String descriptor) {
        String pluralDescriptor = descriptor + "s";
        double[] countLimits = {0, 1, 2};
        String[] countParts = {"0 " + pluralDescriptor, "1 " + descriptor, "{0,number} " + pluralDescriptor};
        ChoiceFormat choiceFormat = new ChoiceFormat(countLimits, countParts);
        MessageFormat format = new MessageFormat("{0}");
        format.setFormatByArgumentIndex(0, choiceFormat);
        Object[] countObjectArray = {count};
        return format.format(countObjectArray);
    }

    /**
     * An alternative to getNumberWithWord() which is actually simpler and one assumes faster.
     * @param count the number of "items"
     * @param descriptor the base word in singular form
     * @return the number, with descriptor in the correct plural/non-plural form
     */
    public static String getNumberWithWord_alt(long count, String descriptor) {
        if (count == 1) {
            return "1 " + descriptor;
        } else {
            return count + " " + descriptor + "s";
        }
        /*
        The code above can be simplified to one line as follows:
        return (count == 1) ? "1 " + descriptor : count + " " + descriptor + "s";
        However I personally find the more verbose if statement easier to read.
         */
    }

    /**
     * If a String contains multiple lines of text, then return just the first line of that String.
     * @param input a String which may or may not have multiple lines
     * @return a String with no line endings, the first line of the input String
     */
    public static String firstLineOnly(String input) {
        if (isNullOrEmpty(input)) {
            return input;
        } else {
            return input.lines().findFirst().orElse("");
        }
    }
}
