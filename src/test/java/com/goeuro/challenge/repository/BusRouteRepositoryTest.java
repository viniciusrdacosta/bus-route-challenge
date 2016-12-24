package com.goeuro.challenge.repository;

import com.goeuro.challenge.io.FileManager;
import com.goeuro.challenge.mapper.BusRouteMapper;
import com.goeuro.challenge.model.BusRoute;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusRouteRepositoryTest {

    @Mock
    private FileManager manager;
    @Mock
    private BusRouteMapper mapper;
    private BusRouteRepository repository;

    @Before
    public void setUp() {
        when(manager.routesData()).thenReturn(routesData());
        when(mapper.map(anyList())).thenReturn(allRoutes());
        repository = new BusRouteRepository(manager, mapper);
    }

    @Test
    public void shouldGetAllBusRoutesFromDepartureToArrivalStations() {
        List<BusRoute> routesWithDepartureStationId3 = repository.getDirectRoutesBetweenStations(3, 6);

        assertThat(routesWithDepartureStationId3).containsExactlyElementsOf(
                asList(new BusRoute(1, asList(0, 3, 1, 6, 5))));

    }

    private List<String> routesData() {
        return asList("3", "0 0 1 2 3 4", "1 3 1 6 5", "2 0 6 4");
    }

    private List<BusRoute> allRoutes() {
        return new ArrayList<>(asList(
                new BusRoute(0, asList(0, 1, 2, 3, 4)),
                new BusRoute(2, asList(0, 6, 4)),
                new BusRoute(1, asList(0, 3, 1, 6, 5))
        ));
    }
}
