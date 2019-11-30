package algorithms.leetcode.divideandconquer;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaximumSubarrayTest {

    private static final int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
    private static final int[] single_array = new int[]{4};
    private static final int[] positive_array = new int[]{4,1,7,3,4};
    private static final int[] negative_array = new int[] {-4,-1,-7,-3,-4};

    private MaximumSubarray ms = new MaximumSubarray();

    @Test
    public void testMaxSubArray() {
        assertEquals(6, ms.maxSubArray(nums));
        assertEquals(4, ms.maxSubArray(single_array));
        assertEquals(19, ms.maxSubArray(positive_array));
        assertEquals(-1,ms.maxSubArray(negative_array));
    }

}
