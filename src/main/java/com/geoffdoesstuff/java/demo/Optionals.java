package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.data.objects.Passenger;
import com.geoffdoesstuff.java.demo.optionals.PopulateOptionals;
import com.geoffdoesstuff.java.utility.DemoUtilities;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An Optional is either "present" or "absent", where present is non-null, and absent means is empty. However,
 * remember, Optional is not a replacement for null.
 * The idea behind Optional is to provide a limited mechanism for representing "no result" where a
 * null would likely cause errors. Optional is immutable.
 * Optional was designed for being return values from methods, not elsewhere like fields or method parameters
 * With Optional, the get() method is a trap, a magnet for misuse, remember it throws an exception if it is empty
 * Remember Optional is a box, a 16 byte object

 The following rules are courtesy of Stuart Marks in his Devoxx talk on Optional in 2016:
 <ul>
   <li>Rule 1 - never assign null to something of type Optional</li>
   <li>Rule 2 - never call Optional.get() unless you have proved it is not absent</li>
   <li>Rule 3 - use alternatives to Optional.isPresent() followed by Optional.get()</li>
   <li>Rule 4 - generally bad to create an Optional just to method chain a value out of it</li>
   <li>Rule 5 - if an Optional chain is nested, or you have an Optional of Optional, it is probably too complex</li>
   <li>Rule 6 - avoid Optional in fields, method parameters and collections</li>
   <li>Rule 7 - avoid identity-sensitive operations on Optionals (serialization) [Note: identity sensitive operations include == and != for example]</li>
 </ul>
 See: <a href="https://www.youtube.com/watch?v=Ej0sss6cq14">Optional - The Mother of All Bikesheds by Stuart Marks</a><br>
      <a href="https://blog.joda.org/2015/08/java-se-8-optional-pragmatic-approach.html">Stephen Colebourne's blog: Java SE 8 Optional, a pragmatic approach</a>
 */
public class Optionals {

    /**
     * This is here to suppress JavaDoc complaining about not commenting the default constructor
     */
    private Optionals() {
    }

    /**
     * Main method, for independent running or use via MainDemoApp menu
     * @param args command line
     * @see com.geoffdoesstuff.java.MainDemoApp
     */
    public static void main(String[] args) {
        Optional<Integer> number = Optional.of(5); // could have used OptionalInt
        Integer integer = number.orElse(7);
        System.out.println("Value of optional, but if empty, the orElse(7) value: " + integer);
        number = Optional.empty();
        integer = number.orElse(7);
        System.out.println("Value of optional, but if empty, the orElse(7) value: " + integer);

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
        nullDemo();
        orElseDemo();
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

    /**
     * It is important to note that if an Optional contains null then it is considered empty,
     * thus, .isEmpty() returns true. You cannot pass a null into .of(), you need to use
     * .ofNullable(), if you don't you will get a NullPointerException.
     */
    private static void nullDemo() {
        DemoUtilities.outputTitle("Example of .ofNullable()", true);

        reportOptionalState(Optional.of(100));
        reportOptionalState(Optional.ofNullable(null));
        reportOptionalState(Optional.of("100"));
    }

    private static void reportOptionalState(Optional optional) {
        if (optional.isEmpty()) {
            System.out.println("The optional is empty");
        } else {
            System.out.println("The optional contains something of type " + optional.get().getClass());
        }
        if (optional.isPresent()) {
            System.out.println("The optional contains something of type " + optional.get().getClass());
        } else {
            System.out.println("The optional is empty");
        }
    }

    /**
     * Demonstrate the various "orElse" methods on the Optional class.
     */
    private static void orElseDemo() {
        DemoUtilities.outputTitle("Example of .orElseThrow()", true);

        int loopIterations = 4;
        for (int i = 1; i <= loopIterations; i++) {
            System.out.println("orElseThrowDemo " + i + " of " + loopIterations);
            try {
                orElseThrowShortExample();
            } catch (NoSuchElementException exception) {
                System.out.println("  It was an empty Optional - " + exception.getMessage());
            }
        }
        DemoUtilities.outputTitle("Example of .orElseThrow(CustomNoArgException::new)", true);
        for (int i = 1; i <= loopIterations; i++) {
            System.out.println("orElseThrowDemo " + i + " of " + loopIterations);
            try {
                orElseThrowShortExample_AnyException();
            } catch (NullPointerException exception) {
                System.out.println("  It was an empty Optional - " + exception.getMessage());
            }
        }
        DemoUtilities.outputTitle("Example of .orElseThrow(() -> new CustomArgsException(arg1, arg2))", true);
        for (int i = 1; i <= loopIterations; i++) {
            System.out.println("orElseThrowDemo " + i + " of " + loopIterations);
            try {
                orElseThrowShortExample_AnyException_Alt();
            } catch (MissingResourceException exception) {
                System.out.println("  It was an empty Optional - " + exception.getMessage() +
                        ", with classname " + exception.getClassName() +
                        ", with key " + exception.getKey());
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
        DemoUtilities.outputTitle("Example of .orElse(T other)", true);
        for (int i = 1; i <= loopIterations; i++) {
            System.out.println("orElseDemo " + i + " of " + loopIterations);
            try {
                orElseShortExample();
            } catch (MissingResourceException exception) {
                System.out.println("  It was an empty Optional - " + exception.getMessage());
            }
        }
        DemoUtilities.outputTitle("Example of .orElseGet(Supplier exceptionSupplier)", true);
        for (int i = 1; i <= loopIterations; i++) {
            System.out.println("orElseDemo " + i + " of " + loopIterations);
            try {
                orElseLongExample();
            } catch (MissingResourceException exception) {
                System.out.println("  It was an empty Optional - " + exception.getMessage());
            }
        }
        for (int i = 1; i <= loopIterations; i++) {
            System.out.println("orElseDemo " + i + " of " + loopIterations);
            try {
                orElseLongExampleAlt();
            } catch (MissingResourceException exception) {
                System.out.println("  It was an empty Optional - " + exception.getMessage());
            }
        }
    }

    /**
     * Might throw java.util.NoSuchElementException if the Optional is empty
     */
    private static void orElseThrowShortExample() {
        Optional<String> stringOptional = PopulateOptionals.randomlyPopulate("  The Optional String");
        System.out.println(stringOptional.orElseThrow());
    }

    /**
     * Might throw NullPointerException, or any exception with a constructor with no arguments
     */
    private static void orElseThrowShortExample_AnyException() {
        Optional<String> stringOptional = PopulateOptionals.randomlyPopulate("  The Optional String");
        System.out.println(stringOptional.orElseThrow(NullPointerException::new)); // only works if exception has parameterless constructor
    }

    /**
     * Might throw any exception, but allows arguments to be specified
     */
    private static void orElseThrowShortExample_AnyException_Alt() {
        Optional<String> stringOptional = PopulateOptionals.randomlyPopulate("  The Optional String");
        System.out.println(stringOptional.orElseThrow(() -> new MissingResourceException("Missing resource message", "classname_value", "key_value")));
    }

    /**
     * Easy to control which exception is thrown
     */
    private static void orElseThrowLongExample() {
        Optional<String> stringOptional = PopulateOptionals.randomlyPopulate("  The Optional String");
        System.out.println(stringOptional.orElseThrow(() -> {
            System.out.println("  Optional is empty");
            return new MissingResourceException("demo", String.class.getName(), "key");
        }));
    }

    private static void orElseShortExample() {
        Optional<String> stringOptional = PopulateOptionals.randomlyPopulate("  The Optional String");
        System.out.println(stringOptional.orElse(getOrElseString())); // the .orElse() method call is always executed, even when not needed
    }

    private static String getOrElseString() {
        System.out.println("  ~~ inside getOrElseString");
        return "  The 'orElse' string";
    }

    private static void orElseLongExample() {
        Optional<String> stringOptional = PopulateOptionals.randomlyPopulate("  The Optional String");
        System.out.println(stringOptional.orElseGet(Optionals::getOrElseString)); // the .orElseGet() is only executed if needed
    }

    private static void orElseLongExampleAlt() {
        Optional<String> stringOptional = PopulateOptionals.randomlyPopulate("  The Optional String");
        System.out.println(stringOptional.orElseGet(() -> {
            System.out.println("Empty....");
            return "Random number: " + ThreadLocalRandom.current().nextInt(100);
        })); // the .orElseGet() is only executed if needed
    }

    private static String passengerNameByDestination(List<Passenger> passList, String destination) {
        return passList.stream()
                .filter(p -> p.getDestination().equalsIgnoreCase(destination))
                .findFirst()
                .map(Passenger::getName) //this is a call on Optional, flatMap() works where Optional is returned
                .orElse("UNKNOWN"); //another call on Optional, chained
    }

    private static List<Passenger> generateSomePassengers() {
        return Arrays.asList(new Passenger("Anne", "Bath", 23),
                new Passenger("Bill", "Chester", 68),
                new Passenger("Charlie", "Derby", 8),
                new Passenger("Dave", "Edinburgh", 27),
                new Passenger("Edward", "Finchley", 31));
    }
}
