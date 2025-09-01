package com.geoffdoesstuff.java.demo;

/**
 * Example to demonstrate the nuance of equality.
 */
public class Equality {

    /**
     * This is here to suppress JavaDoc complaining about not commenting the default constructor
     */
    private Equality() {
    }

    /**
     * Main method, for independent running or use via Demo Menu
     * @param args command line
     */
    public static void main(String[] args) {
        integerEqualityExample(100);
        integerEqualityExample(1000);
    }

    /**
     * If you add -XX:AutoBoxCacheMax=1500 to "VM Options", or -Djava.lang.Integer.IntegerCache.high=1500
     * I believe the former -XX option sets the system property for you
     * then you can use up to 1500, instead of the default -128 to 127
     * @param number number to test
     */
    private static void integerEqualityExample(int number) {
        Integer x = number;
        Integer y = number;
        System.out.println("Comparing with " + number);
        if (x == y) {
            System.out.println(" - Equal");
        } else {
            System.out.println(" - Not equal");
        }
        if (x.equals(y)) {
            System.out.println(" - Equal");
        } else {
            System.out.println(" - Not equal");
        }
        if (x.intValue() == y.intValue()) {
            System.out.println(" - Equal");
        } else {
            System.out.println(" - Not equal");
        }
    }
}
