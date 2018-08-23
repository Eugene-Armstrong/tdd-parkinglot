package com.tdd.parkingLot.services;

import com.tdd.parkingLot.entities.ParkingLot;
import com.tdd.parkingLot.repositories.ParkingLotRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
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
        ParkingLot parkingLot1 = new ParkingLot("1号停车场",10);
        ParkingLot parkingLot2 = new ParkingLot("2号停车场",20);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1,parkingLot2);
        ParkingLotRepository parkingLotRepository = mock(ParkingLotRepository.class);
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotRepository);
        when(parkingLotRepository.findAll()).thenReturn(parkingLots);

        List<ParkingLot> result = parkingLotService.findAllParkingLots();

        assertEquals(result.size(), 2);
    }
}
