package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IdUtilitiesTest {

    @DisplayName("Test isValidUuidText() and isValidUuidObject(), success scenarios")
    @Test
    void isValidUuid_Success() {
        Stream<String> uuidStream
                = Stream.of("1e36d659-5388-4005-a3a3-f67dfb60b1d4",
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString());
        uuidStream.forEach(uuid -> {
            assertTrue(IdUtilities.isValidUuidObject(uuid));
            assertTrue(IdUtilities.isValidUuidText(uuid));
        });
    }

    @DisplayName("Test isValidUuidText() and isValidUuidObject(), failure scenarios")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"hello world", "1e36d659-5388-4005-a3a3-f67dfb60b1d"})
    void isValidUuid_Fail(String id) {
        assertFalse(IdUtilities.isValidUuidObject(id));
        assertFalse(IdUtilities.isValidUuidText(id));
    }

    @DisplayName("Testing @MethodSource annotation")
    @ParameterizedTest
    @MethodSource("testDataSource")
    void example(List<String> input) {
        System.out.println("Testing...");
        input.forEach(item -> {
            System.out.println("  Item: " + item);
            assertInstanceOf(String.class, item);
        });
    }

    private static List<List<String>> testDataSource() {
        return List.of(
                List.of("hello", "world", "cee7fbc9-3658-4fc9-9038-092024ce3900"),
                List.of("geoff", "does", "stuff")
        );
    }
}