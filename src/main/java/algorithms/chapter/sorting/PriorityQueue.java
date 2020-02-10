package algorithms.chapter.sorting;

import java.util.Arrays;

public class PriorityQueue extends Heap {

    public PriorityQueue() {
        super();
    }

    public PriorityQueue(int initialCapacity) {
        super(initialCapacity);
    }

    public PriorityQueue(int[] array) {
        super(array);
    }

    /**
     * Returns the element with largest key
     * @return maximum key of priority queue
     */
    public int heapMaximum() {
        return elementData[0];
    }


    /**
     * Removes and returns the element with the largest key
     * @return maximum key that has been removed
     */
    public int heapExtractMax() {
        if(heapSize < 0) throw new IllegalArgumentException("Heap underflow.");
        int max = elementData[0];
        remove(max);
        return max;
    }

    /**
     * Increases the value of element i’s key to the new value k,
     * which is assumed to be at least as large as i’s current key value
     * @param i ith element
     * @param key new value for i's key
     */
    public void heapIncreaseKey(int i, int key) {
        if(key < elementData[i]) {
            throw new IllegalArgumentException("New key is smaller than current key.");
        }
        elementData[i] = key;
        buildMaxHeap(elementData);
    }

    /**
     * Inserts the element with a key
     * @param key the key of the new element
     */
    public void maxHeapInsert(int key) {
        heapSize++;
        elementData = Arrays.copyOf(this.elementData, heapSize);
        elementData[heapSize-1] = Integer.MIN_VALUE;
        heapIncreaseKey(heapSize-1, key);
    }

}
