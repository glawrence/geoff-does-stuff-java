package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.demo.datesandtimes.FormattingDatesAndTimes;
import com.geoffdoesstuff.java.utility.DateTimeUtilities;
import com.geoffdoesstuff.java.utility.DemoUtilities;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Date and Time demo.
 */
public class DatesAndTimes {

	/**
	 * This is here to suppress Javadoc complaining about not commenting the default constructor
	 */
	private DatesAndTimes() {
	}

	/**
	 * Main method, for independent running or use via Demo Menu
	 * @param args command line
	 */
	public static void main(String[] args) {
		DatesAndTimes datesAndTimes = new DatesAndTimes();
		DemoUtilities.outputTitle("General Demo");
		datesAndTimes.generalDemo();
		DemoUtilities.outputTitle("Calculations Demo");
		datesAndTimes.dateTimeCalculations();
		DemoUtilities.outputTitle("Formatting Demo");
		datesAndTimes.formatNow();
		DemoUtilities.outputTitle("Copied Formatting Demo", true);
		datesAndTimes.formattingExtra();
		DemoUtilities.outputTitle("Unix Timestamps");
		datesAndTimes.unixTimestamps();
		DemoUtilities.outputTitle("Comparison");
		datesAndTimes.dateComparisons();
		DemoUtilities.outputTitle("Durations");
		datesAndTimes.durationDemo();
		DemoUtilities.outputTitle("Parsing");
		datesAndTimes.parsingDemo();
	}

	/**
	 * The code for this seemed useful, so has been moved to the Utilities Library
	 */
    private void parsingDemo() {
		List<String> inputDateStrings = List.of("2021-12-21", "21 12 2021", "21-12-2021",
				"21 December 2021", "21 Dec 2021", "21-Dec-2021", "2021-13-A", "");

		inputDateStrings.forEach(input -> {
			if (DateTimeUtilities.isValidLocalDate(input)) {
				System.out.printf("Parsing %s into LocalDate gives %s%n", input, DateTimeUtilities.parseLocalDate(input));
			} else {
				System.out.printf("Parsing %s into LocalDate fails, it is not a valid date%n", input);
			}
		});
	}

	/**
	 *   1,000 nanoseconds = 1 microsecond
	 *   1,000 microseconds = 1 millisecond
	 *   1,000 milliseconds = 1 second
	 *   1 second = 1,000 milliseconds
	 *   1 second = 1,000,000 microseconds
	 *   1 second = 1,000,000,000 nanoseconds
	 */
	private void durationDemo() {
		Duration duration = Duration.ZERO;
		System.out.println("Duration, ZERO: " + duration);
		duration = Duration.ofSeconds(10);
		System.out.println("Duration, 10 seconds: " + duration);
		duration = Duration.ofMillis(10);
		System.out.println("Duration, 10 milliseconds: " + duration);
		duration = Duration.ofMinutes(10);
		System.out.println("Duration, 10 minutes: " + duration);
		duration = Duration.ofSeconds(1);
		System.out.println("Duration, 1 second: " + duration);
		duration = Duration.ofMillis(1000);
		System.out.println("Duration, 1 second: " + duration);
		duration = Duration.ofNanos(1_000_000_000);
		System.out.println("Duration, 1 second: " + duration);
		duration = Duration.ofMillis(1);
		System.out.println("Duration, 1 millisecond: " + duration);
		duration = Duration.ofNanos(1_000_000);
		System.out.println("Duration, 1 millisecond: " + duration);
		duration = Duration.ofNanos(1_000);
		System.out.println("Duration, 1 microsecond: " + duration);
		duration = Duration.ofNanos(1);
		System.out.println("Duration, 1 nanosecond : " + duration);
	}

	private void dateComparisons() {
		LocalDate today = LocalDate.now();
		LocalDate laterToday = LocalDate.now();
		LocalDate yesterday = LocalDate.now().minusDays(1);
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		System.out.println("Compare " + today.isAfter(laterToday));
		System.out.println("Compare " + today.isBefore(laterToday));
		System.out.println("Compare " + today.isEqual(laterToday));
		DemoUtilities.outputTitle("afterOrEqual", true);
		System.out.println("After or Equal " + DateTimeUtilities.afterOrEqual(today, laterToday) + String.format(", from %s to %s", today, laterToday));
		System.out.println("After or Equal " + DateTimeUtilities.afterOrEqual(today, yesterday) + String.format(", from %s to %s", today, yesterday));
		System.out.println("After or Equal " + DateTimeUtilities.afterOrEqual(today, tomorrow) + String.format(", from %s to %s", today, tomorrow));
		DemoUtilities.outputTitle("before", true);
		System.out.println("After or Equal " + DateTimeUtilities.before(today, laterToday) + String.format(", from %s to %s", today, laterToday));
		System.out.println("After or Equal " + DateTimeUtilities.before(today, yesterday) + String.format(", from %s to %s", today, yesterday));
		System.out.println("After or Equal " + DateTimeUtilities.before(today, tomorrow) + String.format(", from %s to %s", today, tomorrow));
		DemoUtilities.outputTitle("beforeAlt", true);
		System.out.println("After or Equal " + DateTimeUtilities.beforeAlt(today, laterToday) + String.format(", from %s to %s", today, laterToday));
		System.out.println("After or Equal " + DateTimeUtilities.beforeAlt(today, yesterday) + String.format(", from %s to %s", today, yesterday));
		System.out.println("After or Equal " + DateTimeUtilities.beforeAlt(today, tomorrow) + String.format(", from %s to %s", today, tomorrow));
	}


	private void unixTimestamps() {
		LocalDateTime current = LocalDateTime.now();
		ZonedDateTime currentZnd = ZonedDateTime.now();
		Instant currentInst = currentZnd.toInstant();
		System.out.println("Second: " + currentInst.getEpochSecond());
		System.out.println("Milli: " + currentInst.toEpochMilli());
		System.out.println("Nano: " + currentInst.getNano());
		System.out.println("Instant Now: " + Instant.now().toString());
		Clock clock = Clock.systemUTC();
		System.out.println(clock);
		System.out.println(clock.instant());
		System.out.println(clock.instant().getEpochSecond());
	}

	private void dateTimeCalculations() {
		LocalDateTime endOf2020 = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
		Duration diff = Duration.between(endOf2020, LocalDateTime.now());
		System.out.println("Duration between end of 2020 and now: " + diff);
		diff = Duration.between(LocalDateTime.now(), endOf2020);
		System.out.println("Duration between now and end of 2020: " + diff);
		System.out.println("MIN LocalDateTime: " + LocalDateTime.MIN);
		System.out.println("MAX LocalDateTime: " + LocalDateTime.MAX);
	}

	private void generalDemo() {
		Instant now = Instant.now();
		System.out.println("Instant.now()              : " + now);
		LocalDateTime gjl = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
		System.out.println("Now in current timezone    : " + gjl);
		gjl = LocalDateTime.ofInstant(now, ZoneId.of("CET"));
		System.out.println("Now in Europe (CET)        : " + gjl);
		gjl = LocalDateTime.ofInstant(now, ZoneId.of("Asia/Kolkata"));
		System.out.println("Now in India (IST)         : " + gjl);
		gjl = LocalDateTime.ofInstant(now, ZoneId.of("Australia/Canberra"));
		System.out.println("Now in Canberra, Australia : " + gjl);
		gjl = LocalDateTime.ofInstant(now, ZoneId.of("America/New_York"));
		System.out.println("Now in New York, USA       : " + gjl);
	}

	private void formatNow() {
		FormattingDatesAndTimes formattingDatesAndTimes = new FormattingDatesAndTimes();
		formattingDatesAndTimes.formattingDemo();
		formattingDatesAndTimes.outputDateTime();
	}

	private void formattingExtra() {
		System.out.println(getCurrentDateTime(""));
		System.out.println(getCurrentDateTime("E MMM d HH:mm:ss yyyy O"));
		System.out.println(getCurrentDateTime("E MMM d HH:mm:ss yyyy z zzzz"));
		System.out.println(getCurrentDateTime("'The' DD 'day is' dd/MM/YYYY HH:mm:ss"));
		System.out.println(getCurrentDateTime(DateTimeFormatter.RFC_1123_DATE_TIME));
		System.out.println(getCurrentDateTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		System.out.println(getCurrentDateTime(DateTimeFormatter.ISO_ZONED_DATE_TIME));
	}

	private static String getCurrentDateTime(DateTimeFormatter dateTimeFormatter) {
		System.out.println(dateTimeFormatter.toFormat());
		String result = ZonedDateTime.now().format(dateTimeFormatter);
		result += System.lineSeparator();
		ZonedDateTime utc;
		utc = ZonedDateTime.now(ZoneId.of("UTC"));
		result += " (UTC) " + utc.format(dateTimeFormatter);
		return result;
	}

	private static String getCurrentDateTime(String format) {
		if (format.equals("")) {
			// this closely matches ctime from Python
			format = "E MMM d HH:mm:ss yyyy";
		}
		return getCurrentDateTime(DateTimeFormatter.ofPattern(format));
	}
}
