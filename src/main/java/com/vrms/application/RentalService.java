package com.vrms.application;

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
    public void rentVehicle(String rentId,Vehicle vehicle){
        if(!vehicle.isAvailable()){throw new RentalException("Cannot rent vehicle: vehicle is not available");}
        Rental rental=new Rental(rentId, vehicle);
        rentalRepository.save(rental);

        vehicle.setStatus(VehicleStatus.RENTED);
    }

}
