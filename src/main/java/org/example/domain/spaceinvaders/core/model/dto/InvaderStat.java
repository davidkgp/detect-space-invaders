package org.example.domain.spaceinvaders.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvaderStat {

    private final String invaderName;
    private final int count;
}
