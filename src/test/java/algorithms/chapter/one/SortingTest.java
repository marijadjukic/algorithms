package algorithms.chapter.one;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SortingTest {

    Sorting sort = new Sorting();

    @Test
    public void testInsertionSortAsc() {

        assertArrayEquals(new int[]{0,2,3,4,4,8,9}, sort.insertionSortAsc(new int[] {4,2,3,9,8,4,0}));
        assertArrayEquals(new int[]{-8,-5,0,1,3,20}, sort.insertionSortAsc(new int[]{3,1,-8,0,20,-5}));
        assertArrayEquals(new int[]{0}, sort.insertionSortAsc(new int[]{0}));
        assertArrayEquals(new int[]{}, sort.insertionSortAsc(new int[]{}));
    }

    @Test
    public void testInsertionSortDesc() {

        assertArrayEquals(new int[]{9,8,4,4,3,2,0}, sort.insertionSortDesc(new int[] {4,2,3,9,8,4,0}));
        assertArrayEquals(new int[]{20,3,1,0,-5,-8}, sort.insertionSortDesc(new int[]{3,1,-8,0,20,-5}));
        assertArrayEquals(new int[]{0}, sort.insertionSortDesc(new int[]{0}));
        assertArrayEquals(new int[]{}, sort.insertionSortDesc(new int[]{}));
    }

    @Test
    public void testLinearSearch() {

        assertEquals(Integer.valueOf(2), sort.linearSearch(3, new Integer[] {4,2,3,9,8,4,0}));
        assertEquals(null, sort.linearSearch(1, new Integer[] {4,2,3,9,8,4,0}));
    }

    @Test
    public void testSumBinaries() {
        assertArrayEquals(new int[]{1,0,1,0,0}, sort.addTwoBinaries(new int[]{1,0,0,1}, new int[]{1,0,1,1}));
        assertArrayEquals(new int[]{0,1,1,0,0}, sort.addTwoBinaries(new int[]{1,0,0,1}, new int[]{0,0,1,1}));
    }

    @Test
    public void testSelectionSort() {
        assertArrayEquals(new int[]{0,2,3,4,4,8,9}, sort.selectionSort(new int[] {4,2,3,9,8,4,0}));
        assertArrayEquals(new int[]{-8,-5,0,1,3,20}, sort.selectionSort(new int[]{3,1,-8,0,20,-5}));
        assertArrayEquals(new int[]{0}, sort.selectionSort(new int[]{0}));
        assertArrayEquals(new int[]{}, sort.selectionSort(new int[]{}));
    }

    @Test
    public void testMergeSort() {
        assertArrayEquals(new int[] {1,2,2,3,4,5,6,7}, sort.mergeSort(new int[]{5,2,4,7,1,3,2,6}, 0, 7));
    }

    @Test
    public void testBinarySearch() {
        assertEquals(Integer.valueOf(2), sort.binarySearch(3, new int[]{0,2,3,4,4,8,9}));
        assertEquals(Integer.valueOf(5), sort.binarySearch(20, new int[]{-8,-5,0,1,3,20}));
        assertEquals(Integer.valueOf(2), sort.binarySearch(0, new int[]{-8,-5,0,1,3,9,20}));
        assertEquals(Integer.valueOf(4), sort.binarySearch(3, new int[]{-8,-5,0,1,3,9,20}));
    }

    @Test
    public void testBubbleSort() {
        assertArrayEquals(new int[]{0,2,3,4,4,8,9}, sort.bubbleSort(new int[] {4,2,3,9,8,4,0}));
        assertArrayEquals(new int[]{-8,-5,0,1,3,20}, sort.bubbleSort(new int[]{3,1,-8,0,20,-5}));
    }

}