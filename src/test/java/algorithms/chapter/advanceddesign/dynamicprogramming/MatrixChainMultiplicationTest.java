package algorithms.chapter.advanceddesign.dynamicprogramming;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MatrixChainMultiplicationTest {

    private static final int[] p1 = new int[]{2,3,6,4,5};
    private static final int[] p2 = new int[]{30,35,15,5,10,20,25};
    private MatrixChainMultiplication matrixChainMultiplication = new MatrixChainMultiplication();

    @Test
    public void testGetOptimalParenthesizeRecursiveMultiplication() {
        assertThat(matrixChainMultiplication.getOptimalParenthesizeRecursiveMultiplication(p1), is("(((A0A1)A2)A3)"));
        assertThat(matrixChainMultiplication.getOptimalParenthesizeRecursiveMultiplication(p2), is("((A0(A1A2))((A3A4)A5))"));
    }

    @Test
    public void testGetOptimalParenthesizeMemoizedMultiplication() {
        assertThat(matrixChainMultiplication.getOptimalParenthesizeMemoizedMultiplication(p1), is("(((A0A1)A2)A3)"));
        assertThat(matrixChainMultiplication.getOptimalParenthesizeMemoizedMultiplication(p2), is("((A0(A1A2))((A3A4)A5))"));
    }

    @Test
    public void testGetOptimalParenthesizeBottomUpMultiplication() {
        assertThat(matrixChainMultiplication.getOptimalParenthesizeBottomUpMultiplication(p1), is("(((A0A1)A2)A3)"));
        assertThat(matrixChainMultiplication.getOptimalParenthesizeBottomUpMultiplication(p2), is("((A0(A1A2))((A3A4)A5))"));
    }


}
