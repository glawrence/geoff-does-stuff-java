package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.demo.datesandtimes.FormattingDatesAndTimes;
import com.geoffdoesstuff.java.utility.DemoUtilities;

import java.time.*;

/**
 * Date and Time demo.
 */
public class DatesAndTimes {

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
		System.out.println("Instant.now() - " + now);
		LocalDateTime gjl = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
		System.out.println("Now in current timezone: " + gjl);
		gjl = LocalDateTime.ofInstant(now, ZoneId.of("CET"));
		System.out.println("Now in Europe (CET)    : " + gjl);
		gjl = LocalDateTime.ofInstant(now, ZoneId.of("Asia/Kolkata"));
		System.out.println("Now in India (IST)     : " + gjl);
	}

	private void formatNow() {
		FormattingDatesAndTimes formattingDatesAndTimes = new FormattingDatesAndTimes();
		formattingDatesAndTimes.formattingDemo();
		formattingDatesAndTimes.outputDateTime();
	}
}
