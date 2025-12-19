package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilitiesTest {

    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate TOMORROW = TODAY.plusDays(1);
    private static final LocalDate YESTERDAY = TODAY.minusDays(1);

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

    @Test
    void afterOrEqual() {
        assertTrue(DateTimeUtilities.afterOrEqual(TODAY, TODAY));
        assertFalse(DateTimeUtilities.afterOrEqual(TODAY, YESTERDAY));
        assertTrue(DateTimeUtilities.afterOrEqual(TODAY, TOMORROW));
    }

    @Test
    void before() {
        assertFalse(DateTimeUtilities.before(TODAY, TODAY));
        assertTrue(DateTimeUtilities.before(TODAY, YESTERDAY));
        assertFalse(DateTimeUtilities.before(TODAY, TOMORROW));
    }

    @Test
    void beforeOrEqual() {
        assertTrue(DateTimeUtilities.beforeOrEqual(TODAY, TODAY));
        assertTrue(DateTimeUtilities.beforeOrEqual(TODAY, YESTERDAY));
        assertFalse(DateTimeUtilities.beforeOrEqual(TODAY, TOMORROW));
    }

    @Test
    void after() {
        assertFalse(DateTimeUtilities.after(TODAY, TODAY));
        assertFalse(DateTimeUtilities.after(TODAY, YESTERDAY));
        assertTrue(DateTimeUtilities.after(TODAY, TOMORROW));
    }

    @Test
    void beforeAlt() {
        assertFalse(DateTimeUtilities.beforeAlt(TODAY, TODAY));
        assertTrue(DateTimeUtilities.beforeAlt(TODAY, YESTERDAY));
        assertFalse(DateTimeUtilities.beforeAlt(TODAY, TOMORROW));
    }
}