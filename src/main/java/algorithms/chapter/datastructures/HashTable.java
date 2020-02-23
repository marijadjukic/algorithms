package algorithms.chapter.datastructures;

import java.util.Arrays;

public class HashTable<T> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;
    private DoubleLinkedList<T>[] elementData;
    final float loadFactor;
    private int size;
    private int capacity;

    public HashTable() {
        this.elementData = new DoubleLinkedList[DEFAULT_INITIAL_CAPACITY];
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.capacity = DEFAULT_INITIAL_CAPACITY;
        this.size = 0;
    }

    public HashTable(T[] array) {
        this.elementData = new DoubleLinkedList[DEFAULT_INITIAL_CAPACITY];
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.capacity = DEFAULT_INITIAL_CAPACITY;
        for(T element : array) {
            int h = hashCode(element);
            if(size==capacity*DEFAULT_LOAD_FACTOR) {
                resize();
            }
            if(elementData[h]==null){
                elementData[h] = new DoubleLinkedList<>();
            }
            elementData[h].insert(element);
            size++;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int getCapacity() {
        return this.capacity;
    }

    /**
     * In case of collision, we place all the elements that hash to the same slot into the same
     * double linked list
     * @param key key to insert
     * @return hashed key value
     */
    public int insert(T key) {
        int h = hashCode(key);
        if(size==capacity*DEFAULT_LOAD_FACTOR) {
            resize();
        }
        if(elementData[h]==null) {
            elementData[h] = new DoubleLinkedList<>();
        }
        elementData[h].insert(key);
        size++;
        return h;
    }

    /**
     * Removes the key from the table
     * @param key key to delete
     */
    public void delete(T key) {
        int h = hashCode(key);
        elementData[h].delete(key);
        if(elementData[h].isEmpty()) {
            elementData[h] = null;
        }
        size--;
    }

    /**
     * If key exists in the table, hashed value is returned,
     * otherwise -1 is returned
     * @param key key to search for
     * @return hash code of the key or -1
     */
    public int search(T key) {
        int h = hashCode(key);
        if(h>=this.capacity || elementData[h]==null || elementData[h].search(key)==null){
            return -1;
        }
        return h;
    }

    // Double hashing
    public int hashCode(T key) {
        return ((key.hashCode() % capacity) + (1 + (key.hashCode() % (capacity - 1)))) % capacity;
    }

    /**
     * Initial number of slots is 16
     * @return array of table slots
     */
    public DoubleLinkedList<T>[] getSlots() {
        return elementData;
    }

    /**
     * Resizes hash table by double of its capacity when
     * table has 75% of slots occupied
     */
    private void resize() {
        this.capacity = capacity*2;
        elementData = Arrays.copyOf(elementData, this.capacity);
    }
}
