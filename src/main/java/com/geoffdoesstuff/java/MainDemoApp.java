package com.geoffdoesstuff.java;

import java.util.*;

/**
 * The main demo class.
 */
public class MainDemoApp {

	private static final String DEMO_CLASSPATH = "com.geoffdoesstuff.java.demo";

	/**
	 * This is my main demo app class, that looks for the demo classes and puts them into a menu.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("Welcome....");
		List<String> classNames = DemoSupport.getDemoClasses(DEMO_CLASSPATH);
		if (classNames.size() > 0) {
			System.out.println("Demo classes found: " + classNames.size());
		} else {
			System.out.println("No demo classes found");
			return; // if there are no demo classes we need to avoid showing the menu
		}
		Scanner keyboard = new Scanner(System.in);
		displayMenu(classNames);
		do {
			String input = keyboard.nextLine();
			if (input != null) {
				int inputIndex;
				if (input.length() == 1) {
					inputIndex = input.toUpperCase().charAt(0) - 65;
					if ((inputIndex >= 0) && (inputIndex < classNames.size())) {
						DemoSupport.executeDemoClass(classNames.get(inputIndex));
					}
				}
				if ("EX".equalsIgnoreCase(input)) {
					break;
				}
			}
			displayMenu(classNames);
		} while (true);
		keyboard.close();
		System.out.println("All done!");
	}

	private static void displayMenu(List<String> classNames) {
		System.out.println("Options:");
		for (int i = 0; i < classNames.size(); i++) {
			System.out.println("  " + Character.toString(65 + i) + ") " + classNames.get(i));
		}
		System.out.println("  ex) exit");
		System.out.println("Enter command:");
	}

}
