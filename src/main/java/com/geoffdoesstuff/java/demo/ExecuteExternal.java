package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.DemoUtilities;
import com.geoffdoesstuff.java.utility.ProcessExecutionResult;
import com.geoffdoesstuff.java.utility.ProcessExecutionUtility;

/**
 * Runtime was part of Java 1.0, ProcessBuilder was introduced in Java 1.5, so is much newer. ProcessBuilder takes
 * an array of Strings, as arguments but Runtime needs it as one string.
 */
public class ExecuteExternal {

	private static final ExecuteExternalSupport.CommandLine PYTHON_COMMAND_LINE = ExecuteExternalSupport.CommandLine.PYTHON;
	private static final ExecuteExternalSupport.CommandLine POWERSHELL_COMMAND_LINE = ExecuteExternalSupport.CommandLine.POWERSHELL;
	private static final ExecuteExternalSupport.CommandLine PING_COMMAND_LINE = ExecuteExternalSupport.CommandLine.PING;

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
		DemoUtilities.outputTitle("Python Test (with output)", true);
		pythonTest();
	}

	private static void pythonTest() {
		ProcessExecutionResult executionResult;
		executionResult = ProcessExecutionUtility.runtimeExecuteWithOutput(ExecuteExternalSupport.generateCommandLine(PYTHON_COMMAND_LINE));
		if (executionResult.success()) {
			System.out.println("The Python executed successfully");
		} else {
			System.out.println("The Python failed");
			System.out.println("  " + executionResult.errorMessage());
		}
		outputProcessOutput(executionResult);
		executionResult = ProcessExecutionUtility.processBuilderWithOutput(ExecuteExternalSupport.generateCommandLineArray(PYTHON_COMMAND_LINE));
		if (executionResult.success()) {
			System.out.println("The Python executed successfully");
		} else {
			System.out.println("The Python failed");
			System.out.println("  " + executionResult.errorMessage());
		}
		outputProcessOutput(executionResult);
	}

	private static void powerShellTest() {
		ProcessExecutionResult executionResult;
		executionResult = ProcessExecutionUtility.runtimeExecuteWithOutput(ExecuteExternalSupport.generateCommandLine(POWERSHELL_COMMAND_LINE));
		if (executionResult.success()) {
			System.out.println("The PowerShell executed successfully");
		} else {
			System.out.println("The PowerShell failed");
			System.out.println("  " + executionResult.errorMessage());
		}
		outputProcessOutput(executionResult);
		executionResult = ProcessExecutionUtility.processBuilderWithOutput(ExecuteExternalSupport.generateCommandLineArray(POWERSHELL_COMMAND_LINE));
		if (executionResult.success()) {
			System.out.println("The PowerShell executed successfully");
		} else {
			System.out.println("The PowerShell failed");
			System.out.println("  " + executionResult.errorMessage());
		}
		outputProcessOutput(executionResult);
	}

	private static void pingTest() {
		final String hostname = "192.168.1.1";

		ProcessExecutionResult executionResult = ProcessExecutionUtility.runtimeExecute(ExecuteExternalSupport.generateCommandLine(PING_COMMAND_LINE, hostname));
		if (executionResult.success()) {
			System.out.println("The host '" + hostname + "' is responding to ping");
		} else {
			System.out.println("The host '" + hostname + "' is not responding to ping, probably offline or not responding");
			System.out.println("  " + executionResult.errorMessage());
		}
		executionResult = ProcessExecutionUtility.processBuilder(ExecuteExternalSupport.generateCommandLineArray(PING_COMMAND_LINE, hostname));
		if (executionResult.success()) {
			System.out.println("The host '" + hostname + "' is responding to ping");
		} else {
			System.out.println("The host '" + hostname + "' is not responding to ping, probably offline or not responding");
			System.out.println("  " + executionResult.errorMessage());
		}
	}

	private static void outputProcessOutput(ProcessExecutionResult executionResult) {
		System.out.println("Process Output");
		System.out.println("~~~~~~~~~~~~~~");
		System.out.println(executionResult.processOutput());
		System.out.println("~~~~~~~~~~~~~~");
	}
}
