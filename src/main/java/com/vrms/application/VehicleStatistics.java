package com.vrms.application;

import com.vrms.domain.Vehicle;
import com.vrms.persistence.VehicleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides simple statistics about the vehicle fleet: how many vehicles
 * are available, how many are rented, and how many exist of each type.
 */
public class VehicleStatistics {

    private final VehicleRepository vehicleRepository;

    /**
     * Creates a new VehicleStatistics.
     *
     * @param   vehicleRepository   the repository used to look up vehicles
     */
    public VehicleStatistics(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Returns the total number of vehicles in the fleet.
     *
     * @return  the total vehicle count
     */
    public int getTotalCount() {
        return vehicleRepository.findAll().size();
    }

    /**
     * Returns the number of vehicles that are currently available.
     *
     * @return  the available vehicle count
     */
    public int getAvailableCount() {
        int count = 0;
        for (Vehicle vehicle : vehicleRepository.findAll()) {
            if (vehicle.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of vehicles that are currently rented.
     *
     * @return  the rented vehicle count
     */
    public int getRentedCount() {
        int count = 0;
        for (Vehicle vehicle : vehicleRepository.findAll()) {
            if (vehicle.isRented()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of vehicles of each type (e.g. Car, Truck...).
     *
     * @return  a map from vehicle type name to count
     */
    public Map<String, Integer> getCountByType() {
        Map<String, Integer> counts = new HashMap<>();
        List<Vehicle> vehicles = vehicleRepository.findAll();

        for (Vehicle vehicle : vehicles) {
            String type = vehicle.getClass().getSimpleName();
            counts.put(type, counts.getOrDefault(type, 0) + 1);
        }

        return counts;
    }
}