package com.geoffdoesstuff.java.demo;

/**
 * A working demo of Regular Expressions. My personal view is that I do not like using Regular Expressions as they
 * are hard to read, however if they are documented in a similar was to my examples below then they can be useful.
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
	 *   match two upper case characters, or digits                   [A-Z0-9]{2}
	 *   match the end of the string                                             $
	 */
	public static final String REGULAR_EXPRESSION_1 = "^ID-[ABC]\\d{5}[A-Z0-9]{2}$";
	/**
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

	public static void main(String[] args) {
		processRexExMatching("", REGULAR_EXPRESSION_1);
		processRexExMatching("ID-C12345AB.", REGULAR_EXPRESSION_1);
		processRexExMatching("ID-D12345AB", REGULAR_EXPRESSION_1);
		processRexExMatching("ID-A12345XX", REGULAR_EXPRESSION_1);
		processRexExMatching("ID-B4321012", REGULAR_EXPRESSION_1);
		processRexExMatching("ID-A12345A0", REGULAR_EXPRESSION_1);
		System.out.println("--------------------");
		processRexExMatching("", REGULAR_EXPRESSION_2);
		processRexExMatching("II-A7X22X", REGULAR_EXPRESSION_2);
		processRexExMatching("ID-A7X22w", REGULAR_EXPRESSION_2);
		processRexExMatching("ID-A72X2w", REGULAR_EXPRESSION_2);
		processRexExMatching("ID-1_488a", REGULAR_EXPRESSION_2);
		processRexExMatching("ID-A7X22a", REGULAR_EXPRESSION_2);
		processRexExMatching("ID-A7X22X", REGULAR_EXPRESSION_2);
		processRexExMatching("ID-A7v22X", REGULAR_EXPRESSION_2);
	}

	private static void processRexExMatching(String text, String regularExpression) {
		if (text.matches(regularExpression)) {
			System.out.println("Regex match! Matched " + text + " against " + regularExpression);
		} else {
			System.out.printf("No match of %s against %s%n", text, regularExpression);
		}
	}
}
