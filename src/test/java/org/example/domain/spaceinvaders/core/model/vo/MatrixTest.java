package org.example.domain.spaceinvaders.core.model.vo;

import org.example.domain.spaceinvaders.core.exceptions.IllegalRotationException;
import org.example.domain.spaceinvaders.core.exceptions.MalformedMatrixException;
import org.example.domain.spaceinvaders.core.model.Direction;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class MatrixTest {

    @Test
    public void whenInputIsEmptyMatrixWillBeEmpty() {
        Matrix matrix = new Matrix(Collections.emptyList());
        assertEquals(0, matrix.getMatrix().length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInputIsNullMatrixWillBeEmpty() {
        new Matrix(null);
    }

    @Test
    public void whenInputIsNonEmptyProperMatrix() {
        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        assertEquals(matrix.getDimension().getRowCount(), 4);
        assertEquals(matrix.getDimension().getColumnCount(), 3);


    }

    @Test(expected = MalformedMatrixException.class)
    public void whenInputIsNonEmptyButMalformedExpectExecption() {
        new Matrix(Arrays.asList("123", "456", "7819", "111"));

    }

    @Test(expected = MalformedMatrixException.class)
    public void whenInputIsNonEmptyButWrongdelimaterExecption() {
        new Matrix(Arrays.asList("1,2,3", "4,5,6", "7,8,1", "111"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInputIsButContainsNullExpectExecption() {
        new Matrix(Arrays.asList("123", "456", null, "111"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInputisNullTheMatchThrowsExecption() {
        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        matrix.match(null, 0);

    }

    @Test
    public void whenInputisEmptyMatrixThenMismatch() {
        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix matrixCompare = new Matrix(Collections.emptyList());
        assertFalse(matrix.match(matrixCompare, 0));

    }

    @Test
    public void whenInputisDiffDimensionMatrixThenMismatch() {
        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix matrixCompare = new Matrix(Arrays.asList("123", "456", "789"));
        assertFalse(matrix.match(matrixCompare, 0));

    }

    @Test
    public void whenInputisSameMatrixThenMatch() {
        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix matrixCompare = new Matrix(Arrays.asList("123", "456", "789", "111"));
        assertTrue(matrix.match(matrixCompare, 0));

    }

    @Test
    public void whenInputisDiffMatrixThenMismatch() {
        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix matrixCompare = new Matrix(Arrays.asList("123", "999", "789", "111"));
        assertFalse(matrix.match(matrixCompare, 0));

    }

    @Test
    public void whenInputisDiffMatrixThenMatchWithPercentageFaultisHigher() {
        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix matrixCompare = new Matrix(Arrays.asList("123", "999", "789", "111"));
        assertTrue(matrix.match(matrixCompare, 28));

    }

    @Test
    public void whenInputisDiffMatrixThenMisMatchWithLessPercentageFault() {
        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix matrixCompare = new Matrix(Arrays.asList("123", "999", "789", "111"));
        assertFalse(matrix.match(matrixCompare, 12));

    }

    @Test
    public void whenInputisDiffMatrixThenMatchWithPercentageFaultisExact() {
        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix matrixCompare = new Matrix(Arrays.asList("123", "999", "789", "111"));
        assertTrue(matrix.match(matrixCompare, 25));

    }

    @Test
    public void whenInputisDiffMatrixThenMatchWithPercentageFaultisEdge() {
        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix matrixCompare = new Matrix(Arrays.asList("123", "999", "389", "111"));
        assertFalse(matrix.match(matrixCompare, 25));

    }

    @Test
    public void whenInputisDiffMatrixThenMatchWithPercentageFaultHunderdPercent() {
        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix matrixCompare = new Matrix(Arrays.asList("897", "999", "333", "000"));
        assertTrue(matrix.match(matrixCompare, 100));

    }

    @Test(expected = IllegalRotationException.class)
    public void whenRotationIsNotInMultiplesOf90ThenException(){

        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        matrix.rotate(230,Direction.ANTICLOCKWISE);

    }

    @Test
    public void whenRotationIsInMultiplesOf360ThenSameMatrix(){

        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix rotatedMatrix = matrix.rotate(720,Direction.ANTICLOCKWISE);

        assertTrue(matrix.match(rotatedMatrix,0));

    }

    @Test
    public void whenRotationIsZeroThenSameMatrix(){

        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix rotatedMatrix = matrix.rotate(0,Direction.ANTICLOCKWISE);

        assertTrue(matrix.match(rotatedMatrix,0));

    }

    @Test
    public void whenRotationIsAppliedFor90ThenGetRotatedMatrixofAntiClockwise(){

        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix rotatedMatrix = matrix.rotate(90,Direction.ANTICLOCKWISE);

        Matrix expected = new Matrix(Arrays.asList("3691", "2581", "1471"));

        assertTrue(expected.match(rotatedMatrix,0));

    }

    @Test
    public void whenRotationIsAppliedFor180ThenGetRotatedMatrixofAntiClockwise(){

        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix rotatedMatrix = matrix.rotate(180,Direction.ANTICLOCKWISE);

        Matrix expected = new Matrix(Arrays.asList("111", "987", "654", "321"));

        assertTrue(expected.match(rotatedMatrix,0));

    }

    @Test
    public void whenRotationIsAppliedFor270ThenGetRotatedMatrixofAntiClockwise(){

        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix rotatedMatrix = matrix.rotate(270,Direction.ANTICLOCKWISE);

        Matrix expected = new Matrix(Arrays.asList("1741", "1852", "1963"));

        assertTrue(expected.match(rotatedMatrix,0));

    }


    @Test
    public void whenRotationIsAppliedFor90ThenGetRotatedMatrixofClockwise(){

        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix rotatedMatrix = matrix.rotate(90,Direction.CLOCKWISE);

        Matrix expected = new Matrix(Arrays.asList("1741", "1852", "1963"));

        assertTrue(expected.match(rotatedMatrix,0));

    }

    @Test
    public void whenRotationIsAppliedFor180ThenGetRotatedMatrixofClockwise(){

        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix rotatedMatrix = matrix.rotate(180, Direction.CLOCKWISE);

        Matrix expected = new Matrix(Arrays.asList("111", "987", "654", "321"));

        assertTrue(expected.match(rotatedMatrix,0));

    }

    @Test
    public void whenRotationIsAppliedFor270ThenGetRotatedMatrixofClockwise(){

        Matrix matrix = new Matrix(Arrays.asList("123", "456", "789", "111"));
        Matrix rotatedMatrix = matrix.rotate(270,Direction.CLOCKWISE);

        Matrix expected = new Matrix(Arrays.asList("3691", "2581", "1471"));

        assertTrue(expected.match(rotatedMatrix,0));

    }


}