package com.geoffdoesstuff.java.data.objects;

import java.time.LocalDateTime;

/**
 * This is an example data object, and is just used by the demos
 */
public class Passenger {
    private final String name;
    private String destination;
    private final int age;
    private final LocalDateTime created;

    /**
     * Construct a new Passenger object
     * @param name the passenger's name
     * @param destination their destination
     * @param age their age
     */
    public Passenger(String name, String destination, int age) {
        this.name = name;
        this.destination = destination;
        this.age = age;
        created = LocalDateTime.now();
    }

    /**
     * Set the destination
     * @param destination where they are going
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Get the passenger's name
     * @return their name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the passenger's destination
     * @return their destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Get the passenger's age
     * @return their age
     */
    public int getAge() {
        return age;
    }

    /**
     * Get the time the passenger object was created
     * @return date and time of creation
     */
    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", age=" + age +
                '}';
    }
}
