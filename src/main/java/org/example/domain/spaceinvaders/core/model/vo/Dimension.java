package org.example.domain.spaceinvaders.core.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Dimension {

    private final int rows;
    private final int columns;


    @Override
    public boolean equals(Object input) {
        return input instanceof Dimension
                && ((Dimension) input).columns == this.columns
                && ((Dimension) input).rows == this.rows;
    }
}
