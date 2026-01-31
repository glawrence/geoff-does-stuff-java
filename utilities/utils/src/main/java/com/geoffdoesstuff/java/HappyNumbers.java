package com.geoffdoesstuff.java;

import java.util.HashSet;
import java.util.Set;

/**
 * A "Happy Number" is one where we eventually reach 1 when the number is replaced by the sum of the squares of each
 * digit, which is then repeated. The origin of Happy Numbers is unknown, but it is a fun part of Number Theory. As
 * the year I wrote this is 2026 and 2026 is a "happy number", then this is here just for fun.
 * 2026 -> 2^2 + 0^2 + 2^2 + 6^2 = 4 + 4 + 0 + 36 = 44
 *   44 -> 4^2 + 4^2 = 16 + 16 = 32
 *   32 -> 3^2 + 2^2 = 9 + 4 = 13
 *   13 -> 1^2 + 3^2 = 1 + 9 = 10
 *   10 -> 1^2 + 0^2 = 1 + 0 = 1 = Happy!
 */
public class HappyNumbers {

	public static void main() {
		System.out.println("Happy Numbers");
		for (int i = 2025; i <= 2030 ; i++) {
			System.out.printf("Is %s happy? %b%n", i, isHappy(i));
		}
		System.out.println("Is 2112 happy? " + isHappy(2112));
	}

	private static boolean isHappy(int number) {
		boolean isHappy = false;
		Set<Integer> previousResults = new HashSet<>();
		int testNumber = number;

		do {
			int sumOfSquaredDigits = Integer.toString(testNumber).chars()
					.map(c -> c - 48) // '0' is 48 in ASCII
					.map(HappyNumbers::square)
					.sum();
			if (previousResults.contains(sumOfSquaredDigits)) {
				break;
			} else {
				previousResults.add(sumOfSquaredDigits);
			}
			if (sumOfSquaredDigits == 1) {
				isHappy = true;
			} else {
				testNumber = sumOfSquaredDigits;
			}
		} while (!isHappy);

		return isHappy;
	}

	/**
	 * This could be done with Math.pow() but that requires a cast and is most likely slower, but of course much more
	 * flexible.
	 * @param number input to be squared
	 * @return square of input number
	 */
	private static int square(int number) {
		return number * number;
	}
}
