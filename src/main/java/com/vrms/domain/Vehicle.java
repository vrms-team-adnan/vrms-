package com.vrms.domain;

/**
 * Represents a vehicle available for rental.
 */
public abstract class Vehicle {
    private String id;
    private String name;
    private VehicleStatus status;

    /**
     * Creates a new vehicle.
     *
     * @param   id       the vehicle's unique id
     * @param   name     the vehicle's name
     * @param   status   the vehicle's initial status
     */
    public Vehicle(String id, String name, VehicleStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    /**
     * Returns the vehicle's id.
     *
     * @return  the id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the vehicle's name.
     *
     * @return  the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the vehicle's current status.
     *
     * @return  the status
     */
    public VehicleStatus getStatus() {
        return status;
    }

    /**
     * Updates the vehicle's status.
     *
     * @param   status   the new status
     */
    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    /**
     * Checks whether the vehicle is available for rental.
     *
     * @return  true if available, false otherwise
     */
    public boolean isAvailable() {
        return status == VehicleStatus.AVAILABLE;
    }

    /**
     * Checks whether the vehicle is currently rented.
     *
     * @return  true if rented, false otherwise
     */
    public boolean isRented() {
        return status == VehicleStatus.RENTED;
    }

    /**
 * Validates whether this vehicle can be rented, based on type-specific
 * rules (e.g. age restrictions, license checks, battery checks).
 *
 * @param   customerAge   the customer's age
 */

    public abstract void validateForRental(int customerAge);
}