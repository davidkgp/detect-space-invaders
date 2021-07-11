package org.example.domain.spaceinvaders.core.port.incoming;

import org.example.domain.spaceinvaders.core.model.command.RadarSearchCommand;
import org.example.domain.spaceinvaders.core.model.response.RadarResponse;

public interface RadarOffice {
    RadarResponse check(RadarSearchCommand radarSearchCommand);
}
