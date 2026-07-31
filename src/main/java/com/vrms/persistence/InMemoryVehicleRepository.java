package com.vrms.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.vrms.domain.Vehicle;

/**
 * In-memory implementation of VehicleRepository. Stores vehicles in a
 * map while the program is running.
 */
public class InMemoryVehicleRepository implements VehicleRepository {
    private final Map<String, Vehicle> vehicles = new HashMap<>();

    /**
     * Saves a vehicle in the repository.
     *
     * @param   vehicle   the vehicle to save
     */
    public void save(Vehicle vehicle) {
        vehicles.put(vehicle.getId(), vehicle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vehicle> findAll() {
        return new ArrayList<>(vehicles.values());
    }

	
}