package algorithms.chapter.advanceddatastructures;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BTreeTest {

    private static final List<String> list = Arrays.asList("G", "M", "P", "X", "A", "C", "D", "E", "J", "K", "N", "O", "R", "S", "T", "U", "V", "Y", "Z");

    @Test
    public void testCreateBTreeFromCollection() {
        BTree<String> bTree345 = new BTree<>(list, 3);

        assertThat(bTree345.getHeight(), is(2));
        assertThat(bTree345.getRoot().keysToArray().length, is(4));
        assertThat(bTree345.getRoot().childrenToArray().length, is(5));
    }

    @Test
    public void testBTreeSearch() {
        BTree<String> bTree345 = new BTree<>(list, 3);
        BTreeSearchItem<String> item = bTree345.search("J");

        assertThat(item.getNode(), is(bTree345.getRoot().getChildren()[1]));
        assertThat(item.getIndex(), is(2));

    }

}
