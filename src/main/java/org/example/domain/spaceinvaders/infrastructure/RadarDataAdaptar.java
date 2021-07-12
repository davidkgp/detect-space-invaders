package org.example.domain.spaceinvaders.infrastructure;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.spaceinvaders.core.model.Radar;
import org.example.domain.spaceinvaders.core.model.vo.Matrix;
import org.example.domain.spaceinvaders.core.port.outgoing.RadarDataStore;
import org.example.domain.spaceinvaders.infrastructure.repository.interfaces.InvaderRepository;
import org.example.domain.spaceinvaders.infrastructure.repository.interfaces.RadarDataRepository;

import java.util.Collections;

@AllArgsConstructor
@Slf4j
public class RadarDataAdaptar implements RadarDataStore {

    private final RadarDataRepository radarDataRepository;

    @Override
    public Radar get() {
        try{
            return radarDataRepository.get();
        }catch(Exception exception){
            log.error("Error occured while loading radara data from datastore",exception);
            return new Radar(Matrix.EMPTY_MATRIX);
        }
    }
}
