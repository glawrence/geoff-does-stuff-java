package com.geoffdoesstuff.java.utility.network;

import java.util.Arrays;
import java.util.HexFormat;
import java.util.List;
import java.util.Objects;

/**
 * Class to make a "magic packet" for use with Wake-on-LAN. The only input needed is the MAC address, either in text
 * form or as a byte array.
 */
public class WoLMagicPacket {

    private static final byte[] PACKET_HEADER_BYTES = {-1, -1, -1, -1, -1, -1};
    private static final HexFormat COMMA_FORMAT = HexFormat.ofDelimiter(", ").withPrefix("#").withUpperCase();

    private final String macAddressText;
    private final byte[] macAddress;
    private final byte[] magicPacket;

    /**
     * Create a magic packet from the MAC address, as a string, for example "18-37-EA-73-B9-3C"
     * @param macAddress network MAC address as a string
     */
    public WoLMagicPacket(String macAddress) {
        this.macAddressText = parse(macAddress);
        this.macAddress = convertMacAddressTextToBytes();
        magicPacket = createMagicPacket();
    }

    /**
     * Create a magic packet from the MAC address, as an array of bytes, for example "24, 55, -22, 115, -71, 60", which
     * is "18-37-EA-73-B9-3C" as a string
     * @param macAddress network MAC address as array of bytes
     */
    public WoLMagicPacket(byte[] macAddress) {
        this.macAddress = parse(macAddress);
        this.macAddressText = HexFormat.ofDelimiter(":").withUpperCase().formatHex(this.macAddress);
        magicPacket = createMagicPacket();
    }

    /**
     * Get the magic packet as a byte array
     * @return the magic packet byte array
     */
    public byte[] getMagicPacket() {
        return magicPacket;
    }

    /**
     * Generate a String of the magic packet in hex, as a comma separated list
     * @return the formatted String
     */
    public String getFormattedMagicPacket() {
        return COMMA_FORMAT.formatHex(magicPacket);
    }

    /**
     * Get MAC address in colon separate format
     * @return MAC address
     */
    public String getMacAddressAsText() {
        return macAddressText;
    }

    /**
     * Get MAC address as a byte array
     * @return MAC address
     */
    public byte[] getMacAddressAsBytes() {
        return macAddress;
    }

    /**
     * Check the input byte array is the correct length for a MAC address
     * @param macAddress MAC address in byte form
     * @return checked MAC address as byte array
     */
    private byte[] parse(byte[] macAddress) {
        if ((macAddress == null) || (macAddress.length == 0)) {
            throw new IllegalArgumentException("No MAC address supplied");
        }
        if (macAddress.length != 6) {
            throw new IllegalArgumentException("MAC address must be 6 bytes");
        }
        return macAddress;
    }

    /**
     * Handle colon, space or hyphen separated, currently 052D.857D.A5CD is not supported but it is rare.
     * @param macAddress potential MAC address to parse
     * @return MAC address in colon separated format
     */
    private String parse(String macAddress) {
        if (Objects.isNull(macAddress) || macAddress.isEmpty()) {
            throw new IllegalArgumentException("No MAC address supplied");
        }
        if (macAddress.length() == 12) {
            macAddress = macAddress.substring(0, 2) + ":" + macAddress.substring(2, 4) +
                ":" + macAddress.substring(4, 6) + ":" + macAddress.substring(6, 8) +
                ":" + macAddress.substring(8, 10) + ":" + macAddress.substring(10, 12);
        }
        if (macAddress.length() != 17) {
            throw new IllegalArgumentException("Invalid MAC address supplied");
        }
        return macAddress
            .replace('-', ':')
            .replace(' ', ':')
            .toUpperCase();
    }

    /**
     * The "magic packet" is 102 bytes long, it is 6 bytes of 255 (FF in hexadecimal), so FF FF FF FF FF FF followed
     * by 16 repetitions of the 48-bit MAC address
     * @return the 102 byte magic packet
     */
    private byte[] createMagicPacket() {
        byte[] packetData = new byte[102];

        // set the first 6 bytes to 255
        System.arraycopy(PACKET_HEADER_BYTES, 0, packetData, 0, 6);

        // put the MAC address bytes into packet data 16 times, MAC address is 6 bytes long
        for (int count = 1; count <= 16; count++) {
            System.arraycopy(macAddress, 0, packetData, count * 6, 6);
        }
        return packetData;
    }

    /**
     * Convert colon separate MAC address into a byte array
     * @return byte array representation of MAC Address
     */
    private byte[] convertMacAddressTextToBytes() {
        List<String> elements = Arrays.stream(macAddressText.split(":", 6)).toList();
        byte[] bytesMac = new byte[6];
        for (int i = 0; i < 6; i++) {
            bytesMac[i] = Integer.valueOf(Integer.parseInt(elements.get(i), 16)).byteValue();
        }
        return bytesMac;
    }
}
