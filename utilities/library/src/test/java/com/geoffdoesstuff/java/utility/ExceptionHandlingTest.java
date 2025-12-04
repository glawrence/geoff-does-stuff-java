package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionHandlingTest {

	@Test
	@DisplayName("Test we get back a String of reasonable size for a stack trace")
	void testExceptionStackTraceAsString() {
		String stackTrace = ExceptionHandling.exceptionStackTraceAsString(new NullPointerException("The stack trace NPE..."));
		assertNotNull(stackTrace);
		assertTrue(stackTrace.contains("The stack trace NPE..."));
		assertTrue(stackTrace.length() > 8000); // check there are lots of characters
		assertTrue(stackTrace.split("[\n]").length > 80); // check there are lots of lines
	}

	@Test
	@DisplayName("Test we get back a String that is equivalent to the first test, and of suitable size")
	void testExceptionStackTraceAsStringLine() {
		NullPointerException npe = new NullPointerException("The stack trace NPE...");
		String stackTraceSingleLine = ExceptionHandling.exceptionStackTraceAsStringLine(npe);
		String stackTraceMultiLine = ExceptionHandling.exceptionStackTraceAsString(npe);
		assertNotNull(stackTraceSingleLine);
		assertNotNull(stackTraceMultiLine);
		assertEquals(stackTraceMultiLine, stackTraceSingleLine.translateEscapes());
		assertTrue(stackTraceSingleLine.contains("The stack trace NPE..."));
		assertTrue(stackTraceSingleLine.length() > 8000); // check there are lots of characters
		assertTrue(stackTraceSingleLine.split("\\\\n").length > 80); // check there are lots of new line markers
	}

	@Test
	@DisplayName("Test the Array version works as expected")
	void testExceptionStackTraceAsStringArray() {
		String[] stackTrace = ExceptionHandling.exceptionStackTraceAsStringArray(new NullPointerException("The stack trace NPE..."));
		assertNotNull(stackTrace);
		assertTrue(stackTrace[0].contains("The stack trace NPE..."));
		assertTrue(Arrays.stream(stackTrace).mapToInt(String::length).sum() > 8000); // check there are lots of characters
		assertTrue(stackTrace.length > 80); // check there are lots of lines
	}

	@Test
	@DisplayName("Test the List version works as expected")
	void testExceptionStackTraceAsStringList() {
		List<String> stackTrace = ExceptionHandling.exceptionStackTraceAsStringList(new NullPointerException("The stack trace NPE..."));
		assertNotNull(stackTrace);
		assertTrue(stackTrace.get(0).contains("The stack trace NPE..."));
		assertTrue(stackTrace.stream().mapToInt(String::length).sum() > 8000);
		assertTrue(stackTrace.size() > 80); // check there are lots of lines
	}
}
