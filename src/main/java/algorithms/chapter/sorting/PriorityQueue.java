package algorithms.chapter.sorting;

import java.util.Arrays;

public class PriorityQueue<T extends Comparable<? super T>> extends Heap<T> {

    public PriorityQueue() {
        super();
    }

    public PriorityQueue(int initialCapacity) {
        super(initialCapacity);
    }

    public PriorityQueue(T[] array) {
        super(array);
    }

    public PriorityQueue(T[] array, boolean isMin) {
        super(array, isMin);
    }

    /**
     * Returns the element with largest key
     * @return maximum key of priority queue
     */
    public T heapMaximum() {
        return elementData[0];
    }


    /**
     * Removes and returns the element with the largest key
     * @return maximum key that has been removed
     */
    public T heapExtractMax() {
        if(heapSize < 0) throw new IllegalArgumentException("Heap underflow.");
        T max = elementData[0];
        remove(max);
        return max;
    }

    /**
     * Increases the value of element i’s key to the new value k,
     * which is assumed to be at least as large as i’s current key value
     * @param i ith element
     * @param key new value for i's key
     */
    public void heapIncreaseKey(int i, T key) {
        if(key.compareTo(elementData[i]) < 0) {
            throw new IllegalArgumentException("New key is smaller than current key.");
        }
        elementData[i] = key;
        buildMaxHeap(elementData);
    }

    /**
     * Inserts the element with a key
     * @param key the key of the new element
     */
    public void maxHeapInsert(T key) {
        heapSize++;
        elementData = Arrays.copyOf(this.elementData, heapSize);
        elementData[heapSize-1] = key;
        heapIncreaseKey(heapSize-1, key);
    }

    /**
     * Removes and returns the element with the smallest key
     * @return minimum key that has been removed
     */
    public T heapExtractMin() {
        if(heapSize < 0) throw new IllegalArgumentException("Heap underflow.");
        T min = elementData[0];
        minHeapRemove(min);
        return min;
    }

    /**
     * Inserts the element with a key
     * @param key the key of the new element
     */
    public void minHeapInsert(T key) {
        heapSize++;
        elementData = Arrays.copyOf(this.elementData, heapSize);
        elementData[heapSize-1] = key;
        minHeapIncreaseKey(heapSize-1, key);
    }

    public void minHeapIncreaseKey(int i, T key) {
        if(key.compareTo(elementData[i]) < 0) {
            throw new IllegalArgumentException("New key is smaller than current key.");
        }
        elementData[i] = key;
        buildMinHeap(elementData);
    }

}
