package org.example.domain.spaceinvaders.core.model.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RadarSearchCommand {
    private final int percentageFaultTolerance;
}
