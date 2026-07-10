package com.vrms.application;

import com.vrms.common.RentalException;
import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
import com.vrms.persistence.InMemoryRentalRepository;
import com.vrms.persistence.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
// Test 1 and 2 for US2.1 Test 3 for US2.2

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
    rentalService.rentVehicle("3", vehicle,LocalDate.of(2026, 10, 1) ,LocalDate.of(2026, 10, 10) );
    assertEquals(VehicleStatus.RENTED, vehicle.getStatus());

 }
 @Test
 void rentVehicleNotAvailable(){
    Vehicle vehicle=new Vehicle("2", "BMW", VehicleStatus.RENTED);
    assertThrows(RentalException.class, () -> {
    rentalService.rentVehicle("someId", vehicle,LocalDate.of(2026, 10, 1) ,LocalDate.of(2026, 10, 10));
});
 }
 @Test
 void rentDoubleBooking(){
    Vehicle vehicle=new Vehicle("2", "BMW", VehicleStatus.AVAILABLE);
rentalService.rentVehicle("1", vehicle,LocalDate.of(2026, 10, 1) ,LocalDate.of(2026, 10, 10));
    assertThrows(RentalException.class, () -> {
  rentalService.rentVehicle("1", vehicle,LocalDate.of(2026, 10, 1) ,LocalDate.of(2026, 10, 10));
}); 
 }
 @Test
 void rentWithNulls(){
    Vehicle vehicle=new Vehicle("2", "BMW", VehicleStatus.AVAILABLE);
    assertThrows(RentalException.class, () -> {
  rentalService.rentVehicle("1", vehicle,null ,LocalDate.of(2026, 10, 10));
}); 
    assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
 }
 @Test
 void rentWithNulle(){
    Vehicle vehicle=new Vehicle("2", "BMW", VehicleStatus.AVAILABLE);
    assertThrows(RentalException.class, () -> {
  rentalService.rentVehicle("1", vehicle,LocalDate.of(2026, 10, 10),null);
}); 
    assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
 }
 @Test
 void rentWhithErrorDate(){
    Vehicle vehicle=new Vehicle("2", "BMW", VehicleStatus.AVAILABLE);
    assertThrows(RentalException.class, () -> {
  rentalService.rentVehicle("1", vehicle,LocalDate.of(2026, 10, 10),LocalDate.of(2026, 10, 1));
}); 
    assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
 }
 
 @Test
 void rentWithEndDate(){
   Vehicle vehicle=new Vehicle("9", "GOLF", VehicleStatus.AVAILABLE);
   assertThrows(RentalException.class,()->{
  rentalService.rentVehicle("7", vehicle, LocalDate.now(), LocalDate.now().plusDays(40));
   });
 }
}
