package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.spaceinvaders.application.SpaceInvaders;
import org.example.domain.spaceinvaders.core.RadarOfficeFacade;
import org.example.domain.spaceinvaders.core.model.response.RadarResponse;
import org.example.domain.spaceinvaders.core.port.incoming.RadarOffice;
import org.example.domain.spaceinvaders.core.port.outgoing.InvaderDataStore;
import org.example.domain.spaceinvaders.core.port.outgoing.RadarDataStore;
import org.example.domain.spaceinvaders.infrastructure.InvaderAdaptar;
import org.example.domain.spaceinvaders.infrastructure.RadarDataAdaptar;
import org.example.domain.spaceinvaders.infrastructure.repository.LocalInvaderFileRepository;
import org.example.domain.spaceinvaders.infrastructure.repository.LocalRadarDataRepository;
import org.example.domain.spaceinvaders.infrastructure.repository.interfaces.InvaderRepository;
import org.example.domain.spaceinvaders.infrastructure.repository.interfaces.RadarDataRepository;

@Slf4j
public class Trigger {


    public static void main(String[] args) {
        log.info("Start searching ....");
        InvaderRepository invaderRepository = new LocalInvaderFileRepository();
        InvaderDataStore invaderDataStore = new InvaderAdaptar(invaderRepository);

        RadarDataRepository radarDataRepository = new LocalRadarDataRepository();
        RadarDataStore radarDataStore = new RadarDataAdaptar(radarDataRepository);


        RadarOffice radarOffice = new RadarOfficeFacade(invaderDataStore, radarDataStore);
        SpaceInvaders spaceInvaders = new SpaceInvaders(radarOffice);
        RadarResponse radarResponse = spaceInvaders.detect(50);
        log.info("Searching result {}", radarResponse.toString());
    }
}
