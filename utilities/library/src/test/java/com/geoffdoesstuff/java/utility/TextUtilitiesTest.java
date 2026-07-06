package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.Assertions;
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

    @DisplayName("Test isNotBlank() with text")
    @ParameterizedTest
    @ValueSource(strings = {"a", " a ", " \t\t a\n \t"})
    void isNotBlank_True(String text) {
        System.out.printf("Testing with [%s]%n", text);
        assertTrue(TextUtilities.isNotBlank(text));
    }

    @DisplayName("Test isNotBlank() with null or empty strings")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", " \t\t \n \t"})
    void isNotBlank_False(String text) {
        System.out.printf("Testing with [%s]%n", text);
        assertFalse(TextUtilities.isNotBlank(text));
    }

    @DisplayName("Test convertToRepeatedCharacter, char version")
    @Test
    void convertToRepeatedCharacter_char_Test() {
        assertEquals("~~~~~", TextUtilities.convertToRepeatedCharacter("Hello", '~'));
        assertEquals("!!!!!", TextUtilities.convertToRepeatedCharacter("Hello", '!'));
        assertEquals("-------", TextUtilities.convertToRepeatedCharacter("A\nB\nC\tD", '-')); //macoS/Linux
        assertEquals("---------", TextUtilities.convertToRepeatedCharacter("A\r\nB\r\nC\tD", '-')); //Windows

        assertThrows(IllegalArgumentException.class, () -> TextUtilities.convertToRepeatedCharacter(null, '~'));
        assertThrows(IllegalArgumentException.class, () -> TextUtilities.convertToRepeatedCharacter("Hello", null));
        Assertions.assertDoesNotThrow(() -> TextUtilities.convertToRepeatedCharacter("null", (char) 0));
        Assertions.assertDoesNotThrow(() -> TextUtilities.convertToRepeatedCharacter("null", (char) 9));
    }

    @DisplayName("Test convertToRepeatedCharacter, String version")
    @Test
    void convertToRepeatedCharacter_string_Test() {
        assertEquals("~~~~~", TextUtilities.convertToRepeatedString("Hello", "~"));
        assertEquals("!!!!!", TextUtilities.convertToRepeatedString("Hello", "!"));
        assertEquals("-\n-\n---", TextUtilities.convertToRepeatedString("A\nB\nC\tD", "-")); //macoS/Linux
        assertEquals("-\r\n-\r\n---", TextUtilities.convertToRepeatedString("A\r\nB\r\nC\tD", "-")); //Windows

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

    @DisplayName("Test convertToSpaces")
    @Test
    void convertToSpaces_Test() {
        assertEquals("           ", TextUtilities.convertToSpaces("Hello World"));
    }

    @DisplayName("Test convertToHyphens")
    @Test
    void convertToHyphens_Test() {
        assertEquals("-----------", TextUtilities.convertToHyphens("Hello World"));
    }

    @DisplayName("Test getNumberWithWord produces the right output")
    @Test
    void getNumberWithWord_Test() {
        assertEquals("0 files", TextUtilities.getNumberWithWord(0, "file"));
        assertEquals("1 file", TextUtilities.getNumberWithWord(1, "file"));
        assertEquals("2 files", TextUtilities.getNumberWithWord(2, "file"));
        assertEquals("128 files", TextUtilities.getNumberWithWord(128, "file"));
        assertEquals("0 apples", TextUtilities.getNumberWithWord(0, "apple"));
        assertEquals("1 warning", TextUtilities.getNumberWithWord(1, "warning"));
        assertEquals("2 errors", TextUtilities.getNumberWithWord(2, "error"));
        assertEquals("128 albums", TextUtilities.getNumberWithWord(128, "album"));
    }

    @DisplayName("Test getNumberWithWord_alt produces the same output as getNumberWithWord")
    @Test
    void getNumberWithWord_alt_Test() {
        assertEquals(TextUtilities.getNumberWithWord(0, "file"), TextUtilities.getNumberWithWord_alt(0, "file"));
        assertEquals(TextUtilities.getNumberWithWord(1, "file"), TextUtilities.getNumberWithWord_alt(1, "file"));
        assertEquals(TextUtilities.getNumberWithWord(2, "file"), TextUtilities.getNumberWithWord_alt(2, "file"));
        assertEquals(TextUtilities.getNumberWithWord(128, "file"), TextUtilities.getNumberWithWord_alt(128, "file"));
    }
}