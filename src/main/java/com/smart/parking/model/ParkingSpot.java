package com.smart.parking.model;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSpot {
    private final int id;
    private final VechicleType supportedType;
    private final AtomicBoolean occupied;

    public ParkingSpot(int id, VechicleType supportedType) {
        this.id = id;
        this.supportedType = supportedType;
        this.occupied = new AtomicBoolean(false);
    }
    public int getId() {
        return id;
    }
    public VechicleType getSupportedType() {
        return supportedType;
    }
    public boolean isOccupied() {
        return occupied.get();
    }
    public boolean occupy() {
        return occupied.compareAndSet(false, true);
    }
    public boolean release() {
        occupied.set(false);
        return true;
    }
}
