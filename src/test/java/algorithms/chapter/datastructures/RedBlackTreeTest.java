package algorithms.chapter.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RedBlackTreeTest {

    private static final Integer[] array = new Integer[]{11,2,14,1,7,15,5,8};
    private RedBlackTree<Integer> redBlackTree = new RedBlackTree<>(array);
    private RedBlackTree<Integer> emptyRedBlackTree = new RedBlackTree<>();

    @Test
    public void testInit(){
        assertThat(redBlackTree.getRoot().getKey(), is(11));
        assertThat(redBlackTree.getRoot().getColor(), is(TreeColors.BLACK));

        assertThat(redBlackTree.getRoot().getLeftChild().getKey(), is(2));
        assertThat(redBlackTree.getRoot().getLeftChild().getColor(), is(TreeColors.RED));

        assertThat(redBlackTree.getRoot().getRightChild().getKey(), is(14));
        assertThat(redBlackTree.getRoot().getRightChild().getColor(), is(TreeColors.BLACK));

        assertThat(redBlackTree.getRoot().getLeftChild().getLeftChild().getKey(), is(1));
        assertThat(redBlackTree.getRoot().getLeftChild().getLeftChild().getColor(), is(TreeColors.BLACK));

        assertThat(redBlackTree.getRoot().getLeftChild().getRightChild().getKey(), is(7));
        assertThat(redBlackTree.getRoot().getLeftChild().getRightChild().getColor(), is(TreeColors.BLACK));

        assertThat(redBlackTree.getRoot().getRightChild().getRightChild().getKey(), is(15));
        assertThat(redBlackTree.getRoot().getRightChild().getRightChild().getColor(), is(TreeColors.RED));

        assertThat(redBlackTree.getRoot().getLeftChild().getRightChild().getLeftChild().getKey(), is(5));
        assertThat(redBlackTree.getRoot().getLeftChild().getRightChild().getLeftChild().getColor(), is(TreeColors.RED));

        assertThat(redBlackTree.getRoot().getLeftChild().getRightChild().getRightChild().getKey(), is(8));
        assertThat(redBlackTree.getRoot().getLeftChild().getRightChild().getRightChild().getColor(), is(TreeColors.RED));
    }

    @Test
    public void testSize() {
        assertThat(redBlackTree.size(), is(8));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(emptyRedBlackTree.isEmpty());
        assertFalse(redBlackTree.isEmpty());
    }

    @Test
    public void testInsert() {
        redBlackTree.insert(4);

        assertThat(redBlackTree.size(), is(9));

        assertThat(redBlackTree.getRoot().getKey(), is(7));
        assertThat(redBlackTree.getRoot().getColor(), is(TreeColors.BLACK));

        assertThat(redBlackTree.getRoot().getLeftChild().getKey(), is(2));
        assertThat(redBlackTree.getRoot().getLeftChild().getColor(), is(TreeColors.RED));

        assertThat(redBlackTree.getRoot().getRightChild().getKey(), is(11));
        assertThat(redBlackTree.getRoot().getRightChild().getColor(), is(TreeColors.RED));

        assertThat(redBlackTree.getRoot().getLeftChild().getLeftChild().getKey(), is(1));
        assertThat(redBlackTree.getRoot().getLeftChild().getLeftChild().getColor(), is(TreeColors.BLACK));

        assertThat(redBlackTree.getRoot().getLeftChild().getRightChild().getKey(), is(5));
        assertThat(redBlackTree.getRoot().getLeftChild().getRightChild().getColor(), is(TreeColors.BLACK));

        assertThat(redBlackTree.getRoot().getRightChild().getLeftChild().getKey(), is(8));
        assertThat(redBlackTree.getRoot().getRightChild().getLeftChild().getColor(), is(TreeColors.BLACK));

        assertThat(redBlackTree.getRoot().getRightChild().getRightChild().getKey(), is(14));
        assertThat(redBlackTree.getRoot().getRightChild().getRightChild().getColor(), is(TreeColors.BLACK));

        assertThat(redBlackTree.getRoot().getLeftChild().getRightChild().getLeftChild().getKey(), is(4));
        assertThat(redBlackTree.getRoot().getLeftChild().getRightChild().getLeftChild().getColor(), is(TreeColors.RED));

        assertThat(redBlackTree.getRoot().getRightChild().getRightChild().getRightChild().getKey(), is(15));
        assertThat(redBlackTree.getRoot().getRightChild().getRightChild().getRightChild().getColor(), is(TreeColors.RED));
    }

    @Test
    public void testDelete() {
        redBlackTree.delete(2);

        assertThat(redBlackTree.size(), is(7));

        assertThat(redBlackTree.getRoot().getLeftChild().getKey(), is(5));
        assertThat(redBlackTree.getRoot().getLeftChild().getColor(), is(TreeColors.RED));

        assertThat(redBlackTree.getRoot().getLeftChild().getLeftChild().getKey(), is(1));
        assertThat(redBlackTree.getRoot().getLeftChild().getRightChild().getKey(), is(7));
    }

}
