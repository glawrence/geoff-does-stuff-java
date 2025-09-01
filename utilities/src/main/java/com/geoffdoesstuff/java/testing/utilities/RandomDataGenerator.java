package com.geoffdoesstuff.java.testing.utilities;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A utility class to generate random data for use in testing. It is worth noting that java.util.Random is an often
 * used random number generator. Here I have used java.util.concurrent.ThreadLocalRandom, the reason is that
 * ThreadLocalRandom is better in a multithreaded context. It is worth noting that an instance of Random is
 * thread-safe. However, neither Random nor ThreadLocalRandom are cryptographically secure, if you are needing a
 * random number in a security sensitive context then you should use java.security.SecureRandom.
 * <p>
 * There are three different LocalDate generators, getLocalDate() is the slowest, taking about 5 times as long as
 * getLocalDateAlt(). The getLocalDateAlt2() version is slightly quicker than getLocalDateAlt() but the margin is
 * very small. All three LocalDate generators return dates in the range 1970 to 2080, for no reason other than to
 * be similar and useful.
 * <p>
 * I have included two String generators so that output can be limited to alphanumeric if needed, however the
 * String generators do only work in ASCII.
 */
public final class RandomDataGenerator {

	private final static ThreadLocalRandom random = ThreadLocalRandom.current();

	/**
	 * The constructor is marked as private as all methods are static.
	 */
	private RandomDataGenerator() {
	}

	/**
	 * This works by working with the 1st of the month, to work out how many days in the month, after
	 * which we can get a random day in the month.
	 * @return a random {@link LocalDate}
	 */
	public static LocalDate getLocalDate() {
		int year = random.nextInt(1970, 2080);
		int month = random.nextInt(12) + 1;
		LocalDate temp = LocalDate.of(year, month, 1);
		int day = random.nextInt(temp.lengthOfMonth()) + 1;
		return LocalDate.of(year, month, day);
	}

	/**
	 * Similar to the first version but this time we use the first day of the year to test if we
	 * are working with a leap year or not.
	 * @return a random {@link LocalDate}
	 */
	public static LocalDate getLocalDateAlt() {
		int year = random.nextInt(1970, 2080);
		LocalDate temp = LocalDate.of(year, 1, 1);
		if (temp.isLeapYear()) {
			return LocalDate.ofYearDay(year, 366);
		} else {
			return LocalDate.ofYearDay(year, 365);
		}
	}

	/**
	 * This is arguably the most efficient technique as only one random number is generated, and then
	 * we only create one LocalDate object. However, I have limited the range of dates.
	 * @return a random {@link LocalDate}
	 */
	public static LocalDate getLocalDateAlt2() {
		int epochDay = random.nextInt(40176); // 1970-01-01 to 2079-12-31
		return LocalDate.ofEpochDay(epochDay);
	}

	/**
	 * Nice and simple generation of a random time of day, where you can add nanoseconds or not. This
	 * is optional as it is not common to use all the granularity nanoseconds give, often people use
	 * milliseconds.
	 * @param withNanoSeconds enable the adding of Nano Seconds
	 * @return a random {@link LocalTime}
	 */
	public static LocalTime getLocalTime(boolean withNanoSeconds) {
		int hour = random.nextInt(24);
		int minute = random.nextInt(60);
		int second = random.nextInt(60);
		return withNanoSeconds ? LocalTime.of(hour, minute, second)
				: LocalTime.of(hour, minute, second, random.nextInt(1_000_000_000));
	}

	/**
	 * The implementation of this could have used LocalDateTime.of(LocalDate, LocalTime) but it is
	 * just a matter of stylistic preference.
	 * @return a random {@link LocalDateTime}
	 */
	public static LocalDateTime getLocalDateTime() {
		return getLocalDateAlt2().atTime(getLocalTime(false));
	}

	/**
	 * Generate a random String of alphanumeric ASCII characters of the specified length. As the
	 * printable characters are not in a single, contiguous block, we do some extra maths to insert
	 * the required gaps, the ASCII values we want are as follows.
	 * 48 -  57 - digits
	 * 65 -  90 - capital letters
	 * 97 - 122 - letters
	 *
	 * @param length specify how long the generated String should be
	 * @return a String of random alphanumeric ASCII characters
	 */
	public static String getRandomAlphanumeric(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(0, 62) + 48; // this gives us 48 to 109
			if (number > 57) { number += 7; } // makes 48 to 57 and 65 to 116
			if (number > 90) { number += 6; } // makes 48 to 57, 65 to 90 and 97 to 122
			sb.append((char) number);
		}
		return sb.toString();
	}

	/**
	 * Generate a random String of printable ASCII characters of the specified length. The printable
	 * characters are in the range 32 to 126 inclusive.
	 *
	 * @param length specify how long the generated String should be
	 * @return a String of random printable ASCII characters
	 */
	public static String getRandomPrintable(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append((char) random.nextInt(32, 127));
		}
		return sb.toString();
	}

	/**
	 * Not implemented
	 * @return String
	 */
	public static String getRandomUnicode() {
		String string = "Unicode System \u00A9";
		byte[] charset = string.getBytes(StandardCharsets.UTF_8);
		string.codePoints().forEach(System.out::println);
		return new String(charset, StandardCharsets.UTF_8);
	}
}
