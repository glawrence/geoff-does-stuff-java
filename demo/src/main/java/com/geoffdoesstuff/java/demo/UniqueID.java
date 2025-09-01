package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.IdUtilities;

import java.util.List;
import java.util.UUID;

/**
 * In Java a UUID is an immutable universally unique ID representing a 128bit value, which is made from two Long numbers
 * which are 64-bit.
 */
public class UniqueID {

	/**
	 * This is here to suppress JavaDoc complaining about not commenting the default constructor
	 */
    private UniqueID() {
    }

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

		uuid = UUID.randomUUID();
		String id = uuid.toString();
		System.out.println(UUID.fromString(id).compareTo(uuid));

		List<String> uuids = List.of("", "gsgsdgggs", "1e36d659-5388-4005-a3a3-f67dfb60b1d4", id);
		uuids.forEach(value -> {
			System.out.println("Testing " + value);
			System.out.printf("  Is %s a valid UUID? %b%n", value, IdUtilities.isValidUuidText(value));
			System.out.printf("  Is %s a valid UUID? %b%n", value, IdUtilities.isValidUuidObject(value));
		});
		id = null;
		System.out.println("Testing " + id);
		System.out.printf("  Is %s a valid UUID? %b%n", id, IdUtilities.isValidUuidText(id));
		System.out.printf("  Is %s a valid UUID? %b%n", id, IdUtilities.isValidUuidObject(id));
	}
}
