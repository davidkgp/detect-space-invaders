package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.example.exceptions.IllegalRotationException;
import org.example.exceptions.MalformedMatrixException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        //System.out.println(String.format("Percentage param %d, %d" ,count,firstList.size()));

        double value = (double)count/firstList.size();
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);

        //System.out.println(String.format("Percentage mismatch %d" ,mismatchPercentage));

        return bd.multiply(BigDecimal.valueOf(100)).intValue();

    }

    public Matrix rotate(final int rotationAngle, final Direction direction) {
        if(rotationAngle % 90 !=0) {
            throw new IllegalRotationException(String.format("Angle %s not allowed",rotationAngle));
        } else if(rotationAngle % 360 == 0 ){
            return this;
        } else{
            int times = rotationAngle /90;
            Matrix finalMatrix = null;
            Matrix initialMatrix = this;
            for(int i = 0 ; i < times ; i++){

                finalMatrix = rotateOnce(initialMatrix,direction);
                initialMatrix = finalMatrix;

            }

            System.out.println(finalMatrix);

            return finalMatrix;

        }
    }

    private Matrix rotateOnce(Matrix initialMatrix, Direction direction) {

        int startRow = direction.equals(Direction.ANTICLOCKWISE)?0:initialMatrix.getMatrix().length-1;
        int startColumn = direction.equals(Direction.ANTICLOCKWISE) ? initialMatrix.dimension.getColumns()-1:0;


        List<String> rotatedData = new ArrayList<>();
        for(int i = startColumn ; i >=0 ; i--){
            int finalI = i;
            rotatedData.add(Arrays.stream(initialMatrix.getMatrix()).map(row->row[finalI]).collect(Collectors.joining()));
        }


        return new Matrix(rotatedData);
    }

    public boolean isEmpty(){
        return this.dimension.getRows() ==0 && this.dimension.getColumns() ==0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String[] ints : this.matrix) {
            for (int j = 0; j < this.dimension.getColumns(); j++) {
                str.append(ints[j]);
            }
            str.append("\n");
        }
        return str.toString();
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
