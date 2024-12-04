package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.data.objects.Holiday;
import com.geoffdoesstuff.java.data.objects.RomanNumeral;
import com.geoffdoesstuff.java.utility.DemoUtilities;

/**
 * This class contains some logic demos, so things like if and switch.
 * Java 14 introduced switch expressions, prior to this we only had switch statements.
 */
public class Logic {

    /**
     * Main method, for independent running or use via MainDemoApp menu
     * @param args command line
     * @see com.geoffdoesstuff.java.MainDemoApp
     */
    public static void main(String[] args) {
        DemoUtilities.outputTitle("Tri Logic Test");
        testLogic(false, false, false);
        testLogic(false, false, true);
        testLogic(false, true, false);
        testLogic(false, true, true);
        testLogic(true, false, false);
        testLogic(true, false, true);
        testLogic(true, true, false);
        testLogic(true, true, true);

        DemoUtilities.outputTitle("Enhanced Switch");
        testEnhancedSwitch();

        DemoUtilities.outputTitle("Pattern Matching - instanceof");
        patternMatchInstanceOf("Hello World");
        patternMatchInstanceOf(1);
        patternMatchInstanceOf(1L);
        patternMatchInstanceOf(1.5);
        patternMatchInstanceOf(new Holiday("Wales", null, null));

        DemoUtilities.outputTitle("Boolean Object Demo");
        demoTheBooleanObjectProblem();
    }

    private static void testLogic(boolean foundA, boolean foundB, boolean foundC) {
        System.out.printf("Found A = %b, Found B = %b, Found C = %b", foundA, foundB, foundC);
        boolean result = false;
        if ((! foundA) || (! foundB) || (! foundC) ) {
            result = true;
        }
        System.out.printf("  Result = %b%n", result);
    }

    /**
     * This type of "pattern matching" was introduced in JDK 16, and avoids the need for an explicit type cast
     * @param object the input, which is any object
     */
    private static void patternMatchInstanceOf(Object object) {
        if (object instanceof String s) {
            System.out.printf("The string '%s' characters long: %d", s, s.length());
        } else if (object instanceof Number n) {
            System.out.printf("We have a number (%s), with value = %s", n.getClass().getName(), n);
        } else {
            System.out.print("Unexpected object");
        }
        System.out.printf(" (input was %s)%n", object.getClass().getName());
    }

    /**
     * Patterns in switch are still in preview for Java 17
     * @param object any object
     */
    private static void patternMatchSwitch(Object object) {
//        switch(object) {
//            case String s:
//                System.out.printf("The string '%s' characters long: %d%n", s, s.length());
//            case Integer i:
//                System.out.printf("We have a number (%s), with value = %d%n", i.getClass().getName(), i);
//            default:
//                System.out.println("Unexpected object of type " + object.getClass().getName());
//        }
    }

    /**
     * There are times when the auto-unboxing of an object, which wrap a primitive, is useful, however it can have
     * unexpected consequences. This demo is designed to illustrate that.
     */
    private static void demoTheBooleanObjectProblem() {
        Boolean testBoolean = Boolean.TRUE;
        checkTheBoolean(testBoolean);
        testBoolean = Boolean.FALSE;
        checkTheBoolean(testBoolean);
        testBoolean = null;
        try {
            checkTheBoolean(testBoolean);
        } catch (NullPointerException npe) {
            // you cannot "unbox" a Boolean object to a boolean primitive if it is null
            System.out.println("ERROR - " + npe.getMessage());
        }
    }

    /**
     * This is a test method to help illustrate the point of {@link #demoTheBooleanObjectProblem()}
     * @param input a Boolean object for the demo
     */
    private static void checkTheBoolean(Boolean input) {
        System.out.println("Testing with: " + input);
        if (Boolean.TRUE.equals(input)) { // the safe way to test
            System.out.println(" - Input Boolean is true");
        } else {
            System.out.println(" - Input Boolean is false");
        }
        if (input) { // this line can throw a NullPointerException
            System.out.println(" - Input Boolean is true");
        } else {
            System.out.println(" - Input Boolean is false");
        }
    }

    private static void testEnhancedSwitch() {
        System.out.println("Value of " + RomanNumeral.I + " is " + convertFromRomanNumeral(RomanNumeral.I));
        System.out.println("Value of " + RomanNumeral.V + " is " + convertFromRomanNumeral(RomanNumeral.V));
        System.out.println("Value of " + RomanNumeral.X + " is " + convertFromRomanNumeral(RomanNumeral.X));
        System.out.println("Value of " + RomanNumeral.L + " is " + convertFromRomanNumeral(RomanNumeral.L));
        System.out.println("Value of " + RomanNumeral.C + " is " + convertFromRomanNumeral(RomanNumeral.C));
        System.out.println("Value of " + RomanNumeral.D + " is " + convertFromRomanNumeral(RomanNumeral.D));
        System.out.println("Value of " + RomanNumeral.M + " is " + convertFromRomanNumeral(RomanNumeral.M));
    }

    /**
     * This type of switch was introduced in JDK 14, and is a nice optimisation, it also forces all cases to be covered
     * unlike the traditional switch.
     * @param romanNumeral the Roman Numeral to convert to a primitive integer
     */
    private static int convertFromRomanNumeral(RomanNumeral romanNumeral) {
        return switch (romanNumeral) {
            case I -> 1;
            case V -> 5;
            case X -> 10;
            case L -> 50;
            case C -> 100;
            case D -> 500;
            case M -> 1000;
        };
    }
}
