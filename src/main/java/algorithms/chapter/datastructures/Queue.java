package algorithms.chapter.datastructures;

import java.util.Arrays;

public class Queue {
    private static final int DEFAULT_CAPACITY = 10;
    transient int[] elementData;
    private int head = 0;
    private int tail = 0;

    /**
     * Creates empty queue with initial capacity 10
     */
    public Queue() {
        elementData = new int[DEFAULT_CAPACITY];
    }

    /**
     * Creates empty queue with specified capacity
     * @param capacity
     */
    public Queue(int capacity) {
        elementData = new int[capacity];
    }

    /**
     * Creates queue with array elements and initial capacity 10
     * @param array
     */
    public Queue(int[] array) {
        if(array.length > DEFAULT_CAPACITY) {
            throw new Error("Overflow!");
        } else {
            elementData = Arrays.copyOf(array, DEFAULT_CAPACITY);
            tail = array.length;
        }
    }

    /**
     * Creates queue with array elements and specified capacity
     * @param array
     * @param capacity
     */
    public Queue(int[] array, int capacity) {
        if(array.length > capacity) {
            throw new Error("Overflow");
        } else if(capacity < 0) {
            throw new Error("Illegal argument. Capacity cannot be negative.");
        } else {
            elementData = Arrays.copyOf(array, capacity);
            tail = array.length;
        }
    }

    /**
     * Creates queue with array elements and specified capacity
     * First array element is located at the specified head of queue
     * @param array
     * @param head
     * @param capacity
     */
    public Queue(int[] array, int head, int capacity) {
        if(array.length > capacity || head > capacity) {
            throw new Error("Overflow!");
        } else if(capacity < 0 || head < 0) {
            throw new Error("Illegal argument. Capacity and head cannot be negative.");
        } else {
            this.head = head;
            this.elementData = new int[capacity];
            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if(head + i < capacity) {
                    elementData[head + i] = array[i];
                } else {
                    elementData[head + i - capacity] = array[i];
                    count++;
                }
            }
            this.tail = head + array.length < capacity ? head + array.length : count;
        }
    }

    /**
     * If head is equals to tail, queue is empty
     * @return true if queue is empty
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * Inserts element at the end of queue
     * @param x element to insert
     */
    public void enQueue(int x) {
        if(head == tail) {
            throw new Error("Overflow!");
        } else {
            elementData[tail] = x;
        }
        if (tail == elementData.length - 1) {
            tail = 0;
        } else {
            tail = tail + 1;
        }
    }

    /**
     * Deletes the first element from queue
     * @return deleted element
     */
    public int deQueue() {
        int x = 0;
        if(isEmpty()) {
            throw new Error("Underflow!");
        } else {
            x = elementData[head];
        }
        if(head == elementData.length-1) {
            head = 0;
        } else {
            head = head + 1;
        }
        return x;
    }

    /**
     * Returns the size of the queue
     * @return size of the queue
     */
    public int size() {
        return head < tail ? tail - head : elementData.length - head + tail;
    }

}
