package com.geoffdoesstuff.java.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
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
        expectedArguments.keySet().forEach(arg -> assertEquals(expectedArguments.get(arg), actualArguments.get(arg)));
    }

    private static Stream<Arguments> processArgs() {
        return Stream.of(
                Arguments.of(new String[]{}, Collections.emptyMap()),
                Arguments.of(new String[]{"-ip:192.168.1.1"}, Map.of("ip", "192.168.1.1")),
                Arguments.of(new String[]{"--ip-address:192.168.1.1"}, Map.of("ip-address", "192.168.1.1")),
                Arguments.of(new String[]{"/ip:192.168.1.1"}, Map.of("ip", "192.168.1.1")),
                Arguments.of(new String[]{"/debug"}, Map.of("debug", "")),
                Arguments.of(new String[]{"debug"}, Map.of("debug", "")),
                Arguments.of(new String[]{"MODE:normal"}, Map.of("MODE", "normal")),
                Arguments.of(new String[]{"Mode:normal"}, Map.of("Mode", "normal")),
                Arguments.of(new String[]{"/ip:192.168.1.1", "/debug"}, Map.of("ip", "192.168.1.1", "debug", "")),
                Arguments.of(new String[]{"-ip:192.168.1.1", "debug"}, Map.of("ip", "192.168.1.1", "debug", "")),
                Arguments.of(new String[]{"--ip-address:192.168.1.1", "--mac-address:AB:02:CD:03:EF:04"},
                        Map.of("ip-address", "192.168.1.1", "mac-address", "AB:02:CD:03:EF:04")),
                Arguments.of(new String[]{"-ip:192.168.1.1", "//DEBUG", "mode:normal", "/api-version:2"},
                        Map.of("ip", "192.168.1.1", "DEBUG", "", "mode", "normal", "api-version", "2")),
                Arguments.of(new String[]{"--ip-address=192.168.1.1", "--mac-address=AB:02:CD:03:EF:04"},
                        Map.of("ip-address", "192.168.1.1", "mac-address", "AB:02:CD:03:EF:04"))
        );
    }

    @DisplayName("Test valid command line arguments, without case sensitivity")
    @ParameterizedTest
    @MethodSource
    void processArgsCaseInsensitive(String[] inputArgs, Map<String, String> expectedArguments) {
        Map<String, String> actualArguments = CommandLine.processArgs(inputArgs, true);

        assertEquals(expectedArguments.size(), actualArguments.size());
        expectedArguments.keySet().forEach(arg -> assertEquals(expectedArguments.get(arg), actualArguments.get(arg)));
    }

    private static Stream<Arguments> processArgsCaseInsensitive() {
        return Stream.of(
                Arguments.of(new String[]{"MODE:normal"}, Map.of("mode", "normal")),
                Arguments.of(new String[]{"mode:normal"}, Map.of("mode", "normal")),
                Arguments.of(new String[]{"Mode:normal"}, Map.of("mode", "normal")),
                Arguments.of(new String[]{"moDE:normal"}, Map.of("mode", "normal"))
        );
    }

    @DisplayName("Test checkArgs(), success cases")
    @ParameterizedTest
    @MethodSource
    void checkArgs_Successes(Map<String, String> arguments, List<String> expected, boolean caseSensitive) {
        assertTrue(CommandLine.checkArgs(arguments, expected, caseSensitive));
    }

    private static Stream<Arguments> checkArgs_Successes() {
        return Stream.of(
                Arguments.of(Map.of("mode", "normal"), List.of("mode"), true),
                Arguments.of(Map.of("mode", "normal"), List.of("mode"), false),
                Arguments.of(Map.of("mode", "normal", "debug", ""), List.of("Mode", "Debug"), false),
                Arguments.of(Map.of("mode", "normal", "debug", ""), List.of("mode", "debug"), true)
        );
    }

    @DisplayName("Test checkArgs(), failure cases")
    @ParameterizedTest
    @MethodSource
    void checkArgs_Failures(Map<String, String> arguments, List<String> expected, boolean caseSensitive) {
        assertFalse(CommandLine.checkArgs(arguments, expected, caseSensitive));
    }

    private static Stream<Arguments> checkArgs_Failures() {
        return Stream.of(
                Arguments.of(null, List.of("Mode"), true),
                Arguments.of(Map.of("mode", "normal"), null, true),
                Arguments.of(Collections.emptyMap(), Collections.emptyList(), true),
                Arguments.of(Map.of("mode", "normal"), List.of("Mode"), true),
                Arguments.of(Map.of("mode", "normal", "debug", ""), List.of("Mode"), false),
                Arguments.of(Map.of("mode", "normal"), List.of("Mode", "debug"), false)
        );
    }
}