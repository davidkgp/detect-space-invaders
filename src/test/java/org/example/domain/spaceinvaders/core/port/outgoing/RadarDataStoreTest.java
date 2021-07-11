package org.example.domain.spaceinvaders.core.port.outgoing;

import org.example.domain.spaceinvaders.core.model.Radar;
import org.example.domain.spaceinvaders.core.model.vo.Matrix;
import org.example.domain.spaceinvaders.infrastructure.RadarDataAdaptar;
import org.example.domain.spaceinvaders.infrastructure.repository.interfaces.RadarDataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RadarDataStoreTest {

    RadarDataRepository radarDataRepository = mock(RadarDataRepository.class);

    @Test
    public void whenRadarsNotFoundProvideEmptyRadar() {
        RadarDataStore radarDataStore = new RadarDataAdaptar(radarDataRepository);
        when(radarDataRepository.get()).thenReturn(new Radar(new Matrix(Collections.emptyList())));

        assertTrue(radarDataStore.get().getMatrixRepresentation().isEmpty());

    }

    @Test
    public void whenRadarRetrivalThrowsExecptionProvideEmptyRadar() {

        RadarDataStore radarDataStore = new RadarDataAdaptar(radarDataRepository);
        when(radarDataRepository.get()).thenThrow(new IllegalStateException("No radar Found"));

        assertTrue(radarDataStore.get().getMatrixRepresentation().isEmpty());

    }

    @Test
    public void whenRadarFoundProvideNonEmptyRadar() {
        RadarDataStore radarDataStore = new RadarDataAdaptar(radarDataRepository);
        when(radarDataRepository.get()).thenReturn(new Radar(new Matrix(Arrays.asList("123", "456", "789"))));

        assertEquals(3, radarDataStore.get().getMatrixRepresentation().getDimension().getRows());
        assertEquals(3, radarDataStore.get().getMatrixRepresentation().getDimension().getColumns());


    }

}