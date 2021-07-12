package org.example.domain.spaceinvaders.infrastructure;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.spaceinvaders.core.model.Invader;
import org.example.domain.spaceinvaders.core.port.outgoing.InvaderDataStore;
import org.example.domain.spaceinvaders.infrastructure.repository.interfaces.InvaderRepository;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class InvaderAdaptar implements InvaderDataStore {

    private final InvaderRepository invaderRepository;

    @Override
    public List<Invader> get() {
        try {
            return invaderRepository.get();
        } catch (Exception exception) {
            log.error("Error occured while lodading pattern from datastore", exception);
            return Collections.emptyList();
        }

    }
}
