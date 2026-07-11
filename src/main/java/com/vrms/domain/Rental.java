package com.vrms.domain;

import java.time.LocalDate;

/**
 * Represents a rental record linking a specific vehicle to a rental
 * transaction in the Vehicle Rental Management System.
 */
public class Rental {

    private final String id;
    private final Vehicle vehicle;
    private final LocalDate startD;
    private final LocalDate endD;

    /**
     * Creates a new rental record.
     *
     * @param   id        the unique identifier of the rental
     * @param   vehicle   the vehicle being rented
     * @param   startD2   the rental start date
     * @param   endD2     the rental end date
     */
    public Rental(String id, Vehicle vehicle, LocalDate startD2, LocalDate endD2) {
        this.id = id;
        this.vehicle = vehicle;
        this.startD = startD2;
        this.endD = endD2;
    }

    /**
     * Returns the unique identifier of this rental.
     *
     * @return  the rental id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the vehicle associated with this rental.
     *
     * @return  the rented vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Returns the start date of this rental.
     *
     * @return  the start date
     */
    public LocalDate getStartD() {
        return startD;
    }

    /**
     * Returns the end date of this rental.
     *
     * @return  the end date
     */
    public LocalDate getEndD() {
        return endD;
    }
}