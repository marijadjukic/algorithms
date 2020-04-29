package algorithms.chapter.advanceddatastructures;

import algorithms.chapter.datastructures.CircularLinkedList;

import java.util.Collection;

public class FibonacciHeap<T extends Comparable<? super T>> {

    /**
     * Pointer to the root of a tree with the minimum key
     */
    private FibonacciNode<T> min;

    /**
     * List of all circular,doubly linked roots in Fibonacci heap
     */
    private CircularLinkedList<FibonacciNode<T>> rootList;

    /**
     * The number of total nodes in the heap
     */
    private int number;

    public FibonacciHeap() {
        this.number = 0;
        this.min = null;
    }

    public FibonacciHeap(Collection<T> collection) {
        this();
        for(T element : collection) {
            this.insert(element);
        }
    }

    /**
     * Makes an empty Fibonacci heap
     * @return Fibonacci heap object
     */
    public FibonacciHeap<T> makeFibHeap() {
        return new FibonacciHeap<>();
    }

    /**
     * Creates Fibonacci node object with a given key
     * and inserts it in Fibonacci heap
     * @param key key to be inserted
     */
    public void insert(T key) {
        FibonacciNode<T> node = new FibonacciNode<>(key);
        insert(node);
    }

    private void insert(FibonacciNode<T> node) {
        node.setDegree(0);
        node.setParent(null);
        node.setChild(null);
        node.setMark(false);

        if(this.min == null) {
            rootList = new CircularLinkedList<>();
            rootList.insert(node);
            this.min = node;
        } else {
            rootList.insert(node);
            if(node.getKey().compareTo(this.min.getKey())<0) {
                this.min = node;
            }
        }
        this.number++;
    }

    /**
     * Unites Fibonacci heaps heapOne and heapTwo, destroying heapOne and heapTwo in the process.
     * It simply concatenates the root lists of heapOne and heapTwo and then determines the new minimum node.
     * @param heapOne
     * @param heapTwo
     * @return new Fibonacci heap as a union of two heaps
     */
    public FibonacciHeap<T> union(FibonacciHeap<T> heapOne, FibonacciHeap<T> heapTwo) {
        FibonacciHeap<T> unionHeap = makeFibHeap();
        unionHeap.min = heapOne.min;

        CircularLinkedList<FibonacciNode<T>> unionRootList = concatenateRoots(heapOne.rootList, heapTwo.rootList);
        unionHeap.rootList = unionRootList;

        if((heapOne.min == null) || (heapTwo.min != null && heapTwo.min.getKey().compareTo(heapOne.min.getKey())<0)) {
            unionHeap.min = heapTwo.min;
        }
        unionHeap.number = heapOne.number + heapTwo.number;
        return unionHeap;
    }

    /**
     * Concatenates two circular, doubly linked root lists by updating their pointers
     * @param rootListOne
     * @param rootListTwo
     * @return concatenated list of fibonacci's rootListOne and rootListTwo
     */
    private CircularLinkedList<FibonacciNode<T>> concatenateRoots(CircularLinkedList<FibonacciNode<T>> rootListOne, CircularLinkedList<FibonacciNode<T>> rootListTwo) {
        CircularLinkedList<FibonacciNode<T>> concatenatedList = rootListOne;
        concatenatedList.setSize(rootListOne.size() + rootListTwo.size());

        rootListOne.getLast().setNext(rootListTwo.getFirst());
        rootListTwo.getFirst().setPrev(rootListOne.getLast());

        rootListOne.getSentinel().setPrev(rootListTwo.getLast());
        rootListTwo.getLast().setNext(rootListOne.getSentinel());

        return concatenatedList;
    }

    /**
     * Finds the node with the minimum key in the Fibonacci heap
     * @return Fibonacci node object with minimum key
     */
    public FibonacciNode<T> findMinNode() {
        return this.min;
    }

    /**
     * Returns the list of roots in Fibonacci heap
     * @return circular, doubly linked list of fibonacci nodes objects
     */
    public CircularLinkedList<FibonacciNode<T>> getRootList() {
        return this.rootList;
    }

    /**
     * Returns total number of nodes in Fibonacci heap
     * @return size of Fibonacci heap
     */
    public int getSize() {
        return this.number;
    }

}
