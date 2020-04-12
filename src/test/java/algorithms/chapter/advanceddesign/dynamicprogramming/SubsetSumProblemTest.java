package algorithms.chapter.advanceddesign.dynamicprogramming;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubsetSumProblemTest {

    private static final int[] set1 = new int[] {3,34,4,12,5,2};
    private static final int SUM1 = 9;

    private static final int[] set2 = new int[] {2,3,7,8,10};
    private static final int SUM2 = 11;

    private SubsetSumProblem ssp = new SubsetSumProblem();

    @Test
    public void testSubsetSumProblemRecursive() {
        assertTrue(ssp.subsetSumProblemRecursive(set1, SUM1));
        assertTrue(ssp.subsetSumProblemRecursive(set2, SUM2));
    }

    @Test
    public void testSubsetSumProblemRecursiveSolution2() {
        assertTrue(ssp.subsetSumProblemRecursiveSolution2(set1, SUM1));
        assertTrue(ssp.subsetSumProblemRecursiveSolution2(set2, SUM2));
    }

    @Test
    public void testMemoizedSubsetSumProblem() {
        assertTrue(ssp.memoizedSubsetSumProblem(set1, SUM1));
        assertTrue(ssp.memoizedSubsetSumProblem(set2, SUM2));
    }

    @Test
    public void testSubsetSumProblemBottomUp() {
        assertArrayEquals(new int[]{5,4}, ssp.subsetSumProblemBottomUpResult(set1, SUM1));
        assertArrayEquals(new int[]{8,3}, ssp.subsetSumProblemBottomUpResult(set2, SUM2));
    }

}
