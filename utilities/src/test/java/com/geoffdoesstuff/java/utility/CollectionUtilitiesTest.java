package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilitiesTest {

    public static final String ERROR_MESSAGE = "The size of the test list should not change";

    @Test
    void isListImmutable() {
        List<String> testList = List.of("Hello", "Geoff's", "World", "test");
        int testListSize = testList.size();

        assertTrue(CollectionUtilities.isListImmutable(testList));
        assertEquals(testListSize, testList.size(), ERROR_MESSAGE);
        // an ArrayList is mutable
        testList = new ArrayList<>(testList);
        assertEquals(testListSize, testList.size(), ERROR_MESSAGE);
        assertFalse(CollectionUtilities.isListImmutable(testList));
        assertEquals(testListSize, testList.size(), ERROR_MESSAGE);
        // Collections.unmodifiableList() returns an immutable list
        assertTrue(CollectionUtilities.isListImmutable(Collections.unmodifiableList(testList)));
        assertEquals(testListSize, testList.size(), ERROR_MESSAGE);
    }

    @Test
    void isListMutable() {
        List<String> testList = List.of("Hello", "test");
        int testListSize = testList.size();

        assertFalse(CollectionUtilities.isListMutable(testList));
        assertEquals(testListSize, testList.size(), ERROR_MESSAGE);
        // an ArrayList is mutable
        testList = new ArrayList<>(testList);
        assertEquals(testListSize, testList.size(), ERROR_MESSAGE);
        assertTrue(CollectionUtilities.isListMutable(testList));
        assertEquals(testListSize, testList.size(), ERROR_MESSAGE);
        // Collections.unmodifiableList() returns an immutable list
        assertFalse(CollectionUtilities.isListMutable(Collections.unmodifiableList(testList)));
        assertEquals(testListSize, testList.size(), ERROR_MESSAGE);
    }
}