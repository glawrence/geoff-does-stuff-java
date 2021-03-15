package com.geoffdoesstuff.java.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class to make working with Exceptions easier.
 */
public class ExceptionHandling {

	/**
	 * Convert the Stack Trace to a single string, exactly as printStackTrace() would output it to stderr.
	 * @param exception to be converted
	 * @return String representation
	 */
	public static String exceptionStackTraceAsString(Exception exception) {
		StringWriter error = new StringWriter();
		exception.printStackTrace(new PrintWriter(error));
		return error.toString();
	}

	/**
	 * Convert the Stack Trace to a single string, exactly as printStackTrace(), except that all new lines
	 * are converted to their escaped form and hence converted to a single line.
	 * @param exception to be converted
	 * @return String representation
	 */
	public static String exceptionStackTraceAsStringLine(Exception exception) {
		return exceptionStackTraceAsString(exception).replaceAll("[\n]", "\\\\n")
				.replaceAll("[\r]", "\\\\r");
	}

	/**
	 * Convert the Stack Trace to an array of strings, exactly as printStackTrace() would output it to stderr.
	 * @param exception to be converted
	 * @return String representation
	 */
	public static String[] exceptionStackTraceAsStringArray(Exception exception) {
		return exceptionStackTraceAsString(exception).split("[\n]");
	}

	/**
	 * Convert the Stack Trace to a list of strings, exactly as printStackTrace() would output it to stderr.
	 * @param exception to be converted
	 * @return String representation
	 */
	public static List<String> exceptionStackTraceAsStringList(Exception exception) {
		return Arrays.asList(exceptionStackTraceAsStringArray(exception));
	}
}
