package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.data.objects.Passenger;
import com.geoffdoesstuff.java.demo.optionals.PopulateOptionals;
import com.geoffdoesstuff.java.utility.DemoUtilities;

import java.util.*;

/**
 * An Optional is either "present" or "absent", where present is non-null, and absent means is empty. However,
 * remember, Optional is not a replacement for null.
 * The idea behind Optional is to provide a limited mechanism for representing "no result" where a
 * null would likely cause errors. Optional is immutable.
 * Optional was designed for being return values from methods, not elsewhere like fields or method parameters
 * With Optional, the get() method is a trap, a magnet for misuse, remember it throws an exception if it is empty
 * Remember Optional is a box, a 16 byte object
 *
 * The following rules are courtesy of Stuart Marks in his Devoxx talk on Optional in 2016
 * Rule 1 - never assign null to something of type Optional
 * Rule 2 - never call Optional.get() unless you have proved it is not absent
 * Rule 3 - use alternatives to Optional.isPresent() followed by Optional.get()
 * Rule 4 - generally bad to create an Optional just to method chain a value out of it
 * Rule 5 - if an Optional chain is nested, or you have an Optional of Optional, it is probably too complex
 * Rule 6 - avoid Optional in fields, method parameters and collections
 * Rule 7 - avoid identity-sensitive operations on Optionals (serialization) [Note: identity sensitive operations include ==
 *          and != for example]
 *
 * See: https://www.youtube.com/watch?v=Ej0sss6cq14 (Stuart Marks' talk)
 *      https://blog.joda.org/2015/08/java-se-8-optional-pragmatic-approach.html
 */
public class Optionals {

    /**
     * Main method, for independent running or use via Demo Menu
     * @param args command line
     */
    public static void main(String[] args) {
        Optional<Integer> number = Optional.of(5); // could have used OptionalInt
        Integer integer = number.orElse(7);
        System.out.println("Value of optional, but if empty, the orElse(7) value: " + integer);
        number = Optional.empty();
        integer = number.orElse(7);
        System.out.println("Value of optional, but if empty, the orElse(7) value: " + integer);
//     integer = number.orElseThrow(IllegalStateException::new); // uncomment this line and an exception will be thrown
//     number.map(System.out::println);

        // demo of Optional.map()
        System.out.println("Anyone for Bath? " + passengerNameByDestination(generateSomePassengers(), "Bath"));
        System.out.println("Anyone for Aberdeen? " + passengerNameByDestination(generateSomePassengers(), "Aberdeen"));
        // Other useful Optional methods: .filter() .ifPresent()

        Integer num = 5;
        Integer next = Objects.requireNonNullElse(num, Integer.MAX_VALUE); //interesting.....
        System.out.println("Currently next = " + next);
        System.out.println("Require not null = " + Objects.requireNonNull(num, "Woah!"));
        num = null;
        next = Objects.requireNonNullElse(num, Integer.MAX_VALUE); //interesting.....
        System.out.println("Currently next = " + next);
//     System.out.println("Require not null = " + Objects.requireNonNull(num, "Woah!"));

        /*
            It depends what you want to test but usually, yes

            x == y means these are the same Optional instance

            x.equals(y) means these are Optionals containing the same value

            What's missing is a shallow test: x and y are different Optionals containing the same object instance
         */
        Optional<Passenger> one = Optional.of(new Passenger("Anne", "Bath", 34));
        Optional<Passenger> two = Optional.of(new Passenger("Anne2", "Bath2", 35));
        System.out.println("Compare a - " + ((one == two) ? "Match":"Different"));
        System.out.println("Compare b - " + ((one.equals(two)) ? "Match":"Different"));
        two = Optional.of(new Passenger("Anne", "Bath", 34));
        System.out.println("Compare a - " + ((one == two) ? "Match":"Different"));
        System.out.println("Compare b - " + ((one.equals(two)) ? "Match":"Different"));
        two = Optional.of(one.get());
        System.out.println("Compare a - " + ((one == two) ? "Match":"Different"));
        System.out.println("Compare b - " + ((one.equals(two)) ? "Match":"Different"));
        System.out.println("--ifPresentDemo()--");
        ifPresentDemo();
        orElseThrowDemo();
    }

    private static void ifPresentDemo() {
        Optional<Passenger> optionalPassenger = PopulateOptionals.randomlyPopulate(new Passenger("Anne", "Bath", 34));
//     optionalPassenger = Optional.of(null);
        optionalPassenger.ifPresentOrElse(entity -> {
                    System.out.println(optionalPassenger.get());
                    System.out.println(entity);
                    if (entity.equals(optionalPassenger.get())) System.out.println("Match!");
        },
                () -> System.out.println("Nothing"));
    }

    private static void orElseThrowDemo() {
        int loopIterations = 4;
        DemoUtilities.outputTitle("Example of .orElseThrow()", true);
        for (int i = 1; i <= loopIterations; i++) {
            System.out.println("orElseThrowDemo " + i + " of " + loopIterations);
            try {
                orElseThrowShortExample();
            } catch (NoSuchElementException exception) {
                System.out.println("  It was an empty Optional - " + exception.getMessage());
            }
        }
        DemoUtilities.outputTitle("Example of .orElseThrow(Supplier exceptionSupplier)", true);
        for (int i = 1; i <= loopIterations; i++) {
            System.out.println("orElseThrowDemo " + i + " of " + loopIterations);
            try {
                orElseThrowLongExample();
            } catch (MissingResourceException exception) {
                System.out.println("  It was an empty Optional - " + exception.getMessage());
            }
        }
    }

    private static void orElseThrowShortExample() {
        Optional<String> stringOptional = PopulateOptionals.randomlyPopulate("  The Optional String");
        System.out.println(stringOptional.orElseThrow());
    }

    private static void orElseThrowLongExample() {
        Optional<String> stringOptional = PopulateOptionals.randomlyPopulate("  The Optional String");
        System.out.println(stringOptional.orElseThrow(() -> {
            System.out.println("  Optional is empty");
            return new MissingResourceException("demo", String.class.getName(), "key");
        }));
    }

    static private String passengerNameByDestination(List<Passenger> passList, String destination) {
        return passList.stream()
                .filter(p -> p.getDestination().equalsIgnoreCase(destination))
                .findFirst()
                .map(Passenger::getName) //this is a call on Optional, flatMap() works where Optional is returned
                .orElse("UNKNOWN"); //another call on Optional, chained
    }

    static private List<Passenger> generateSomePassengers() {
        return Arrays.asList(new Passenger("Anne", "Bath", 23),
                new Passenger("Bill", "Chester", 68),
                new Passenger("Charlie", "Derby", 8),
                new Passenger("Dave", "Edinburgh", 27),
                new Passenger("Edward", "Finchley", 31));
    }
}
