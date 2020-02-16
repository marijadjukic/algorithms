package algorithms.chapter.datastructures;

import java.util.Objects;

public class CircularLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private Node<E> sentinel;
    private Node<E>[] data;

    public CircularLinkedList() {
        sentinel = new Node<E>();
    }

    public CircularLinkedList(E[] array) {
        int n = array.length;
        size = n;
        data = new Node[n];
        sentinel = new Node<E>();

        for(int i=0; i<n; i++){
            Node<E> node = new Node<>();
            node.key = array[i];
            data[i] = node;
        }
        sentinel.next = data[0];
        sentinel.prev = data[n-1];
        data[0].prev = sentinel;
        data[0].next = data[1];
        data[n-1].next = sentinel;
        data[n-1].prev = data[n-2];
        for(int i=1; i<n-1; i++) {
            data[i].prev = data[i-1];
            data[i].next = data[i+1];
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel.prev;
    }

    @Override
    public Node search(E key) {
        Node<E> node = sentinel.next;
        while(node != sentinel && node.key != key) {
            node = node.next;
        }
        return node;
    }

    @Override
    public void insert(E key) {
        Node<E> node = new Node<>();
        node.key = key;
        node.next = sentinel.next;
        if(sentinel.next != null) {
            sentinel.next.prev = node;
        }
        sentinel.next = node;
        node.prev = sentinel;
        size++;
    }

    @Override
    public void delete(E key) {
        Node<E> node = sentinel.next;
        while(node.key != key) {
            node = node.next;
        }

        if(node.prev != sentinel) {
            node.prev.next = node.next;
        } else {
            sentinel.next = node.next;
        }
        if(node.next != sentinel) {
            node.next.prev = node.prev;
        } else {
            sentinel.prev = node.prev;
        }
        size = size - 1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for(int i=0; i<size; i++) {
            array[i] = data[i].key;
        }
        return array;
    }

    public Node<E> getSentinel() {
        return sentinel;
    }

    class Node<E> {
        private Node<E> prev;
        private E key;
        private Node<E> next;

        public Node<E> getPrev() {
            return prev;
        }

        public E getKey() {
            return key;
        }

        public Node<E> getNext() {
            return next;
        }
    }

}
