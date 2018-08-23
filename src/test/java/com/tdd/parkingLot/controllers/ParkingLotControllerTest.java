package com.tdd.parkingLot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.parkingLot.entities.ParkingLot;
import com.tdd.parkingLot.repositories.ParkingLotRepository;
import com.tdd.parkingLot.services.ParkingLotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ParkingLotController.class)
@EnableSpringDataWebSupport
public class ParkingLotControllerTest {
    @MockBean
    private ParkingLotService parkingLotService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void should_return_201_when_post_parkinglot() throws Exception{
        ParkingLot parkingLot = new ParkingLot("东门停车场",10);
        when(parkingLotService.addNewParkingLot(parkingLot)).thenReturn(parkingLot);

        ResultActions resultActions = mockMvc.perform(post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(parkingLot)));

        resultActions.andExpect(status().isCreated());

    }
}
