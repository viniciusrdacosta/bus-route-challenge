package com.goeuro.challenge.controller;

import com.goeuro.challenge.controller.response.BusRouteResponse;
import com.goeuro.challenge.service.BusRouteService;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BusRouteController {

    BusRouteService service;

    @Autowired
    public BusRouteController(BusRouteService service) {
        this.service = service;
    }

    @RequestMapping(method = GET, path = "/direct")
    public BusRouteResponse directRoute(@RequestParam("dep_sid") Integer departureId,
                                        @RequestParam("arr_sid") Integer arrivalId) {
        boolean hasDirectRoute = service.hasDirectRouteBetweenStations(departureId, arrivalId);
        return new BusRouteResponse(departureId, arrivalId, hasDirectRoute);
    }
}
