package com.smart.parking.service;

import com.smart.parking.model.Vechicle;
import java.util.Map;
import com.smart.parking.model.VechicleType;
import com.smart.parking.strategy.FeeStrategy;
import com.smart.parking.model.ParkingTicket;

public class FeeService {
    private final Map<VechicleType, FeeStrategy> strategyMap;

    public FeeService(Map<VechicleType, FeeStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }
    public double calculateFee(ParkingTicket ticket) {
        long hours = ticket.getDurationInHours();

        return strategyMap.get(ticket.getVehicle().getVehicleType()).calculateFee(hours);
        
    }
    
}
