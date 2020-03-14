package algorithms.chapter.datastructures;

import algorithms.chapter.datastructures.beans.Interval;

import java.math.BigDecimal;
import java.text.MessageFormat;

public class IntervalTree {

    private int size;

    private Node sentinel;

    private Node root;

    public IntervalTree() {
        this.size = 0;
        this.sentinel = new Node(null,null, 0, TreeColors.BLACK);
    }

    public IntervalTree(Number[] array){
        this.sentinel = new Node(null,null,0, TreeColors.BLACK);
        this.root = sentinel;
        for (int i=0; i<array.length; i=i+2) {
            insert(array[i],array[i+1]);
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node getRoot() {
        return this.root;
    }

    public Node search(Number low, Number high) {
        Interval interval = new Interval(low, high);
        Node node = root;

        while(node!=sentinel && !isOverlappingInterval(interval,node.interval)){
            if(node.leftChild != sentinel && interval.getLow().compareTo(node.leftChild.max) <= 0){
                node = node.leftChild;
            } else {
                node = node.rightChild;
            }
        }
        return node;
    }

    private boolean isOverlappingInterval(Interval firstInterval, Interval secondInterval) {
        return firstInterval.getLow().compareTo(secondInterval.getHigh()) <= 0
                && secondInterval.getLow().compareTo(firstInterval.getHigh()) <= 0;
    }

    public void leftRotate(Node x) {
        Node y = x.rightChild;
        x.rightChild = y.leftChild;
        if(y.leftChild!=sentinel) {
            y.leftChild.parent = x;
        }
        y.parent = x.parent;
        if(x.parent==sentinel){
            this.root = y;
        } else if(x.parent.leftChild==x){
            x.parent.leftChild = y;
        } else {
            x.parent.rightChild = y;
        }
        y.leftChild = x;
        x.parent = y;

        x.max = getMax(x);
        y.max = getMax(y);
    }

    public void rightRotate(Node y) {
        Node x = y.leftChild;
        y.leftChild = x.rightChild;
        if(x.rightChild!=sentinel) {
            x.rightChild.parent = y;
        }
        x.parent = y.parent;
        if(y.parent==sentinel){
            this.root = x;
        } else if(y==y.parent.leftChild) {
            y.parent.leftChild = x;
        } else {
            y.parent.rightChild = x;
        }
        x.rightChild = y;
        y.parent = x;

        y.max = getMax(y);
        x.max = getMax(x);
    }

    private BigDecimal getMax(Node node) {
        return node.leftChild.max.max(node.rightChild.max).max(node.interval.getHigh());
    }

    public void insert(Number low, Number high) {
        Node newNode = new Node(low,high, high, TreeColors.RED);

        Node trackNode = root;
        Node parentNode = sentinel;

        while(trackNode!=sentinel) {
            parentNode = trackNode;
            if(newNode.interval.getLow().compareTo(parentNode.interval.getLow())<0) {
                trackNode = trackNode.leftChild;
            } else {
                trackNode = trackNode.rightChild;
            }
            parentNode.max = getMax(parentNode);
        }
        newNode.parent = parentNode;
        if(parentNode==sentinel) {
            this.root = newNode;
        } else if(newNode.interval.getLow().compareTo(parentNode.interval.getLow())<0){
            parentNode.leftChild = newNode;
        } else {
            parentNode.rightChild = newNode;
        }
        newNode.leftChild = sentinel;
        newNode.rightChild = sentinel;
        insertFixUp(newNode);
        this.size++;
    }

    private void insertFixUp(Node newNode){
        while(newNode.parent.color==TreeColors.RED) {
            if(newNode.parent==newNode.parent.parent.leftChild){
                Node uncle = newNode.parent.parent.rightChild;
                if(uncle.color==TreeColors.RED) {
                    newNode.parent.parent.color = TreeColors.RED;
                    newNode.parent.color = TreeColors.BLACK;
                    uncle.color = TreeColors.BLACK;
                    newNode = newNode.parent.parent;
                } else {
                    if(newNode == newNode.parent.rightChild) {
                        newNode = newNode.parent;
                        leftRotate(newNode);
                    }
                    newNode.parent.color = TreeColors.BLACK;
                    newNode.parent.parent.color = TreeColors.RED;
                    rightRotate(newNode.parent.parent);
                }
            } else {
                Node uncle = newNode.parent.parent.leftChild;
                if(uncle.color==TreeColors.RED) {
                    newNode.parent.parent.color = TreeColors.RED;
                    newNode.parent.color = TreeColors.BLACK;
                    uncle.color = TreeColors.BLACK;
                    newNode = newNode.parent.parent;
                } else {
                    if(newNode == newNode.parent.leftChild) {
                        newNode = newNode.parent;
                        rightRotate(newNode);
                    }
                    newNode.parent.color = TreeColors.BLACK;
                    newNode.parent.parent.color = TreeColors.RED;
                    leftRotate(newNode.parent.parent);
                }
            }
        }
        this.root.color = TreeColors.BLACK;
    }

    class Node {

        private Interval interval;

        private BigDecimal max;

        private Node parent;

        private Node leftChild;

        private Node rightChild;

        private TreeColors color;

        public Node(Number low, Number high, Number max, TreeColors color) {
            if((low!=null && high!=null) && low.doubleValue() > high.doubleValue()){
                throw new IllegalArgumentException(MessageFormat.format(
                        "Invalid interval [{0}{1}]. Left endpoint has to be less than or equal to right endpoint.",
                        low.toString(), high.toString()));
            }
            this.interval = new Interval(low, high);
            this.color = color;
            if(max!=null) {
                this.max = new BigDecimal(max.doubleValue());
            }
        }

        public Interval getInterval() {
            return interval;
        }

        public BigDecimal getMax() {
            return max;
        }

        public Node getParent() {
            return parent;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public TreeColors getColor() {
            return color;
        }
    }

}
