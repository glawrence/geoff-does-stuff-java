package com.geoffdoesstuff.java.utility.network;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WoLMagicPacketTest {

    public static final String STANDARD_MAC_ADDRESS = "05:2D:85:7D:A5:CD";
    private static final byte[] STANDARD_BYTES = new byte[]{-1, -1, -1, -1, -1, -1, 5, 45, -123, 125, -91,
            -51, 5, 45, -123, 125, -91, -51, 5, 45, -123, 125, -91, -51, 5, 45, -123, 125, -91, -51, 5, 45,
            -123, 125, -91, -51, 5, 45, -123, 125, -91, -51, 5, 45, -123, 125, -91, -51, 5, 45, -123, 125,
            -91, -51, 5, 45, -123, 125, -91, -51, 5, 45, -123, 125, -91, -51, 5, 45, -123, 125, -91, -51, 5,
            45, -123, 125, -91, -51, 5, 45, -123, 125, -91, -51, 5, 45, -123, 125, -91, -51, 5, 45, -123,
            125, -91, -51, 5, 45, -123, 125, -91, -51};

    @DisplayName("Verify the MAC address produces the expected magic packet")
    @ParameterizedTest
    @MethodSource
    void constructorTestingString(String macAddress, byte[] expectedMagicPacket) {
        WoLMagicPacket magicPacket = new WoLMagicPacket(macAddress);
        assertArrayEquals(expectedMagicPacket, magicPacket.getMagicPacket());
    }

    private static Stream<Arguments> constructorTestingString() {
        return Stream.of(
                Arguments.of(STANDARD_MAC_ADDRESS, STANDARD_BYTES),
                Arguments.of("05-2D-85-7D-A5-CD", STANDARD_BYTES),
                Arguments.of("05 2D 85 7D A5 CD", STANDARD_BYTES),
                Arguments.of("05:2D-85 7D-A5:CD", STANDARD_BYTES),
                Arguments.of("052D.857D.A5CD", STANDARD_BYTES),
                Arguments.of("37:12:85:D3:29:B2", new byte[]{-1, -1, -1, -1, -1, -1, 55, 18, -123, -45, 41,
                        -78, 55, 18, -123, -45, 41, -78, 55, 18, -123, -45, 41, -78, 55, 18, -123, -45, 41, -78, 55, 18,
                        -123, -45, 41, -78, 55, 18, -123, -45, 41, -78, 55, 18, -123, -45, 41, -78, 55, 18, -123, -45,
                        41, -78, 55, 18, -123, -45, 41, -78, 55, 18, -123, -45, 41, -78, 55, 18, -123, -45, 41, -78, 55,
                        18, -123, -45, 41, -78, 55, 18, -123, -45, 41, -78, 55, 18, -123, -45, 41, -78, 55, 18, -123,
                        -45, 41, -78, 55, 18, -123, -45, 41, -78}),
                Arguments.of("00:01:02:03:04:05", new byte[]{-1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 0, 1,
                        2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3,
                        4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5,
                        0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5}));
    }

    @DisplayName("Verify the String constructor fails when it should")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n", "052D857DA5C", "052D857DA5CDD"})
    void constructorTestingString_Fail(String macAddress) {
        assertThrowsExactly(IllegalArgumentException.class, () -> new WoLMagicPacket(macAddress));
    }

    @DisplayName("Verify the String constructor fails when it should")
    @ParameterizedTest
    @ValueSource(strings = {"not a mac address", "05_2D_85_7D_A5_CD", "05:2D:85:7D:A5:GG"})
    void constructorTestingString_FailNumber(String macAddress) {
        assertThrowsExactly(NumberFormatException.class, () -> new WoLMagicPacket(macAddress));
    }

    @DisplayName("Test byte array version of constructor")
    @ParameterizedTest
    @MethodSource
    void constructorTestByteArray(byte[] value, boolean successExpected, String macAddress) {
        if (successExpected) {
            WoLMagicPacket temporaryWoLMagicPacket = new WoLMagicPacket(value);
            assertEquals(macAddress, temporaryWoLMagicPacket.getMacAddressAsText());
        } else {
            assertThrows(IllegalArgumentException.class, () -> new WoLMagicPacket(value));
        }
    }

    private static Stream<Arguments> constructorTestByteArray() {
        return Stream.of(Arguments.of(null, false, null),
            Arguments.of(new byte[]{}, false, null),
            Arguments.of(new byte[]{0}, false, null),
            Arguments.of(new byte[]{101, 102, 103, 104, 105}, false, null),
            Arguments.of(new byte[]{101, 102, 103, 104, 105, 106, 107}, false, null),
            Arguments.of(new byte[]{101, 102, 103, 104, 105, 106}, true, "65:66:67:68:69:6A"),
            Arguments.of(new byte[]{5, 45, -123, 125, -91, -51}, true, STANDARD_MAC_ADDRESS));
    }

    @DisplayName("Test MAC address parsing")
    @ParameterizedTest
    @MethodSource
    void getMacAddress(String macAddress, String parsedMacAddress, byte[] macAddressBytes) {
        WoLMagicPacket woLMagicPacket = new WoLMagicPacket(macAddress);
        assertEquals(parsedMacAddress, woLMagicPacket.getMacAddressAsText());
        assertArrayEquals(macAddressBytes, woLMagicPacket.getMacAddressAsBytes());
    }

    private static Stream<Arguments> getMacAddress() {
        return Stream.of(Arguments.of(STANDARD_MAC_ADDRESS, STANDARD_MAC_ADDRESS, new byte[]{5, 45, -123, 125, -91, -51}),
                Arguments.of("05-2D-85-7D-A5-CD", STANDARD_MAC_ADDRESS, new byte[]{5, 45, -123, 125, -91, -51}),
                Arguments.of("05 2D 85 7D A5 CD", STANDARD_MAC_ADDRESS, new byte[]{5, 45, -123, 125, -91, -51}),
                Arguments.of("052D857DA5CD", STANDARD_MAC_ADDRESS, new byte[]{5, 45, -123, 125, -91, -51}),
                Arguments.of("052D.857D.A5CD", STANDARD_MAC_ADDRESS, new byte[]{5, 45, -123, 125, -91, -51}),
                Arguments.of(STANDARD_MAC_ADDRESS, STANDARD_MAC_ADDRESS, new byte[]{5, 45, -123, 125, -91, -51}));
    }

    @DisplayName("Test output of getFormattedMagicPacket() is correct")
    @Test
    void getFormattedMagicPacket() {
        WoLMagicPacket woLMagicPacket = new WoLMagicPacket(STANDARD_MAC_ADDRESS);
        String expectedMagicPacket = "#FF, #FF, #FF, #FF, #FF, #FF, #05, #2D, #85, #7D, #A5, #CD, #05, #2D, #85, #7D, " +
                "#A5, #CD, #05, #2D, #85, #7D, #A5, #CD, #05, #2D, #85, #7D, #A5, #CD, #05, #2D, #85, #7D, #A5, #CD, " +
                "#05, #2D, #85, #7D, #A5, #CD, #05, #2D, #85, #7D, #A5, #CD, #05, #2D, #85, #7D, #A5, #CD, #05, #2D, " +
                "#85, #7D, #A5, #CD, #05, #2D, #85, #7D, #A5, #CD, #05, #2D, #85, #7D, #A5, #CD, #05, #2D, #85, #7D, " +
                "#A5, #CD, #05, #2D, #85, #7D, #A5, #CD, #05, #2D, #85, #7D, #A5, #CD, #05, #2D, #85, #7D, #A5, #CD, " +
                "#05, #2D, #85, #7D, #A5, #CD";
        assertEquals(expectedMagicPacket, woLMagicPacket.getFormattedMagicPacket());
    }
}