package algorithms.chapter.datastructures;

public class AVLTree<T extends Comparable<? super T>> {

    private int size;

    private Node<T> root;

    public AVLTree() {
        this.size = 0;
    }

    public AVLTree(T[] array) {
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

    public void insert(T key) {
        Node<T> newNode = new Node<>(key,0);
        newNode.leftChild = new Node<>(null, -1);
        newNode.rightChild = new Node<>(null, -1);

        Node<T> trackNode = root;
        Node<T> parentNode = null;

        // BST insert
        while(trackNode!=null && trackNode.height!=-1){
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

        // fix AVL property
        insertFixUp(newNode);
        this.size++;
    }

    private void insertFixUp(Node<T> newNode) {
        while(newNode.parent!=null) {
            newNode.parent.height = getNodeHeight(newNode.parent);
            if(newNode.parent.parent!=null) {
                if (newNode.parent == newNode.parent.parent.leftChild) {
                    Node<T> uncle = newNode.parent.parent.rightChild;
                    if (getBalance(newNode.parent.height, uncle.height) > 1) {
                        if (newNode == newNode.parent.leftChild) {
                            rightRotate(newNode.parent.parent);
                            newNode = newNode.parent;
                        } else {
                            if (newNode == newNode.parent.rightChild) {
                                leftRotate(newNode.parent);
                                newNode.leftChild.height = getNodeHeight(newNode.leftChild);
                                newNode.height = getNodeHeight(newNode);
                            }
                            rightRotate(newNode.parent);
                        }
                        newNode.rightChild.height = getNodeHeight(newNode.rightChild);
                        newNode.height = getNodeHeight(newNode);
                    } else {
                        newNode = newNode.parent;
                    }
                } else {
                    Node<T> uncle = newNode.parent.parent.leftChild;
                    if (getBalance(newNode.parent.height, uncle.height) > 1) {
                        if (newNode == newNode.parent.rightChild) {
                            leftRotate(newNode.parent.parent);
                            newNode = newNode.parent;
                        } else {
                            if (newNode == newNode.parent.leftChild) {
                                rightRotate(newNode.parent);
                                newNode.rightChild.height = getNodeHeight(newNode.rightChild);
                                newNode.height = getNodeHeight(newNode);
                            }
                            leftRotate(newNode.parent);
                        }
                        newNode.leftChild.height = getNodeHeight(newNode.leftChild);
                        newNode.height = getNodeHeight(newNode);
                    } else {
                        newNode = newNode.parent;
                    }
                }
            } else {
                newNode = newNode.parent;
            }
        }
    }

    private int getBalance(int leftHeight, int rightHeight) {
        return Math.abs(leftHeight-rightHeight);
    }

    private int getNodeHeight(Node<T> node) {
        return Math.max(node.leftChild.height, node.rightChild.height) + 1;
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

    public void delete(T key) {

    }

    class Node<T> {

        private T key;

        private Node<T> parent;

        private Node<T> leftChild;

        private Node<T> rightChild;

        private int height;

        Node(T key, int height){
            this.key = key;
            this.height = height;
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

        public int getHeight() {
            return height;
        }
    }

}
