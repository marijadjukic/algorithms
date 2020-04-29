package algorithms.chapter.advanceddatastructures;

import algorithms.chapter.datastructures.CircularLinkedList;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FibonacciHeapTest {

    private static final List<Integer> list = Arrays.asList(24, 17, 3, 7, 23);

    private FibonacciHeap<Integer> emptyFibHeap = new FibonacciHeap<>();
    private FibonacciHeap<Integer> fibHeap = new FibonacciHeap<>(list);

    @Test
    public void testEmptyFibonacciHeap() {
        assertThat(emptyFibHeap.getSize(), is(0));
        assertNull(emptyFibHeap.findMinNode());
    }

    @Test
    public void testInitFibonacciHeap() {
        assertThat(fibHeap.findMinNode().getKey(), is(3));
        assertThat(fibHeap.getSize(), is(list.size()));

        CircularLinkedList<FibonacciNode<Integer>> rootList = fibHeap.getRootList();
        CircularLinkedList.Node node = rootList.search(new FibonacciNode<>(3));
        FibonacciNode<Integer> fibNode = (FibonacciNode<Integer>) node.getPrev().getKey();

        assertThat(fibNode.getKey(), is(7));
    }

    @Test
    public void testInsert() {
        fibHeap.insert(21);
        assertThat(fibHeap.getSize(), is(6));

        fibHeap.insert(2);
        assertThat(fibHeap.findMinNode().getKey(), is(2));
    }

    @Test
    public void testUnion() {
        FibonacciHeap<Integer> unionHeap = emptyFibHeap.union(
                new FibonacciHeap<>(Arrays.asList(3,21,7,23)),
                new FibonacciHeap<>(Arrays.asList(24,17)));
        assertThat(unionHeap.getSize(), is(6));
        assertThat(unionHeap.getRootList().getLast().getKey().getKey(), is(24));

    }

}
