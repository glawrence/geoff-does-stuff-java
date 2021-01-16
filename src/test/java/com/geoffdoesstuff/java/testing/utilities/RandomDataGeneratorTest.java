package com.geoffdoesstuff.java.testing.utilities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

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
}