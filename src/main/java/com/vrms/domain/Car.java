package com.vrms.domain;

/**
 * Represents a car. No special rental restrictions.
 */
public class Car extends Vehicle {

    /**
     * Creates a new car.
     *
     * @param   id       the vehicle's unique id
     * @param   name     the vehicle's name
     * @param   status   the vehicle's initial status
     */
    public Car(String id, String name, VehicleStatus status) {
        super(id, name, status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateForRental(Customer customer) {

    }
}