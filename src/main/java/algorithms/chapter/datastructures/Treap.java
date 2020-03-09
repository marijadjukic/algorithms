package algorithms.chapter.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Treap<T extends Comparable<? super T>> {

    private static final Integer MAX_PRIORITY = Integer.MAX_VALUE;

    private int size;

    private Node<T> root;

    private List<T> sortedTreap;

    //save node priority to ensure that each node has unique priority
    private static List<Integer> priorities = new ArrayList<>();

    public Treap() {
        this.size = 0;
        this.sortedTreap = new ArrayList<>();
    }

    public Treap(T[] array) {
        this.sortedTreap = new ArrayList<>();

        for(T element : array) {
            insert(element);
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public Node<T> search(T key) {
        return treeSearchRecursive(root, key);
    }

    private Node<T> treeSearchRecursive(Node<T> node, T key) {
        if(node == null || node.key == key) {
            return node;
        }
        if(key.compareTo(node.key) < 0) {
            return treeSearchRecursive(node.leftChild, key);
        } else {
            return treeSearchRecursive(node.rightChild, key);
        }
    }

    public List<T> inorderTreeWalk(Node<T> node) {
        if(node!=null){
            inorderTreeWalk(node.getLeftChild());
            sortedTreap.add(node.getKey());
            inorderTreeWalk(node.getRightChild());
        }
        return sortedTreap;
    }

    public void insert(T key) {
        Node<T> newNode = new Node<>(key);
        Integer priority = new Random().nextInt(MAX_PRIORITY);

        // if priority is not unique find a new one
        while(priorities.contains(priority)) {
            priority = new Random().nextInt(MAX_PRIORITY);
        }
        newNode.priority = priority;

        Node<T> trackNode = root;
        Node<T> parentNode = null;

        // BST insert
        while(trackNode!=null){
            parentNode = trackNode;
            if(key.compareTo(trackNode.key)<0) {
                trackNode = trackNode.leftChild;
            } else {
                trackNode = trackNode.rightChild;
            }
        }

        newNode.parent = parentNode;
        if(parentNode==null){
            this.root = newNode;
        } else if(key.compareTo(parentNode.getKey())<0) {
            parentNode.leftChild = newNode;
        } else {
            parentNode.rightChild = newNode;
        }

        //fix max-heap order property
        if(newNode!=root) {
            insertFixUp(newNode);
        }
        this.size++;
    }

    private void insertFixUp(Node<T> newNode) {
        while(newNode.parent!=null && newNode.priority>newNode.parent.priority) {
            if(newNode == newNode.parent.leftChild) {
                rightRotate(newNode.parent);
            } else if(newNode == newNode.parent.rightChild) {
                leftRotate(newNode.parent);
            }
        }
    }

    public void leftRotate(Node<T> x) {
        Node<T> y = x.rightChild;
        x.rightChild = y.leftChild;
        if(y.leftChild!=null) {
            y.leftChild.parent = x;
        }
        y.parent = x.parent;
        if(x.parent==null){
            this.root = y;
        } else if(x.parent.leftChild==x){
            x.parent.leftChild = y;
        } else {
            x.parent.rightChild = y;
        }
        y.leftChild = x;
        x.parent = y;
    }

    public void rightRotate(Node<T> y) {
        Node<T> x = y.leftChild;
        y.leftChild = x.rightChild;
        if(x.rightChild!=null) {
            x.rightChild.parent = y;
        }
        x.parent = y.parent;
        if(y.parent==null){
            this.root = x;
        } else if(y==y.parent.leftChild) {
            y.parent.leftChild = x;
        } else {
            y.parent.rightChild = x;
        }
        x.rightChild = y;
        y.parent = x;
    }

    class Node<T> {

        private T key;

        private Node<T> parent;

        private Node<T> leftChild;

        private Node<T> rightChild;

        private int priority;

        public Node(T key){
            this.key = key;
        }

        public T getKey() {
            return key;
        }

        public Node<T> getParent() {
            return parent;
        }

        public Node<T> getLeftChild() {
            return leftChild;
        }

        public Node<T> getRightChild() {
            return rightChild;
        }

        public int getPriority() {
            return priority;
        }
    }

}
