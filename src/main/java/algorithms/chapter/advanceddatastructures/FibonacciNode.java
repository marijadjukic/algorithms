package algorithms.chapter.advanceddatastructures;

import algorithms.chapter.datastructures.CircularLinkedList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FibonacciNode<T extends Comparable<? super T>> implements Comparable<FibonacciNode<T>> {

    private T key;

    private FibonacciNode<T> parent;

    private FibonacciNode<T> child;

    /**
     * The number of child nodes in this node
     */
    private int degree;

    /**
     * Indicates whether node x has lost a child
     * since the last time x was made the child of another node
     */
    private boolean isMark;

    public FibonacciNode(T key) {
        this.key = key;
    }

    @Override
    public int compareTo(FibonacciNode<T> o) {
        return this.key.compareTo(o.getKey());
    }
}
