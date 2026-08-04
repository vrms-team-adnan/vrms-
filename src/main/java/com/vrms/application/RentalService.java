package com.vrms.application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.vrms.common.RentalException;
import com.vrms.domain.Rental;
import com.vrms.domain.RentelStatus;
import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
import com.vrms.persistence.RentalRepository;
import com.vrms.application.strategy.CarRentalStrategy;
import com.vrms.application.strategy.RentalPricingStrategy;
import com.vrms.domain.Customer;
/**
 * Handles the logic for renting a vehicle.
 */
public class RentalService {
    private final RentalRepository rentalRepository;
    private final RentalPricingStrategy priciStategy;

    /**
     * Creates a new RentalService.
     *
     * @param   rentalRepository   the repository used to save rentals
     */
    public RentalService(RentalRepository rentalRepository) {
        this(rentalRepository,new CarRentalStrategy(50, 20));
    }
    /**
     * Creates a rental service with a pricing strategy
     * @param rentalRepository2 the rental repository
     * @param priciStategy the pricing strategy
     */

    public RentalService(RentalRepository rentalRepository2, RentalPricingStrategy priciStategy) {
    	this.rentalRepository = rentalRepository2;
    	this.priciStategy = priciStategy;
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
 * @param   customer  the customer requesting the rental
 * @throws  RentalException   if a date is null, the start date is
 *          after the end date, the duration exceeds 30 days, or the
 *          vehicle is not available
 */
    public void rentVehicle(String rentId, Vehicle vehicle, LocalDate startD, LocalDate endD,Customer customer) {
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
       vehicle.validateForRental(customer);
        Rental rental = new Rental(rentId, vehicle, customer,startD, endD);
        rentalRepository.save(rental);

        vehicle.setStatus(VehicleStatus.RENTED);
    }
    /**
     * Return the car (Make it close)
     * change the  Vehicle Status to AVAILABLE
     * @param rentId the rental id
     * @throws RentalException if the rental is not found or is already closed
     */
    public void returnV(String rentId) {
    	Rental rental=rentalRepository.findById(rentId);
    	if(rental==null) {
    		throw new RentalException("Cannot return vehicle: rental not found");
    	}
    	if(rental.getStatus()==RentelStatus.CLOSED)
    	{
    		throw new RentalException("Rental is already closed");
    	}
    	rental.closed();
    	rental.getVehicle().setStatus(VehicleStatus.AVAILABLE);
    }
    
    /**
     * Calculates the rental cost
     * @param rentId the rental id
     * @return the rental cost
     * @throws RentalException if the rental is not found
     */
    public double costrental(String rentId) {
    	Rental rental=rentalRepository.findById(rentId);
    	if(rental==null) {
    		throw new RentalException("Cannot return vehicle: rental not found");
    	}
    	return priciStategy.calcRental(rental);
    	
    }
    
    /**
     * Calculates the late return fee
     * @param rentId the rental id
     * @param returnD the real return date
     * @return the late fee
     * @throws RentalException if the rental is not found or the date is null
     */
    public double costlate(String rentId,LocalDate returnD) {
    	Rental rental=rentalRepository.findById(rentId);
    	if(rental==null) {
    		throw new RentalException("Cannot return vehicle: rental not found");
    	}
    	if (returnD == null) {
            throw new RentalException("Cannot calculate late penalty: return date is null");
        }
    	return priciStategy.calcLateP(rental, returnD);
    }
    
    /**
     * Calculates the rental cost and late fee together
     * @param rentId the rental id
     * @param returnD the real return date
     * @return the total cost
     * @throws RentalException if the rental is not found or the date is null
     */
    public double totalcost(String rentId,LocalDate returnD)
    {
    	Rental rental=rentalRepository.findById(rentId);
    	if(rental==null) {
    		throw new RentalException("Cannot return vehicle: rental not found");
    	}
    	if (returnD == null) {
            throw new RentalException("Cannot calculate total cost: return date is null");
        }
    	return priciStategy.calcRental(rental)+priciStategy.calcLateP(rental, returnD);
    }
}