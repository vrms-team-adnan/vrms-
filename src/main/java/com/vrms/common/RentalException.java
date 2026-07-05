package com.vrms.common;

// Thrown when trying to rent a vehicle that is not available for rental.
public class RentalException extends RuntimeException {

    public RentalException(String message) {
        super(message);
    }
}