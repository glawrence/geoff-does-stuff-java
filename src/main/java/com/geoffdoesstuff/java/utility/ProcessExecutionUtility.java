package com.geoffdoesstuff.java.utility;

import java.io.IOException;

/**
 * The intention of this utility class is to make executing external programs easy, but also to provide flexibility. In
 * part, it is also a "demonstration".
 */
public class ProcessExecutionUtility {

    /**
     * The method uses the original Java 1.0 approach to external execution.
     * @param commandLine whole command line on a single String
     * @return a {@link ProcessExecutionResult}
     */
    public static ProcessExecutionResult runtimeExecute(String commandLine) {
        try {
            Process proc = Runtime.getRuntime().exec(commandLine);
            int result = proc.waitFor();
            return new ProcessExecutionResult(result, displayExecutionResult(result));
        } catch (IOException | InterruptedException e) {
            System.out.println("ERROR in runtimeExecute: " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This method uses the Java 1.5 ProcessBuilder to execute external processes. It takes an array of String so
     * you can pass parameters separately.
     * @param commandLine string array of program and optionally, arguments
     * @return a {@link ProcessExecutionResult}
     */
    public static ProcessExecutionResult processBuilder(String... commandLine) {
        try {
            Process process = new ProcessBuilder(commandLine).start();
            int result = process.waitFor();
            return new ProcessExecutionResult(result, displayExecutionResult(result));
        } catch (IOException | InterruptedException e) {
            System.out.println("ERROR in processBuilder: " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is specific to the ping utility.
     * @param result the process exit code
     * @return text based on exit code and operating system
     */
    private static String displayExecutionResult(int result) {
        String message;
        if (result == 0) {
            message = null;
        } else {
            if (JavaSystemInfo.isPlatformMacOS()) {
                switch (result) {
                    case  2 -> message = "Ping sent but no response";
                    case 64 -> message = "Command line usage error";
                    case 68 -> message = "Cannot resolve, unknown host";
                    default -> message = "ERROR: ping failed";
                }
            } else {
                message = "Command executed but failed, Exit Code: " + result;
            }
        }
        return message;
    }
}
