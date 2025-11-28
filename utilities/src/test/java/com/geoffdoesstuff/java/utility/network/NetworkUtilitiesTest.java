package com.geoffdoesstuff.java.utility.network;

import com.geoffdoesstuff.java.utility.JavaSystemInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for NetworkUtilities testing. A little operating system analysis shows which ports or often/usually
 * already in use, they are as follows:
 * Windows: 139 NetBios, 3389 RDP
 * macOS: 5000 & 7000 Control Centre
 * Linux: 22 SSH, 631 CUPSD (printing scheduler)
 */
class NetworkUtilitiesTest {

    @Test
    @DisplayName("Test that the free port number is actually free")
    void test_getFreePort() throws IOException {
        int freePort = NetworkUtilities.getFreePort();

        // check manually first
        assertNotEquals(0, freePort);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(freePort);
            assertTrue(serverSocket.isBound());
            assertFalse(serverSocket.isClosed());
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
        assertTrue(serverSocket.isClosed());

        // now use the isPortInUse() method
        assertFalse(NetworkUtilities.isPortInUse(freePort));
    }

    @Test
    @DisplayName("Check a port being used reports as such")
    void test_isPortInUse() {
        int testPort;
        if (JavaSystemInfo.isPlatformWindows()) {
            testPort = 139;
        } else if (JavaSystemInfo.isPlatformMacOS()) {
            testPort = 5000;
        } else if (JavaSystemInfo.isPlatformLinux()) {
            testPort = 631;
        } else {
            testPort = 22;
        }
        assertTrue(NetworkUtilities.isPortInUse(testPort));
    }
}