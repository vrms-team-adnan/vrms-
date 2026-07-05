package com.vrms.persistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.vrms.domain.Vehicle;
public class InMemoryVehicleRepository implements VehicleRepository {
	private final Map<String, Vehicle> vehicles = new HashMap<>();
	public void save(Vehicle vehicle) {
		vehicles.put(vehicle.getId(), vehicle);
	}

	@Override
	public List<Vehicle> findAll() {
   return new ArrayList<>(vehicles.values());
	}
	 

}
