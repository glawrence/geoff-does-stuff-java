package com.geoffdoesstuff.java.data.objects;

import java.time.LocalDate;

/**
 * We can implement the Comparable interface, if necessary.
 * Canonical constructors are always called with deserialization, which is unlike classes, making records better
 * for serialization/deserialization scenarios.
 * @param destination the holiday destination
 * @param fromDate start date
 * @param toDate end date
 * @param cost price
 */
public record HolidayRecord(String destination, LocalDate fromDate, LocalDate toDate, int cost) {

    /**
     * This is a compact constructor, where the compiler does the initialisation for us, like a
     * constructor-less record, but we can add validation
     * @param destination the holiday destination
     * @param fromDate start date
     * @param toDate end date
     * @param cost price
     */
    public HolidayRecord {
        if (cost < 0) {
            throw new IllegalArgumentException("Negative cost is not permitted");
        }
    }
}
