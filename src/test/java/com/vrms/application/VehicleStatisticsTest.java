package com.vrms.application;

import com.vrms.domain.Car;
import com.vrms.domain.Truck;
import com.vrms.domain.VehicleStatus;
import com.vrms.persistence.InMemoryVehicleRepository;
import com.vrms.persistence.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleStatisticsTest {

    private VehicleStatistics statistics;

    @BeforeEach
    void setUp() {
        VehicleRepository repository = new InMemoryVehicleRepository();
        ((InMemoryVehicleRepository) repository).save(new Car("V1", "Car A", VehicleStatus.AVAILABLE));
        ((InMemoryVehicleRepository) repository).save(new Car("V2", "Car B", VehicleStatus.RENTED));
        ((InMemoryVehicleRepository) repository).save(new Truck("V3", "Truck A", VehicleStatus.AVAILABLE, false));

        statistics = new VehicleStatistics(repository);
    }

    @Test
    void totalCountCountsAllVehicles() {
        assertEquals(3, statistics.getTotalCount());
    }

    @Test
    void availableCountCountsOnlyAvailable() {
        assertEquals(2, statistics.getAvailableCount());
    }

    @Test
    void rentedCountCountsOnlyRented() {
        assertEquals(1, statistics.getRentedCount());
    }

    @Test
    void countByTypeGroupsCorrectly() {
        Map<String, Integer> counts = statistics.getCountByType();
        assertEquals(2, counts.get("Car"));
        assertEquals(1, counts.get("Truck"));
    }
}