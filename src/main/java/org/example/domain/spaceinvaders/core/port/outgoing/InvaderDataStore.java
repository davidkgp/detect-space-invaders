package org.example.domain.spaceinvaders.core.port.outgoing;

import org.example.domain.spaceinvaders.core.model.Invader;

import java.util.List;

public interface InvaderDataStore {

    List<Invader> get();

}
