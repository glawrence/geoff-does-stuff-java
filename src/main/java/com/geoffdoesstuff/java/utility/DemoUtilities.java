package com.geoffdoesstuff.java.utility;

import java.util.Objects;

/**
 * Provide some utility methods to use in the demo classes.
 */
public class DemoUtilities {

    private static final String REGEX_ANY_CHARACTER = ".";

    /**
        Output as a "major" title
     * @param title text to display as a title
     */
    public static void outputTitle(String title) {
        outputTitle(title, false);
    }

    /**
        For "major":
            1) a new line, 2) a line of ~ characters, the same length as the title, 3) the title 4) the ~ line again
        For "minor":
            1) a new line, 2) the title 3) a line of - characters, the same length as the title
     * @param title text to display as a title
     * @param minor if true display text as a minor, or sub-title
     */
    public static void outputTitle(String title, boolean minor) {
        String message = Objects.requireNonNull(title, "title must not be null");
        if (!minor) {
            System.out.print(System.lineSeparator() + message.replaceAll(REGEX_ANY_CHARACTER, "~"));
        }
        System.out.println(System.lineSeparator() + message);
        if (minor) {
            System.out.println(message.replaceAll(REGEX_ANY_CHARACTER, "-"));
        } else {
            System.out.println(message.replaceAll(REGEX_ANY_CHARACTER, "~"));
        }
    }
}
