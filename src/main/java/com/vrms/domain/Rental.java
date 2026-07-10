package com.vrms.domain;
import java.time.LocalDate;
/**
 * Represents a rental record linking a specific vehicle to a rental
 * transaction in the Vehicle Rental Management System.
 */
public class Rental{

    private final String id;
    private final Vehicle vehicle;
    private final LocalDate startD;
    private final LocalDate endD;	
    public Rental(String id,Vehicle vehicle, LocalDate startD2, LocalDate endD2){
        this.id=id;
        this.vehicle=vehicle;
        this.startD=startD2;
        this.endD=endD2;
    }
    public String getId(){
        return id;
    }
    public Vehicle getVehicle(){
        return vehicle;
    }

    public LocalDate getStartD(){
        return startD;
    }
    public LocalDate getEndD(){
        return endD;
    }
}