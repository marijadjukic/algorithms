package algorithms.chapter.datastructures;

public class SingleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private Node<E> head;
    private Node<E> tail;
    private Node<E>[] data;

    public SingleLinkedList() {}

    public SingleLinkedList(E[] array) {
        int n = array.length;
        size = n;
        data = new Node[n];
        head = new Node<>();
        head.key = array[0];
        tail = new Node<>();
        tail.key = array[n-1];
        data[0] = head;
        data[n-1] = tail;
        for(int i=1; i<n-1; i++){
            Node<E> node = new Node<>();
            node.key = array[i];
            data[i] = node;
        }
        data[0].next = data[1];
        for(int i=1; i<n-1; i++) {
            data[i].next = data[i+1];
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Node search(E key) {
        Node<E> node = head;
        while(node != null && node.key != key) {
            node = node.next;
        }
        return node;
    }

    @Override
    public void insert(E key) {
        Node<E> node = new Node<>();
        node.key = key;
        if(head != null) {
            node.next = head;
        }
        head = node;
        size++;
    }

    @Override
    public void delete(E key) {
        Node<E> node = head;
        Node<E> prevNode = head;
        while(node.key != key) {
            prevNode = node;
            node = node.next;
        }

        if(prevNode != null) {
            prevNode.next = node.next;

        } else {
            head = node.next;
        }
        if(node.next != null) {
            node.next = null;
        } else {
            tail = prevNode;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for(int i=0; i<size; i++) {
            array[i] = data[i].key;
        }
        return array;
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    class Node<E> {
        private E key;
        private Node<E> next;

        public E getKey() {
            return key;
        }

        public Node<E> getNext() {
            return next;
        }
    }
}
