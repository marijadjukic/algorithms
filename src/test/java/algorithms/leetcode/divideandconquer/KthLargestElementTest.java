package algorithms.leetcode.divideandconquer;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class KthLargestElementTest {

    private static final int[] arr1 = new int[]{3,2,1,5,6,4};
    private static final int[] arr2 = new int[]{3,2,3,1,2,4,5,5,6};

    private KthLargestElement kle = new KthLargestElement();

    @Test
    public void testFindKthLargest() {
        assertThat(kle.findKthLargest(arr1,2),is(5));
        assertThat(kle.findKthLargest(arr2,4),is(4));
    }
}
