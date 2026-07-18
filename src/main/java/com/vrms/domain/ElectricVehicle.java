package com.vrms.domain;

import com.vrms.common.RentalException;

/**
 * Represents an electric vehicle. Requires a minimum battery level to
 * be rented.
 */
public class ElectricVehicle extends Vehicle {

    private final int batteryLevel;

    /**
     * Creates a new electric vehicle.
     *
     * @param   id             the vehicle's unique id
     * @param   name           the vehicle's name
     * @param   status         the vehicle's initial status
     * @param   batteryLevel   the current battery level (0-100)
     */
    public ElectricVehicle(String id, String name, VehicleStatus status, int batteryLevel) {
        super(id, name, status);
        this.batteryLevel = batteryLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateForRental(Customer customer) {
        if (batteryLevel < 20) {
            throw new RentalException("This electric vehicle's battery is too low to be rented");
        }
    }
}