package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.spaceinvaders.application.SpaceInvaders;
import org.example.domain.spaceinvaders.core.RadarOfficeFacade;
import org.example.domain.spaceinvaders.core.model.response.RadarResponse;
import org.example.domain.spaceinvaders.core.model.vo.Matrix;
import org.example.domain.spaceinvaders.core.port.incoming.RadarOffice;
import org.example.domain.spaceinvaders.core.port.outgoing.InvaderDataStore;
import org.example.domain.spaceinvaders.infrastructure.InvaderAdaptar;
import org.example.domain.spaceinvaders.infrastructure.repository.LocalInvaderFileRepository;
import org.example.domain.spaceinvaders.infrastructure.repository.interfaces.InvaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Slf4j
public class Trigger {


    public static void main(String[] args) {
        log.info("Start searching ....");
        InvaderRepository invaderRepository = new LocalInvaderFileRepository();
        InvaderDataStore invaderDataStore = new InvaderAdaptar(invaderRepository);
        RadarOffice radarOffice = new RadarOfficeFacade(invaderDataStore);
        SpaceInvaders spaceInvaders = new SpaceInvaders(radarOffice);
        RadarResponse radarResponse = spaceInvaders.detect(10);
        log.info("Searching result {}", radarResponse.toString());
    }
}
