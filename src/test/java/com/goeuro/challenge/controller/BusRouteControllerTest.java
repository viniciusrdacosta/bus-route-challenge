package com.goeuro.challenge.controller;

import com.goeuro.challenge.service.BusRouteService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BusRouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusRouteService service;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("file", "");
    }

    @Test
    public void shouldReturnBusRouteResponseWithDirectBusRoute() throws Exception {
        when(service.hasDirectRouteBetweenStations(anyInt(), anyInt())).thenReturn(true);

        this.mockMvc.perform(get("/api/direct").param("dep_sid", "3").param("arr_sid", "6"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dep_sid").value("3"))
                .andExpect(jsonPath("$.arr_sid").value("6"))
                .andExpect(jsonPath("$.direct_bus_route").value("true"));
    }

    @Test
    public void shouldReturnBusRouteResponseWithoutDirectBusRoute() throws Exception {
        when(service.hasDirectRouteBetweenStations(anyInt(), anyInt())).thenReturn(false);

        this.mockMvc.perform(get("/api/direct").param("dep_sid", "5").param("arr_sid", "7"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dep_sid").value("5"))
                .andExpect(jsonPath("$.arr_sid").value("7"))
                .andExpect(jsonPath("$.direct_bus_route").value("false"));
    }

    @Test
    public void shouldReturnBadRequestWhenDepartureStationIdIsMissingFromRequest() throws Exception {
        this.mockMvc.perform(get("/api/direct").param("arr_sid", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Required Integer parameter 'dep_sid' is not present"));
    }

    @Test
    public void shouldReturnBadRequestWhenArrivalStationIdIsMissingFromRequest() throws Exception {
        this.mockMvc.perform(get("/api/direct").param("dep_sid", "5"))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Required Integer parameter 'arr_sid' is not present"));
    }
}