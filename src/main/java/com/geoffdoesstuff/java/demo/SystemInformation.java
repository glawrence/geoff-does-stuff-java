package com.geoffdoesstuff.java.demo;

import java.time.Duration;

/**
 * Demonstration of what kind of system information you can get.
 */
public class SystemInformation {

    /**
     * This is here to suppress JavaDoc complaining about not commenting the default constructor
     */
    private SystemInformation() {
    }

    /**
     * Main method, for independent running or use via MainDemoApp menu
     * @param args command line
     */
    public static void main(String... args) {
        // System::nanoTime always increases, unlike the time in millis which can go backwards after an NTP sync
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
        long nanos = System.nanoTime();
        Duration elapsed = Duration.ofNanos(nanos);
        System.out.println(elapsed);
        System.out.println(Duration.ofNanos(System.nanoTime()));
        System.out.println(Duration.ofNanos(System.nanoTime()));
        System.out.println(Duration.ofNanos(System.nanoTime()));

        // you can use currency symbols, not just Dollar, but also Pound, Euro and Yen
        // NOTE with Java 17 and earlier you need to specify UTF-8 encoding of the source files. When using
        //      Java 18 which included "JEP 400: UTF-8 by Default" you don't need to specify this.
        int blah£$¥¢€ = 5;
        System.out.println(blah£$¥¢€);
    }
}
