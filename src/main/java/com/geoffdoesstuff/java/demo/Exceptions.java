package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.ExceptionHandling;

import java.util.Arrays;

/**
 * Demonstrate the capturing of a stack trace as a String
 */
public class Exceptions {

	/**
	 * Main method, for independent running or use via Demo Menu
	 * @param args command line
	 */
	public static void main(String[] args) {
		String empty = null;
		printStackTraceToStdErr(empty);
		getStackTraceAsString(empty);
	}

	private static void printStackTraceToStdErr(String empty) {
		try {
			System.out.println("Use normal printStackTrace() method to write the stack trace to stderr");
			System.out.println(empty.toUpperCase());
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}

	private static void getStackTraceAsString(String empty) {
		try {
			System.out.println("Convert the stack trace to one, or many Strings, to then use and manipulate");
			System.out.println(empty.toUpperCase());
		} catch (NullPointerException npe) {
			System.out.println("Single String");
			System.out.println("=============");
			System.out.println(ExceptionHandling.exceptionStackTraceAsString(npe));
			System.out.println("Single line String");
			System.out.println("==================");
			System.out.println(ExceptionHandling.exceptionStackTraceAsStringLine(npe));
			System.out.println("All Lines (Array)");
			System.out.println("=================");
			Arrays.stream(ExceptionHandling.exceptionStackTraceAsStringArray(npe)).forEach(System.out::println);
			System.out.println("All Lines (List)");
			System.out.println("================");
			ExceptionHandling.exceptionStackTraceAsStringList(npe).forEach(System.out::println);
		}
	}
}
