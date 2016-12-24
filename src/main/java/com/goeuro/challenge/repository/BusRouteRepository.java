package com.goeuro.challenge.repository;

import com.goeuro.challenge.io.FileManager;
import com.goeuro.challenge.mapper.BusRouteMapper;
import com.goeuro.challenge.model.BusRoute;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BusRouteRepository {

    FileManager fileManager;
    BusRouteMapper mapper;
    List<BusRoute> routes;

    @Autowired
    public BusRouteRepository(FileManager fileManager, BusRouteMapper mapper) {
        this.fileManager = fileManager;
        this.mapper = mapper;
        this.routes = busRoutes();
    }

    public List<BusRoute> getDirectRoutesBetweenStations(Integer departureId, Integer arrivalId) {
        return routes.stream()
                .filter(busRoute -> withDirectRoute(busRoute, departureId, arrivalId))
                .collect(Collectors.toList());
    }

    private boolean withDirectRoute(BusRoute busRoute, Integer departureId, Integer arrivalId) {
        return busRoute.getStations().containsAll(asList(departureId, arrivalId))
                && busRoute.getStations().indexOf(departureId) < busRoute.getStations().indexOf(arrivalId);
    }

    private List<BusRoute> busRoutes() {
        return mapper.map(fileManager.routesData());
    }

}
