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

    public Passenger(String name, String destination, int age) {
        this.name = name;
        this.destination = destination;
        this.age = age;
        created = LocalDateTime.now();
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public int getAge() {
        return age;
    }

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
