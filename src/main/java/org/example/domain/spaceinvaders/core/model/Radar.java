package org.example.domain.spaceinvaders.core.model;

import lombok.AllArgsConstructor;
import org.example.domain.spaceinvaders.core.model.dto.InvaderStat;
import org.example.domain.spaceinvaders.core.model.vo.Matrix;

@AllArgsConstructor
public class Radar {

    private final Matrix matrixRepresentation;

    public InvaderStat containsInvaderPattern(final Invader invadarPattern) {
        if(this.matrixRepresentation.isEmpty()){
            return new InvaderStat(invadarPattern.getInvaderName(), 0);
        }
        return null;

    }

    public boolean isEmpty(){
        return this.matrixRepresentation.isEmpty();
    }

    public int getRowCount(){
        return this.matrixRepresentation.getDimension().getRowCount();
    }

    public int getColumnCount(){
        return this.matrixRepresentation.getDimension().getColumnCount();
    }

}
