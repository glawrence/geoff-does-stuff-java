package com.geoffdoesstuff.java.utility;

/**
 * Record class that encapsulates the process exit code and any error message, if there is one
 * @param exitCode process exit code
 * @param errorMessage null if process ran successfully, otherwise the error message
 * @param processOutput the console output from the process
 */
public record ProcessExecutionResult(int exitCode, String errorMessage, String processOutput) {

    /**
     * Helper function to test the exit code for success
     * @return boolean, true if exit code represents success
     */
    public boolean success() {
        return (exitCode == 0);
    }
}
