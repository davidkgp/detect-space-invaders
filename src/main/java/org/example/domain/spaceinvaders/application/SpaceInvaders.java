package org.example.domain.spaceinvaders.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.spaceinvaders.core.model.command.RadarSearchCommand;
import org.example.domain.spaceinvaders.core.model.response.RadarResponse;
import org.example.domain.spaceinvaders.core.port.incoming.RadarOffice;

@AllArgsConstructor
@Slf4j
public class SpaceInvaders {

    private final RadarOffice radarOffice;

    public RadarResponse detect(final int percentageFaultTolerance){
        return radarOffice.check(new RadarSearchCommand(percentageFaultTolerance));
    }

}
