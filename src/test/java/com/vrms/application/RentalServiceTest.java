package com.vrms.application;
import com.vrms.domain.Car;
import com.vrms.domain.Rental;
import com.vrms.domain.RentelStatus;
import com.vrms.common.RentalException;
import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
import com.vrms.persistence.InMemoryRentalRepository;
import com.vrms.persistence.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import com.vrms.application.strategy.carRentalStrategy;
import static org.junit.jupiter.api.Assertions.assertEquals;
// Test 1 and 2 for US2.1 Test 3 for US2.2

public class RentalServiceTest {
    
private RentalService rentalService;
private RentalRepository rentalRepository;
 @BeforeEach
 void setUp(){
     rentalRepository = new InMemoryRentalRepository();
     rentalService = new RentalService(rentalRepository, new carRentalStrategy(50, 20));
 }
 @Test
 void rentVehicleSucceed(){
    Vehicle vehicle=new Car("2", "BMW", VehicleStatus.AVAILABLE);
    rentalService.rentVehicle("3", vehicle,LocalDate.of(2026, 10, 1) ,LocalDate.of(2026, 10, 10) );
    assertEquals(VehicleStatus.RENTED, vehicle.getStatus());

 }
 @Test
 void rentVehicleNotAvailable(){
    Vehicle vehicle=new Car("2", "BMW", VehicleStatus.RENTED);
    assertThrows(RentalException.class, () -> {
    rentalService.rentVehicle("someId", vehicle,LocalDate.of(2026, 10, 1) ,LocalDate.of(2026, 10, 10));
});
 }
 @Test
 void rentDoubleBooking(){
    Vehicle vehicle=new Car("2", "BMW", VehicleStatus.AVAILABLE);
rentalService.rentVehicle("1", vehicle,LocalDate.of(2026, 10, 1) ,LocalDate.of(2026, 10, 10));
    assertThrows(RentalException.class, () -> {
  rentalService.rentVehicle("1", vehicle,LocalDate.of(2026, 10, 1) ,LocalDate.of(2026, 10, 10));
}); 
 }
 @Test
 void rentWithNulls(){
    Vehicle vehicle=new Car("2", "BMW", VehicleStatus.AVAILABLE);
    assertThrows(RentalException.class, () -> {
  rentalService.rentVehicle("1", vehicle,null ,LocalDate.of(2026, 10, 10));
}); 
    assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
 }
 @Test
 void rentWithNulle(){
    Vehicle vehicle=new Car("2", "BMW", VehicleStatus.AVAILABLE);
    assertThrows(RentalException.class, () -> {
  rentalService.rentVehicle("1", vehicle,LocalDate.of(2026, 10, 10),null);
}); 
    assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
 }
 @Test
 void rentWhithErrorDate(){
    Vehicle vehicle=new Car("2", "BMW", VehicleStatus.AVAILABLE);
    assertThrows(RentalException.class, () -> {
  rentalService.rentVehicle("1", vehicle,LocalDate.of(2026, 10, 10),LocalDate.of(2026, 10, 1));
}); 
    assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
 }
 
 @Test
 void rentWithEndDate(){
   Vehicle vehicle=new Car("9", "GOLF", VehicleStatus.AVAILABLE);
   assertThrows(RentalException.class,()->{
  rentalService.rentVehicle("7", vehicle, LocalDate.now(), LocalDate.now().plusDays(40));
   });
 }
 
 //4.1
 @Test
 void returnVehicle() {
	 Vehicle vehicle=new Car("5","Skuda",VehicleStatus.AVAILABLE);
	 rentalService.rentVehicle("ren-50",vehicle,LocalDate.of(2026, 10,1),LocalDate.of(2026, 10,10));
	 rentalService.returnV("ren-50");
	 Rental rental=rentalRepository.findById("ren-50");
	 assertEquals(VehicleStatus.AVAILABLE,vehicle.getStatus());
	 assertEquals(RentelStatus.CLOSED,rental.getStatus());
 }
 
 @Test
 void returnVehicleIsClose() {
	 Vehicle vehicle=new Car("5","Skuda",VehicleStatus.AVAILABLE);
	 rentalService.rentVehicle("ren-50",vehicle,LocalDate.of(2026, 10,1),LocalDate.of(2026, 10,10));
	 rentalService.returnV("ren-50");
	 assertThrows(RentalException.class,()->{
		 rentalService.returnV("ren-50"); 
	 });
 }
 @Test
 void returnVehicleNotFound() {
	 assertThrows(RentalException.class,()->{
		 rentalService.returnV("ren-50"); 
	 });
 }
 @Test
 void calcRentalCost() {
     Vehicle vehicle = new Car("20", "BMW", VehicleStatus.AVAILABLE);
     rentalService.rentVehicle("ren-60",vehicle,LocalDate.of(2026, 10,1),LocalDate.of(2026, 10,10));
     double cost=rentalService.costrental("ren-60");
     assertEquals(450, cost);
 }
 @Test
 void calcRentalCostNotFound() {
     assertThrows(RentalException.class, () -> {
         rentalService.costrental("not-found");
     });
 }
 @Test
 void calcLateP() {
     Vehicle vehicle = new Car("21", "Toyota", VehicleStatus.AVAILABLE);
     rentalService.rentVehicle("rent-21",vehicle,LocalDate.of(2026, 10, 1),LocalDate.of(2026, 10, 10));
     double penalty = rentalService.costlate("rent-21",LocalDate.of(2026, 10, 12));
     assertEquals(40, penalty);
 }
 @Test
 void calcLatePOnTime() {
     Vehicle vehicle = new Car("22", "Golf", VehicleStatus.AVAILABLE);
     rentalService.rentVehicle("rent-22",vehicle,LocalDate.of(2026, 10, 1),LocalDate.of(2026, 10, 10) );
     double penalty = rentalService.costlate( "rent-22", LocalDate.of(2026, 10, 10));
     assertEquals(0, penalty);
 }
 @Test
 void calculateTotalCost() {
     Vehicle vehicle = new Car("24", "Mercedes", VehicleStatus.AVAILABLE);
     rentalService.rentVehicle( "ren-24",vehicle,LocalDate.of(2026, 10, 1),LocalDate.of(2026, 10, 10));
     double total = rentalService.totalcost("ren-24",LocalDate.of(2026, 10, 12));
     assertEquals(490, total);
 }
}
