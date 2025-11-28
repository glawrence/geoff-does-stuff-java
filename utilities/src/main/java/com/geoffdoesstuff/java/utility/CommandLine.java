package com.geoffdoesstuff.java.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A Utility class to help process command line arguments. They are processed into a key/value paid map. Some arguments
 * will have no value, and thus their presence in the map indicates they were on the command line.
 */
public class CommandLine {

    private static final Set<Character> PREFIX_CHARS = Set.of('/', '-');

    /**
     * This is here to suppress JavaDoc complaining about not commenting the default constructor
     */
    private CommandLine() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Process the command line arguments into a map of key value pairs.
     * @param args the args from the main() method
     * @return a map of key/value pairs
     */
    public static Map<String, String> processArgs(String[] args) {
        Map<String, String> arguments = new HashMap<>(args.length);
        for (String arg : args) {
            processArgument(arg, arguments);
        }
        return arguments;
    }

    private static void processArgument(String arg, Map<String, String> arguments) {
        String argument = arg;
        if (PREFIX_CHARS.contains(argument.charAt(0))) {
            argument = argument.substring(1);
            if (PREFIX_CHARS.contains(argument.charAt(0))) { // handle -- or // arguments
                argument = argument.substring(1);
            }
        }
        String[] keyValue = argument.split(":", 2);
        String key = keyValue[0];
        String value = (keyValue.length > 1) ? keyValue[1] : "";
        arguments.put(key, value);
    }
}
