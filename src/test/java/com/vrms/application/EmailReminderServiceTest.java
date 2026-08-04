package com.vrms.application;

import com.vrms.domain.Car;
import com.vrms.domain.Customer;
// This test calls EmailReminderService directly (no mock) to make sure
// JaCoCo actually covers its real logic, since RentalSubjectTest only
// tests it through a mocked NotificationObserver.

import com.vrms.domain.Rental;
import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EmailReminderServiceTest {

    @Test
    void notifyShouldNotThrowException() {
        Vehicle vehicle = new Car("2", "Seat", VehicleStatus.AVAILABLE);
        Customer customer = new Customer("c1", "Test Customer", 25, false);
        Rental rental = new Rental("4", vehicle, customer, LocalDate.now(), LocalDate.now().plusDays(3));
        EmailReminderService service = new EmailReminderService();
        assertDoesNotThrow(() -> {
            service.notify(rental);
        });
    }
}