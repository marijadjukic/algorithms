package algorithms.chapter.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class HashTableTest {

    private Integer[] array = new Integer[]{79,69,98,72,14,50,22,4,9,33,19,42};
    private HashTable<Integer> emptyHashTable = new HashTable<>();
    private HashTable<Integer> hashTable = new HashTable<>(array);

    @Test
    public void testCapacity() {
        assertThat(hashTable.getCapacity(), is(16));
        hashTable.insert(15);
        assertThat(hashTable.getCapacity(),is(32));
    }

    @Test
    public void testInsert() {
        assertTrue(emptyHashTable.isEmpty());

        int h = emptyHashTable.insert(79);
        emptyHashTable.insert(69);
        emptyHashTable.insert(98);
        emptyHashTable.insert(72);
        emptyHashTable.insert(14);
        emptyHashTable.insert(50);
        emptyHashTable.insert(22);
        emptyHashTable.insert(4);
        emptyHashTable.insert(9);
        emptyHashTable.insert(33);
        emptyHashTable.insert(19);
        emptyHashTable.insert(42);

        assertThat(emptyHashTable.size(), is(12));
        assertThat(emptyHashTable.hashCode(79), is(h));
        assertEquals(emptyHashTable.getSlots()[h].getHead().getKey(), hashTable.getSlots()[h].getHead().getKey());
    }

    @Test
    public void testDelete() {
        hashTable.delete(14);
        assertThat(hashTable.size(), is(11));
        assertThat(hashTable.getCapacity(), is(16));

        DoubleLinkedList<Integer>[] slots = hashTable.getSlots();
        int h = hashTable.hashCode(14);
        assertNull(slots[h]);
    }

    @Test
    public void testSearch() {
        int h = hashTable.search(50);
        assertThat(hashTable.hashCode(50), is(h));

        assertThat(hashTable.search(3), is(-1));
    }

}
