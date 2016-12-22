package com.goeuro.challenge.service;

import com.goeuro.challenge.model.BusRoute;
import com.goeuro.challenge.repository.BusRouteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.emptyList;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusRouteServiceTest {

    @Mock
    private BusRouteRepository repository;
    private BusRouteService service;

    @Before
    public void setUp() {
        service = new BusRouteService(repository);
    }

    @Test
    public void shouldHaveDirectRouteFromDepartureToArrival() {
        when(repository.getDirectRoutesBetweenStations(anyInt(), anyInt()))
                .thenReturn(asList(new BusRoute(0, asList(0, 1, 2, 3, 4))));

        boolean hasDirectRoute = service.hasDirectRouteBetweenStations(1, 2);
        assertThat(hasDirectRoute).isTrue();
    }

    @Test
    public void shouldNotHaveDirectRouteFromDepartureToArrival() {
        when(repository.getDirectRoutesBetweenStations(anyInt(), anyInt())).thenReturn(emptyList());

        boolean hasDirectRoute = service.hasDirectRouteBetweenStations(1, 2);
        assertThat(hasDirectRoute).isFalse();
    }

}