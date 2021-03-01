package com.geoffdoesstuff.java.utility;

/**
 * @author Geoff Lawrence
 */
public class JavaSystemInfo {

	/**
	 * Write some basic JVM version and vendor information to standard out
	 * <p>
	 * There are a number of system properties that are useful to know about when trying to understand the details of
	 * the JVM that is currently being used, this method write some of the most useful to standard out
	 */
	public static void outputJavaInfo() {
		System.out.println(getPropertyOutput("java.vm.vendor"));
		System.out.println(getPropertyOutput("java.vm.name"));
		System.out.println(getPropertyOutput("java.vm.info"));
		System.out.println(getPropertyOutput("java.version"));
		System.out.println(getPropertyOutput("java.runtime.name"));
		System.out.println(getPropertyOutput("java.runtime.version"));
		System.out.println(getPropertyOutput("java.home"));
	}

	private static String getPropertyOutput(String strProperty) {
		return strProperty.replaceAll("[.]", " ") + ": " + System.getProperty(strProperty, strProperty + " was not found");
	}
}
