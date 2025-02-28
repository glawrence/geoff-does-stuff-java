package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.DemoUtilities;
import com.geoffdoesstuff.java.utility.JavaSystemInfo;

import java.io.IOException;
import java.util.Optional;

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

		Optional<String> executionResult = runtimeExecute(commandLine);
		if (executionResult.isEmpty()) {
			System.out.println("The host '" + hostname + "' is responding to ping");
		} else {
			System.out.println("The host '" + hostname + "' is not responding to ping, probably offline or not responding");
			System.out.println("  " + executionResult.get());
		}
		executionResult = processBuilder(commandLineArray);
		if (executionResult.isEmpty()) {
			System.out.println("The host '" + hostname + "' is responding to ping");
		} else {
			System.out.println("The host '" + hostname + "' is not responding to ping, probably offline or not responding");
			System.out.println("  " + executionResult.get());
		}
	}

	private static Optional<String> runtimeExecute(String commandLine) {
		try {
			Process proc = Runtime.getRuntime().exec(commandLine);
			int result = proc.waitFor();
			return Optional.ofNullable(displayExecutionResult(result));
		} catch (IOException | InterruptedException e) {
			System.out.println("ERROR in runtimeExecute: " + e);
			throw new RuntimeException(e);
		}
	}

	private static Optional<String> processBuilder(String... commandLine) {
		try {
			Process process = new ProcessBuilder(commandLine).start();
			int result = process.waitFor();
			return Optional.ofNullable(displayExecutionResult(result));
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
