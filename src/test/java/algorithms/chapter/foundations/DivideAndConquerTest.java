package algorithms.chapter.foundations;

import org.junit.Test;

import static org.junit.Assert.*;

public class DivideAndConquerTest {

    DivideAndConquer dac = new DivideAndConquer();

    int[] array = new int[]{13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
    int[] single_array = new int[]{4};
    int[] positive_array = new int[]{4,1,7,3,4};
    int[] negative_array = new int[] {-4,-1,-7,-3,-4};

    int[][] I2 = new int[][]{{1,0},{0,1}};

    @Test
    public void testFindMaximumSubarray() {
        assertEquals(new MaxSubarrayBean(7, 10, 43), dac.findMaximumSubarray(array, 0, array.length-1));
        assertEquals(new MaxSubarrayBean(0, 0, 4), dac.findMaximumSubarray(single_array, 0, single_array.length-1));
        assertEquals(new MaxSubarrayBean(0, 4, 19), dac.findMaximumSubarray(positive_array, 0, positive_array.length-1));
        assertEquals(new MaxSubarrayBean(1, 1, -1), dac.findMaximumSubarray(negative_array, 0, negative_array.length-1));
    }

    @Test
    public void testSquareMatrixMultiply() {
        int[][] A = new int[][]{{1,3},{7,5}};
        int[][] B = new int[][]{{6,8},{4,2}};
        assertArrayEquals(new int[][]{{18,14},{62,66}}, dac.squareMatrixMultiply(A,B));
    }

    @Test
    public void testSquareMatrixMultiplyDAC() {
        int[][] A = new int[][]{{1,3},{7,5}};
        int[][] B = new int[][]{{6,8},{4,2}};
        //assertArrayEquals(new int[][]{{18,14},{62,66}}, dac.squareMatrixMultiplyDAC(A,B));

        int[][] C = new int[][]{{1,4,2,0},{-1,0,0,5},{3,-6,-2,1},{1,1,1,1}};
        int[][] D = new int[][]{{1,-3,0,1},{0,0,0,0},{2,-1,2,-1},{5,4,3,2}};
        assertArrayEquals(new int[][]{{5, -5, 4, -1}, {24, 23, 15, 9}, {4, -3, -1, 7}, {8, 0, 5, 2}}, dac.squareMatrixMultiplyDAC(C,D));

        int[][] E = new int[][]{{2,-1},{10,1}};
        assertArrayEquals(E, dac.squareMatrixMultiplyDAC(E,I2));
    }

}