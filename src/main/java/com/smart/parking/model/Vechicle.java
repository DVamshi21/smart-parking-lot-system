package com.smart.parking.model;

public class Vechicle { 
    private final String licensePlate;
    private final VechicleType vehicleType;
    public Vechicle(String licensePlate, VechicleType vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    public VechicleType getVehicleType() {
        return vehicleType;
    }
    
}
