package com.vrms.domain;

import com.vrms.common.RentalException;

/**
 * Represents a motorcycle. Requires the customer to be at least 18
 * years old.
 */
public class Motorcycle extends Vehicle {

    /**
     * Creates a new motorcycle.
     *
     * @param   id       the vehicle's unique id
     * @param   name     the vehicle's name
     * @param   status   the vehicle's initial status
     */
    public Motorcycle(String id, String name, VehicleStatus status) {
        super(id, name, status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateForRental(Customer customer) {
        if(customer.getAge()<18)throw new RentalException("Motorcycle rental requires the customer to be at least 18 years old");

    }
}