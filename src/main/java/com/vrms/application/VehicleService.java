package com.vrms.application;

import java.util.ArrayList;
import java.util.List;
import com.vrms.domain.Vehicle;
import com.vrms.persistence.VehicleRepository;

/**
 * Handles retrieving available vehicles.
 */
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    /**
     * Creates a new VehicleService.
     *
     * @param   vehicleRepository   the repository used to look up vehicles
     */
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Returns all vehicles that are currently available for rental.
     *
     * @return  a list of available vehicles
     */
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicleRepository.findAll()) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }
}