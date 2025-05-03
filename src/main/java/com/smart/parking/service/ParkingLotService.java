package com.smart.parking.service;


import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.smart.parking.model.ParkingSpot;
import com.smart.parking.model.ParkingTicket;
import com.smart.parking.model.Vechicle;
import com.smart.parking.model.VechicleType;
public class ParkingLotService {
    private final List<ParkingSpot> spots; 
    private final Map<String, ParkingTicket> activeTickets; 
    private final FeeService feeService;
    
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    public ParkingLotService(List<ParkingSpot> spots, FeeService feeService) {
        this.spots = spots;
        this.activeTickets = new ConcurrentHashMap<>();
        this.feeService = feeService;
    }
    public void vechicleEntry(Vechicle vechicle){
        executor.submit(()->{
            Optional<ParkingSpot> spot = spots.stream()
                .filter(s -> s.getSupportedType() == vechicle.getVehicleType() && !s.isOccupied())
                .findFirst();
                if(spot.isPresent() && spot.get().occupy()){
                    ParkingTicket ticket = new ParkingTicket(vechicle, spot.get(), LocalDateTime.now());
                    activeTickets.put(vechicle.getLicensePlate(), ticket);
                    System.out.println("Vechicle " + vechicle.getLicensePlate() + " parked at spot " + spot.get().getId());
                } else {
                    System.out.println("No available parking spots for vechicle " + vechicle.getLicensePlate());
                }
        });
    }
    public void vechicleExit(String licensePlate){
        executor.submit(()->{
            ParkingTicket ticket = activeTickets.remove(licensePlate);
            if(ticket != null){
                ticket.setExitTime(LocalDateTime.now());
                double fee = feeService.calculateFee(ticket);
                ticket.getParkingSpot().release();
                System.out.println("Vechicle " + licensePlate + " exited. Fee: " + fee);
            } else {
                System.out.println("No active ticket found for vechicle " + licensePlate);
            }
        });
    }
    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    
}
