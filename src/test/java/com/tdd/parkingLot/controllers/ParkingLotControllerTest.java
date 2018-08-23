package com.tdd.parkingLot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.parkingLot.entities.ParkingLot;
import com.tdd.parkingLot.repositories.ParkingLotRepository;
import com.tdd.parkingLot.services.ParkingLotService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ParkingLotControllerTest {
    @Autowired
    private ParkingLotController parkingLotController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ObjectMapper mapper;
    
    @Test
    public void should_return_201_when_post_parkinglot() throws Exception{
        ParkingLot parkingLot = new ParkingLot("东门停车场",10);
        ParkingLotService mockService = mock(ParkingLotService.class);
        when(mockService.addNewParkingLot(parkingLot)).thenReturn(parkingLot);

        ResultActions resultActions = mockMvc.perform(post("/parkinglots"));

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",is("东门停车场")))
                .andExpect(jsonPath("$.initSize",is(10)));

    }
}
