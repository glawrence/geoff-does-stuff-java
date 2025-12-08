package com.geoffdoesstuff.java.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * A Utility class to help process command line arguments. They are processed into a key/value paid map. Some arguments
 * will have no value, and thus their presence in the map indicates they were on the command line.
 * When working with the Map&lt;String, String&gt; returned by processArgs() you can use arguments.containsKey("test") which
 * will check if the argument "test" has been passed, however it does not differentiate between "test" and "test=on".
 * There are a number of supported ways to specify the same command line option, as follows:
 * <pre>
 * debug
 * /debug
 * //debug
 * -debug
 * --debug
 * </pre>
 * If the argument needs to specify a value then two separators are supported as follows:
 * <pre>
 * --mode=debug
 * --mode:debug
 * </pre>
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
     *
     * @param args the args from the main() method
     * @return a map of key/value pairs
     */
    public static Map<String, String> processArgs(String[] args) {
        return processArgs(args, false);
    }

    /**
     * This version of processArgs() method is useful when case sensitivity in the arguments is not needed, all the
     * keys are converted to lowercase, if required.
     *
     * @param args          the args from the main() method
     * @param lowerCaseKeys if true convert all keys to lowercase
     * @return a map of key/value pairs
     */
    public static Map<String, String> processArgs(String[] args, boolean lowerCaseKeys) {
        Map<String, String> arguments = new HashMap<>(args.length);
        for (String arg : args) {
            processArgument(arg, arguments, lowerCaseKeys);
        }
        return arguments;
    }

    /**
     * Examine the arguments and check if the required arguments are there, and that there are no extras.
     *
     * @param arguments     the processed command line arguments from processArgs()
     * @param required      list of required arguments
     * @param caseSensitive enable or disable case sensitivity
     * @return whether the required args are present or not
     */
    public static boolean checkArgs(Map<String, String> arguments, List<String> required, boolean caseSensitive) {
        if (Objects.isNull(arguments) || Objects.isNull(required)) {
            return false;
        }
        if (required.isEmpty()) {
            return false;
        }
        if (required.size() != arguments.size()) {
            return false;
        }

        boolean result = true;
        List<String> keys = new ArrayList<>(arguments.size());
        arguments.keySet().forEach(key -> keys.add(caseSensitive ? key : key.toLowerCase()));
        for (String item: required) {
            if (caseSensitive) {
                result = (result && (keys.contains(item)));
            } else {
                result = (result && (keys.contains(item.toLowerCase())));
            }
        }
        return result;
    }

    /**
     * Generate a String of all the command line arguments and their values, looking nicer than the default output
     * for a Map.
     * @param arguments map of command line arguments
     * @return a nicely formatted string
     */
    public static String getFormattedArguments(Map<String, String> arguments) {
        final String NEWLINE = System.lineSeparator();
        StringBuilder formattedArguments = new StringBuilder();
        if (arguments.isEmpty()) {
            formattedArguments.append("No command line arguments").append(NEWLINE);
        } else {
            formattedArguments.append("Command Line Arguments:").append(NEWLINE);
        }
        arguments.forEach((key, value) -> {
            formattedArguments.append("  ").append(key);
            if (!TextUtilities.isNullOrBlank(value)) {
                formattedArguments.append("=").append(value);
            }
            formattedArguments.append(NEWLINE);
        });
        return formattedArguments.toString();
    }

    private static void processArgument(String arg, Map<String, String> arguments, boolean lowerCaseKeys) {
        String argument = arg;
        if (PREFIX_CHARS.contains(argument.charAt(0))) {
            argument = argument.substring(1);
            if (PREFIX_CHARS.contains(argument.charAt(0))) { // handle -- or // arguments
                argument = argument.substring(1);
            }
        }
        String splitChar = "=";
        if (!argument.contains("=")) {
            splitChar = ":";
        }
        String[] keyValue = argument.split(splitChar, 2);
        String key = lowerCaseKeys ? keyValue[0].toLowerCase() : keyValue[0];
        String value = (keyValue.length > 1) ? keyValue[1] : "";
        arguments.put(key, value);
    }
}
