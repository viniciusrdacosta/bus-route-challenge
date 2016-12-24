package com.goeuro.challenge.mapper;

import com.goeuro.challenge.model.BusRoute;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BusRouteMapper {

    static String EMPTY_SPACE_REGEX = "\\s+";

    public List<BusRoute> map(List<String> data) {
        return data.stream().map(this::map).collect(Collectors.toList());
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

}
