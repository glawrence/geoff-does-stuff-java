package com.geoffdoesstuff.java.data.objects;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This is an example data object, and is just used by the demos
 */
public class Holiday {
    private String destination;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int cost;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Holiday(String destination, LocalDate fromDate, LocalDate toDate) {
        this.destination = destination;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Holiday(String destination, LocalDate fromDate, LocalDate toDate, int cost) {
        this.destination = destination;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holiday holiday = (Holiday) o;
        return cost == holiday.cost && Objects.equals(destination, holiday.destination) && Objects.equals(fromDate, holiday.fromDate) && Objects.equals(toDate, holiday.toDate);
    }

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
