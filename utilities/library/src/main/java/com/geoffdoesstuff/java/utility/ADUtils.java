package com.geoffdoesstuff.java.utility;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Class to support the use of Microsoft Active Directory (AD)
 */
public class ADUtils {

    private static final Instant MICROSOFT_ZERO_DATE = Instant.parse("1601-01-01T00:00:00Z");
    private static final ZoneId UTC_TIMEZONE = ZoneId.of("UTC");

    /**
     * In AD a timestamp is the "number of 100 nanosecond intervals since 1st January 1601", so this conversion
     * needs a little calculation
     * @param msFileTimestamp the number of 100 nanosecond intervals
     * @return a ZonedDateTime in UTC
     */
    public static ZonedDateTime convertMsFileTimeStampToDate(long msFileTimestamp) {
        Instant instant = toInstant(msFileTimestamp);
        return ZonedDateTime.ofInstant(instant, UTC_TIMEZONE);
    }

    /**
     * Take the Microsoft Timestamp and convert it to a Java Instant, both of which are UTC. One approach would be to
     * multiply the input by 100 to get the number of nanoseconds, but this is then too big for a long. This
     * method divides by 10 to get the microseconds and then uses a mathematical mod of 10 which is small enough
     * to multiply by 100 to get the number of nanoseconds. Next a duration of the microseconds has the number of
     * nanoseconds added to it, and finally we add this duration to the Zero Date.
     * There are 116444736000000000 x 100 nanoseconds between 1-Jan-1601 and 1-Jan-1970
     * @param msFileTimestamp number of 100 nanosecond intervals since 1-Jan-1601
     * @return the Instance of msFileTimestamp
     */
    private static Instant toInstant(long msFileTimestamp) {
        // Note: type long is not big enough to handle (msFileTimestamp * 100) to get to number of nanoseconds
        long microseconds = msFileTimestamp / 10; // 1000 nanoseconds in a microsecond
        long additionalNanos = (msFileTimestamp % 10) * 100;
        Duration duration = Duration
                .of(microseconds, ChronoUnit.MICROS)
                .plus(additionalNanos, ChronoUnit.NANOS);
        return MICROSOFT_ZERO_DATE.plus(duration);
    }
}
