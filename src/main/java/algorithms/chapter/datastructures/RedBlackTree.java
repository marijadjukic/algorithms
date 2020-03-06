package algorithms.chapter.datastructures;

public class RedBlackTree<T extends Comparable<? super T>> {

    private int size;

    private Node<T> sentinel;

    private Node<T> root;

    public RedBlackTree() {
        this.size = 0;
        this.sentinel = new Node<>();
        this.sentinel.color = TreeColors.BLACK;
    }

    public RedBlackTree(T[] array) {
        this.sentinel = new Node<>();
        this.sentinel.color = TreeColors.BLACK;
        this.root = sentinel;
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

    public void leftRotate(Node<T> x) {
        Node<T> y = x.rightChild;
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
    }

    public void rightRotate(Node<T> y) {
        Node<T> x = y.leftChild;
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
    }

    public void insert(T key) {
        Node<T> newNode = new Node<>();
        newNode.key = key;

        Node<T> trackNode = root;
        Node<T> parentNode = sentinel;

        while(trackNode!=sentinel) {
            parentNode = trackNode;
            if(key.compareTo(parentNode.key)<1) {
                trackNode = trackNode.leftChild;
            } else {
                trackNode = trackNode.rightChild;
            }
        }
        newNode.parent = parentNode;
        if(parentNode==sentinel) {
            this.root = newNode;
        } else if(newNode.key.compareTo(parentNode.key)<1){
            parentNode.leftChild = newNode;
        } else {
            parentNode.rightChild = newNode;
        }
        newNode.leftChild = sentinel;
        newNode.rightChild = sentinel;
        newNode.color = TreeColors.RED;
        insertFixUp(newNode);
        this.size++;
    }

    private void insertFixUp(Node<T> newNode){
        while(newNode.parent.color==TreeColors.RED) {
            if(newNode.parent==newNode.parent.parent.leftChild){
                Node<T> uncle = newNode.parent.parent.rightChild;
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
                Node<T> uncle = newNode.parent.parent.leftChild;
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

    public void delete(T key) {
        Node<T> nodeToDelete = search(key);
        Node<T> trackNode = nodeToDelete;
        TreeColors trackNodeOriginalColor = trackNode.color;

        Node<T> fixUpNode;

        if(nodeToDelete.leftChild==sentinel){
            fixUpNode = nodeToDelete.rightChild;
            transplant(nodeToDelete, nodeToDelete.rightChild);
        } else if(nodeToDelete.rightChild==sentinel) {
            fixUpNode = nodeToDelete.leftChild;
            transplant(nodeToDelete, nodeToDelete.leftChild);
        } else {
            trackNode = treeMinimum(nodeToDelete.rightChild);
            trackNodeOriginalColor = trackNode.color;
            fixUpNode = trackNode.rightChild;
            if(trackNode.parent==nodeToDelete){
                fixUpNode.parent = trackNode;
            } else {
                transplant(trackNode, trackNode.rightChild);
                trackNode.rightChild = nodeToDelete.rightChild;
                trackNode.rightChild.parent = trackNode;
            }
            transplant(nodeToDelete, trackNode);
            trackNode.leftChild = nodeToDelete.leftChild;
            trackNode.leftChild.parent = trackNode;
            trackNode.color = nodeToDelete.color;
        }

        if(trackNodeOriginalColor==TreeColors.BLACK) {
            deleteFixUp(fixUpNode);
        }
        this.size--;
    }

    private void deleteFixUp(Node<T> fixUpNode) {
        while(fixUpNode!=root && fixUpNode.color==TreeColors.BLACK) {
            if(fixUpNode==fixUpNode.parent.leftChild) {
                Node<T> sibling = fixUpNode.parent.rightChild;
                if(sibling.color==TreeColors.RED) {
                    sibling.color=TreeColors.BLACK;
                    fixUpNode.parent.color = TreeColors.RED;
                    leftRotate(fixUpNode.parent);
                    sibling = fixUpNode.parent.rightChild;
                }
                if(sibling.leftChild.color==TreeColors.BLACK && sibling.rightChild.color==TreeColors.BLACK) {
                    sibling.color = TreeColors.RED;
                    fixUpNode = fixUpNode.parent;
                } else{
                    if(sibling.rightChild.color==TreeColors.BLACK) {
                        sibling.leftChild.color = TreeColors.BLACK;
                        sibling.color = TreeColors.RED;
                        rightRotate(sibling);
                        sibling = fixUpNode.parent.rightChild;
                    }
                    sibling.color = fixUpNode.parent.color;
                    fixUpNode.parent.color = TreeColors.BLACK;
                    sibling.rightChild.color = TreeColors.BLACK;
                    leftRotate(fixUpNode.parent);
                    fixUpNode = root;
                }
            } else {
                Node<T> sibling = fixUpNode.parent.leftChild;
                if(sibling.color==TreeColors.RED) {
                    sibling.color=TreeColors.BLACK;
                    fixUpNode.parent.color = TreeColors.RED;
                    rightRotate(fixUpNode.parent);
                    sibling = fixUpNode.parent.leftChild;
                }
                if(sibling.rightChild.color==TreeColors.BLACK && sibling.leftChild.color==TreeColors.BLACK) {
                    sibling.color = TreeColors.RED;
                    fixUpNode = fixUpNode.parent;
                } else{
                    if(sibling.leftChild.color==TreeColors.BLACK) {
                        sibling.rightChild.color = TreeColors.BLACK;
                        sibling.color = TreeColors.RED;
                        leftRotate(sibling);
                        sibling = fixUpNode.parent.leftChild;
                    }
                    sibling.color = fixUpNode.parent.color;
                    fixUpNode.parent.color = TreeColors.BLACK;
                    sibling.leftChild.color = TreeColors.BLACK;
                    rightRotate(fixUpNode.parent);
                    fixUpNode = root;
                }
            }
        }
        fixUpNode.color = TreeColors.BLACK;
    }

    private void transplant(Node<T> u, Node<T> v) {
        if(u.parent==sentinel){
            this.root = v;
        } else if(u.parent.leftChild == u) {
            u.parent.leftChild = v;
        } else {
            u.parent.rightChild = v;
        }
        v.parent = u.parent;
    }

    private Node<T> treeMinimum(Node<T> node) {
        while(node.leftChild!=sentinel) {
            node = node.leftChild;
        }
        return node;
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


    class Node<T> {

        private T key;

        private Node<T> parent;

        private Node<T> leftChild;

        private Node<T> rightChild;

        private TreeColors color;

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

        public TreeColors getColor() {
            return color;
        }
    }

}
