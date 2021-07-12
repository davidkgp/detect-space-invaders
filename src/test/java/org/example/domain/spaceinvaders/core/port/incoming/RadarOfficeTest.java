package org.example.domain.spaceinvaders.core.port.incoming;

import org.example.domain.spaceinvaders.core.RadarOfficeFacade;
import org.example.domain.spaceinvaders.core.model.Invader;
import org.example.domain.spaceinvaders.core.model.Radar;
import org.example.domain.spaceinvaders.core.model.command.RadarSearchCommand;
import org.example.domain.spaceinvaders.core.model.response.RadarResponse;
import org.example.domain.spaceinvaders.core.model.vo.Matrix;
import org.example.domain.spaceinvaders.core.port.outgoing.InvaderDataStore;
import org.example.domain.spaceinvaders.core.port.outgoing.RadarDataStore;
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
public class RadarOfficeTest {

    private final InvaderDataStore invaderDataStore = mock(InvaderDataStore.class);
    private final RadarDataStore radarDataStore = mock(RadarDataStore.class);

    @Test
    public void whenNoInvaderRetrievedButRadarPresentRadarResponseisEmpty() {

        RadarOffice radarOffice = new RadarOfficeFacade(invaderDataStore, radarDataStore);

        when(invaderDataStore.get()).thenReturn(Collections.emptyList());
        when(radarDataStore.get()).thenReturn(new Radar(new Matrix(Arrays.asList("123", "456"))));


        RadarResponse radarResponse = radarOffice.check(new RadarSearchCommand(10));


        assertTrue(radarResponse.getInvaderCount().isEmpty());

    }

    @Test
    public void whenInvaderRetrievedButNoRadarPresentRadarResponseisEmpty() {

        RadarOffice radarOffice = new RadarOfficeFacade(invaderDataStore, radarDataStore);

        when(invaderDataStore.get())
                .thenReturn(Collections
                        .singletonList(new Invader("Some",
                                new Matrix(Arrays.asList("123", "456")))));
        when(radarDataStore.get()).thenReturn(new Radar(Matrix.EMPTY_MATRIX));


        RadarResponse radarResponse = radarOffice.check(new RadarSearchCommand(10));


        assertTrue(radarResponse.getInvaderCount().isEmpty());

    }

    @Test
    public void whenInvaderRetrievedButNotPresentinRadarRadarResponseisEmpty() {

        RadarOffice radarOffice = new RadarOfficeFacade(invaderDataStore, radarDataStore);

        when(invaderDataStore.get())
                .thenReturn(Collections
                        .singletonList(new Invader("Some",
                                new Matrix(Arrays.asList("123", "456")))));
        when(radarDataStore.get()).thenReturn(new Radar(new Matrix(Arrays.asList("987", "097"))));


        RadarResponse radarResponse = radarOffice.check(new RadarSearchCommand(10));


        assertTrue(radarResponse.getInvaderCount().isEmpty());

    }

    @Test
    public void whenInvaderRetrievedPresentinRadarRadarResponseisNonEmpty() {

        RadarOffice radarOffice = new RadarOfficeFacade(invaderDataStore, radarDataStore);

        when(invaderDataStore.get())
                .thenReturn(Collections
                        .singletonList(new Invader("Some",
                                new Matrix(Arrays.asList("123", "456")))));
        when(radarDataStore.get()).thenReturn(new Radar(new Matrix(Arrays.asList("123", "456"))));


        RadarResponse radarResponse = radarOffice.check(new RadarSearchCommand(10));


        assertEquals(1, radarResponse.getInvaderCount().size());
        assertEquals(radarResponse.getInvaderCount().get("Some"), Integer.valueOf(1));

    }


}