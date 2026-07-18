package com.vrms.domain;

/**
 * Represents a van. No special rental restrictions.
 */
public class Van extends Vehicle {

    /**
     * Creates a new van.
     *
     * @param   id       the vehicle's unique id
     * @param   name     the vehicle's name
     * @param   status   the vehicle's initial status
     */
    public Van(String id, String name, VehicleStatus status) {
        super(id, name, status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateForRental(Customer customer) {

    }
}