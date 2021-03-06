package org.example.domain.spaceinvaders.core.model.vo;

import lombok.Getter;
import lombok.NonNull;
import org.example.domain.spaceinvaders.core.exceptions.IllegalRotationException;
import org.example.domain.spaceinvaders.core.exceptions.MalformedMatrixException;
import org.example.domain.spaceinvaders.core.model.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Getter
public class Matrix {

    private static final Logger log
            = LoggerFactory.getLogger(Matrix.class);
    public static Matrix EMPTY_MATRIX = new Matrix(Collections.emptyList());
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
        dimension = new Dimension(matrix.length, matrix.length == 0 ? 0 : matrix[0].length);
    }

    public boolean match(@NonNull final Matrix compareTo, final int percentageFaultTolerance) {
        return !compareTo.isEmpty()
                && compareTo.dimension.equals(this.dimension)
                && percentageMisMatch(this.matrix, compareTo.matrix) <= percentageFaultTolerance;
    }

    private int percentageMisMatch(final String[][] first, final String[][] second) {

        final List<String> firstList = Arrays.stream(first).flatMap(Arrays::stream).collect(Collectors.toList());
        final List<String> secondList = Arrays.stream(second).flatMap(Arrays::stream).collect(Collectors.toList());

        final Iterator<String> firstIterator = firstList.iterator();
        final Iterator<String> secondIterator = secondList.iterator();


        int count = 0;
        while (secondIterator.hasNext()) {
            if (!firstIterator.next().equals(secondIterator.next())) {
                count++;
            }
        }


        log.info(String.format("Percentage param %d, %d", count, firstList.size()));

        double value = (double) count / firstList.size();
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);

        int mismatchPercentage = bd.multiply(BigDecimal.valueOf(100)).intValue();
        log.info(String.format("Percentage mismatch %d", mismatchPercentage));

        return mismatchPercentage;

    }

    public List<Matrix> sliding(final int windowRowCount, final int windowColumnCount) {

        if ((this.getDimension().getRowCount() < windowRowCount && this.getDimension().getColumnCount() < windowColumnCount)
                || (windowRowCount == 0 && windowColumnCount == 0)) {
            return Collections.singletonList(EMPTY_MATRIX);
        } else if (this.getDimension().getRowCount() == windowRowCount && this.getDimension().getColumnCount() == windowColumnCount) {
            return Collections.singletonList(this);
        } else {

            List<List<String>> matrixListReprsentation = Arrays
                    .stream(this.matrix)
                    .map(Arrays::asList)
                    .collect(Collectors.toList());

            return applySliding(matrixListReprsentation, windowRowCount)
                    .flatMap(slideList -> getMultipleMatrix(slideList, windowColumnCount))
                    .collect(Collectors.toList());

        }

    }

    private Stream<Matrix> getMultipleMatrix(List<List<String>> intermediateRowSlides, int windowRow) {

        List<List<String>> row = intermediateRowSlides.stream().flatMap(each -> applySliding(each, windowRow)).collect(Collectors.toList());

        int mergeLength = row.size() / intermediateRowSlides.size();

        List<Matrix> mat = new ArrayList<>();

        for (int i = 0; i < mergeLength; i++) {

            List<List<String>> temp = new ArrayList<>();
            for (int j = 0; j < intermediateRowSlides.size(); j++) {
                temp.add(row.get(i + (mergeLength * j)));
            }

            mat.add(new Matrix(temp.stream().map(value -> String.join("", value)).collect(Collectors.toList())));
        }
        return mat.stream();

    }

    private <T> Stream<List<T>> applySliding(List<T> list, int size) {
        if (size > list.size()) {
            return Stream.empty();
        } else {
            return IntStream.range(0, list.size() - size + 1)
                    .mapToObj(start -> list.subList(start, start + size));
        }
    }

    public Matrix rotate(final int rotationAngle, final Direction direction) {
        if (rotationAngle % 90 != 0) {
            throw new IllegalRotationException(String.format("Angle %s not allowed", rotationAngle));
        } else if (rotationAngle % 360 == 0) {
            return this;
        } else {
            int times = rotationAngle / 90;
            Matrix finalMatrix = null;
            Matrix initialMatrix = this;
            for (int i = 0; i < times; i++) {

                finalMatrix = rotateOnce(initialMatrix, direction);
                initialMatrix = finalMatrix;

            }

            log.info(finalMatrix.toString());

            return finalMatrix;

        }
    }

    private Matrix rotateOnce(Matrix initialMatrix, Direction direction) {

        int startColumn = direction.equals(Direction.ANTICLOCKWISE) ? initialMatrix.dimension.getColumnCount() - 1 : 0;


        List<String> rotatedData = new ArrayList<>();
        if (direction.equals(Direction.ANTICLOCKWISE)) {
            for (int i = startColumn; i >= 0; i--) {
                int finalI = i;
                rotatedData.add(Arrays.stream(initialMatrix.getMatrix()).map(row -> row[finalI]).collect(Collectors.joining()));
            }
        } else {
            for (int i = startColumn; i < initialMatrix.dimension.getColumnCount(); i++) {
                int finalI = i;
                StringBuilder st = new StringBuilder(Arrays.stream(initialMatrix.getMatrix()).map(row -> row[finalI]).collect(Collectors.joining()));
                rotatedData.add(st.reverse().toString());
            }

        }


        return new Matrix(rotatedData);
    }

    public boolean isEmpty() {
        return this.dimension.getRowCount() == 0 && this.dimension.getColumnCount() == 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String[] ints : this.matrix) {
            for (int j = 0; j < this.dimension.getColumnCount(); j++) {
                str.append(ints[j]);
            }
            str.append("\n");
        }
        return str.toString();
    }
}

