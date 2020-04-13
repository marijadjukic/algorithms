package algorithms.chapter.advanceddesign.dynamicprogramming;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CoinChangeProblemTest {

    private static final int[] coins1 = new int[]{7,2,3,6};
    private static final int TOTAL_1 = 13;

    private static final int[] coins2 = new int[]{2,5,3,6};
    private static final int TOTAL_2 = 10;

    private CoinChangeProblem ccp = new CoinChangeProblem();

    @Test
    public void testCoinChangeRecursive() {
        assertThat(ccp.coinChangeRecursive(coins1, TOTAL_1), is(2));
        assertThat(ccp.coinChangeRecursive(coins2, TOTAL_2), is(2));
    }

    @Test
    public void testCoinChangeRecursiveGivenTotalWillReturnOneCoin() {
        assertThat(ccp.coinChangeRecursive(coins1, 7), is(1));
    }

    @Test
    public void testCoinChangeRecursiveGivenTotalWillReturnZeroCoins() {
        assertThat(ccp.coinChangeRecursive(coins1, 1), is(0));
    }

    @Test
    public void testCoinChangeTotalNumSolutionRecursive() {
        assertThat(ccp.coinChangeTotalNumSolutionRecursive(coins1, TOTAL_1), is(6));
        assertThat(ccp.coinChangeTotalNumSolutionRecursive(coins2, TOTAL_2), is(5));
    }

    @Test
    public void testMemoizedCoinChange() {
        assertThat(ccp.memoizedCoinChange(coins1, TOTAL_1), is(2));
        assertThat(ccp.memoizedCoinChange(coins2, TOTAL_2), is(2));
    }

    @Test
    public void testCoinChangeBottomUp() {
        assertThat(ccp.coinChangeBottomUp(coins1, TOTAL_1)[coins1.length][TOTAL_1], is(2));
        assertThat(ccp.coinChangeBottomUp(coins2, TOTAL_2)[coins2.length][TOTAL_2], is(2));
    }

    @Test
    public void testCoinChangeBottomUpResult() {
        assertArrayEquals(new int[]{6,7}, ccp.coinChangeBottomUpResult(coins1, TOTAL_1));
        assertArrayEquals(new int[]{5,5}, ccp.coinChangeBottomUpResult(coins2, TOTAL_2));
    }

    @Test
    public void testCoinChangeBottomUpSpaceOptimization() {
        assertThat(ccp.coinChangeBottomUpSpaceOptimization(coins1, TOTAL_1), is(2));
        assertThat(ccp.coinChangeBottomUpSpaceOptimization(coins2, TOTAL_2), is(2));
    }

}
