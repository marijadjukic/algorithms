package algorithms.chapter.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CircularLinkedListTest {

    private Integer[] array = new Integer[]{9,16,4,1};
    private CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>(array);
    private CircularLinkedList<Integer> emptyCircularLinkedList = new CircularLinkedList<>();

    @Test
    public void testInit() {
        assertThat(circularLinkedList.getSentinel().getNext().getKey(), is(9));
        assertThat(circularLinkedList.getSentinel().getPrev().getKey(), is(1));
    }

    @Test
    public void testSize() {
        assertThat(circularLinkedList.size(), is(4));
        assertArrayEquals(array, circularLinkedList.toArray());
    }

    @Test
    public void testEmptyLinkedList() {
        assertThat(emptyCircularLinkedList.size(), is(0));
        assertTrue(emptyCircularLinkedList.isEmpty());
    }

    @Test
    public void testSearch() {
        CircularLinkedList.Node node = circularLinkedList.search(4);
        assertThat(node.getKey(),is(4));
        assertThat(node.getPrev().getKey(), is(16));
        assertThat(node.getNext().getKey(), is(1));

        CircularLinkedList.Node node2 = circularLinkedList.search(7);
        assertThat(node2, is(circularLinkedList.getSentinel()));
    }

    @Test
    public void testInsert() {
        circularLinkedList.insert(25);
        assertThat(circularLinkedList.size(), is(5));
        assertThat(circularLinkedList.getSentinel().getNext().getKey(), is(25));
        assertThat(circularLinkedList.getSentinel().getNext().getNext().getKey(), is(9));
        assertThat(circularLinkedList.getSentinel().getNext().getPrev(), is(circularLinkedList.getSentinel()));
    }

    @Test
    public void testDelete() {
        circularLinkedList.delete(4);
        assertThat(circularLinkedList.size(), is(3));
        assertThat(circularLinkedList.getSentinel().getPrev().getPrev().getKey(), is(16));
    }


}
