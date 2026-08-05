package com.vrms.persistence;

import com.vrms.domain.Car;
import com.vrms.domain.Customer;
import com.vrms.domain.Rental;
import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryRentalRepositoryTest {

    private InMemoryRentalRepository repository;
    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    void setUp() {
        repository = new InMemoryRentalRepository();
        customer1 = new Customer("c1", "Ahmad", 25, false);
        customer2 = new Customer("c2", "Sara", 30, false);
    }

    @Test
    void findByCustomerIdReturnsOnlyMatchingRentals() {
        Vehicle vehicle = new Car("V1", "Toyota", VehicleStatus.AVAILABLE);

        repository.save(new Rental("R1", vehicle, customer1, LocalDate.now(), LocalDate.now().plusDays(2)));
        repository.save(new Rental("R2", vehicle, customer1, LocalDate.now(), LocalDate.now().plusDays(3)));
        repository.save(new Rental("R3", vehicle, customer2, LocalDate.now(), LocalDate.now().plusDays(1)));

        List<Rental> result = repository.findByCustomerId("c1");

        assertEquals(2, result.size());
    }

    @Test
    void findByCustomerIdReturnsEmptyWhenNoMatch() {
        List<Rental> result = repository.findByCustomerId("unknown");
        assertTrue(result.isEmpty());
    }
}