package algorithms.chapter.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class QueueTest {

    private static final int[] array1 = new int[]{15,6,9,8,4};
    private static final int[] array2 = new int[]{15,6,9,8,4,17,3,5};

    private Queue emptyQueue = new Queue();
    private Queue queue1 = new Queue(array1,6,12);
    private Queue queue2 = new Queue(array2,6,10);

    @Test
    public void testIsEmpty() {
        assertTrue(emptyQueue.isEmpty());
        assertFalse(queue1.isEmpty());
    }

    @Test
    public void testEnqueue() {
        queue1.enQueue(17);
        queue1.enQueue(3);
        queue1.enQueue(5);
        assertThat(queue1.size(), is(8));

        queue2.enQueue(1);
        assertThat(queue2.size(), is(9));
    }

    @Test
    public void testDequeue() {
        int element = queue1.deQueue();
        assertThat(element, is(15));
        assertThat(queue1.size(), is(4));
    }

    @Test(expected = Error.class)
    public void testUnderflow() {
        emptyQueue.deQueue();
    }

    @Test (expected = Error.class)
    public void testOverflow() {
        queue2.enQueue(3);
        queue2.enQueue(6);
        assertThat(queue2.size(),is(10));
        queue2.enQueue(6);
    }

}
