package algorithms.chapter.datastructures;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<? super T>> {

    private int size;

    private Node<T> root;

    private List<T> sortedTree = new ArrayList<>();

    public BinarySearchTree(){
        this.size = 0;
    }

    public BinarySearchTree(T[] array) {
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

    /**
     * Returns the list of keys from node to the end of the tree in sorted order
     * @param node node to start tree walk from
     */
    public List<T> inorderTreeWalk(Node<T> node) {
        if(node!=null){
            inorderTreeWalk(node.getLeftChild());
            sortedTree.add(node.getKey());
            inorderTreeWalk(node.getRightChild());
        }
        return sortedTree;
    }

    /**
     * Returns the pointer to node with given key
     * or null if key doesn't exists
     * @param key key to search for
     * @return node with given key
     */
    public Node<T> search(T key) {
        return treeSearchRecursive(root, key);
    }

    private Node<T> treeSearchRecursive(Node<T> node, T key) {
        if(node == null || node.key == key) {
            return node;
        }
        if(key.compareTo((T)node.key) < 0) {
            return treeSearchRecursive(node.leftChild, key);
        } else {
            return treeSearchRecursive(node.rightChild, key);
        }
    }

    /**
     * Returns the smallest key in the tree
     * @return minimum
     */
    public T minimum() {
        Node<T> min = null;
        if(root!=null) {
            min = treeMinimum(root);
        }
        return min != null ? min.getKey() : null;
    }

    private Node<T> treeMinimum(Node<T> node) {
        while(node.leftChild!=null) {
            node = node.leftChild;
        }
        return node;
    }

    /**
     * Returns the largest key in the tree
     * @return maximum
     */
    public T maximum() {
        Node<T> max = null;
        if(root!=null) {
            max = treeMaximum(root);
        }
        return max != null ? max.getKey() : null;
    }

    private Node<T> treeMaximum(Node<T> node) {
        while(node.rightChild!=null) {
            node = node.rightChild;
        }
        return node;
    }

    /**
     * Returns node with the smallest key greater than given key
     * @param key key to find successor for
     * @return node
     */
    public Node<T> successor(T key) {
        Node<T> node = search(key);
        if(node.rightChild != null) {
            return treeMinimum(node.rightChild);
        }
        Node<T> parentNode = node.parent;
        while(parentNode != null && node == parentNode.rightChild) {
            node = parentNode;
            parentNode = parentNode.parent;
        }
        return parentNode;
    }

    /**
     * Returns node with the largest key smaller than given key
     * @param key key to find predecessor for
     * @return node
     */
    public Node<T> predecessor(T key) {
        Node<T> node = search(key);
        if(node.leftChild != null) {
            return treeMaximum(node.leftChild);
        }
        Node<T> parentNode = node.parent;
        while(parentNode != null && node == parentNode.leftChild) {
            node = parentNode;
            parentNode = parentNode.parent;
        }
        return parentNode;
    }

    /**
     * Inserts node with given key in appropriate position in the tree
     * @param key key value to insert
     */
    public void insert(T key) {
        Node<T> newNode = new Node<>();
        Node<T> trackNode = root;
        Node<T> parentNode = null;
        newNode.key = key;

        while (trackNode != null) {
            parentNode = trackNode;
            if(key.compareTo((T)trackNode.key)<0) {
                trackNode = trackNode.leftChild;
            } else {
                trackNode = trackNode.rightChild;
            }
        }
        newNode.parent = parentNode;
        if(parentNode==null) { // tree was empty
            root = newNode;
        } else if(key.compareTo((T)parentNode.key)<1) {
            parentNode.leftChild = newNode;
        } else {
            parentNode.rightChild = newNode;
        }
        this.size++;
    }

    /**
     * Executes the four cases when deleting node from tree:
     * 1. node has no left child
     * 2. node has a left child but no right child
     * 3. node has two children and its successor is its right child
     * 4. node has two children and its successor lies within the subtree rooted at nodes right child
     * @param key key to remove from tree
     */
    public void delete(T key) {
        Node<T> nodeToDelete = search(key);
        if(nodeToDelete.leftChild == null) {
            transplant(nodeToDelete, nodeToDelete.rightChild);
        } else if(nodeToDelete.rightChild==null) {
            transplant(nodeToDelete, nodeToDelete.leftChild);
        } else {
            Node<T> successor = treeMinimum(nodeToDelete.rightChild);
            if(successor.parent!= nodeToDelete) {
                transplant(successor, successor.rightChild);
                successor.rightChild = nodeToDelete.rightChild;
                successor.rightChild.parent = successor;
            }
            transplant(nodeToDelete, successor);
            successor.leftChild = nodeToDelete.leftChild;
            successor.leftChild.parent = successor;
        }
        this.size--;
    }

    /**
     * Replaces one subtree as a child of its parent with another subtree
     * @param u node to replace
     * @param v node that replaces
     */
    private void transplant(Node<T> u, Node<T> v) {
        if(u.parent==null) {
            root = v;
        } else if(u==u.parent.leftChild) {
            u.parent.leftChild = v;
        } else {
            u.parent.rightChild = v;
        }
        if(v!=null) {
            v.parent = u.parent;
        }
    }

    class Node<T> {

        private T key;

        private Node<T> parent;

        private Node<T> leftChild;

        private Node<T> rightChild;

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
    }

}
