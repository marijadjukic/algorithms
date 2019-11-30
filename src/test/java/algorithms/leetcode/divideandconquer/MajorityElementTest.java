package algorithms.leetcode.divideandconquer;

import org.junit.Test;

import static org.junit.Assert.*;

public class MajorityElementTest {

    private static final int[] arr1 = new int[]{3,2,3};
    private static final int[] arr2 = new int[]{-1,-1,2147483647};
    private static final int[] arr3 = new int[]{2,2,1,1,1,2,2};
    private static final int[] arr4 = new int[]{2147483647};
    private static final int[] arr5 = new int[]{-1,100,2,100,100,4,100};

    private MajorityElement me = new MajorityElement();

    @Test
    public void testMajorityElement() {
        assertEquals(3,me.majorityElement(arr1));
        assertEquals(-1,me.majorityElement(arr2));
        assertEquals(2,me.majorityElement(arr3));
        assertEquals(2147483647,me.majorityElement(arr4));
        assertEquals(100,me.majorityElement(arr5));
    }

}
