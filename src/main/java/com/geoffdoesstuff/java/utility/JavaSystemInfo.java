package com.geoffdoesstuff.java.utility;

/**
 * Java System Info support class
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

	/**
	 * Check if we are on Java 8 or higher, returning true if we are
	 * @return boolean
	 */
	public static boolean isAtLeastJava8() {
		return (System.getProperty("java.version", "0.0").compareTo("1.8") >= 0);
	}

	/**
	 * Check if we are on Java 11 or higher, returning true if we are
	 * @return boolean
	 */
	public static boolean isAtLeastJava11() {
		return (System.getProperty("java.version", "0.0").compareTo("11") >= 0);
	}

	/**
	 * Check if we are on Java 15 or higher, returning true if we are
	 * @return boolean
	 */
	public static boolean isAtLeastJava15() {
		return (System.getProperty("java.version", "0.0").compareTo("15") >= 0);
	}

	/**
	 * Check if we are on Java 16 or higher, returning true if we are
	 * @return boolean
	 */
	public static boolean isAtLeastJava16() {
		return (System.getProperty("java.version", "0.0").compareTo("16") >= 0);
	}

	/**
	 * Check if we are on Java 17 or higher, returning true if we are
	 * @return boolean
	 */
	public static boolean isAtLeastJava17() {
		return (System.getProperty("java.version", "0.0").compareTo("17") >= 0);
	}

	/**
	 * Check if we are on Java 18 or higher, returning true if we are
	 * @return boolean
	 */
	public static boolean isAtLeastJava18() {
		return (System.getProperty("java.version", "0.0").compareTo("18") >= 0);
	}

	/**
	 * Output the Java Version demo
	 */
	public static void javaVersionDemo() {
		Runtime.Version runtimeVersion = Runtime.version();
		System.out.println(runtimeVersion);
		System.out.println("Major Version: " + Runtime.version().version().get(0));
        System.out.println("Minor Version: " + ((runtimeVersion.version().size() > 1) ? runtimeVersion.version().get(1) : 0));
        System.out.println("Security Version: " + ((runtimeVersion.version().size() > 2) ? runtimeVersion.version().get(2) : 0));
		System.out.println("Build Version: " + Runtime.version().build().orElse(0));
	}

	private static String getPropertyOutput(String strProperty) {
		return strProperty.replaceAll("[.]", " ") + ": " + System.getProperty(strProperty, strProperty + " was not found");
	}
}
