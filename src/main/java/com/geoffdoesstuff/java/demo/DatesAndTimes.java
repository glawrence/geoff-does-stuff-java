package com.geoffdoesstuff.java.demo;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DatesAndTimes {

	public static void main(String[] args) {
		DatesAndTimes datesAndTimes = new DatesAndTimes();
		datesAndTimes.demo();
	}

	public void demo() {
		LocalDateTime endOf2020 = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
		Duration diff = Duration.between(endOf2020, LocalDateTime.now());
		System.out.println("Duration between end of 2020 and now: " + diff);
		diff = Duration.between(LocalDateTime.now(), endOf2020);
		System.out.println("Duration between now and end of 2020: " + diff);
		Instant now = Instant.now();
		System.out.println("Instant.now() - " + now);
		LocalDateTime gjl = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
		System.out.println("Now in current timezone: " + gjl);
		gjl = LocalDateTime.ofInstant(now, ZoneId.of("CET"));
		System.out.println("Now in Europe (CET)    : " + gjl);
		gjl = LocalDateTime.ofInstant(now, ZoneId.of("Asia/Kolkata"));
		System.out.println("Now in India (IST)     : " + gjl);

		System.out.println("MIN LocalDateTime: " + LocalDateTime.MIN);
		System.out.println("MAX LocalDateTime: " + LocalDateTime.MAX);
	}
}
