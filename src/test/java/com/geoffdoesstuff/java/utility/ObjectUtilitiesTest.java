package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ObjectUtilitiesTest {

    static List<List<String>> stringList = List.of(List.of("One", "Two", "Three"));

    @Test
    void isNullOrEmpty() {
        Optional<String> stringOptional = null;
        assertTrue(ObjectUtilities.isNullOrEmpty(stringOptional));
        stringOptional = Optional.empty();
        assertTrue(ObjectUtilities.isNullOrEmpty(stringOptional));
        stringOptional = Optional.ofNullable(null);
        assertTrue(ObjectUtilities.isNullOrEmpty(stringOptional));
        stringOptional = Optional.of("test");
        assertFalse(ObjectUtilities.isNullOrEmpty(stringOptional));
    }

    @Test
    void hasValue() {
        Optional<String> stringOptional = null;
        assertFalse(ObjectUtilities.hasValue(stringOptional));
        stringOptional = Optional.empty();
        assertFalse(ObjectUtilities.hasValue(stringOptional));
        stringOptional = Optional.ofNullable(null);
        assertFalse(ObjectUtilities.hasValue(stringOptional));
        stringOptional = Optional.of("test");
        assertTrue(ObjectUtilities.hasValue(stringOptional));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testIsNullOrEmpty_Collection_Null(Collection<?> collection) {
        System.out.println(collection);
        assertTrue(ObjectUtilities.isNullOrEmpty(collection));
    }

    @ParameterizedTest
    @FieldSource("stringList")
    void testIsNullOrEmpty_Collection_NotNull(Collection<?> collection) {
        System.out.println(collection);
        assertTrue(ObjectUtilities.hasValue(collection));
    }

    @Test
    void testHasValue() {
        Collection<String> list = List.of("Value 1", "Value 2");
        System.out.println(list);
        assertTrue(ObjectUtilities.hasValue(list));
    }
}