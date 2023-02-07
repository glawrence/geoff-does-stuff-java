package com.geoffdoesstuff.java.utility;

import java.util.Objects;
import java.util.UUID;

/**
 * Utility class for working with IDs, specifically {@link java.util.UUID}, in its current form.
 */
public class IdUtilities {

    /**
     * Regular expression for checking a string is a valid UUID.
     */
    /*
     *   match the start of the string                    ^
     *   match any of abcdefABCDEF0123456789               [a-fA-F0-9]
     *   match the previous item eight times                          {8}
     *   match the literal, which is a hyphen                            -
     *   match any of abcdefABCDEF0123456789                              [a-fA-F0-9]
     *   match the previous item four times                                          {4}
     *   match the literal, which is a hyphen                                           -
     *   match any of abcdefABCDEF0123456789                                             [a-fA-F0-9]
     *   match the previous item four times                                                         {4}
     *   match the literal, which is a hyphen                                                          -
     *   match any of abcdefABCDEF0123456789                                                            [a-fA-F0-9]
     *   match the previous item four times                                                                        {4}
     *   match the literal, which is a hyphen                                                                         -
     *   match any of abcdefABCDEF0123456789                                                                           [a-fA-F0-9]
     *   match the previous item twelve times                                                                                     {12}
     *   match the end of the string                                                                                                  $
     */
    public static final String UUID_REGULAR_EXPRESSION = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";

    /**
     * Test whether a String with a UUID in contains a valid UUID. The test is done with a regular expression.
     * @param uuid String form of UUID
     * @return true if valid UUID, false otherwise
     */
    public static boolean isValidUuidText(String uuid) {
        if (Objects.isNull(uuid)) {
            return false;
        }
        return uuid.matches(UUID_REGULAR_EXPRESSION);
    }

    /**
     * Test whether a String with a UUID in contains a valid UUID. The test is done with the java.util.UUID object.
     * @param uuid String form of UUID
     * @return true if valid UUID, false otherwise
     */
    public static boolean isValidUuidObject(String uuid) {
        try {
            UUID uuidObject = UUID.fromString(uuid);
            return uuidObject.toString().equalsIgnoreCase(uuid);
        } catch (RuntimeException e) {
            return false;
        }
    }
}
