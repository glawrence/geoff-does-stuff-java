package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilitiesTest {

    @ParameterizedTest
    @ValueSource(strings = {"21-12-2021", "2021-12-21", "21 12 2021", "21 December 2021", "21 Dec 2021", "21-Dec-2021"})
    void parseLocalDate_success(String dateText) {
        assertEquals(LocalDate.of(2021, 12, 21), DateTimeUtilities.parseLocalDate(dateText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"32-12-2021", "2022-12-21", "2a 12 2021", "date", "32-13-2021"})
    @NullAndEmptySource
    void parseLocalDate_fail(String dateText) {
        assertNotEquals(LocalDate.of(2021, 12, 21), DateTimeUtilities.parseLocalDate(dateText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"21-12-2021", "2021-12-21", "21 12 2021", "21 December 2021", "21 Dec 2021", "21-Dec-2021"})
    void isValidLocalDate_true(String dateText) {
        assertTrue(DateTimeUtilities.isValidLocalDate(dateText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"32-12-2021", "2021-21-12", "2a 12 2021", "date", "32-13-2021"})
    @NullAndEmptySource
    void isValidLocalDate_false(String dateText) {
        assertFalse(DateTimeUtilities.isValidLocalDate(dateText));
    }
}