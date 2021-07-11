package org.example.domain.spaceinvaders.core.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Dimension {

    private final int rowCount;
    private final int columnCount;


    @Override
    public boolean equals(Object input) {
        return input instanceof Dimension
                && ((Dimension) input).columnCount == this.columnCount
                && ((Dimension) input).rowCount == this.rowCount;
    }
}
