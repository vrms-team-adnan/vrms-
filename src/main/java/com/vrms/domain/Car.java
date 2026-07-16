package com.vrms.domain;

// Represents a car. No special rental restrictions.
public class Car extends Vehicle {

    public Car(String id, String name, VehicleStatus status) {
        super(id, name, status);
    }

    @Override
    public void validateForRental(int customerAge) {
        
    }
}