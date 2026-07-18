package com.vrms.application.strategy;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vrms.domain.Car;
import com.vrms.domain.Rental;
import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
class CarRentalStrategyTest {
	private carRentalStrategy strategy;
	private Rental rental;
	@BeforeEach
    void setUp() {
		strategy=new carRentalStrategy(50,20);
		Vehicle vehicle =new Car("1","kya",VehicleStatus.RENTED);
		rental=new Rental("1ren",vehicle,LocalDate.of(2026, 10, 1),LocalDate.of(2026, 10, 10));
	}

	@Test
	void calcRentcost() {
		double cost=strategy.calcRental(rental);
		assertEquals(450, cost);
	}
	@Test
    void returnBeforeEndDateHasNoPenalty() {
        LocalDate returnDate =LocalDate.of(2026,10,8);
        double penalty = strategy.calcLateP(rental, returnDate);
        assertEquals(0, penalty);
    }
	@Test
    void returnOnEndDateHasNoPenalty() {
        LocalDate returnDate = LocalDate.of(2026, 10, 10);
        double penalty = strategy.calcLateP(rental, returnDate);
        assertEquals(0, penalty);
    }
	@Test
    void returnTwoDaysLateHasPenalty() {
        LocalDate returnDate = LocalDate.of(2026, 10, 12);
        double penalty = strategy.calcLateP(rental, returnDate);
        assertEquals(40, penalty);
    }
}
