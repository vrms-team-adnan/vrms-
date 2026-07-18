package com.vrms.domain;

import com.vrms.common.RentalException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TruckTest {

    @Test
    void succeedsWhenLicenseNotRequired() {
        Truck truck=new Truck("6", "Volovo", VehicleStatus.AVAILABLE, false);
        Customer customer = new Customer("9", " Customer", 25, false);
        assertDoesNotThrow(() -> {
    truck.validateForRental(customer);
});
    }

    @Test
    void succeedsWhenCustomerHasLicense() {
            Truck truck=new Truck("9", "Marcdec", VehicleStatus.AVAILABLE, true);
        Customer customer = new Customer("9", " Customer", 25, true);
        assertDoesNotThrow(() -> {
    truck.validateForRental(customer);
});
    }

    @Test
    void throwsWhenLicenseMissing() {
         Truck truck=new Truck("9", "Marcdec", VehicleStatus.AVAILABLE, true);
        Customer customer = new Customer("9", " Customer", 25, false);
assertThrows(RentalException.class, () -> {
    truck.validateForRental(customer);
});    }
}