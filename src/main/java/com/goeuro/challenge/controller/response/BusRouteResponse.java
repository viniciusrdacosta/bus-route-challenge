package com.goeuro.challenge.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class BusRouteResponse {

    @JsonProperty("dep_sid")
    int departureId;

    @JsonProperty("arr_sid")
    int arrivalId;

    @JsonProperty("direct_bus_route")
    boolean directBusRoute;

    public BusRouteResponse(int departureId, int arrivalId, boolean directBusRoute) {
        this.departureId = departureId;
        this.arrivalId = arrivalId;
        this.directBusRoute = directBusRoute;
    }
}
