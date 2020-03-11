package algorithms.chapter.datastructures;

public class OrderStatisticTree<T extends Comparable<? super T>> {

    private int size;

    private Node<T> sentinel;

    private Node<T> root;

    public OrderStatisticTree() {
        this.size = 0;
        this.sentinel = new Node<>(null, TreeColors.BLACK,0);
    }

    public OrderStatisticTree(T[] array){
        this.sentinel = new Node<>(null, TreeColors.BLACK,0);
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

    /**
     * Returns ith smallest key in an order-statistic tree
     * @param i given rank
     * @return ith smallest key
     */
    public T select(int i) {
        return selectRecursive(this.root, i);
    }

    // returns ith smallest key in the subtree rooted at node
    private T selectRecursive(Node<T> node, int i) {
        int r = node.leftChild.nodeSize + 1;
        if(i==r){
            return node.key;
        } else if(i<r) {
            return selectRecursive(node.leftChild, i);
        } else {
            return selectRecursive(node.rightChild,i-r);
        }
    }

    /**
     * Returns the number of nodes preceding node with given key in an inorder tree walk, plus 1 for node itself
     * @param key given key to return rank for
     * @return node's rank
     */
    public int rank(T key) {
        Node<T> node = search(key);
        int r = node.leftChild.nodeSize + 1;
        Node<T> y = node;
        while(y!=root) {
            if(y == y.parent.rightChild) {
                r = r + y.parent.leftChild.nodeSize + 1;
            }
            y = y.parent;
        }
        return r;
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

        y.nodeSize = x.nodeSize;
        x.nodeSize = x.leftChild.nodeSize + x.rightChild.nodeSize + 1;
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

        x.nodeSize = y.nodeSize;
        y.nodeSize = y.leftChild.nodeSize + y.rightChild.nodeSize + 1;
    }

    public void insert(T key) {
        Node<T> newNode = new Node<>(key, TreeColors.RED, 1);

        Node<T> trackNode = root;
        Node<T> parentNode = sentinel;

        while(trackNode!=sentinel) {
            trackNode.nodeSize++;
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

    class Node<T> {

        private T key;

        private Node<T> parent;

        private Node<T> leftChild;

        private Node<T> rightChild;

        private TreeColors color;

        private int nodeSize;

        Node(T key, TreeColors color, int nodeSize) {
            this.key = key;
            this.color = color;
            this.nodeSize = nodeSize;
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

        public TreeColors getColor() {
            return color;
        }

        public int getNodeSize() {
            return nodeSize;
        }
    }

}
