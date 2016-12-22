package com.goeuro.challenge.mapper;

import com.goeuro.challenge.model.BusRoute;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BusRouteMapperTest {

    private BusRouteMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new BusRouteMapper();
    }

    @Test
    public void shouldTransformRoutesDataFileIntoBusRoutes() {
        List<BusRoute> actualRoutes = mapper.map(asList("3", "0 0 1 2 3 4", "1 3 1 6 5", "2 0 6 4"));
        List<BusRoute> expectedRoutes = asList(
                new BusRoute(0, asList(0, 1, 2, 3, 4)),
                new BusRoute(1, asList(3, 1, 6, 5)),
                new BusRoute(2, asList(0, 6, 4)));

        assertThat(actualRoutes).containsExactlyElementsOf(expectedRoutes);
    }

    @Test
    public void shouldNotHaveSameStationMoreThanOnceInTheSameBusRoute() {
        assertThatThrownBy(() -> mapper.map(asList("3", "0 0 1 2 3 4", "1 3 1 6 5 6", "2 0 6 4")))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Bus Route [1] have duplicated station [3, 1, 6, 5, 6]");
    }
}
