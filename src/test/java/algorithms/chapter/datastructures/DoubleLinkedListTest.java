package algorithms.chapter.datastructures;

import org.junit.Test;
import org.w3c.dom.Node;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DoubleLinkedListTest {

    private Integer[] array = new Integer[]{9,16,4,1};
    private DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>(array);
    private DoubleLinkedList<Integer> emptyDoubleLinkedList = new DoubleLinkedList<>();


    @Test
    public void testInit() {
        assertThat(doubleLinkedList.getHead().getKey(), is(9));
        assertThat(doubleLinkedList.getHead().getNext().getKey(), is(16));
        assertNull(doubleLinkedList.getHead().getPrev());

        assertThat(doubleLinkedList.getTail().getKey(), is(1));
        assertThat(doubleLinkedList.getTail().getPrev().getKey(), is(4));
        assertNull(doubleLinkedList.getTail().getNext());
    }

    @Test
    public void testSize() {
        assertThat(doubleLinkedList.size(), is(4));
        assertArrayEquals(array,doubleLinkedList.toArray());
    }

    @Test
    public void testEmptyLinkedList() {
        assertThat(emptyDoubleLinkedList.size(), is(0));
        assertTrue(emptyDoubleLinkedList.isEmpty());
    }

    @Test
    public void testSearch() {
        DoubleLinkedList.Node node = doubleLinkedList.search(4);
        assertThat(node.getKey(),is(4));
        assertThat(node.getPrev().getKey(), is(16));
        assertThat(node.getNext().getKey(), is(1));

        DoubleLinkedList.Node node2 = doubleLinkedList.search(7);
        assertNull(node2);
    }

    @Test
    public void testInsert() {
        doubleLinkedList.insert(25);
        assertThat(doubleLinkedList.size(), is(5));
        assertThat(doubleLinkedList.getHead().getKey(), is(25));
        assertThat(doubleLinkedList.getHead().getNext().getKey(), is(9));
        assertNull(doubleLinkedList.getHead().getPrev());
    }

    @Test
    public void testDelete() {
        doubleLinkedList.delete(4);
        assertThat(doubleLinkedList.size(), is(4));
        assertThat(doubleLinkedList.getTail().getPrev().getKey(), is(16));
    }

}
