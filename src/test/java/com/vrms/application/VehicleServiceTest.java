package com.vrms.application;
import com.vrms.domain.Car;
import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
import com.vrms.persistence.InMemoryVehicleRepository;
import com.vrms.persistence.VehicleRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
class VehicleServiceTest {
	private VehicleService vehicleService;
	@BeforeEach
	void setUp() {
		VehicleRepository vehicleRepository=new InMemoryVehicleRepository();
		((InMemoryVehicleRepository) vehicleRepository).save(new Car("1","Kya",VehicleStatus.AVAILABLE));
		((InMemoryVehicleRepository) vehicleRepository).save(new Car("2","merceses",VehicleStatus.AVAILABLE));
		((InMemoryVehicleRepository) vehicleRepository).save(new Car("3","BMW",VehicleStatus.RENTED));
		
		vehicleService = new VehicleService(vehicleRepository);
	}

	
	@Test
	void availableVehicles() {
		List<Vehicle> result=getAvailableVehiclesSuccessfuly();
		assertTrue(2==result.size());
	}

	private List<Vehicle> getAvailableVehiclesSuccessfuly() {
		return vehicleService.getAvailableVehicles();
	}
	
	
	@Test
	void rentedVehiclesHidden() {
		List<Vehicle> result =getAvailableVehiclesSuccessfuly();
		boolean rentedVehicleFound=false;
		for (Vehicle vehicle:result) {
			if (vehicle.isRented()) {
				rentedVehicleFound=true;
			}
		}
		assertFalse(rentedVehicleFound);
	}
	
	
	@Test
	void allDisplayedVehiclesAreAvailable() {
		List<Vehicle> result=getAvailableVehiclesSuccessfuly();
		boolean allVehiclesAvailable=true;
		for (Vehicle vehicle:result) {
			if (!vehicle.isAvailable()) {
				allVehiclesAvailable =false;
				
			}
		}
		assertTrue(allVehiclesAvailable);
	}
}
