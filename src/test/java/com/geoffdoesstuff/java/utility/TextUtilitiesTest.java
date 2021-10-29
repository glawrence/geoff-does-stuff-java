package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TextUtilitiesTest {

    @DisplayName("Test isNullOrEmpty() with null or empty strings")
    @ParameterizedTest
    @NullAndEmptySource
    void isNullOrEmpty_True(String text) {
        System.out.printf("Testing with [%s]%n", text);
        assertTrue(TextUtilities.isNullOrEmpty(text));
    }

    @DisplayName("Test isNullOrEmpty() with text")
    @ParameterizedTest
    @ValueSource(strings = {"a", " a ", " \t\t a\n \t"})
    void isNullOrEmpty_False(String text) {
        System.out.printf("Testing with [%s]%n", text);
        assertFalse(TextUtilities.isNullOrEmpty(text));
    }

    @DisplayName("Test isNullOrBlank() with null or empty strings")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", " \t\t \n \t"})
    void isNullOrBlank_True(String text) {
        System.out.printf("Testing with [%s]%n", text);
        assertTrue(TextUtilities.isNullOrBlank(text));
    }

    @DisplayName("Test isNullOrBlank() with text")
    @ParameterizedTest
    @ValueSource(strings = {"a", " a ", " \t\t a\n \t"})
    void isNullOrBlank_False(String text) {
        System.out.printf("Testing with [%s]%n", text);
        assertFalse(TextUtilities.isNullOrBlank(text));
    }
}