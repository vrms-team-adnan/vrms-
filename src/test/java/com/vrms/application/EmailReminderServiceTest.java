package com.vrms.application;

import com.vrms.domain.Car;

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
        Vehicle vehicle=new Car("2", "Seat", VehicleStatus.AVAILABLE);
        Rental rental = new Rental("4", vehicle, LocalDate.now(), LocalDate.now().plusDays(3));
        EmailReminderService service = new EmailReminderService();
        assertDoesNotThrow(() -> {
    service.notify(rental);
});
    }
}