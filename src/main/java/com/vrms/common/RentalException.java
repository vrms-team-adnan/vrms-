package com.vrms.common;

/**
 * Thrown when trying to rent a vehicle that is not available for rental,
 * or when rental data is invalid (e.g. wrong dates).
 */
public class RentalException extends RuntimeException {

    /**
     * Creates a new RentalException.
     *
     * @param   message   the error message
     */
    public RentalException(String message) {
        super(message);
    }
}