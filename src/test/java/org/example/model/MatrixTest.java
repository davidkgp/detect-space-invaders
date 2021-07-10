package org.example.model;

import org.example.exceptions.MalformedMatrixException;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;


public class MatrixTest {

    @Test
    public void whenInputIsEmptyMatrixWillBeEmpty(){
        Matrix matrix = new Matrix(Collections.emptyList());
        assertEquals(0, matrix.getMatrix().length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInputIsNullMatrixWillBeEmpty(){
        new Matrix(null);
    }

    @Test
    public void whenInputIsNonEmptyProperMatrix(){
        Matrix matrix = new Matrix(Arrays.asList("123","456","789","111"));
        assertEquals(matrix.getDimension().getRows(),4);
        assertEquals(matrix.getDimension().getColumns(),3);


    }
    @Test(expected = MalformedMatrixException.class)
    public void whenInputIsNonEmptyButMalformedExpectExecption(){
        new Matrix(Arrays.asList("123","456","7819","111"));

    }

    @Test(expected = MalformedMatrixException.class)
    public void whenInputIsNonEmptyButWrongdelimaterExecption(){
        new Matrix(Arrays.asList("1,2,3","4,5,6","7,8,1","111"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInputIsButContainsNullExpectExecption(){
        new Matrix(Arrays.asList("123","456",null,"111"));

    }


}