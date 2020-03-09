package algorithms.chapter.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AVLTreeTest {

    private static Integer[] array = new Integer[]{41,20,65,11,29,50,26};
    private AVLTree<Integer> avlTree = new AVLTree<>(array);
    private AVLTree<Integer> emptyAvlTree = new AVLTree<>();

    @Test
    public void testInit() {
        assertThat(avlTree.size(), is(7));

        assertThat(avlTree.getRoot().getKey(), is(41));
        assertThat(avlTree.getRoot().getHeight(), is(3));

        assertThat(avlTree.getRoot().getLeftChild().getKey(), is(20));
        assertThat(avlTree.getRoot().getLeftChild().getHeight(), is(2));

        assertThat(avlTree.getRoot().getRightChild().getKey(), is(65));
        assertThat(avlTree.getRoot().getRightChild().getHeight(), is(1));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(emptyAvlTree.isEmpty());
        assertFalse(avlTree.isEmpty());
    }

    @Test
    public void testInsertRightRotate() {
        avlTree.insert(23);

        assertThat(avlTree.size(), is(8));

        AVLTree<Integer>.Node<Integer> node = avlTree.search(23);
        assertThat(node.getParent().getKey(), is(26));
        assertThat(node.getParent().getHeight(), is(1));
    }

    @Test
    public void testInsertLeftRightRotations() {
        avlTree.insert(55);
        AVLTree<Integer>.Node<Integer> node = avlTree.search(55);

        assertThat(avlTree.getRoot().getHeight(), is(3));
        assertThat(node.getHeight(), is(1));
        assertThat(node.getParent().getKey(), is(41));
        assertThat(node.getLeftChild().getKey(), is(50));
        assertThat(node.getLeftChild().getHeight(), is(0));
        assertThat(node.getRightChild().getKey(), is(65));
        assertThat(node.getRightChild().getHeight(), is(0));
    }

    @Test
    public void testInsertInEmptyTree() {
        emptyAvlTree.insert(1);
        emptyAvlTree.insert(2);
        emptyAvlTree.insert(3);

        assertThat(emptyAvlTree.getRoot().getKey(), is(2));
        assertThat(emptyAvlTree.getRoot().getHeight(), is(1));

        assertThat(emptyAvlTree.getRoot().getLeftChild().getKey(), is(1));
        assertThat(emptyAvlTree.getRoot().getLeftChild().getHeight(), is(0));

        assertThat(emptyAvlTree.getRoot().getRightChild().getKey(), is(3));
        assertThat(emptyAvlTree.getRoot().getRightChild().getHeight(), is(0));
    }

}
