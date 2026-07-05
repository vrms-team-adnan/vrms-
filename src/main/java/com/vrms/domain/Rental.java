package com.vrms.domain;
/**
 * Represents a rental record linking a specific vehicle to a rental
 * transaction in the Vehicle Rental Management System.
 */
public class Rental{

    private final String id;
    private final Vehicle vehicle;
    
    public Rental(String id,Vehicle vehicle){
        this.id=id;
        this.vehicle=vehicle;
    }
    public String getId(){
        return id;
    }
    public Vehicle getVehicle(){
        return vehicle;
    }
}