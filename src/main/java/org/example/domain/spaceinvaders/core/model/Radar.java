package org.example.domain.spaceinvaders.core.model;

import lombok.AllArgsConstructor;
import org.example.domain.spaceinvaders.core.model.dto.InvaderStat;
import org.example.domain.spaceinvaders.core.model.vo.Matrix;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class Radar {

    private final Matrix matrixRepresentation;

    public InvaderStat containsInvaderPattern(final Invader invadarPattern, int percentageFaultTolerance) {

        List<Matrix> matrixList = getPossibleHorrizontalSlidingWindows(invadarPattern.getRowCount(), invadarPattern.getColumnCount());

        int sumOfOccurance = matrixList.stream().map(matrixRadar -> invadarPattern.compare(matrixRadar, percentageFaultTolerance) ? 1 : 0).reduce(0, Integer::sum);

        return new InvaderStat(invadarPattern.getInvaderName(), sumOfOccurance);

    }

    private List<Matrix> getPossibleHorrizontalSlidingWindows(int rowCount, int columnCount) {

        if (this.matrixRepresentation.getDimension().getRowCount() > rowCount
                && this.matrixRepresentation.getDimension().getColumnCount() > columnCount) {

            return this.matrixRepresentation.sliding(rowCount, columnCount);


        } else if (this.matrixRepresentation.getDimension().getRowCount() == rowCount
                && this.matrixRepresentation.getDimension().getColumnCount() == columnCount) {

            return Collections.singletonList(this.matrixRepresentation);

        }

        return Collections.emptyList();
    }

    public boolean isEmpty() {
        return this.matrixRepresentation.isEmpty();
    }

    public int getRowCount() {
        return this.matrixRepresentation.getDimension().getRowCount();
    }

    public int getColumnCount() {
        return this.matrixRepresentation.getDimension().getColumnCount();
    }

}
