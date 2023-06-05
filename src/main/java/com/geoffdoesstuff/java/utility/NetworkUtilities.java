package com.geoffdoesstuff.java.utility;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Utility code for dealing with network related matters.
 */
public class NetworkUtilities {

    /**
     * Find the number of a free port on the local machine.
     * @return number of free port
     */
    public static int getFreePort() {
        int port;
        try (ServerSocket serverSocket = new ServerSocket(0)) { // 0 means automatically allocate a port, usually in the ephemeral range
            serverSocket.setReuseAddress(true);
            port = serverSocket.getLocalPort();
        } catch (IOException exception) {
            port = 52112; // hard coded port in ephemeral range
        }
        return port;
    }

    /**
     * Check if the port is free to use
     * Passing a 0 will always return false, because
     * @param port to check if in use
     * @return whether port is free or not
     */
    public static boolean isPortInUse(int port) {
        boolean portInUse;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            portInUse = !serverSocket.isBound();
        } catch (IOException | IllegalArgumentException | SecurityException exception) {
            // IOException and unexpected IO error
            // IllegalArgumentException port not in range 0 to 65535
            // SecurityException port opening blocked by security manager
            portInUse = true;
        }
        return portInUse;
    }
}
