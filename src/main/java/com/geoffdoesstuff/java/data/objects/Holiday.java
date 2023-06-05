package com.geoffdoesstuff.java.data.objects;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This is an example data object, and is just used by the demos. The code was generated by IntelliJ IDEA, with minor
 * changes, the alternative was to use Project Lombok, which does the same thing with annotations, and generates on the
 * fly, so the java file is smaller.
 * A more modern alternative to both of the previous solutions is to use a Java Record.
 */
public class Holiday {
    private String destination;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int cost;

    /**
     * Create a new Holiday, with the cost defaulting to zero
     * @param destination location of holiday
     * @param fromDate holiday's start date
     * @param toDate holiday's end date
     */
    public Holiday(String destination, LocalDate fromDate, LocalDate toDate) {
        this.destination = destination;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Create a new Holiday, specifying all the fields
     * @param destination location of holiday
     * @param fromDate holiday's start date
     * @param toDate holiday's end date
     * @param cost cost of holiday
     */
    public Holiday(String destination, LocalDate fromDate, LocalDate toDate, int cost) {
        this.destination = destination;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.cost = cost;
    }

    /**
     * Get the holiday destination
     * @return location of holiday
     */
    public String getDestination() {
        return destination;
    }

    /** Set the holiday destination
     * @param destination location of holiday
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Get the holiday's start date
     * @return holiday's start date
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Set the holiday's start date
     * @param fromDate holiday's start date
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Get the holiday's end date
     * @return holiday's end date
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Set the holiday's end date
     * @param toDate holiday's end date
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    /**
     * Get the cost of the holiday
     * @return cost of holiday
     */
    public int getCost() {
        return cost;
    }

    /**
     * Set the cost of the holiday
     * @param cost cost of holiday
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holiday holiday = (Holiday) o;
        return cost == holiday.cost && Objects.equals(destination, holiday.destination) && Objects.equals(fromDate, holiday.fromDate) && Objects.equals(toDate, holiday.toDate);
    }

    /**
     * We need to support hash codes to allow storing in a hash table, a hashCode is an integer that represents an
     * object, which is used to test equality in a MashMap
     * @return the hash number, based on current field values
     */
    @Override
    public int hashCode() {
        return Objects.hash(destination, fromDate, toDate, cost);
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "destination='" + destination + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", cost=" + cost +
                '}';
    }
}
