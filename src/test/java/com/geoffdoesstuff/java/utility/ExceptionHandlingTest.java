package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExceptionHandlingTest {
	private ByteArrayOutputStream byteArrayOutputStream;
	private PrintStream originalStdOut;
	private PrintStream originalStdErr;

	/**
	 * Here we capture the normal StdOut and StdErr and redirect both to a ByteArrayStream so that we can query it later.
	 */
	@BeforeEach
	void setup() {
		byteArrayOutputStream = new ByteArrayOutputStream();
		originalStdOut = System.out;
		originalStdErr = System.err;
		System.setOut(new PrintStream(byteArrayOutputStream));
		System.setErr(new PrintStream(byteArrayOutputStream));
	}

	/**
	 * Put the original StdOut and StdErr back, so other tests are not impacted.
	 */
	@AfterEach
	void tearDown() {
		System.setOut(originalStdOut);
		System.setErr(originalStdErr);
	}

	@Test
	void testGetStackTraceNull() {
		ExceptionHandling.getStackTrace(null);
		String outputStr = byteArrayOutputStream.toString();
		assertTrue(outputStr.contains("The stack trace.."));
		assertTrue(outputStr.contains(NullPointerException.class.getName()));
	}

	@Test
	void testGetStackTraceNotNull() {
		ExceptionHandling.getStackTrace("");
		String outputStr = byteArrayOutputStream.toString();
		assertFalse(outputStr.contains("The stack trace.."));
		assertFalse(outputStr.contains(NullPointerException.class.getName()));
	}

	@Test
	void testPrintStackTraceNull() {
		ExceptionHandling.printStackTraceToStdErr(null);
		String outputStr = byteArrayOutputStream.toString();
		assertTrue(outputStr.contains(NullPointerException.class.getName()));
	}

	@Test
	void testPrintStackTraceNotNull() {
		ExceptionHandling.printStackTraceToStdErr("");
		String outputStr = byteArrayOutputStream.toString();
		assertFalse(outputStr.contains(NullPointerException.class.getName()));
	}
}
