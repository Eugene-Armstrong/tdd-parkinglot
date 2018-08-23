package com.tdd.parkingLot.controllers;

import com.tdd.parkingLot.entities.ParkingLot;
import com.tdd.parkingLot.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("")
    public ResponseEntity<Void> addNewParkingLot(@NotNull @RequestBody ParkingLot parkingLot) {
        try {
            parkingLotService.addNewParkingLot(parkingLot);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public List<ParkingLot> findAllParkingLots() {
        return parkingLotService.findAllParkingLots();
    }
}
