package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.DemoUtilities;
import com.geoffdoesstuff.java.utility.TextUtilities;

import java.util.*;

/**
 * Demo of Locales
 */
public class Locales {

	/**
	 * This is here to suppress Javadoc complaining about not commenting the default constructor
	 */
	private Locales() {
	}

	/**
	 * Main method, for independent running or use via Demo Menu
	 * @param args command line
	 */
	public static void main(String[] args) {
		DemoUtilities.outputTitle("Locales");
		Locales locales = new Locales();

		DemoUtilities.outputTitle("Examine Locale Data");
		locales.localeData();
		DemoUtilities.outputTitle("Demo Map of Locales");
		locales.mapDemo();
		DemoUtilities.outputTitle("Test for existence (GB)");
		locales.doesItExistDemo("GB");
		DemoUtilities.outputTitle("Test for existence (TH)");
		locales.doesItExistDemo("TH");
		DemoUtilities.outputTitle("Test for existence (IN)");
		locales.doesItExistDemo("IN");
		DemoUtilities.outputTitle("Test for existence (JP)");
		locales.doesItExistDemo("JP");
		DemoUtilities.outputTitle("Test for existence (XX)");
		locales.doesItExistDemo("XX");
		DemoUtilities.outputTitle("Locale Detail");
		DemoUtilities.outputTitle("UK", true);
		locales.displayLocaleDetails(Locale.UK);
		DemoUtilities.outputTitle("Korea", true);
		locales.displayLocaleDetails(Locale.KOREA);

		DemoUtilities.outputTitle("Thailand", true);
		locales.displayLocaleDetails(Locale.forLanguageTag("th-TH-u-nu-thai-x-lvariant-TH"));
		locales.displayLocaleDetails(Locale.forLanguageTag("th-THai-TH"));
		locales.displayLocaleDetails(Locale.forLanguageTag("th-TH-TH-#u-nu-thai"));
		locales.displayLocaleDetails(Locale.forLanguageTag("ja-JP-JP-#u-ca-japanese"));

		DemoUtilities.outputTitle("Display Locale name if different Locale");
		System.out.println("Example: " + Locale.UK.getDisplayName(Locale.FRENCH));
		System.out.println("Example: " + Locale.CANADA.getDisplayName(Locale.FRENCH));
		System.out.println("Example: " + Locale.CANADA.getDisplayName(Locale.ENGLISH));
		System.out.println("Example: " + Locale.CANADA_FRENCH.getDisplayName(Locale.FRENCH));
		System.out.println("Example: " + Locale.CANADA_FRENCH.getDisplayName(Locale.ENGLISH));
		System.out.println("Example: " + Locale.FRANCE.getDisplayName(Locale.CHINESE));
		System.out.println("Example: " + Locale.KOREA.getDisplayName(Locale.FRENCH));
		System.out.println("Example: " + Locale.KOREA.getDisplayName(Locale.FRENCH));
	}

    /**
	 * Display all the detail of a given Locale
     * @param locale locale to display
     */
	public void displayLocaleDetails(Locale locale) {
		System.out.printf("Language Tag: [%s]%n", locale.toLanguageTag());
		System.out.printf("  Display Name: [%s]%n", locale.getDisplayName());
		System.out.printf("  Display Name (French): [%s]%n", locale.getDisplayName(Locale.FRENCH));
		System.out.printf("  Display Country: [%s]%n", locale.getDisplayCountry());
		System.out.printf("  Display Language: [%s]%n", locale.getDisplayLanguage());
		System.out.printf("  Display Script: [%s]%n", locale.getDisplayScript());
		System.out.printf("  Display Variant: [%s]%n", locale.getDisplayVariant());
		System.out.printf("  Country: [%s]%n", locale.getCountry());
		System.out.printf("  ISO3 Country: [%s]%n", locale.getISO3Country());
		System.out.printf("  ISO3 Language: [%s]%n", locale.getISO3Language());
		System.out.printf("  Attributes: [%s]%n", locale.getUnicodeLocaleAttributes());
		System.out.printf("  Extension Keys: [%s]%n", locale.getExtensionKeys());
		System.out.printf("  Unicode Locale Keys: [%s]%n", locale.getUnicodeLocaleKeys());
		System.out.printf("  Language: [%s]%n", locale.getLanguage());
		System.out.printf("  Script: [%s]%n", locale.getScript());
		System.out.printf("  Variant: [%s]%n", locale.getVariant());
	}

	/**
	 * Demo of basic locale data. The number with country plus the number without should add up to the count. Note that
	 * the ones with variant will most likely also have a country so are listed there too. Expect over one thousand locales.
	 */
	public void localeData() {
		Locale[] availableLocales = Locale.getAvailableLocales();
		List<Locale> locales = Arrays.asList(availableLocales.clone());

		DemoUtilities.outputTitle("Locales on this system", true);
		System.out.println("Count = " + locales.size());

		DemoUtilities.outputTitle("Locales with variant", true);
		locales.stream()
				.sorted(Comparator.comparing(Locale::toLanguageTag))
				.filter(locale -> !TextUtilities.isNullOrBlank(locale.getVariant())).forEach(locale -> {
					System.out.printf("%s (%s) %s%n", locale.getDisplayName(), locale.toLanguageTag(), locale.getVariant());
				});

		DemoUtilities.outputTitle("Locales, without country", true);
		locales.stream()
				.filter(locale -> TextUtilities.isNullOrEmpty(locale.getCountry()))
				.sorted(Comparator.comparing(Locale::getDisplayName))
				.forEach(locale -> {
					System.out.println("Language: [" + locale.toLanguageTag() + "], " + locale.getDisplayLanguage() + ", " + locale.getDisplayScript() + ", " + locale.getDisplayName());
				});

		DemoUtilities.outputTitle("Country based Locales", true);
		locales.stream()
				.filter(locale -> ! TextUtilities.isNullOrEmpty(locale.getCountry()))
				.sorted(Comparator.comparing(Locale::getDisplayCountry))
				.forEach(locale -> {
					System.out.println("Country: " + locale.getDisplayCountry() + " [" + locale.toLanguageTag() + "], " + locale.getDisplayLanguage() + ", " + locale.getDisplayScript() + ", " + locale.getDisplayName());
				});
	}

	/**
	 * Another locale demo, but with Map&lt;&gt; where we look up some Locales and use them.
	 */
	public void mapDemo() {
		Map<String, Locale> myLocales = new HashMap<>();
		myLocales.put("Home", Locale.UK);
		myLocales.put("UK", Locale.UK);
		myLocales.put("France", Locale.FRANCE);
		myLocales.put("Holiday", Locale.forLanguageTag("Welsh"));

        myLocales.forEach((s, t) -> System.out.println("In " + s + " we speak " + t.getDisplayLanguage()));
		System.out.println("My work: " + myLocales.getOrDefault("Work", Locale.CANADA).getDisplayName());
		System.out.println("My home: " + myLocales.getOrDefault("Home", Locale.CANADA).getDisplayName());
	}

	/**
	 * Check if country exists demo.
	 * @param country name of country
	 */
	public void doesItExistDemo(String country) {
		Locale[] availableLocales = Locale.getAvailableLocales();
		List<Locale> locales = Arrays.asList(availableLocales.clone());
		boolean countryExists = locales.stream()
				.anyMatch(locale -> locale.getCountry().equalsIgnoreCase(country));
		if (countryExists) {
			System.out.println("Country " + country + " has the following locales:");
			locales.stream()
					.filter(locale -> locale.getCountry().equalsIgnoreCase(country))
					.sorted(Comparator.comparing(Locale::getDisplayName))
					.forEach(locale -> System.out.printf("  %s [%s]%n", locale.getDisplayName(), locale.toLanguageTag()));
		} else {
			System.out.println("Country " + country + " not found");
		}
	}
}
