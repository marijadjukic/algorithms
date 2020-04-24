package algorithms.chapter.sorting;

import java.util.Arrays;

public class Heap<T extends Comparable<? super T>> {

    private static final int DEFAULT_CAPACITY = 10;

    protected int heapSize = 0;
    protected T[] elementData;

    public Heap() {
        this.elementData = newArray(DEFAULT_CAPACITY);
    }

    public Heap(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = newArray(initialCapacity);
        } else if (initialCapacity == 0){
            this.elementData = newArray(0);
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public Heap(T[] array) {
        elementData = array;
        heapSize = array.length;
        buildMaxHeap(elementData);
    }

    public Heap(T[] array, boolean isMin) {
        elementData = newArray(array.length, array);
        heapSize = array.length;
        if(isMin) {
            buildMinHeap(elementData);
        } else {
            buildMaxHeap(elementData);
        }
    }

    @SafeVarargs
    static <T> T[] newArray(int length, T... array) {
        return Arrays.copyOf(array, length);
    }

    private void maxHeapify(T[] array, int i) {
        int l = 2*i+1;
        int r = 2*i+2;
        int largest = Integer.MIN_VALUE;
        if (l<heapSize && array[l].compareTo(array[i])>0) {
            largest = l;
        } else {
            largest = i;
        }
        if(r<heapSize && array[r].compareTo(array[largest])>0) {
            largest = r;
        }
        if (largest != i) {
            T tmp = array[largest];
            array[largest] = array[i];
            array[i] = tmp;
            maxHeapify(array, largest);
        }
    }

    protected void buildMaxHeap(T[] array) {
        for(int i= heapSize/2; i>=0; i--){
            maxHeapify(array,i);
        }
    }

    private void minHeapify(T[] array, int i) {
        int l = 2*i+1;
        int r = 2*i+2;
        int largest = Integer.MIN_VALUE;
        if (l<heapSize && array[l].compareTo(array[i])<0) {
            largest = l;
        } else {
            largest = i;
        }
        if(r<heapSize && array[r].compareTo(array[largest])<0) {
            largest = r;
        }
        if (largest != i) {
            T tmp = array[largest];
            array[largest] = array[i];
            array[i] = tmp;
            minHeapify(array, largest);
        }
    }

    protected void buildMinHeap(T[] array) {
        for(int i= heapSize/2; i>=0; i--){
            minHeapify(array,i);
        }
    }

    public void minHeapProperty() {
        buildMinHeap(elementData);
    }

    public void insert(T element) {
        if (this.heapSize == this.elementData.length) {
            elementData = Arrays.copyOf(this.elementData, heapSize + 1);
            elementData[heapSize] = element;
        }
        heapSize++;
        buildMaxHeap(elementData);
    }

    public boolean remove(T element) {
        int i = this.indexOf(element);
        if(i == -1) {
            return false;
        }
        int newSize = elementData.length - 1;
        if (newSize > i) {
            System.arraycopy(elementData, i + 1, elementData, i, newSize-i);
            elementData = Arrays.copyOfRange(elementData,0,newSize);
            heapSize--;
            buildMaxHeap(elementData);
            return true;
        }
        return false;
    }

    public boolean minHeapRemove(T element) {
        int i = this.indexOf(element);
        if(i == -1) {
            return false;
        }
        int newSize = elementData.length - 1;
        if (newSize > i) {
            System.arraycopy(elementData, i + 1, elementData, i, newSize-i);
            elementData = Arrays.copyOfRange(elementData,0,newSize);
            heapSize--;
            buildMinHeap(elementData);
            return true;
        }
        return false;
    }

    public T[] heapSort() {
        buildMaxHeap(elementData);
        for(int i=elementData.length-1; i>0; i--) {
            T tmp = elementData[0];
            elementData[0] = elementData[i];
            elementData[i] = tmp;
            heapSize--;
            maxHeapify(elementData,0);
        }
        heapSize = elementData.length;
        return elementData;
    }

    public T getParent(int i) {
        return elementData[i/2];
    }

    public T getLeft(int i) {
        return elementData[2*i+1];
    }

    public T getRight(int i) {
        return elementData[2*i+2];
    }

    public T getRoot() {
        return elementData[0];
    }

    public int size() {
        return this.heapSize;
    }

    public int indexOf(T element) {
        for(int i=0; i<heapSize; i++) {
            if(elementData[i] == element){
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.heapSize == 0;
    }

    public T[] toArray() {
        return elementData;
    }

    @Override
    public String toString() {
        String elements = "";
        for(T element : elementData) {
            elements += element + " ";
        }
        return "[ " + elements + "]";
    }
}
