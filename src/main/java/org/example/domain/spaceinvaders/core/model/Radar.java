package org.example.domain.spaceinvaders.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.domain.spaceinvaders.core.model.dto.InvaderStat;
import org.example.domain.spaceinvaders.core.model.vo.Matrix;

@AllArgsConstructor
@Getter
public class Radar {

    private final Matrix matrixRepresentation;

    public InvaderStat containsInvaderPattern(final Invader invadarPattern) {
        return null;
    }

}
