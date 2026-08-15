package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.DemoUtilities;

/**
 * The IO class was introduced in Java 25, and is designed as a simple way to process terminal output and input for
 * beginners. In other words it is a simple wrapper around existing Java classes. See {@link IO} for more
 * details, but experienced developers would do well to read the source code at
 * <a href="https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/lang/IO.java">java.lang.IO</a>
 */
public class TerminalIO {

    /**
     * This is here to suppress Javadoc complaining about not commenting the default constructor
     */
    private TerminalIO() {
    }

    /**
     * Main method, for independent running or use via MainDemoApp menu
     * @param args command line
     * @see com.geoffdoesstuff.java.MainDemoApp
     */
    public static void main(String[] args) {
        DemoUtilities.outputTitle("java.lang.IO demo");
        IO.print("Outputting text as well as objects like ");
        IO.print(Long.decode("2112"));
        IO.println(" a Long");

        DemoUtilities.outputTitle("Input demo");
        String input = IO.readln("Please enter something: ");
        IO.println("Your input was: \"" + input + "\"");
    }
}
