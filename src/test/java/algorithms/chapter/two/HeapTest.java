package algorithms.chapter.two;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class HeapTest {

    private final int[] array = new int[]{16,14,10,8,7,9,3,2,4,1};
    private Heap heap = new Heap(array);

    @Test
    public void testHeapDataStructure() {

        assertThat(heap.getLeft(3), is(2));
        assertThat(heap.getRight(3), is(4));
        assertThat(heap.getParent(3), is(14));
        assertThat(heap.getRoot(), is(16));
        assertThat(heap.size(), is(10));

    }

    @Test
    public void testEmptyHeap() {
        Heap emptyHeap = new Heap();
        assertTrue(emptyHeap.isEmpty());
    }

    @Test
    public void testHeapInsert() {
        heap.insert(5);
        assertThat(heap.size(), is(11));

        heap.insert(13);
        assertThat(heap.size(), is(12));
        assertThat(heap.indexOf(13), is(2));
        assertThat(heap.getLeft(2), is(10));
    }

    @Test
    public void testHeapRemove() {
        heap.remove(8);

        assertThat(heap.size(), is(9));
        assertThat(heap.getLeft(1), is(7));

        assertFalse(heap.remove(100));
    }

    @Test
    public void testHeapSort() {
        assertArrayEquals(new int[]{1,2,3,4,7,8,9,10,14,16},heap.heapSort());
    }

    @Test
    public void testMinHeapProperty() {
        heap.minHeapProperty();
        assertArrayEquals(new int[]{1,2,3,4,7,9,10,8,16,14}, heap.toArray());
        assertThat(heap.getRoot(), is(1));
        assertThat(heap.getLeft(3), is(8));
        assertThat(heap.getRight(3), is(16));
        assertThat(heap.getParent(3), is(2));
    }
}
