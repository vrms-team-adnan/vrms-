package com.vrms.application;

import com.vrms.common.RentalException;
import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
import com.vrms.persistence.InMemoryRentalRepository;
import com.vrms.persistence.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalServiceTest {
    
private RentalService rentalService;
 @BeforeEach
 void setUp(){
     RentalRepository rentalRepository = new InMemoryRentalRepository();
        rentalService = new RentalService(rentalRepository);
 }
 @Test
 void rentVehicleSucceed(){
    Vehicle vehicle=new Vehicle("2", "BMW", VehicleStatus.AVAILABLE);
    rentalService.rentVehicle("3", vehicle);
    assertEquals(VehicleStatus.RENTED, vehicle.getStatus());

 }
 @Test
 void rentVehicleNotAvailable(){
    Vehicle vehicle=new Vehicle("2", "BMW", VehicleStatus.RENTED);
    assertThrows(RentalException.class, () -> {
    rentalService.rentVehicle("someId", vehicle);
});
 }

}
