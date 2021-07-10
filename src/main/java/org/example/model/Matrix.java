package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.exceptions.MalformedMatrixException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class Matrix {

    private final String[][] matrix;
    private final Dimension dimension;


    public Matrix(@NonNull() final List<String> rowsOfData) {

        if (rowsOfData.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("One of the inputs is null");
        }

        if (rowsOfData.stream().map(String::length).collect(Collectors.toSet()).size() > 1) {
            throw new MalformedMatrixException("Not proper input for matrix");
        }
        matrix = rowsOfData.stream().map(row -> row.split("")).toArray(String[][]::new);
        dimension = new Dimension(matrix.length,matrix.length == 0 ? 0 : matrix[0].length);
    }

    public boolean match(@NonNull final Matrix compareTo, final int percentageFaultTolerance) {
        return !compareTo.isEmpty()
                && compareTo.dimension.equals(this.dimension)
                && percentageMisMatch(this.matrix,compareTo.matrix) <= percentageFaultTolerance;
    }

    private int percentageMisMatch(final String[][] first, final String[][] second) {

        final List<String> firstList = Arrays.stream(first).flatMap(Arrays::stream).collect(Collectors.toList());
        final List<String> secondList = Arrays.stream(second).flatMap(Arrays::stream).collect(Collectors.toList());

        final Iterator<String> firstIterator = firstList.iterator();
        final Iterator<String> secondIterator = secondList.iterator();


        int count = 0;
        while (secondIterator.hasNext()){
            if (!firstIterator.next().equals(secondIterator.next())){
                count++;
            }
        }

        //TODO logging
        System.out.println(String.format("Percentage param %d, %d" ,count,firstList.size()));

        double value = (double)count/firstList.size();
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);

        int mismatchPercentage = bd.multiply(BigDecimal.valueOf(100)).intValue();

        System.out.println(String.format("Percentage mismatch %d" ,mismatchPercentage));

        return mismatchPercentage;

    }

    public Matrix rotate(final int multiplesOfNinety, final Direction direction) {
        return null;
    }

    public boolean isEmpty(){
        return this.dimension.getRows() ==0 && this.dimension.getColumns() ==0;
    }


}

@AllArgsConstructor
@Getter
class Dimension {

    private final int rows;
    private final int columns;


    @Override
    public boolean equals(Object input) {
        return input instanceof Dimension
                && ((Dimension) input).columns == this.columns
                && ((Dimension) input).rows == this.rows;
    }
}
