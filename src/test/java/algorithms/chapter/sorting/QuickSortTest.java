package algorithms.chapter.sorting;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {

    private static final int[] array1 = new int[]{2, 8, 7, 1, 3, 5, 6, 4};
    private static final int[] sortedArray1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

    private static final int[] array2 = new int[] {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
    private static final int[] sortedArray2 = new int[]{2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 19, 21};

    private static final int[] emptyArray = new int[]{};

    private QuickSort qs = new QuickSort();

    @Test
    public void quickSortTest() {
        assertArrayEquals(sortedArray1, qs.quickSort(array1));
        assertArrayEquals(sortedArray2, qs.quickSort(array2));
        assertArrayEquals(emptyArray, qs.quickSort(emptyArray));
    }

    @Test
    public void randomizedQuickSortTest() {
        assertArrayEquals(sortedArray1, qs.randomizedQuickSort(array1));
        assertArrayEquals(sortedArray2, qs.randomizedQuickSort(array2));
        assertArrayEquals(emptyArray, qs.randomizedQuickSort(emptyArray));
    }

    @Test
    public void hoareQuickSortTest() {
        assertArrayEquals(sortedArray1, qs.hoareQuickSort(array1));
        assertArrayEquals(sortedArray2, qs.hoareQuickSort(array2));
        assertArrayEquals(emptyArray, qs.hoareQuickSort(emptyArray));
    }

}
