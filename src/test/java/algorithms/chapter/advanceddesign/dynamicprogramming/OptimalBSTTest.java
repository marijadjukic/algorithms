package algorithms.chapter.advanceddesign.dynamicprogramming;

import algorithms.chapter.datastructures.BinarySearchTree;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class OptimalBSTTest {

    private static final int[] keys = new int[] {10,12,16,21};
    private static final int[] frequency = new int[] {4,2,6,3};

    private OptimalBST optimalBST = new OptimalBST();

    @Test
    public void testOptimalBSTRecursive() {
        assertThat(optimalBST.optimalBSTRecursive(keys,frequency), is(26));
    }

    @Test
    public void testOptimalBSTMemoized() {
        assertThat(optimalBST.optimalBSTMemoized(keys,frequency), is(26));
    }

    @Test
    public void testOptimalBSTBottomUp() {
        BinarySearchTree<Integer> bst = optimalBST.getOptimalBST(keys,frequency);

        assertThat(bst.getRoot().getKey(), is(16));
        assertThat(bst.getRoot().getLeftChild().getKey(), is(10));
        assertThat(bst.getRoot().getLeftChild().getRightChild().getKey(), is(12));
        assertThat(bst.getRoot().getRightChild().getKey(), is(21));
    }

}
