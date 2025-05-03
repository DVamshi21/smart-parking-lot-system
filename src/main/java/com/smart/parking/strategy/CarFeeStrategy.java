package com.smart.parking.strategy;

public class CarFeeStrategy implements FeeStrategy {
    // private static final double BASE_RATE = 2.0; // Base rate for the first hour
    // private static final double ADDITIONAL_RATE = 1.5; // Rate for each additional hour

    @Override
    public double calculateFee(long hours) {
        
        return hours * 10;
    }
    
}
