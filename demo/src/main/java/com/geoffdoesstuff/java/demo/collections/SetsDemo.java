package com.geoffdoesstuff.java.demo.collections;

import com.geoffdoesstuff.java.data.objects.FuelType;
import com.geoffdoesstuff.java.utility.DemoUtilities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.geoffdoesstuff.java.data.objects.FuelType.DIESEL;
import static com.geoffdoesstuff.java.data.objects.FuelType.DIESEL_HYBRID;
import static com.geoffdoesstuff.java.data.objects.FuelType.PETROL;
import static com.geoffdoesstuff.java.data.objects.FuelType.PETROL_HYBRID;
import static com.geoffdoesstuff.java.data.objects.FuelType.USES_DIESEL;
import static com.geoffdoesstuff.java.data.objects.FuelType.USES_PETROL;

/**
 * Class to demonstrate some of the functionality of Sets
 */
public class SetsDemo {

	/**
	 * This is here to suppress Javadoc complaining about not commenting the default constructor
	 */
	private SetsDemo() {
	}

	/**
	 * Run the Sets Demo
	 */
	public static void setOperationsDemo() {
		DemoUtilities.outputTitle("Set Addition", true);
		setAdditionDemo();
		DemoUtilities.outputTitle("Set Merging", true);
		setDemo();
		DemoUtilities.outputTitle("Sets of Enum", true);
		demoSetsOfEnum();
	}

	/**
	 * This is a demo of Set Union. It is worth noting that Set.of() produces an immutable set (fixed, can't be changed)
	 * whereas creating a new HashSet gives a mutable set
	 */
	private static void setAdditionDemo() {
		Set<String> alpha = new HashSet<>(Set.of("One", "Two", "Three"));
		Set<String> beta = Set.of("Eleven", "Twelve", "Thirteen");
		Set<String> gamma = Set.of("One", "Eleven", "Twenty One");
		System.out.println("Alpha: " + alpha);
		System.out.println("Beta: " + beta);
		System.out.println("Gamma: " + gamma);
		alpha.addAll(beta);
		System.out.println("Alpha + Beta: " + alpha);
		alpha = new HashSet<>(Set.of("One", "Two", "Three"));
		Set<String> delta = new HashSet<>();
		delta.addAll(alpha);
		delta.addAll(gamma);
		System.out.println("Alpha + Gamma: " + delta);
		delta.addAll(beta);
		System.out.println("Alpha + Beta + Gamma: " + delta);
		delta.addAll(alpha);
		delta.addAll(beta);
		delta.addAll(gamma);
		System.out.println("Alpha + Beta + Gamma: " + delta);
	}

	private static void setDemo() {
		checkSetForOneTwoOrBoth(Set.of("Eleven", "Twelve", "Thirteen"));
		checkSetForOneTwoOrBoth(Set.of("one", "Twelve", "Thirteen"));
		checkSetForOneTwoOrBoth(Set.of("two", "Twelve", "Thirteen"));
		checkSetForOneTwoOrBoth(Set.of("one", "two", "Thirteen"));
		checkSetForOneTwoOrBoth(Set.of("both", "Three", "Thirteen"));
	}

	private static void checkSetForOneTwoOrBoth(Set<String> testInfo) {
		Set<String> setOne = new HashSet<>(List.of("one", "both")); // create mutable set
		Set<String> setTwo = new HashSet<>(List.of("two", "both")); // create mutable set
		setOne.retainAll(testInfo);
		setTwo.retainAll(testInfo);
		System.out.println("Input: " + testInfo + " setOne: " + setOne + " setTwo: " + setTwo);
		if (!setOne.isEmpty()) {
			System.out.println("  Execute one");
		}
		if (!setTwo.isEmpty()) {
			System.out.println("  Execute two");
		}
	}
	private static void demoSetsOfEnum() {
		FuelType fuelType = PETROL;
		System.out.println("Petrol A: " + usesPetrolSet(fuelType));
		System.out.println("Petrol B: " + usesPetrolLogic(fuelType));
		System.out.println("Diesel A: " + usesDieselSet(fuelType));
		System.out.println("Diesel B: " + usesDieselLogic(fuelType));
	}

	private static boolean usesPetrolSet(FuelType fuelType) {
		return USES_PETROL.contains(fuelType);
	}

	private static boolean usesPetrolLogic(FuelType fuelType) {
		return (fuelType.equals(PETROL) || fuelType.equals(PETROL_HYBRID));
	}
	private static boolean usesDieselSet(FuelType fuelType) {
		return USES_DIESEL.contains(fuelType);
	}

	private static boolean usesDieselLogic(FuelType fuelType) {
		return (fuelType.equals(DIESEL) || fuelType.equals(DIESEL_HYBRID));
	}
}
