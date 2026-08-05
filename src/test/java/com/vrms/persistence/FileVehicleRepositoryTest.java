package com.vrms.persistence;

import com.vrms.domain.Car;
import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileVehicleRepositoryTest {

    private static final String TEST_FILE = "target/test-vehicles.csv";

    @AfterEach
    void cleanUp() {
        new File(TEST_FILE).delete();
    }

    @Test
    void saveAndFindAllShouldReturnSavedVehicle() {
        FileVehicleRepository repository = new FileVehicleRepository(TEST_FILE);
        Vehicle vehicle = new Car("V1", "Test Car", VehicleStatus.AVAILABLE);

        repository.save(vehicle);
        List<Vehicle> vehicles = repository.findAll();

        assertEquals(1, vehicles.size());
        assertEquals("V1", vehicles.get(0).getId());
        assertEquals("Test Car", vehicles.get(0).getName());
        assertTrue(new File(TEST_FILE).exists());
    }
}