package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.example.exceptions.MalformedMatrixException;

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
        return !compareTo.isEmpty() && compareTo.dimension.equals(this.dimension);
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
