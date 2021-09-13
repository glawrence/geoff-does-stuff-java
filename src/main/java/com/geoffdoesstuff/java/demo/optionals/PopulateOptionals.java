package com.geoffdoesstuff.java.demo.optionals;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class PopulateOptionals {
    /*
        This method takes an object and randomly returns the optional with the object in or an empty optional.
        This should mean the code using this then does not show warnings that the test is always true or false.
     */
    public static <T> Optional<T> randomlyPopulate(T object) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        if (random.nextBoolean()) {
            return Optional.of(object);
        } else {
            return Optional.empty();
        }
    }
}
