package algorithms.chapter.sorting;

import java.util.Arrays;

public class Heap {

    private static final int DEFAULT_CAPACITY = 10;

    protected int heapSize = 0;
    transient int[] elementData;

    public Heap() {
        this.elementData = new int[DEFAULT_CAPACITY];
    }

    public Heap(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new int[initialCapacity];
        } else if (initialCapacity == 0){
            this.elementData = new int[0];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public Heap(int[] array) {
        elementData = array;
        heapSize = array.length;
        buildMaxHeap(elementData);
    }

    private void maxHeapify(int[] array, int i) {
        int l = 2*i+1;
        int r = 2*i+2;
        int largest = Integer.MIN_VALUE;
        if (l<heapSize && array[l]>array[i]) {
            largest = l;
        } else {
            largest = i;
        }
        if(r<heapSize && array[r]>array[largest]) {
            largest = r;
        }
        if (largest != i) {
            int tmp = array[largest];
            array[largest] = array[i];
            array[i] = tmp;
            maxHeapify(array, largest);
        }
    }

    protected void buildMaxHeap(int[] array) {
        for(int i= heapSize/2; i>=0; i--){
            maxHeapify(array,i);
        }
    }

    private void minHeapify(int[] array, int i) {
        int l = 2*i+1;
        int r = 2*i+2;
        int largest = Integer.MIN_VALUE;
        if (l<heapSize && array[l]<array[i]) {
            largest = l;
        } else {
            largest = i;
        }
        if(r<heapSize && array[r]<array[largest]) {
            largest = r;
        }
        if (largest != i) {
            int tmp = array[largest];
            array[largest] = array[i];
            array[i] = tmp;
            minHeapify(array, largest);
        }
    }

    private void buildMinHeap(int[] array) {
        for(int i= heapSize/2; i>=0; i--){
            minHeapify(array,i);
        }
    }

    public void minHeapProperty() {
        buildMinHeap(elementData);
    }

    public void insert(int element) {
        if (this.heapSize == this.elementData.length) {
            elementData = Arrays.copyOf(this.elementData, heapSize + 1);
            elementData[heapSize] = element;
        }
        heapSize++;
        buildMaxHeap(elementData);
    }

    public boolean remove(int element) {
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

    public int[] heapSort() {
        buildMaxHeap(elementData);
        for(int i=elementData.length-1; i>0; i--) {
            int tmp = elementData[0];
            elementData[0] = elementData[i];
            elementData[i] = tmp;
            heapSize--;
            maxHeapify(elementData,0);
        }
        heapSize = elementData.length;
        return elementData;
    }

    public int getParent(int i) {
        return elementData[i/2];
    }

    public int getLeft(int i) {
        return elementData[2*i+1];
    }

    public int getRight(int i) {
        return elementData[2*i+2];
    }

    public int getRoot() {
        return elementData[0];
    }

    public int size() {
        return this.heapSize;
    }

    public int indexOf(int element) {
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

    public int[] toArray() {
        return elementData;
    }

    @Override
    public String toString() {
        String elements = "";
        for(int element : elementData) {
            elements += element + " ";
        }
        return "[ " + elements + "]";
    }
}
