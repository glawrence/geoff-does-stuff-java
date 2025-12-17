package com.geoffdoesstuff.java.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Basic utility class providing some handy methods for working with dates and times.
 */
public class DateTimeUtilities {

    /**
     * This is here to suppress Javadoc complaining about not commenting the default constructor
     */
    private DateTimeUtilities() {
    }

    /**
     * Convert input text into a LocalDate object.
     *
     * @param input the text to convert to a date
     * @return the converted text as a LocalDate
     */
    public static LocalDate parseLocalDate(String input) {
        DateTimeFormatter[] dateFormats = {
                DateTimeFormatter.ISO_DATE,
                DateTimeFormatter.ofPattern("dd MM yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("dd MMM yyyy"),
                DateTimeFormatter.ofPattern("dd-MMM-yyyy"),
                DateTimeFormatter.ofPattern("dd MMMM yyyy")
        };
        try {
            AtomicReference<LocalDate> parsedLocalDate = new AtomicReference<>();
            Arrays.stream(dateFormats).forEach(pattern -> {
                try {
                    parsedLocalDate.set(LocalDate.parse(input, pattern));
                } catch (Exception e) {}
            });
            return parsedLocalDate.get();
        } catch (DateTimeParseException dtpe) {
            System.out.printf("Error parsing '%s' to LocalDate with message \"%s\"%n", dtpe.getParsedString(), dtpe.getMessage());
            return null;
        } catch (NullPointerException npe) {
            System.out.printf("Error parsing 'null' to LocalDate with message \"%s\"%n", npe.getMessage());
            return null;
        }
    }

    /**
     * Check a string to see if we can successfully parse it to a date.
     *
     * @param input text to check if valid date
     * @return true if input can be parsed to a date
     */
    public static boolean isValidLocalDate(String input) {
        return Objects.nonNull(parseLocalDate(input));
    }

}
