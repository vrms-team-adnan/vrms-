package com.vrms.domain;

import java.time.LocalDate;

/**
 * Represents a rental record linking a specific vehicle to a rental
 * transaction in the Vehicle Rental Management System.
 */
public class Rental {

    private final String id;
    private final Vehicle vehicle;
    private final Customer customer;
    private final LocalDate startD;
    private final LocalDate endD;
    private RentelStatus status;

    /**
     * Creates a new rental record.
     *
     * @param   id         the unique identifier of the rental
     * @param   vehicle    the vehicle being rented
     * @param   customer   the customer renting the vehicle
     * @param   startD2    the rental start date
     * @param   endD2      the rental end date
     */
    public Rental(String id, Vehicle vehicle, Customer customer, LocalDate startD2, LocalDate endD2) {
        this.id = id;
        this.vehicle = vehicle;
        this.customer = customer;
        this.startD = startD2;
        this.endD = endD2;
        this.status = RentelStatus.RUNNING;
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
     * Returns the customer associated with this rental.
     *
     * @return  the customer who rented the vehicle
     */
    public Customer getCustomer() {
        return customer;
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

    /**
     * Changes the rental status to closed
     */
    public void closed() {
        this.status = RentelStatus.CLOSED;
    }

    /**
     * Get rental status
     * @return the rental status
     */
    public RentelStatus getStatus() {
        return status;
    }
}