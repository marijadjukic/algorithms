package algorithms.chapter.advanceddesign.dynamicprogramming;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RodCuttingTest {

    private static final int[] profit = new int[]{2,5,7,8};
    private static final int[] profit2 = new int[]{1,5,8,9};
    private RodCutting rc = new RodCutting();

    @Test
    public void testCutRodRecursive() {
        assertThat(rc.cutRodRecursive(profit,5), is(12));
        assertThat(rc.cutRodRecursive(profit2,4), is(10));
    }

    @Test
    public void testMemoizedCutRod() {
        assertThat(rc.memoizedCutRod(profit,5), is(12));
        assertThat(rc.memoizedCutRod(profit2,4), is(10));
    }

    @Test
    public void testBottomUpCutRod() {
        assertThat(rc.bottomUpCutRod(profit,5), is(12));
        assertThat(rc.bottomUpCutRod(profit2,4), is(10));
    }

    @Test
    public void testExtendedBottomUpCutRod() {
        assertArrayEquals(new int[]{1,2,2}, rc.extendedBottomUpCutRod(profit,5));
        assertArrayEquals(new int[]{2,2}, rc.extendedBottomUpCutRod(profit2,4));
    }
}
