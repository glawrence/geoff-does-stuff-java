package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ADUtilsTest {

    @Test
    void convertMsFileTimeStampToDate() {
        String timestamp = "134172635085501632"; // 09:38:28 6-Mar-2026
        long longTimestamp = Long.parseLong(timestamp);

        ZonedDateTime actualZonedDateTime = ADUtils.convertMsFileTimeStampToDate(longTimestamp);

        assertEquals("2026-03-06T09:38:28.550163200Z[UTC]", actualZonedDateTime.toString());
        assertEquals(
                ZonedDateTime.of(2026, 3, 6, 9, 38, 28, 550163200, ZoneId.of("UTC")),
                actualZonedDateTime);

        longTimestamp = Long.parseLong("134423611700000000");
        actualZonedDateTime = ADUtils.convertMsFileTimeStampToDate(longTimestamp);

        assertEquals("2026-12-21T21:12:50Z[UTC]", actualZonedDateTime.toString());
        assertEquals(
                ZonedDateTime.of(2026, 12, 21, 21, 12, 50, 0, ZoneId.of("UTC")),
                actualZonedDateTime);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"13417263508550163299", "134172635085501632aa"})
    void convertMsFileTimeStampToDate_failures(String testInput) {
        assertThrows(NumberFormatException.class, () -> Long.parseLong(testInput));
    }
}
