package com.geoffdoesstuff.java.utility;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHandling {
	public static void main(String[] args) {
		String empty = null;
		printStackTraceToStdErr(empty);
		getStackTrace(empty);
	}

	protected static void printStackTraceToStdErr(String empty) {
		try {
			System.out.println(empty.toUpperCase());
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}

	protected static void getStackTrace(String empty) {
		try {
			System.out.println(empty.toUpperCase());
		} catch (NullPointerException npe) {
			StringWriter error = new StringWriter();
			npe.printStackTrace(new PrintWriter(error));
			System.out.println("The stack trace....." + System.lineSeparator() + error.toString());
		}
	}
}
