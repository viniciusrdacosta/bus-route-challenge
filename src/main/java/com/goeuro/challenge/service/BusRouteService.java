package com.goeuro.challenge.service;

import com.goeuro.challenge.model.BusRoute;
import com.goeuro.challenge.repository.BusRouteRepository;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BusRouteService {

    BusRouteRepository repository;

    @Autowired
    public BusRouteService(BusRouteRepository repository) {
        this.repository = repository;
    }

    public boolean hasDirectRouteBetweenStations(Integer departureId, Integer arrivalId) {
        List<BusRoute> directRoutes = repository.getDirectRoutesBetweenStations(departureId, arrivalId);
        return hasDirectRoute(directRoutes);
    }

    private boolean hasDirectRoute(List<BusRoute> directRoutes) {
        return directRoutes.size() > 0;
    }

}
