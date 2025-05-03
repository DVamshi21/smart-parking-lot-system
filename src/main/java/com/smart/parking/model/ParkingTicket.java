package com.smart.parking.model;

import java.time.LocalDateTime;

public class ParkingTicket {
    private final Vechicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public ParkingTicket(Vechicle vehicle, ParkingSpot parkingSpot, LocalDateTime entryTime) {
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;
    }
    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
    public long getDurationInHours() {
        
        if
            (exitTime == null) {
            throw new IllegalStateException("Exit time is not set");
        }
        return java.time.Duration.between(entryTime, exitTime).toHours();
    }
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
    public Vechicle getVehicle() {
        return vehicle;
    }
    
}
