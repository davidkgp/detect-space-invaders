package org.example.domain.spaceinvaders.core.model;

import lombok.AllArgsConstructor;
import org.example.domain.spaceinvaders.core.model.dto.InvaderStat;
import org.example.domain.spaceinvaders.core.model.vo.Matrix;

@AllArgsConstructor
public class Radar {

    private final Matrix matrixRepresentation;

    public InvaderStat containsInvaderPattern(final Invader invadarPattern) {
        return null;
    }

}
