package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("Test convertToRepeatedCharacter, char version")
    @Test
    void convertToRepeatedCharacter_char_Test() {
        assertEquals("~~~~~", TextUtilities.convertToRepeatedCharacter("Hello", '~'));
        assertEquals("!!!!!", TextUtilities.convertToRepeatedCharacter("Hello", '!'));

        assertThrows(IllegalArgumentException.class, () -> TextUtilities.convertToRepeatedCharacter(null, '~'));
        assertDoesNotThrow(() -> TextUtilities.convertToRepeatedCharacter("null", (char) 0));
        assertDoesNotThrow(() -> TextUtilities.convertToRepeatedCharacter("null", (char) 9));
    }

    @DisplayName("Test convertToRepeatedCharacter, String version")
    @Test
    void convertToRepeatedCharacter_string_Test() {
        assertEquals("~~~~~", TextUtilities.convertToRepeatedString("Hello", "~"));
        assertEquals("!!!!!", TextUtilities.convertToRepeatedString("Hello", "!"));

        assertThrows(IllegalArgumentException.class, () -> TextUtilities.convertToRepeatedString(null, "~"));
        assertThrows(IllegalArgumentException.class, () -> TextUtilities.convertToRepeatedString("Hello", null));
        assertThrows(IllegalArgumentException.class, () -> TextUtilities.convertToRepeatedString("Hello", ""));
        assertThrows(IllegalArgumentException.class, () -> TextUtilities.convertToRepeatedString("Hello", "~-"));
    }

    @DisplayName("Test convertToRepeatedCharacter char version against String version")
    @Test
    void convertToRepeatedCharacter_comparison_Test() {
        assertEquals(TextUtilities.convertToRepeatedCharacter("Hello World", '='), TextUtilities.convertToRepeatedString("Hello World", "="));
        assertEquals(TextUtilities.convertToRepeatedCharacter("Hello World", '#'), TextUtilities.convertToRepeatedString("Hello World", "#"));
    }
}