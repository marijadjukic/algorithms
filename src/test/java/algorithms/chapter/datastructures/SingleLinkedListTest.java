package algorithms.chapter.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SingleLinkedListTest {

    private Integer[] array = new Integer[]{9,16,4,1};
    private SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>(array);
    private SingleLinkedList<Integer> emptySingleLinkedList = new SingleLinkedList<>();

    @Test
    public void testInit() {
        assertThat(singleLinkedList.getHead().getKey(), is(9));
        assertThat(singleLinkedList.getHead().getNext().getKey(), is(16));

        assertThat(singleLinkedList.getTail().getKey(), is(1));
        assertNull(singleLinkedList.getTail().getNext());
    }

    @Test
    public void testSize() {
        assertThat(singleLinkedList.size(), is(4));
        assertArrayEquals(array,singleLinkedList.toArray());
    }

    @Test
    public void testEmptyLinkedList() {
        assertThat(emptySingleLinkedList.size(), is(0));
        assertTrue(emptySingleLinkedList.isEmpty());
    }

    @Test
    public void testSearch() {
        SingleLinkedList.Node node = singleLinkedList.search(4);
        assertThat(node.getKey(),is(4));
        assertThat(node.getNext().getKey(), is(1));

        SingleLinkedList.Node node2 = singleLinkedList.search(7);
        assertNull(node2);
    }

    @Test
    public void testInsert() {
        singleLinkedList.insert(25);
        assertThat(singleLinkedList.size(), is(5));
        assertThat(singleLinkedList.getHead().getKey(), is(25));
        assertThat(singleLinkedList.getHead().getNext().getKey(), is(9));
    }

    @Test
    public void testDelete() {
        singleLinkedList.delete(4);
        assertThat(singleLinkedList.size(), is(4));
    }

}
