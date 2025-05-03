package com.smart.parking.SmartParkingLotSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.smart.parking.model.ParkingSpot;
import com.smart.parking.model.Vechicle;
import com.smart.parking.model.VechicleType;
import com.smart.parking.service.FeeService;
import com.smart.parking.service.ParkingLotService;
import com.smart.parking.strategy.BusFeeStrategy;
import com.smart.parking.strategy.CarFeeStrategy;
import com.smart.parking.strategy.FeeStrategy;
import com.smart.parking.strategy.MotorcycleFeeStrategy;

@SpringBootApplication
public class SmartParkingLotSystemApplication {

	public static void main(String[] args) throws InterruptedException {
		List<ParkingSpot> spots = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			spots.add(new ParkingSpot(i, VechicleType.CAR));
		}
		for (int i = 10; i < 20; i++) {
			spots.add(new ParkingSpot(i, VechicleType.MOTORCYCLE));
		}
		for (int i = 20; i < 30; i++) {
			spots.add(new ParkingSpot(i, VechicleType.BUS));
		}
		Map<VechicleType, FeeStrategy> feeMap = new HashMap<>();
		feeMap.put(VechicleType.CAR, new CarFeeStrategy());
		feeMap.put(VechicleType.MOTORCYCLE, new MotorcycleFeeStrategy());
		feeMap.put(VechicleType.BUS, new BusFeeStrategy());
		FeeService feeService = new FeeService(feeMap);
		ParkingLotService parkingLotService = new ParkingLotService(spots, feeService);
		Vechicle car1 = new Vechicle("ABC123", VechicleType.CAR);
		Vechicle motorcycle1 = new Vechicle("XYZ789", VechicleType.MOTORCYCLE);
		Vechicle bus1 = new Vechicle("BUS456", VechicleType.BUS);
		parkingLotService.vechicleEntry(car1);	
		parkingLotService.vechicleEntry(motorcycle1);
		parkingLotService.vechicleEntry(bus1);
		Thread.sleep(5000);
		parkingLotService.vechicleExit(car1.getLicensePlate());
		Thread.sleep(5000);
		parkingLotService.vechicleExit(motorcycle1.getLicensePlate());
		Thread.sleep(5000);
		parkingLotService.vechicleExit(bus1.getLicensePlate());
		parkingLotService.shutdown();
		
		//SpringApplication.run(SmartParkingLotSystemApplication.class, args);
	}

}
