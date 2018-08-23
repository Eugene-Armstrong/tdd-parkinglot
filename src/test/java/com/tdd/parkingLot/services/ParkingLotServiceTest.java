package com.tdd.parkingLot.services;

import com.tdd.parkingLot.entities.ParkingLot;
import com.tdd.parkingLot.repositories.ParkingLotRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingLotServiceTest {
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void should_return_new_parkingLot_when_given_parkingLot(){
        ParkingLot parkingLot = new ParkingLot("好日子停车场", 10);
        ParkingLotRepository parkingLotRepository = mock(ParkingLotRepository.class);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotRepository);

        when(parkingLotRepository.save(parkingLot)).thenReturn(parkingLot);
        ParkingLot result = parkingLotService.addNewParkingLot(parkingLot);

        assertEquals("好日子停车场",result.getName());
        assertEquals(10,result.getInitSize());
    }

    @Test
    public void should_return_parkingLots_list_when_call_findAllParkingLot(){
        entityManager.persist(new ParkingLot("1号停车场",10));
        entityManager.persist(new ParkingLot("2号停车场",10));

        List<ParkingLot> parkingLots = ParkingLotService.findAllParkingLot();

        assertEquals(parkingLots.size(), 2);
    }
}
