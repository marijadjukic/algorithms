package algorithms.chapter.advanceddesign.dynamicprogramming;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class MinJumpsProblemTest {

    private static final int[] jumps1 = new int[]{2, 3, 1, 1, 2, 4, 2, 0, 1, 1};
    private static final int[] jumps2 = new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};

    private MinJumpsProblem mjp = new MinJumpsProblem();

    @Test
    public void testMinJumpsRecursive() {
        assertThat(mjp.minJumpsRecursive(jumps1), is(4));
        assertThat(mjp.minJumpsRecursive(jumps2), is(3));
    }

    @Test
    public void testMemoizedMinJumps() {
        assertThat(mjp.memoizedMinJumps(jumps1), is(4));
        assertThat(mjp.memoizedMinJumps(jumps2), is(3));
    }

    @Test
    public void testMinJumpsBottomUp() {
        MinJumpsItem minJumpsItem1 = mjp.minJumpsBottomUp(jumps1);
        assertThat(minJumpsItem1.getMinNumOfJumps(), is(4));
        assertThat(minJumpsItem1.getJumpsArray(), is(new int[]{0, 1, 4, 5 ,9}));

        MinJumpsItem minJumpsItem2 = mjp.minJumpsBottomUp(jumps2);
        assertThat(minJumpsItem2.getMinNumOfJumps(), is(3));
        assertThat(minJumpsItem2.getJumpsArray(), is(new int[]{0, 1, 3, 10}));
    }

}
