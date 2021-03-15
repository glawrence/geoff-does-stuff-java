package com.geoffdoesstuff.java.demo;

import java.util.UUID;

/**
 * In Java a UUID is an immutable universally unique ID representing a 128bit value, which is made from two Long numbers
 * which are 64-bit.
 */
public class UniqueID {

	/**
	 * Main method, for independent running or use via Demo Menu
	 * @param args command line
	 */
	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		System.out.println("Random UUID: " + uuid);
		System.out.println(String.format("  Most Significant Bits: %d Least Significant Bits: %d", uuid.getMostSignificantBits(), uuid.getLeastSignificantBits()));
		System.out.println(String.format("  Version: %d Variant: %d", uuid.version(), uuid.variant()));

		uuid = new UUID(123456789, 987654321);
		System.out.println("Generated UUID: " + uuid);
		System.out.println(String.format("  Most Significant Bits: %d Least Significant Bits: %d", uuid.getMostSignificantBits(), uuid.getLeastSignificantBits()));
		System.out.println(String.format("  Version: %d Variant: %d", uuid.version(), uuid.variant()));

		uuid = new UUID(1, -1);
		System.out.println("Generated UUID: " + uuid);
		System.out.println(String.format("  Most Significant Bits: %d Least Significant Bits: %d", uuid.getMostSignificantBits(), uuid.getLeastSignificantBits()));
		System.out.println(String.format("  Version: %d Variant: %d", uuid.version(), uuid.variant()));
	}
}
