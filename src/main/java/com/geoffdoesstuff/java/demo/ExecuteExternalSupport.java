package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.JavaSystemInfo;

/**
 * There are three, predefined command lines, in both single String and String array form. The ping commands lines show
 * how sometimes different platforms need different commands for the same task.
 * With the Python and PowerShell command lines, they are designed to show how the output order differs depending on
 * the Java class used to execute the command line. These both show "standard output" and "error output" and how
 * redirectErrorStream() in ProcessBuilder does what it says, rather than creating a separate output.
 */
public class ExecuteExternalSupport {

    public enum CommandLine {
        PING,
        POWERSHELL,
        PYTHON
    }

    public static String generateCommandLine(CommandLine commandLine, String... arguments) {
        switch (commandLine) {
            case PING -> {
                String hostname = arguments[0];
                switch (JavaSystemInfo.getOperatingSystem()) {
                    case WINDOWS      -> { // on Windows ping always returns 0, find command needed to get exit code
                        return "cmd /c ping -n 1 " + hostname + " | find \"TTL\"";
                    }
                    case MACOS, LINUX -> { // ping count = 1, timeout = 1s
                        return "ping -c 1 -t 1 " + hostname;
                    }
                    default           -> {
                        return "";
                    }
                }
            }
            case POWERSHELL -> {
                return "pwsh -Command \"Write-Output 'StdOut 1'; Write-Error 'StdErr'; Write-Output 'StdOut 2'\"";
            }
            case PYTHON -> {
                return "python -c \"import sys;print('StdOut 1');sys.stderr.write('StdErr\\n');print('StdOut 2')\"";
            }
            default -> {
                return "";
            }
        }
    }

    public static String[] generateCommandLineArray(CommandLine commandLine, String... arguments) {
        switch (commandLine) {
            case PING -> {
                String hostname = arguments[0];
                switch (JavaSystemInfo.getOperatingSystem()) {
                    case WINDOWS -> { // on Windows ping always returns 0, find command needed to get exit code
                        return new String[] {"cmd", "/c", "ping -n 1 " + hostname + " | find \"TTL\""};
                    }
                    case MACOS, LINUX -> { // ping count = 1, timeout = 1s
                        return new String[] {"ping", "-c 1", "-t 1", hostname};
                    }
                    default -> {
                        return new String[] {};
                    }
                }
            }
            case POWERSHELL -> {
                return new String[] {"pwsh", "-Command", "\"Write-Output 'StdOut 1'; Write-Error 'StdErr'; Write-Output 'StdOut 2'\""};
            }
            case PYTHON -> {
                return new String[] {"python", "-c", "\"import sys;print('StdOut 1');sys.stderr.write('StdErr\\n');print('StdOut 2')\""};
            }
            default -> {
                return new String[] {};
            }
        }
    }
}
