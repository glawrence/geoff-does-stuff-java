package com.geoffdoesstuff.java.utility;

import java.util.Collection;
import java.util.Optional;

/**
 * General purpose helper methods for dealing with objects
 */
public class ObjectUtilities {

    /**
     * Test if the {@link java.util.Optional} is null or empty
     * @param optional to test
     * @return true if null or empty
     */
    public static boolean isNullOrEmpty(Optional<?> optional) {
        return (optional == null || optional.isEmpty());
    }

    /**
     * Test if the {@link java.util.Optional} has a value, sometimes clearer than saying "not isNullOrEmpty"
     * @param optional to test
     * @return true it contains a value
     */
    public static boolean hasValue(Optional<?> optional) {
        return (optional != null && optional.isPresent());
    }

    /**
     * Test if the {@link java.util.Collection} is null or empty
     * @param collection to test
     * @return true if null or empty
     */
    public static boolean isNullOrEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * Test if the {@link java.util.Collection} has a value, sometimes clearer than saying "not isNullOrEmpty"
     * @param collection to test
     * @return true it contains a value
     */
    public static boolean hasValue(Collection<?> collection) {
        return (collection != null && !collection.isEmpty());
    }
}
