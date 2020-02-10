package algorithms.chapter.sorting;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinearSortingTest {

    private static final int[] array1 = new int[] {6,0,2,0,1,3,4,6,1,3,2};
    private static final int[] array2 = new int[] {2,5,3,0,2,3,0,3};
    private static final int[] array3 = new int[] {329,457,657,839,436,720,355};
    private static final double[] array4 = new double[] {0.79,0.13,0.16,0.64,0.39,0.20,0.89,0.53,0.71,0.42};

    LinearSorting ls = new LinearSorting();

    @Test
    public void testCountingSort() {
        assertArrayEquals(new int[] {0,0,1,1,2,2,3,3,4,6,6}, ls.countingSort(array1));
        assertArrayEquals(new int[] {0,0,2,2,3,3,3,5}, ls.countingSort(array2));
    }

    @Test
    public void testRadixSort() {
        assertArrayEquals(new int[] {329,355,436,457,657,720,839}, ls.radixSort(array3));

    }

    @Test
    public void testBucketSort() {
        assertArrayEquals(new double[]{0.13,0.16,0.20,0.39,0.42,0.53,0.64,0.71,0.79,0.89}, ls.bucketSort(array4),2);
    }

}
