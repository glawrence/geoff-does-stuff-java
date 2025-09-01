package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.DemoUtilities;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstration of different techniques to execute the same code, repeatedly.
 */
public class Repeating {

    /**
     * This is here to suppress JavaDoc complaining about not commenting the default constructor
     */
    private Repeating() {
    }

    /**
     * Main method, for independent running or use via Demo Menu
     * @param args command line
     */
    public static void main(String[] args) {
        List<String> strings = List.of("One", "Two", "Three", "Four", "Five", "Six");

        DemoUtilities.outputTitle("Classic for loop (basic)", true);
        classicForLoop(strings);

        DemoUtilities.outputTitle("New style for loop (enhanced)", true);
        newForLoop(strings);

        DemoUtilities.outputTitle("Iterator with while", true);
        whileIterator(strings);

        DemoUtilities.outputTitle("While loop", true);
        whileLoop(strings);

        DemoUtilities.outputTitle("Do loop", true);
        doLoop(strings);

        DemoUtilities.outputTitle("Break example", true);
        loopBreakExample(strings);

        DemoUtilities.outputTitle("Continue example", true);
        loopContinueExample(strings);

        DemoUtilities.outputTitle("Streams.forEach()", true);
        streamsLoop(strings);
    }

    private static void classicForLoop(List<String> strings) {
        for (int index = 1; index <= strings.size(); index++) {
            System.out.println(index + " - " + strings.get(index-1));
        }
    }

    private static void newForLoop(List<String> strings) {
        int index = 1;
        for (String string: strings) {
            System.out.println(index++ + " - " + string);
        }
    }

    private static void whileIterator(List<String> strings) {
        Iterator<String> iterator = strings.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            System.out.println(index++ + " - " + iterator.next());
        }
    }

    private static void whileLoop(List<String> strings) {
        int index = 0;
        while (index < strings.size()) {
            String value = strings.get(index++);
            System.out.println(index + " - " + value);
        }
    }

    private static void doLoop(List<String> strings) {
        int index = 0;
        do {
            String value = strings.get(index++);
            System.out.println(index + " - " + value);
        } while (index < strings.size());
    }

    private static void loopBreakExample(List<String> strings) {
        for (int i = 1; i <= strings.size(); i++) {
            if (strings.get(i-1).equalsIgnoreCase("THREE")) {
                break; // exit the loop, completely
            }
            System.out.println(i + " - " + strings.get(i-1));
        }
    }

    private static void loopContinueExample(List<String> strings) {
        for (int i = 1; i <= strings.size(); i++) {
            if (strings.get(i-1).equalsIgnoreCase("THREE")) {
                continue; // exit the current iteration of the loop, but continue with any subsequent ones
            }
            System.out.println(i + " - " + strings.get(i-1));
        }
    }

    private static void streamsLoop(List<String> strings) {
        // can't use a primitive, so AtomicInteger used instead
        AtomicInteger newNumber = new AtomicInteger(1);
        strings.forEach(string -> {
            System.out.println(newNumber.getAndIncrement() + " - " + string);
        });
    }
}
