package com.goeuro.challenge.model;

import lombok.Value;

import java.util.HashSet;
import java.util.List;

import static java.lang.String.format;

@Value
public class BusRoute {

    int id;
    List<Integer> stations;

    public BusRoute(int id, List<Integer> stations) {
        this.id = id;
        this.stations = stations;
        validateDuplicatedStation(stations);
    }

    private void validateDuplicatedStation(List<Integer> stationIds) {
        if (stationIds.size() > new HashSet<>(stationIds).size()) {
            throw new RuntimeException(format("Bus Route [%s] have duplicated station %s", id, stationIds));
        }
    }

}
