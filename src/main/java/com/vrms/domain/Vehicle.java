package com.vrms.domain;

public class Vehicle {
	private String id;
	private String name;
	private VehicleStatus status;
	
	public Vehicle(String id,String name,VehicleStatus status) {
		this.id=id;
		this.name=name;
		this.status=status;
		
	}
	public String getId() {
		return id;
	}
    public String getName() {
		return name;
	}
    public VehicleStatus getStatus() {
    	return status;
    }
    public void setStatus(VehicleStatus status) {
    	this.status=status;
    }
    public boolean isAvailable() {
    	return status== VehicleStatus.AVAILABLE;
    }
    public boolean isRented() {
    	return status== VehicleStatus.RENTED;
    }
	

}
