package com.vrms.persistence;

import java.util.List;
import com.vrms.domain.Vehicle;

/**
 * Interface for retrieving vehicles.
 */
public interface VehicleRepository {

    /**
     * Returns all vehicles in the repository.
     *
     * @return  a list of all vehicles
     */
    List<Vehicle> findAll();
}