package org.example.domain.spaceinvaders.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.domain.spaceinvaders.core.model.vo.Matrix;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
public class Invader {

    private final String invaderName;

    private final Matrix matrixRepresentation;

    public List<Invader> possibleVariations(){
        return matrixRepresentation !=null
                && !matrixRepresentation.isEmpty() ?
                Arrays.asList(this,
                        new Invader(this.invaderName+"-rotated90anticlock",matrixRepresentation.rotate(90,Direction.ANTICLOCKWISE)),
                        new Invader(this.invaderName+"-rotated180anticlock",matrixRepresentation.rotate(180,Direction.ANTICLOCKWISE)),
                        new Invader(this.invaderName+"-rotated270anticlock",matrixRepresentation.rotate(270,Direction.ANTICLOCKWISE)),
                        new Invader(this.invaderName+"-rotated90clock",matrixRepresentation.rotate(90,Direction.CLOCKWISE)),
                        new Invader(this.invaderName+"-rotated180clock",matrixRepresentation.rotate(180,Direction.CLOCKWISE)),
                        new Invader(this.invaderName+"-rotated270clock",matrixRepresentation.rotate(270,Direction.CLOCKWISE))
                        )
        :Collections.emptyList();
    }

}
