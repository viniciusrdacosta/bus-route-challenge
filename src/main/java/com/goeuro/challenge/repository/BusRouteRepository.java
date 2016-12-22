package com.goeuro.challenge.repository;

import com.goeuro.challenge.mapper.BusRouteMapper;
import com.goeuro.challenge.model.BusRoute;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BusRouteRepository {

    BusRouteMapper mapper;
    List<BusRoute> routes;

    @Autowired
    public BusRouteRepository(@Value("${file}") String file, BusRouteMapper mapper) {
        this.mapper = mapper;
        this.routes = busRoutes(file);
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

    @SneakyThrows
    private List<BusRoute> busRoutes(String file) {
        List<String> routesData = Files.readAllLines(getPath(file));
        return mapper.map(routesData);
    }

    @SneakyThrows
    private Path getPath(String file) {
        return isNullOrEmpty(file) ? Paths.get(ClassLoader.getSystemResource("sample-data").toURI())
                : Paths.get(file);
    }

    private boolean isNullOrEmpty(String file) {
        return file == null || file.isEmpty();
    }

}
