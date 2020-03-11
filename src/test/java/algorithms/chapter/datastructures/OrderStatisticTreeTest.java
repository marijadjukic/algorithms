package algorithms.chapter.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class OrderStatisticTreeTest {

    private static final Integer[] array = new Integer[]{26,17,41,14,21,30,47,10,16,19,21,28,38,7,12,14,20,35,39,3};
    private OrderStatisticTree<Integer> osTree = new OrderStatisticTree<>(array);

    @Test
    public void testNodeSize(){
        assertThat(osTree.getRoot().getNodeSize(), is(20));
        assertThat(osTree.getRoot().getLeftChild().getNodeSize(), is(12));
        assertThat(osTree.getRoot().getRightChild().getNodeSize(), is(7));
    }

    @Test
    public void testSelect(){
        assertThat(osTree.select(17), is(38));
        assertThat(osTree.select(10), is(20));
    }

    @Test
    public void testRank() {
        assertThat(osTree.rank(38), is(17));
        assertThat(osTree.rank(20), is(10));
        assertThat(osTree.rank(35), is(16));
    }

}
