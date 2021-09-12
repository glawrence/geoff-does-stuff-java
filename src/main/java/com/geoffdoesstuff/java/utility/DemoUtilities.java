package com.geoffdoesstuff.java.utility;

import java.util.Objects;

public class DemoUtilities {
    /*
        Output as a "major" title
     */
    public static void outputTitle(String title) {
        outputTitle(title, false);
    }

    /*
        For "major":
            1) a new line, 2) a line of ~ characters, the same length as the title, 3) the title 4) the ~ line again
        For "minor":
            1) a new line, 2) the title 3) a line of - characters, the same length as the title
     */
    public static void outputTitle(String title, boolean minor) {
        String message = Objects.requireNonNull(title, "title must not be null");
        if (!minor) {
            System.out.print(System.lineSeparator() + message.replaceAll(".", "~"));
        }
        System.out.println(System.lineSeparator() + message);
        if (minor) {
            System.out.println(message.replaceAll(".", "-"));
        } else {
            System.out.println(message.replaceAll(".", "~"));
        }
    }
}
