package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.DemoUtilities;
import com.geoffdoesstuff.java.utility.JavaSystemInfo;
import com.geoffdoesstuff.java.utility.ProcessExecutionResult;
import com.geoffdoesstuff.java.utility.ProcessExecutionUtility;

/**
 * Runtime was part of Java 1.0, ProcessBuilder was introduced in Java 1.5, so is much newer. ProcessBuilder takes
 * an array of Strings, as arguments but Runtime needs it as one string.
 */
public class ExecuteExternal {

	/**
	 * Main method, for independent running or use via Demo Menu
	 * @param args command line
	 */
	public static void main(String[] args) {
		DemoUtilities.outputTitle("Execute External");
		DemoUtilities.outputTitle("Ping Test", true);
		pingTest();
		DemoUtilities.outputTitle("PowerShell Test (with output)", true);
		powerShellTest();
	}

	private static void powerShellTest() {
		ProcessExecutionResult executionResult;
		executionResult = ProcessExecutionUtility.runtimeExecuteWithOutput("pwsh -Command \"Write-Output 'StdOut 1'; Write-Error 'StdErr'; Write-Output 'StdOut 2'\"");
		if (executionResult.success()) {
			System.out.println("The PowerShell executed successfully");
		} else {
			System.out.println("The PowerShell failed");
			System.out.println("  " + executionResult.errorMessage());
		}
		System.out.println("Process Output");
		System.out.println("~~~~~~~~~~~~~~");
		System.out.println(executionResult.processOutput());
		System.out.println("~~~~~~~~~~~~~~");
		executionResult = ProcessExecutionUtility.processBuilderWithOutput("pwsh", "-Command", "\"Write-Output 'StdOut 1'; Write-Error 'StdErr'; Write-Output 'StdOut 2'\"");
		if (executionResult.success()) {
			System.out.println("The PowerShell executed successfully");
		} else {
			System.out.println("The PowerShell failed");
			System.out.println("  " + executionResult.errorMessage());
		}
		System.out.println("Process Output");
		System.out.println("~~~~~~~~~~~~~~");
		System.out.println(executionResult.processOutput());
		System.out.println("~~~~~~~~~~~~~~");
	}

	private static void pingTest() {
		String hostname = "192.168.1.1";

		String commandLine;
		String[] commandLineArray;

		switch (JavaSystemInfo.getOperatingSystem()) {
			case WINDOWS      -> { // on Windows ping always returns 0, find needed to get exit code
				commandLine = "cmd /c ping -n 1 " + hostname + " | find \"TTL\"";
				commandLineArray = new String[] {"cmd", "/c", "ping -n 1 " + hostname + " | find \"TTL\""};
			}
			case MACOS, LINUX -> { // ping count = 1, timeout = 1s
				commandLine = "ping -c 1 -t 1 " + hostname;
				commandLineArray = new String[] {"ping", "-c 1", "-t 1", hostname};
			}
			default           -> {
				commandLine = null;
				commandLineArray = null;
			}
		}

		ProcessExecutionResult executionResult = ProcessExecutionUtility.runtimeExecute(commandLine);
		if (executionResult.success()) {
			System.out.println("The host '" + hostname + "' is responding to ping");
		} else {
			System.out.println("The host '" + hostname + "' is not responding to ping, probably offline or not responding");
			System.out.println("  " + executionResult.errorMessage());
		}
		executionResult = ProcessExecutionUtility.processBuilder(commandLineArray);
		if (executionResult.success()) {
			System.out.println("The host '" + hostname + "' is responding to ping");
		} else {
			System.out.println("The host '" + hostname + "' is not responding to ping, probably offline or not responding");
			System.out.println("  " + executionResult.errorMessage());
		}
	}
}
