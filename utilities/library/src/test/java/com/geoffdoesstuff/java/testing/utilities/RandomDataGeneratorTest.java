package com.geoffdoesstuff.java.testing.utilities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ASCII is single byte encoding, where the first 127 characters are standard and the remaining 128 vary by encoding,
 * where ISO-8859-1 (aka "ISO Latin 1") was popular.
 * Unicode is the common standard now and is flexible, UTF-32 uses 4 bytes per character, often inefficient
 * UTF-8 is variable length of bytes, thus it is the most common on the internet.
 * UTF-16 uses a minimum of two bytes, whereas UTF-8 uses a minimum of 1 but both are variable. UTF-8 has advantages
 * over the wire and on disk, UTF-16 has in-memory advantages
 * Charset.defaultCharset().displayName()
 * Java 17 made the default charset UTF-8
 */
class RandomDataGeneratorTest {

	private static final int TEST_CYCLES = 10_000;

	@Test
	void getLocalDateTest() {
		assertNotNull(RandomDataGenerator.getLocalDate());
		for (int i = 0; i < TEST_CYCLES; i++) {
			LocalDate temp = RandomDataGenerator.getLocalDate();
			System.out.println(temp);
		}
	}

	@Test
	void getLocalDateAltTest() {
		assertNotNull(RandomDataGenerator.getLocalDateAlt());
		for (int i = 0; i < TEST_CYCLES; i++) {
			LocalDate temp = RandomDataGenerator.getLocalDateAlt();
			System.out.println(temp);
		}
	}

	@Test
	void getLocalDateAlt2Test() {
		assertNotNull(RandomDataGenerator.getLocalDateAlt2());
		for (int i = 0; i < TEST_CYCLES; i++) {
			LocalDate temp = RandomDataGenerator.getLocalDateAlt2();
			System.out.println(temp);
		}
	}

	@Test
	void getLocalTimeTest() {
		assertNotNull(RandomDataGenerator.getLocalTime(false));
		for (int i = 0; i < TEST_CYCLES; i++) {
			LocalTime temp = RandomDataGenerator.getLocalTime(false);
			System.out.println(temp);
		}
	}

	@Test
	void getLocalTimeTestNano() {
		assertNotNull(RandomDataGenerator.getLocalTime(true));
		for (int i = 0; i < TEST_CYCLES; i++) {
			LocalTime temp = RandomDataGenerator.getLocalTime(true);
			System.out.println(temp);
		}
	}

	@Test
	void getLocalDateTimeTest() {
		assertNotNull(RandomDataGenerator.getLocalDateTime());
		for (int i = 0; i < TEST_CYCLES; i++) {
			LocalDateTime temp = RandomDataGenerator.getLocalDateTime();
			System.out.println(temp);
		}
	}

	@Test
	void getRandomAlphanumericTest() {
		for (int i = 0; i < TEST_CYCLES; i++) {
			System.out.println(RandomDataGenerator.getRandomAlphanumeric(50));
		}
	}

	@Test
	void getRandomPrintableTest() {
		for (int i = 0; i < TEST_CYCLES; i++) {
			System.out.println(RandomDataGenerator.getRandomPrintable(50));
		}
	}

	@Test
	void getRandomUnicode() {
		System.out.println(RandomDataGenerator.getRandomUnicode());
		System.out.println("Unicode System \u00A9");
		System.out.println("\u2705");
		System.out.println("\u5F3A");
	}
}