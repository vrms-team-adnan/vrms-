package com.vrms.domain;

import com.vrms.common.RentalException;

/**
 * Represents a truck. Requires the customer to have a special license.
 */
public class Truck extends Vehicle {

    private final boolean requiresSpecialLicense;

    /**
     * Creates a new truck.
     *
     * @param   id                        the vehicle's unique id
     * @param   name                      the vehicle's name
     * @param   status                    the vehicle's initial status
     * @param   requiresSpecialLicense    whether this truck requires a
     *                                    special license
     */
    public Truck(String id, String name, VehicleStatus status, boolean requiresSpecialLicense) {
        super(id, name, status);
        this.requiresSpecialLicense = requiresSpecialLicense;
    }

    /**
     * Returns whether this truck requires a special license to rent.
     *
     * @return  true if a special license is required
     */
    public boolean requiresSpecialLicense() {
        return requiresSpecialLicense;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateForRental(Customer customer) {
        if (requiresSpecialLicense && !customer.hasSpecialLicense()) {
            throw new RentalException("This truck requires a special license");
        }
    }
}