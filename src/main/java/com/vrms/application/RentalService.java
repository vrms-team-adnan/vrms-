package com.vrms.application;
import java.time.LocalDate;


import com.vrms.common.RentalException;
import com.vrms.domain.Rental;
import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
import com.vrms.persistence.RentalRepository;
// This class handles the logic for renting a vehicle.

public class RentalService {
    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository){
this.rentalRepository=rentalRepository;
    }
    public void rentVehicle(String rentId,Vehicle vehicle, LocalDate startD,LocalDate endD){
    	if(startD==null||endD==null)
    	{
    		throw new RentalException("Cannot rent vehicle: Date is null");
    	}
    	if(startD.isAfter(endD))
    	{
    		throw new RentalException("Cannot rent vehicle: error of date >> start Date is after end Date");
    	}
        if(!vehicle.isAvailable()){throw new RentalException("Cannot rent vehicle: vehicle is not available");}
        Rental rental=new Rental(rentId, vehicle,startD,endD);
        rentalRepository.save(rental);

        vehicle.setStatus(VehicleStatus.RENTED);
    }

}
