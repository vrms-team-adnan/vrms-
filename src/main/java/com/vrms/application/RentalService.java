package com.vrms.application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.vrms.common.RentalException;
import com.vrms.domain.Rental;
import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
import com.vrms.persistence.RentalRepository;

/**
 * Handles the logic for renting a vehicle.
 */
public class RentalService {
    private final RentalRepository rentalRepository;

    /**
     * Creates a new RentalService.
     *
     * @param   rentalRepository   the repository used to save rentals
     */
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    /**
     * Rents a vehicle for the given period. Validates the dates and the
     * vehicle's availability, then creates a rental record and marks the
     * vehicle as rented.
     *
     * @param   rentId    the unique identifier of the rental
     * @param   vehicle   the vehicle to rent
     * @param   startD    the rental start date
     * @param   endD      the rental end date
     * @throws  RentalException   if a date is null, the start date is
     *          after the end date, the duration exceeds 30 days, or the
     *          vehicle is not available
     */
    public void rentVehicle(String rentId, Vehicle vehicle, LocalDate startD, LocalDate endD) {
        if (startD == null || endD == null) {
            throw new RentalException("Cannot rent vehicle: Date is null");
        }

        if (startD.isAfter(endD)) {
            throw new RentalException("Cannot rent vehicle: error of date >> start Date is after end Date");
        }

        long days = ChronoUnit.DAYS.between(startD, endD);
        if (days > 30) {
            throw new RentalException("Cannot rent vehicle: rental duration exceeds maximum allowed (30 days)");
        }

        if (!vehicle.isAvailable()) {
            throw new RentalException("Cannot rent vehicle: vehicle is not available");
        }

        Rental rental = new Rental(rentId, vehicle, startD, endD);
        rentalRepository.save(rental);

        vehicle.setStatus(VehicleStatus.RENTED);
    }
}