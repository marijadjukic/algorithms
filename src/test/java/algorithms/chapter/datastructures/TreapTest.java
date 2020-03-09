package algorithms.chapter.datastructures;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TreapTest {

    private static final String[] array = new String[]{"G","B","H","A","E","K","I"};
    private Treap<String> treap = new Treap<>(array);
    private Treap<String> emptyTreap = new Treap<>();

    @Test
    public void testInit() {
        assertThat(treap.size(), is(7));

        for(String key : array){
            Treap<String>.Node<String> node = treap.search(key);
            if(node.getParent()!=null) {
                assertTrue(node.getPriority() < node.getParent().getPriority());
            }
        }
    }

    @Test
    public void testInsert() {
        treap.insert("D");

        assertThat(treap.size(), is(8));

        List<String> sortedTreap = treap.inorderTreeWalk(treap.getRoot());
        for(String key : sortedTreap){
            Treap<String>.Node<String> node = treap.search(key);
            if(node.getParent()!=null) {
                assertTrue(node.getPriority() < node.getParent().getPriority());
            }
        }
    }

    @Test
    public void testIsEmpty() {
        assertTrue(emptyTreap.isEmpty());
        assertFalse(treap.isEmpty());
    }

}
