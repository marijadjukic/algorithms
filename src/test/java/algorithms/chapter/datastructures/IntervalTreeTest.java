package algorithms.chapter.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IntervalTreeTest {

    private static Integer[] array = new Integer[]{16,21,8,9,25,30,5,8,15,23,17,19,26,26,0,3,6,10,19,20};
    private IntervalTree intervalTree = new IntervalTree(array);
    private IntervalTree emptyIntervalTree = new IntervalTree();

    @Test
    public void testIsEmpty(){
        assertTrue(emptyIntervalTree.isEmpty());
        assertFalse(intervalTree.isEmpty());
    }

    @Test
    public void testSize() {
        assertThat(intervalTree.size(), is(10));
        assertThat(emptyIntervalTree.size(), is(0));
    }

    @Test
    public void testInit() {
        assertThat(intervalTree.getRoot().getInterval().getLow().intValue(), is(16));
        assertThat(intervalTree.getRoot().getInterval().getHigh().intValue(), is(21));
        assertThat(intervalTree.getRoot().getMax().intValue(), is(30));

        assertThat(intervalTree.getRoot().getLeftChild().getInterval().getLow().intValue(), is(8));
        assertThat(intervalTree.getRoot().getLeftChild().getInterval().getHigh().intValue(), is(9));
        assertThat(intervalTree.getRoot().getLeftChild().getMax().intValue(), is(23));

        assertThat(intervalTree.getRoot().getRightChild().getInterval().getLow().intValue(), is(25));
        assertThat(intervalTree.getRoot().getRightChild().getInterval().getHigh().intValue(), is(30));
        assertThat(intervalTree.getRoot().getRightChild().getMax().intValue(), is(30));
    }

    @Test
    public void testSuccessfulSearch() {
        IntervalTree.Node node = intervalTree.search(22,25);
        assertThat(node.getInterval().getLow().intValue(), is(15));
        assertThat(node.getInterval().getHigh().intValue(), is(23));
    }

    @Test
    public void testUnsuccessfulSearch() {
        IntervalTree.Node node = intervalTree.search(11,14);
        assertNull(node.getInterval().getLow());
        assertNull(node.getInterval().getHigh());
    }

}
