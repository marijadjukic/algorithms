package algorithms.chapter.datastructures;

import org.junit.Test;

import java.awt.color.ICC_Profile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BinarySearchTreeTest {

    private static final Integer[] array = new Integer[]{15,6,18,3,7,17,20,2,4,13,9};
    private BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    private BinarySearchTree<Integer> bstFromArray = new BinarySearchTree<>(array);

    @Test
    public void testIsEmpty() {
        assertTrue(bst.isEmpty());
        assertFalse(bstFromArray.isEmpty());
    }

    @Test
    public void testSize() {
        assertThat(bstFromArray.size(), is(11));
        assertThat(bst.size(), is(0));
    }

    @Test
    public void testInit() {
        // root
        assertThat(bstFromArray.getRoot().getKey(), is(15));

        // first level
        assertThat(bstFromArray.getRoot().getLeftChild().getKey(), is(6));
        assertThat(bstFromArray.getRoot().getRightChild().getKey(), is(18));

        // second level
        assertThat(bstFromArray.getRoot().getLeftChild().getLeftChild().getKey(), is(3));
        assertThat(bstFromArray.getRoot().getLeftChild().getRightChild().getKey(), is(7));
        assertThat(bstFromArray.getRoot().getRightChild().getLeftChild().getKey(), is(17));
        assertThat(bstFromArray.getRoot().getRightChild().getRightChild().getKey(), is(20));

        // third level
        assertThat(bstFromArray.getRoot().getLeftChild().getLeftChild().getLeftChild().getKey(), is(2));
        assertThat(bstFromArray.getRoot().getLeftChild().getLeftChild().getRightChild().getKey(), is(4));
        assertNull(bstFromArray.getRoot().getLeftChild().getRightChild().getLeftChild());
        assertThat(bstFromArray.getRoot().getLeftChild().getRightChild().getRightChild().getKey(), is(13));

        // fourth level
        assertThat(bstFromArray.getRoot().getLeftChild().getRightChild().getRightChild().getLeftChild().getKey(), is(9));
        assertNull(bstFromArray.getRoot().getLeftChild().getRightChild().getRightChild().getRightChild());
    }

    @Test
    public void testInorderTreeWalk() {
        List<Integer> sortedKeys = bstFromArray.inorderTreeWalk(bstFromArray.getRoot());
        List<Integer> sortedList = Arrays.asList(array).stream().sorted().collect(Collectors.toList());
        assertEquals(sortedKeys, sortedList);
    }

    @Test
    public void testSearch() {
        BinarySearchTree.Node nodeInTree = bstFromArray.search(13);
        assertThat(nodeInTree.getKey(), is(13));
        assertThat(nodeInTree.getParent().getKey(), is(7));
        assertThat(nodeInTree.getLeftChild().getKey(), is(9));

        BinarySearchTree.Node nodeNotInTree = bstFromArray.search(5);
        assertNull(nodeNotInTree);
    }

    @Test
    public void testMinimum() {
        Integer min = bstFromArray.minimum();
        assertThat(min, is(2));
        assertNull(bst.minimum());
    }

    @Test
    public void testMaximum() {
        Integer max = bstFromArray.maximum();
        assertThat(max, is(20));
        assertNull(bst.maximum());
    }

    @Test
    public void testSuccessor() {
        Integer successor15 = bstFromArray.successor(15).getKey();
        assertThat(successor15, is(17));

        Integer successor13 = bstFromArray.successor(13).getKey();
        assertThat(successor13, is(15));
    }

    @Test
    public void testPredecessor() {
        Integer predecessor15 = bstFromArray.predecessor(15).getKey();
        assertThat(predecessor15, is(13));

        Integer predecessor7 = bstFromArray.predecessor(7).getKey();
        assertThat(predecessor7, is(6));
    }

    @Test
    public void testInsert() {
        bst.insert(8);
        assertThat(bst.size(), is(1));
        assertThat(bst.getRoot().getKey(), is(8));

        bst.insert(5);
        bst.insert(6);
        assertThat(bst.size(), is(3));
        assertThat(bst.getRoot().getLeftChild().getKey(), is(5));
        assertThat(bst.getRoot().getLeftChild().getRightChild().getKey(), is(6));

        bstFromArray.insert(14);
        BinarySearchTree.Node node = bstFromArray.search(14);
        assertThat(node.getParent().getKey(), is(13));
        assertThat(bstFromArray.size(),is(12));
    }

    @Test
    public void testDeleteNoLeftChild() {
        bstFromArray.delete(7);
        assertThat(bstFromArray.size(), is(10));
        assertThat(bstFromArray.search(6).getRightChild().getKey(), is(13));
    }

    @Test
    public void testDeleteHasLeftChildAndNoRightChild() {
        bstFromArray.delete(13);
        assertThat(bstFromArray.search(7).getRightChild().getKey(), is(9));
    }

    @Test
    public void testDeleteTwoChildrenAndSuccessorIsRightChild() {
        bstFromArray.delete(6);
        assertThat(bstFromArray.search(7).getLeftChild().getKey(), is(3));
        assertThat(bstFromArray.search(7).getRightChild().getKey(), is(13));
        assertThat(bstFromArray.search(7).getParent().getKey(), is(15));
    }

    @Test
    public void testDeleteTwoChildrenAndSuccessorIsNotRightChild() {
        BinarySearchTree.Node successor = bstFromArray.successor(15);
        bstFromArray.delete(15);
        assertThat(successor.getLeftChild().getKey(), is(6));
        assertThat(successor.getRightChild().getKey(), is(18));

    }

}
