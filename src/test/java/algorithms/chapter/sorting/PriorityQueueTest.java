package algorithms.chapter.sorting;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PriorityQueueTest {

    private final int[] array = new int[]{16,14,10,8,7,9,3,2,4,1};

    private PriorityQueue priorityQueue = new PriorityQueue(array);

    @Test
    public void testHeapMaximum() {
        assertThat(priorityQueue.heapMaximum(), is(16));

        priorityQueue.maxHeapInsert(20);
        assertThat(priorityQueue.heapMaximum(), is(20));
    }

    @Test
    public void testHeapExtractMax() {
        assertThat(priorityQueue.size(), is(10));
        assertThat(priorityQueue.heapExtractMax(), is(16));
        assertThat(priorityQueue.size(), is(9));
    }

    @Test
    public void testHeapIncreaseKey() {
        priorityQueue.heapIncreaseKey(8, 15);

        assertThat(priorityQueue.size(), is(10));
        assertThat(priorityQueue.elementData[1], is(15));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHeapIncreaseWrongKey() {
        priorityQueue.heapIncreaseKey(8, 2);
    }

    @Test
    public void testMaxHeapInsert() {
        priorityQueue.maxHeapInsert(5);
        assertThat(priorityQueue.size(), is(11));

        priorityQueue.insert(13);
        assertThat(priorityQueue.size(), is(12));
        assertThat(priorityQueue.indexOf(13), is(2));
        assertThat(priorityQueue.getLeft(2), is(10));
    }

}
