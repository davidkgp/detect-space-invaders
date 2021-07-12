package org.example.domain.spaceinvaders.infrastructure.repository.interfaces;

import org.example.domain.spaceinvaders.core.model.Invader;

import java.util.List;

public interface InvaderRepository {
    List<Invader> get();
}
