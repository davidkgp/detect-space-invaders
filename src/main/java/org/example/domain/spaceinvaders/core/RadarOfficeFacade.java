package org.example.domain.spaceinvaders.core;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.spaceinvaders.core.model.Invader;
import org.example.domain.spaceinvaders.core.model.Radar;
import org.example.domain.spaceinvaders.core.model.command.RadarSearchCommand;
import org.example.domain.spaceinvaders.core.model.dto.InvaderStat;
import org.example.domain.spaceinvaders.core.model.response.RadarResponse;
import org.example.domain.spaceinvaders.core.port.incoming.RadarOffice;
import org.example.domain.spaceinvaders.core.port.outgoing.InvaderDataStore;
import org.example.domain.spaceinvaders.core.port.outgoing.RadarDataStore;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
public class RadarOfficeFacade implements RadarOffice {

    private final InvaderDataStore invaderDataStore;
    private final RadarDataStore radarDataStore;

    @Override
    public RadarResponse check(final RadarSearchCommand radarSearchCommand) {
        final List<Invader> invaderList = invaderDataStore.get();

        final Radar radarData = radarDataStore.get();

        return radarData.isEmpty()? new RadarResponse(Collections.emptyMap()):new RadarResponse(invaderList.stream()
                .flatMap(invader -> invader.possibleVariations().stream())
                .map(radarData::containsInvaderPattern)
                .collect(Collectors.toMap(InvaderStat::getInvaderName, InvaderStat::getCount)));
    }
}
