package algorithms.chapter.advanceddatastructures;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class VanEmdeBoasTreeTest {

    private static final Set<Integer> set = new TreeSet<>(Arrays.asList(2,3,4,5,7,14,15));
    private VanEmdeBoasTree vEBTree = new VanEmdeBoasTree(set);

    @Test
    public void testMinAndMax() {
        assertThat(vEBTree.minimum(), is(2));
        assertThat(vEBTree.maximum(), is(15));
    }

    @Test
    public void testIsMember() {
        assertTrue(vEBTree.isMember(5));
        assertFalse(vEBTree.isMember(9));
    }

    @Test
    public void testSuccessor() {
        assertThat(vEBTree.successor(7), is(14));
        assertThat(vEBTree.successor(2), is(3));
    }

    @Test
    public void testPredecessor() {
        assertThat(vEBTree.predecessor(7), is(5));
        assertThat(vEBTree.predecessor(14), is(7));
    }

    @Test
    public void testInsert() {
        vEBTree.insert(10);
        assertThat(vEBTree.successor(7), is(10));
        assertThat(vEBTree.predecessor(14), is(10));

        vEBTree.insert(1);
        assertThat(vEBTree.minimum(), is(1));
    }

    @Test
    public void testDelete() {
        vEBTree.delete(14);
        assertThat(vEBTree.successor(7), is(15));

        vEBTree.delete(15);
        assertThat(vEBTree.maximum(), is(7));
    }

}
