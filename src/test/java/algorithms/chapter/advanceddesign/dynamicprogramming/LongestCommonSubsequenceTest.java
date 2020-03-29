package algorithms.chapter.advanceddesign.dynamicprogramming;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LongestCommonSubsequenceTest {

    private static final String X1 = "ABBA";
    private static final String Y1 = "AA";
    private static final String X2 = "ABCBDAB";
    private static final String Y2 = "BDCABA";
    private static final String X3 = "10010101";
    private static final String Y3 = "010110110";

    private LongestCommonSubsequence lcs = new LongestCommonSubsequence();

    @Test
    public void testLongestCommonSubsequenceRecursive() {
        assertThat(lcs.longestCommonSubsequenceRecursive(X1,Y1), is(2));
        assertThat(lcs.longestCommonSubsequenceRecursive(X2,Y2), is(4));
        assertThat(lcs.longestCommonSubsequenceRecursive(X3,Y3), is(6));
    }

    @Test
    public void testMemoizedLongestCommonSubsequence() {
        assertThat(lcs.memoizedLongestCommonSubsequence(X1,Y1), is(2));
        assertThat(lcs.memoizedLongestCommonSubsequence(X2,Y2), is(4));
        assertThat(lcs.memoizedLongestCommonSubsequence(X3,Y3), is(6));
    }

    @Test
    public void testGetLongestCommonSubsequenceResult() {
        assertThat(lcs.getLongestCommonSubsequenceResult(X1,Y1), is("AA"));
        assertThat(lcs.getLongestCommonSubsequenceResult(X2,Y2), is("BCBA"));
        assertThat(lcs.getLongestCommonSubsequenceResult(X3,Y3), is("100110"));
    }

}
