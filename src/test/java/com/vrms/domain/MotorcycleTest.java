package com.vrms.domain;

import com.vrms.common.RentalException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MotorcycleTest {

    @Test
    void validateSucceedForAdult() {
        Motorcycle motorcycle = new Motorcycle("1", "Harley", VehicleStatus.AVAILABLE);
        Customer customer = new Customer("c1", "Adult Customer", 25, false);

       assertDoesNotThrow(() -> {
    motorcycle.validateForRental(customer);
});
    }

    @Test
    void validateThrowForMinor() {
        Motorcycle motorcycle = new Motorcycle("2", "Yamaha", VehicleStatus.AVAILABLE);
        Customer customer = new Customer("c2", "Young Customer", 16, false);

assertThrows(RentalException.class, () -> {
    motorcycle.validateForRental(customer);
});
    }
}