package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

class JavaSystemInfoTest {

	@Test
	@DisplayName("Just make sure outputJavaInfo() does not throw an exception")
	void outputJavaInfoTest() {
		// using a method reference and asserting nothing goes wrong
		assertDoesNotThrow(JavaSystemInfo::outputJavaInfo);
	}

	@Test
	@DisplayName("Test the actual isAtLeastJavaXX() code")
	void isAtLeastJavaXXTest() {
		assertTrue(JavaSystemInfo.isAtLeastJava8());
		assertTrue(JavaSystemInfo.isAtLeastJava11());
		assertTrue(JavaSystemInfo.isAtLeastJava15());
		assertTrue(JavaSystemInfo.isAtLeastJava16());
		assertTrue(JavaSystemInfo.isAtLeastJava17());
		assertTrue(JavaSystemInfo.isAtLeastJava18());
		assertTrue(JavaSystemInfo.isAtLeastJava19());
		assertTrue(JavaSystemInfo.isAtLeastJava20());
		assertTrue(JavaSystemInfo.isAtLeastJava21());
		assertTrue(JavaSystemInfo.isAtLeastJava22());
		assertTrue(JavaSystemInfo.isAtLeastJava23());
		assertTrue(JavaSystemInfo.isAtLeastJava24());
		assertTrue(JavaSystemInfo.isAtLeastJava25());
		assertFalse(JavaSystemInfo.isAtLeastJava26());
	}

	@Test
	@DisplayName("Test the way isAtLeastJavaXX() works")
	void isAtLeastJava11Test() {
		final String ELEVEN = "11";
		String javaVersion;

		javaVersion = "1.8.0_202"; //this is Java 8
		assertFalse(javaVersion.compareTo(ELEVEN) >= 0);
		javaVersion = "10.1.7";
		assertFalse(javaVersion.compareTo(ELEVEN) >= 0);

		javaVersion = "11";
		assertTrue(javaVersion.compareTo(ELEVEN) >= 0);
		javaVersion = "11.0";
		assertTrue(javaVersion.compareTo(ELEVEN) >= 0);
		javaVersion = "11.0.1";
		assertTrue(javaVersion.compareTo(ELEVEN) >= 0);
		javaVersion = "15.0.2";
		assertTrue(javaVersion.compareTo(ELEVEN) >= 0);
		javaVersion = "15.0";
		assertTrue(javaVersion.compareTo(ELEVEN) >= 0);
		javaVersion = "16.0";
		assertTrue(javaVersion.compareTo(ELEVEN) >= 0);
	}

	@Test
	@DisplayName("Just make sure outputPlatformInfo() does not throw an exception")
	void outputPlatformInfoTest() {
		// using a method reference and asserting nothing goes wrong
		assertDoesNotThrow(JavaSystemInfo::outputPlatformInfo);
	}

	@Test
	@EnabledOnOs(OS.WINDOWS)
	void testOnWindows() {
		assertTrue(JavaSystemInfo.isPlatformWindows());
		assertFalse(JavaSystemInfo.isPlatformMacOS());
		assertFalse(JavaSystemInfo.isPlatformLinux());
		assertFalse(JavaSystemInfo.isPlatformOther());
	}

	@Test
	@EnabledOnOs(OS.MAC)
	void testOnMacOS() {
		assertFalse(JavaSystemInfo.isPlatformWindows());
		assertTrue(JavaSystemInfo.isPlatformMacOS());
		assertFalse(JavaSystemInfo.isPlatformLinux());
		assertFalse(JavaSystemInfo.isPlatformOther());
	}
}