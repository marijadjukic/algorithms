package algorithms.chapter.datastructures;

import java.util.Arrays;

public class Stack {

    private static final int DEFAULT_CAPACITY = 10;
    transient int[] elementData;
    private int top = -1;

    /**
     * Creates empty stack with initial capacity 10
     */
    public Stack() {
        elementData = new int[DEFAULT_CAPACITY];
    }

    /**
     * Creates empty stack with capacity
     * @param capacity
     */
    public Stack(int capacity) {
        elementData = new int[capacity];
    }

    /**
     * Creates stack with array elements and initial capacity 10
     * @param array
     */
    public Stack(int[] array) {
        elementData = Arrays.copyOf(array,DEFAULT_CAPACITY);
        top = array.length-1;
    }

    /**
     * Creates stack with array elements and capacity
     * @param array
     * @param capacity
     */
    public Stack(int[] array, int capacity) {
        elementData = Arrays.copyOf(array,capacity);
        top = array.length-1;
    }

    /**
     * If top pointer is less than 0, stack is empty
     * @return true if stack is empty
     */
    public boolean stackEmpty() {
        return top == -1 ? true : false;
    }

    /**
     * Inserts element at the end of the stack
     * @param element
     */
    public void push(int element) {
        if(top == elementData.length-1) {
            throw new Error("Overflow");
        } else {
            top = top + 1;
            elementData[top] = element;
        }
    }

    /**
     * Deletes the last element from the stack
     * @return deleted element
     */
    public int pop() {
        if (stackEmpty()) {
            throw new Error("Underflow!");
        } else if(top == elementData.length) {
            throw new Error("Overflow");
        } else {
            top = top - 1;
            return elementData[top + 1];
        }
    }

    /**
     * Returns the size of the stack
     * @return size of the stack
     */
    public int size() {
        return top + 1;
    }

}
