package com.geoffdoesstuff.java.utility;

import java.util.Objects;

/**
 * Helper class to provide handy methods when working with primitives and their wrapper classes.
 */
public class PrimitiveUtilities {

	/**
	 * This is here to suppress Javadoc complaining about not commenting the default constructor
	 */
	private PrimitiveUtilities() {
	}

	/**
	 * Convert object to primitive in a null safe way, with control over the default if null
	 * @param bool input Boolean object
	 * @param def default return value if object is null
	 * @return primitive boolean
	 */
	public static boolean getAsPrimitive(Boolean bool, boolean def) {
		return Objects.requireNonNullElse(bool, def);
	}

	/**
	 * If the Boolean object is null, return false, otherwise return the value of the object
	 * @param bool input Boolean object
	 * @return primitive boolean
	 */
	public static boolean getAsPrimitive(Boolean bool) {
		return getAsPrimitive(bool, false);
	}
}
