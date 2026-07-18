package com.vrms.domain;

import com.vrms.common.RentalException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ElectricVehicleTest {

    @Test
    void succeedsWhenBatteryOk() {
        ElectricVehicle vehicle = new ElectricVehicle("1", "Tesla", VehicleStatus.AVAILABLE, 50);
        Customer customer = new Customer("c1", "Customer", 25, false);

        
        assertDoesNotThrow(() -> {
            vehicle.validateForRental(customer);
        });
    }

    @Test
    void throwsWhenBatteryLow() {
        ElectricVehicle vehicle = new ElectricVehicle("2", "Leaf", VehicleStatus.AVAILABLE, 10);
        Customer customer = new Customer("c2", "Customer", 25, false);

        assertThrows(RentalException.class, () -> {
            vehicle.validateForRental(customer);
        });
    }
}