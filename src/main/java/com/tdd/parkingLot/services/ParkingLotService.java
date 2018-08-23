package com.tdd.parkingLot.services;

import com.tdd.parkingLot.entities.ParkingLot;
import com.tdd.parkingLot.repositories.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLot addNewParkingLot(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

}
