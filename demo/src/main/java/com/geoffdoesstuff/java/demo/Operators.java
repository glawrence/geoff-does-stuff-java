package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.DemoUtilities;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Examples of different Java operators.
 */
public class Operators {

    /**
     * This is here to suppress Javadoc complaining about not commenting the default constructor
     */
    private Operators() {
    }

    /**
     * Main method, for independent running or use via MainDemoApp menu
     * @param args command line
     * @see com.geoffdoesstuff.java.MainDemoApp
     */
    public static void main(String[] args) {
        DemoUtilities.outputTitle("Operators");
        DemoUtilities.outputTitle("Ternary Operator");
        ternaryOperator();
    }

    /**
     * Demo the Java Ternary Operator, which is a shorthand way of doing a simple if-else operation
     */
    private static void ternaryOperator() {
        for (int i = 0; i < 5; i++) {
            boolean flag = ThreadLocalRandom.current().nextBoolean();
            System.out.println("Flag is " + flag + ", which gives: " + (flag ? "one" : "two"));
        }
    }
}
