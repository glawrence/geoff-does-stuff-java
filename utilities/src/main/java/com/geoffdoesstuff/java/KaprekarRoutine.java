package com.geoffdoesstuff.java;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Why is 6174 one of the strangest numbers in the world?
 * <br>
 * An Indian mathematician Dattatreya Ramchandra Kaprekar (1905–1986) discovered this magic number.
 * <br>
 * Kaprekar’s routine:
 * <ol>
 * <li>Take any four‑digit number with at least two different digits (leading zeros allowed, e.g., 0213).</li>
 * <li>Form the largest number by arranging its digits in descending order.</li>
 * <li>Form the smallest number by arranging its digits in ascending order.</li>
 * <li>Subtract the smaller from the larger.</li>
 * <li>Repeat with the result.</li>
 * </ol>
 * For example:
 * <br>
 * Start 3524 → 5432 − 2345 = 3087 → 8730 − 0378 = 8352 → 8532 − 2358 = 6174.
 * <br>
 * Addendum: 7 is possibly the maximum number of loops
 */
public class KaprekarRoutine {

    /**
     * This is here to suppress JavaDoc complaining about not commenting the default constructor
     */
    private KaprekarRoutine() {
    }

    /**
     * Start the Kaprekar Routine utility, which can be run in test mode, demo mode, or interactive mode, where the
     * latter is the default
     * @param args only first arg is used to specify mode (test or demo)
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].toUpperCase().contains("TEST")) {
                process(null);
                process("");
                process("123");
                process("12345");
                process("aaaa");
                process("1111");
                process("3524");
            } else if (args[0].toUpperCase().contains("DEMO")) {
                process("0213");
                process("0911");
                process("0909");
                process("0990");
                process("9009");
                process("9090");
                process("9900");
                process("9991");
                process("2221");
                process("7648");
                process("2413");
            } else {
                System.err.println("Unknown argument - " + args[0]);
                System.out.println("Please try \"test\" or \"demo\"");
            }
        } else {
            Scanner keyboard = new Scanner(System.in);
            do {
                displayMenu();
                String input = keyboard.nextLine();
                if (input != null) {
                    if ("EX".equalsIgnoreCase(input)) {
                        break;
                    }
                    process(input);
                }
            } while (true);
            keyboard.close();
        }
    }

    private static void displayMenu() {
        System.out.println("Enter number or EX to quit");
    }

    private static void process(String number) {
        boolean valid = valid(number);
        if (valid) {
            System.out.println("Number " + number + " is valid");
            String nextNumber = number;
            int i = 0;
            while (!nextNumber.equals("6174")) {
                nextNumber = processToNewNumber(nextNumber);
                if (++i == 30) throw new IllegalArgumentException("Invalid input, leading to infinite loop - " + number);
            }
        } else {
            System.out.println("Number " + number + " is not valid");
        }
    }

    private static boolean valid(String number) {
        boolean valid;
        if (Objects.isNull(number)) {
            valid = false;
        } else if (number.length() != 4) {
            valid = false;
        } else {
            valid = true;
        }
        if (valid) {
            try {
                int integer = Integer.parseInt(number);
                char[] digits = Integer.toString(integer).toCharArray();
                if ((digits[0] == digits[1]) && (digits[1] == digits[2]) && (digits[2] == digits[3])) {
                    valid = false;
                }
            } catch (NumberFormatException e) {
                valid = false;
            }
        }
        return valid;
    }

    private static String largest(String number) {
        char[] digits = number.toCharArray();
        Arrays.sort(digits);
        return String.valueOf(digits[3]) + digits[2] + digits[1] + digits[0];
    }

    private static String smallest(String number) {
        char[] digits = number.toCharArray();
        Arrays.sort(digits);
        return String.valueOf(digits[0]) + digits[1] + digits[2] + digits[3];
    }

    private static String processToNewNumber(String number) {
        int smallest = Integer.parseInt(smallest(number));
        int largest = Integer.parseInt(largest(number));
        String difference = String.format("%04d", largest - smallest);
        System.out.println("  Number " + number + " becomes " + largest(number) + " and " + smallest(number) + " giving " + difference);
        return difference;
    }
}
