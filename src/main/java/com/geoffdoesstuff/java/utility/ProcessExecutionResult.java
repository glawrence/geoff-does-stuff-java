package com.geoffdoesstuff.java.utility;

public record ProcessExecutionResult(int exitCode, String errorMessage){
    public boolean success() {
        return (exitCode == 0);
    }
}
