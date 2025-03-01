package com.geoffdoesstuff.java.utility;

import java.io.IOException;

public class ProcessExecutionUtility {

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

    private static String displayExecutionResult(int result) {
        String message = null;
        if (result != 0) {
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
