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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void should_return_201_when_post_parkinglot_given_parkinglot() throws Exception{
        ParkingLot parkingLot = new ParkingLot("东门停车场",10);
        when(parkingLotService.addNewParkingLot(parkingLot)).thenReturn(parkingLot);

        ResultActions resultActions = mockMvc.perform(post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(parkingLot)));

        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void should_return_200_when_get_parkinglot() throws Exception{
        ParkingLot parkingLot1 = new ParkingLot("1号停车场",10);
        ParkingLot parkingLot2 = new ParkingLot("2号停车场",20);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1,parkingLot2);
        when(parkingLotService.findAllParkingLots()).thenReturn(parkingLots);

        ResultActions resultActions = mockMvc.perform(get("/parkinglots"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", containsString("1号停车场")))
                .andExpect(jsonPath("$[0].initSize", is(10)));
    }
}
