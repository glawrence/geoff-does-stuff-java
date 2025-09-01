package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.JavaSystemInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * The tests for generateCommandLine are using JUnit and being explicit.
 * The tests for generateCommandLineArray are using JUnit but with more automatic techniques.
 */
class ExecuteExternalSupportTest {

    @ParameterizedTest
    @EnumSource(ExecuteExternalSupport.CommandLine.class)
    void generateCommandLine_BasicTest(ExecuteExternalSupport.CommandLine commandLineEnum) {
        String commandLine = ExecuteExternalSupport.generateCommandLine(commandLineEnum, "ping-host");
        assertNotNull(commandLine);
        assertFalse(commandLine.isEmpty());
    }

    @ParameterizedTest
    @EnumSource
    void generateCommandLineArray_BasicTest(ExecuteExternalSupport.CommandLine commandLineEnum) {
        String[] commandLine = ExecuteExternalSupport.generateCommandLineArray(commandLineEnum, "ping-host");
        if (JavaSystemInfo.isPlatformMacOS() && commandLineEnum.equals(ExecuteExternalSupport.CommandLine.PING)) {
            assertEquals(4, commandLine.length);
        } else {
            assertEquals(3, commandLine.length);
        }
        for (String item: commandLine) {
            assertFalse(item.isEmpty());
        }
    }

    @ParameterizedTest
    @MethodSource("generateCommandLineTestData")
    void generateCommandLine_Test(ExecuteExternalSupport.CommandLine commandLineEnum, String expectedCommandLine) {
        assertEquals(expectedCommandLine, ExecuteExternalSupport.generateCommandLine(commandLineEnum, "ping-host"));
    }

    static Stream<Arguments> generateCommandLineTestData() {
        Arguments pingArguments;
        if (JavaSystemInfo.isPlatformWindows()) {
            pingArguments = Arguments.of(ExecuteExternalSupport.CommandLine.PING, "cmd /c ping -n 1 ping-host | find \"TTL\"");
        } else {
            pingArguments = Arguments.of(ExecuteExternalSupport.CommandLine.PING, "ping -c 1 -t 1 ping-host");
        }
        return Stream.of(
                pingArguments,
                arguments(ExecuteExternalSupport.CommandLine.POWERSHELL, "pwsh -Command \"Write-Output 'StdOut 1'; Write-Error 'StdErr'; Write-Output 'StdOut 2'\""),
                arguments(ExecuteExternalSupport.CommandLine.PYTHON, "python -c \"import sys;print('StdOut 1');sys.stderr.write('StdErr\\n');print('StdOut 2')\"")
        );
    }

    @ParameterizedTest
    @MethodSource
    void generateCommandLineArray_Test(ExecuteExternalSupport.CommandLine commandLineEnum, String[] expectedCommandLineArray) {
        String[] commandLineArray = ExecuteExternalSupport.generateCommandLineArray(commandLineEnum, "ping-host");
        assertArrayEquals(expectedCommandLineArray, commandLineArray);
    }

    static Stream<Arguments> generateCommandLineArray_Test() {
        Arguments pingArguments;
        if (JavaSystemInfo.isPlatformWindows()) {
            pingArguments = Arguments.of(ExecuteExternalSupport.CommandLine.PING, new String[] {"cmd", "/c", "ping -n 1 ping-host | find \"TTL\""});
        } else {
            pingArguments = Arguments.of(ExecuteExternalSupport.CommandLine.PING, new String[] {"ping", "-c 1", "-t 1", "ping-host"});
        }
        return Stream.of(
                pingArguments,
                Arguments.of(ExecuteExternalSupport.CommandLine.POWERSHELL, new String[] {"pwsh", "-Command", "\"Write-Output 'StdOut 1'; Write-Error 'StdErr'; Write-Output 'StdOut 2'\""}),
                Arguments.of(ExecuteExternalSupport.CommandLine.PYTHON, new String[] {"python", "-c", "\"import sys;print('StdOut 1');sys.stderr.write('StdErr\\n');print('StdOut 2')\""})
        );
    }
}