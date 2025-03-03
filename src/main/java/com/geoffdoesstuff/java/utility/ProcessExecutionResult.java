package com.geoffdoesstuff.java.utility;

/**
 * Record class that encapsulates the process exit code and any error message, if there is one
 * @param exitCode
 * @param errorMessage
 */
public record ProcessExecutionResult(int exitCode, String errorMessage){

    /**
     * Helper function to test the exit code for success
     * @return boolean, true if exit code represents success
     */
    public boolean success() {
        return (exitCode == 0);
    }
}
