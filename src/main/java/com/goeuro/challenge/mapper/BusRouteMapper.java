package com.goeuro.challenge.mapper;

import com.goeuro.challenge.model.BusRoute;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
public class BusRouteMapper {

    private static final String EMPTY_SPACE_REGEX = "\\s+";

    public List<BusRoute> map(List<String> data) {
        return routes(data).stream().map(this::map).collect(Collectors.toList());
    }

    private BusRoute map(String routesData) {
        String[] routes = routesData.split(EMPTY_SPACE_REGEX);

        int routeId = Integer.parseInt(routes[0]);
        List<Integer> stationIds = Arrays.stream(routes)
                .mapToInt(Integer::parseInt)
                .boxed()
                .skip(1)
                .collect(Collectors.toList());

        return new BusRoute(routeId, stationIds);
    }

    private List<String> routes(List<String> data) {
        int numberOfRoutes = Integer.parseInt(data.get(0));
        List<String> routes = data.subList(1, data.size());
        validateNumberOfRoutes(numberOfRoutes, routes);
        return routes;
    }

    private void validateNumberOfRoutes(int numberOfRoutes, List<String> routes) {
        if (routes.size() != numberOfRoutes) {
            throw new RuntimeException(format("Number of routes [%s] is different than defined [%s]",
                    routes.size(), numberOfRoutes));
        }
    }

}
