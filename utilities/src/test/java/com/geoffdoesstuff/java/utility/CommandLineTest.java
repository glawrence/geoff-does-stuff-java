package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineTest {

    @DisplayName("Test valid command line arguments")
    @ParameterizedTest
    @MethodSource
    void processArgs(String[] inputArgs, Map<String, String> expectedArguments) {
        Map<String, String> actualArguments = CommandLine.processArgs(inputArgs);

        assertEquals(expectedArguments.size(), actualArguments.size());
        expectedArguments.keySet().forEach(arg -> assertEquals(actualArguments.get(arg), expectedArguments.get(arg)));
    }

    private static Stream<Arguments> processArgs() {
        return Stream.of(
                Arguments.of(new String[]{}, Collections.emptyMap()),
                Arguments.of(new String[]{"-ip:192.168.1.1"}, Map.of("ip", "192.168.1.1")),
                Arguments.of(new String[]{"--ip-address:192.168.1.1"}, Map.of("ip-address", "192.168.1.1")),
                Arguments.of(new String[]{"/ip:192.168.1.1"}, Map.of("ip", "192.168.1.1")),
                Arguments.of(new String[]{"/debug"}, Map.of("debug", "")),
                Arguments.of(new String[]{"/ip:192.168.1.1", "/debug"}, Map.of("ip", "192.168.1.1", "debug", ""))
        );
    }
}