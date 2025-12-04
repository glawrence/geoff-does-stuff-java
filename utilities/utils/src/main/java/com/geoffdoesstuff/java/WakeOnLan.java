package com.geoffdoesstuff.java;

import com.geoffdoesstuff.java.utility.CommandLine;
import com.geoffdoesstuff.java.utility.JavaSystemInfo;
import com.geoffdoesstuff.java.utility.ProcessExecutionResult;
import com.geoffdoesstuff.java.utility.ProcessExecutionUtility;
import com.geoffdoesstuff.java.utility.TextUtilities;
import com.geoffdoesstuff.java.utility.network.WoLMagicPacket;

import java.io.IOException;
import java.net.*;
import java.util.Map;

/**
 * A command line utility for Wake-on-LAN functionality. This will either send a magic packet, or check if the
 * specified host is responding to a ping and confirm when it stops responding. These two options make it a
 * handy utility to start a host up, or check it has gone to sleep or shutdown.
 */
public class WakeOnLan {

    // this should be a command line argument, 20 is fine from sleep, but we need 40 for a cold start
    private static final int MAX_PING_COUNT = 20;
    private static final String CMD_LINE_MAC_ADDRESS = "mac-address";
    private static final String CMD_LINE_IP_ADDRESS = "ip-address";
    private static final String CMD_LINE_DOWN = "down";

    private WakeOnLan() {
        throw new UnsupportedOperationException(
                "This is a utility application and cannot be instantiated, it is started via its main() method");
    }

    /**
     * This method starts the utility.
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        System.out.println("Starting Wake on LAN utility");
        Map<String, String> cmdLineArguments = CommandLine.processArgs(args);
        System.out.println("  command line arguments: " + cmdLineArguments);

        if (cmdLineArguments.size() != 2) {
            displayIncorrectCommandLineError();
        } else {
            String ipAddress = cmdLineArguments.get(CMD_LINE_IP_ADDRESS);
            if (TextUtilities.isNullOrBlank(ipAddress)) {
                displayIncorrectCommandLineError();
            } else {
                if (cmdLineArguments.containsKey(CMD_LINE_MAC_ADDRESS)) {
                    startHost(cmdLineArguments.get(CMD_LINE_MAC_ADDRESS), ipAddress);
                } else if (cmdLineArguments.containsKey(CMD_LINE_DOWN)) {
                    pingUntilShutdown(ipAddress);
                } else {
                    displayIncorrectCommandLineError();
                }
            }
        }
    }

    private static void displayIncorrectCommandLineError() {
        System.err.println("ERROR: please specify a command line option");
        System.err.println("       --mac-address=FF:FF:FF:FF:FF:FF --ip-address=192.168.1.1  -  send Wake-on-LAN packet and check it started");
        System.err.println("       --down --ip-address=192.168.1.1  -  this will test if the host is down");
        System.err.println("       Note: you have to use one option or the other, not both");
    }

    private static void startHost(String inputMacAddress, String targetIpAddress) {
        String macAddress = inputMacAddress;

        System.out.println("Here we go.....");
        try {
            WoLMagicPacket woLMagicPacket = new WoLMagicPacket(inputMacAddress);
            macAddress = woLMagicPacket.getMacAddressAsText();
            send(woLMagicPacket.getMagicPacket(), targetIpAddress);
            System.out.println("Wake On LAN packet sent to " + macAddress + " (" + targetIpAddress + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: could not parse MAC address (" + macAddress + ") - " + targetIpAddress);
            return;
        }

        pingUntilResponding(targetIpAddress);
    }

    private static void pingUntilShutdown(String host) {
        boolean running = true;
        for (int i = 1; i <= MAX_PING_COUNT; i++) {
            ProcessExecutionResult processExecutionResult = getPingProcessExecutionResult(host);
            if (processExecutionResult.success()) {
                System.out.printf("%2d: %s is running%n", i, host);
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    // ignore this
                }
            } else {
                System.out.printf("%2d: pinging %s - %s%n", i, host, processExecutionResult.errorMessage());
                running = false;
                break;
            }
        }
        if (running) {
            System.out.println("Host is still responding");
        } else {
            System.out.println("The host has stopped responding");
        }
    }

    private static void pingUntilResponding(String host) {
        boolean started = false;
        for (int i = 1; i <= MAX_PING_COUNT; i++) {
            ProcessExecutionResult processExecutionResult = getPingProcessExecutionResult(host);
            if (processExecutionResult.success()) {
                System.out.printf("%2d: %s has started%n", i, host);
                started = true;
                break;
            } else {
                System.out.printf("%2d: pinging %s - %s%n", i, host, processExecutionResult.errorMessage());
            }
        }
        if (started) {
            System.out.println("Host successfully started!");
        } else {
            System.out.println("Sadly it did not start, as far as we know");
        }
    }

    private static ProcessExecutionResult getPingProcessExecutionResult(String hostname) {
        String[] commandLine;
        switch (JavaSystemInfo.getOperatingSystem()) {
            case WINDOWS -> { // on Windows ping always returns 0, find command needed to get exit code
                commandLine = new String[] {"cmd", "/c", "ping -n 1 " + hostname + " | find \"TTL\""};
            }
            case MACOS, LINUX -> { // ping count = 1, timeout = 1s
                commandLine = new String[] {"ping", "-c 1", "-t 1", hostname};
            }
            default -> {
                commandLine = new String[] {};
            }
        }
        return ProcessExecutionUtility.processBuilder(commandLine);
    }

    private static void send(byte[] magicPacket, String broadcastIp) {
        DatagramPacket packet;
        try {
            final InetAddress address = InetAddress.getByName(broadcastIp);
            packet = new DatagramPacket(magicPacket, magicPacket.length, address, 9);
            final DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);
            socket.send(packet);
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}