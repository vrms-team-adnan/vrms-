package com.vrms.application;
import com.vrms.domain.Car;
import com.vrms.domain.Rental;
import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class RentalSubjectTest {

    @Test
    void notifyObserversShouldCallNotifyOnRegisteredObserver() {
        
        RentalSubject subject = new RentalSubject();

        NotificationObserver mockObserver = mock(NotificationObserver.class);
        subject.subscribe(mockObserver);

        
        Vehicle vehicle = new Car("1", "Toyota", VehicleStatus.RENTED);
        Rental rental = new Rental("100", vehicle, LocalDate.now(), LocalDate.now().plusDays(3));
        subject.notifyObservers(rental);

       

        verify(mockObserver).notify(rental);
    }
}