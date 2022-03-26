package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.DemoUtilities;

import java.util.List;

/**
 * A working demo of Regular Expressions.
 * My personal view is that I do not like using Regular Expressions as they are hard to read. If they are documented
 * in a similar way to my examples below then they can be easier to understand and hence potentially useful, and I
 * have done just this.
 */
public class RegularExpressions {

    /**
     *   Regular expressions are complicated, and not easy to understand, especially with more advanced uses. This
     *   is a reasonably simple example that I have tried to document clearly what each element is doing.
     *
     *   match the start of the string                 ^
     *   match simple literal text                      ID-
     *   match only one of A, B or C                       [ABC]
     *   is actually \d{5} and means match 5 digits             \\d{5}
     *   match two characters, either an upper case
     *   letter or digit                                              [A-Z0-9]{2}
     *   match the end of the string                                             $
     */
    public static final String REGULAR_EXPRESSION_1 = "^ID-[ABC]\\d{5}[A-Z0-9]{2}$";

    /*
     *   match the start of the string                 ^
     *   match simple literal text                      ID-
     *   match three letters, numbers or underscores       \\w{3}
     *   is actually \d{2} and means match 2 digits              \\d{2}
     *   the start of a section                                        (
     *   match one lowercase letter, a to c inclusive                   [a-c]
     *   match either the part before or the part after                      |
     *   match one uppercase letter, X to Z inclusive                         [X-Z]
     *   the end of the section started earlier                                    )
     *   match the end of the string                                                $
     */
    public static final String REGULAR_EXPRESSION_2 = "^ID-\\w{3}\\d{2}([a-c]|[X-Z])$";

    public static final String REGULAR_EXPRESSION_3 =
        /*
             ^        - match the start of the string
              [A-Z]   - match any uppercase character
                   *  - match zero or more of the preceding element, in this case [A-Z]
                    $ - match against the end of the string
         */
            "^[A-Z]*$";

    public static final String REGULAR_EXPRESSION_4 =
        /*
             ^        - match the start of the string
              [A-Z]   - match any uppercase character
                   +  - match one or more of the preceding element, in this case [A-Z]
                    $ - match against the end of the string
         */
            "^[A-Z]+$";

    /**
     * Main method, for independent running or use via Demo Menu
     * @param args command line
     */
    public static void main(String[] args) {
        DemoUtilities.outputTitle("Regular Expression - " + REGULAR_EXPRESSION_1, true);
        processRexExMatching("", REGULAR_EXPRESSION_1);
        processRexExMatching("ID-C12345AB.", REGULAR_EXPRESSION_1);
        processRexExMatching("ID-D12345AB", REGULAR_EXPRESSION_1);
        processRexExMatching("ID-A12345XX", REGULAR_EXPRESSION_1);
        processRexExMatching("ID-B4321012", REGULAR_EXPRESSION_1);
        processRexExMatching("ID-A12345A0", REGULAR_EXPRESSION_1);

        DemoUtilities.outputTitle("Regular Expression - " + REGULAR_EXPRESSION_2, true);
        processRexExMatching("", REGULAR_EXPRESSION_2);
        processRexExMatching("II-A7X22X", REGULAR_EXPRESSION_2);
        processRexExMatching("ID-A7X22w", REGULAR_EXPRESSION_2);
        processRexExMatching("ID-A72X2w", REGULAR_EXPRESSION_2);
        processRexExMatching("ID-1_488a", REGULAR_EXPRESSION_2);
        processRexExMatching("ID-A7X22a", REGULAR_EXPRESSION_2);
        processRexExMatching("ID-A7X22X", REGULAR_EXPRESSION_2);
        processRexExMatching("ID-A7v22X", REGULAR_EXPRESSION_2);

        List<String> exampleTextList = List.of("", " ", "a", "1", "~", ".", "A", "AB", "AZS", "GFHFJFJFJF", "AZS7", " AZS");

        DemoUtilities.outputTitle("Regular Expression - " + REGULAR_EXPRESSION_3, true);
        for (String exampleText : exampleTextList) {
            processRexExMatching(exampleText, REGULAR_EXPRESSION_3);
        }

        DemoUtilities.outputTitle("Regular Expression - " + REGULAR_EXPRESSION_4, true);
        for (String exampleText : exampleTextList) {
            processRexExMatching(exampleText, REGULAR_EXPRESSION_4);
        }
    }

    private static void processRexExMatching(String text, String regularExpression) {
        if (text.matches(regularExpression)) {
            System.out.println("Regex match! Matched '" + text + "' against " + regularExpression);
        } else {
            System.out.printf("No match of '%s' against %s%n", text, regularExpression);
        }
    }
}
