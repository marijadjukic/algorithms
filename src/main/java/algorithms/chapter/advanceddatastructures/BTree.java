package algorithms.chapter.advanceddatastructures;

import algorithms.util.ArraysUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BTree<T extends Comparable<? super T>> {

    private static final String FILE_NAME = "btree.txt";

    private BTreeNode<T> root;

    //minimum degree of the B-tree
    private int t;

    private int height = 0;

    /**
     * Creates an empty B-tree with minimum degree
     * @param t minimum degree
     */
    public BTree(int t) {
        this.t = t;
        this.createBTree();
    }

    public BTree(Collection<T> collection, int t) {
        this.t = t;
        this.createBTree();
        for (T element : collection) {
            this.insert(element);
        }
    }

    public void createBTree() {
        BTreeNode<T> node = new BTreeNode<>();
        node.setLeaf(true);
        node.setKeysNum(0);
        diskWrite(node);
        this.root = node;
        height++;
    }

    public BTreeSearchItem<T> search(T key) {
        return search(this.root, key);
    }

    private BTreeSearchItem<T> search(BTreeNode<T> node, T key) {
        int i = 0;
        int keysNumber = node.getKeysNum();

        while (i < keysNumber && key.compareTo(node.getKeys()[i]) > 0) {
            i++;
        }

        if(i < keysNumber && key.compareTo(node.getKeys()[i]) == 0) {
            return new BTreeSearchItem<>(node, i);
        } else if (node.isLeaf()) {
            return null;
        } else {
            BTreeNode<T> ithChild = node.getChildren()[i]; //BTreeNode<T> ithChild = diskRead(ithChild);
            return search(ithChild, key);
        }
    }

    public void splitChild(BTreeNode<T> x, int i) {
        BTreeNode<T> z = new BTreeNode<>();
        BTreeNode<T> y = x.getChildren()[i];
        z.setLeaf(y.isLeaf());
        z.setKeysNum(t-1);
        z.setKeys(ArraysUtil.newArray(2*t - 1));
        z.setChildren(new BTreeNode[2*t]);

        for(int j = 0; j < t-1; j++) {
            z.getKeys()[j] = y.getKeys()[t+j];
        }
        if(!y.isLeaf()) {
            for(int j = 0; j<t; j++) {
                z.getChildren()[j] = y.getChildren()[t+j];
            }
        }
        y.setKeysNum(t-1);

        for(int j = x.getKeysNum() + 1; j >= i + 1; j--) {
            x.getChildren()[j+1] = x.getChildren()[j];
        }

        x.getChildren()[i+1] = z;
        for(int j = x.getKeysNum(); j >= i; j--) {
            x.getKeys()[j+1] = x.getKeys()[j];
        }

        x.getKeys()[i] = y.getKeys()[t-1];
        x.setKeysNum(x.getKeysNum() + 1);

        diskWrite(y);
        diskWrite(z);
        diskWrite(x);
    }

    public void insert(T key) {
        BTreeNode<T> r = this.root;
        if(r.getKeysNum() == 2*t - 1) {
            BTreeNode<T> s = new BTreeNode<>();
            this.root = s;
            s.setLeaf(false);
            s.setKeysNum(0);
            s.setChildren(new BTreeNode[2*t]);
            s.setKeys(ArraysUtil.newArray(2*t - 1));
            s.getChildren()[0] = r;
            splitChild(s, 0);
            insertNonFull(s, key);
            this.height++;
        } else {
            if(r.getKeys()==null) {
                r.setKeys(ArraysUtil.newArray(2*t - 1));
            }
            if(r.getChildren()==null) {
                r.setChildren(new BTreeNode[2*t]);
            }
            insertNonFull(r, key);
        }
    }

    private void insertNonFull(BTreeNode<T> node, T key) {
        int i = node.getKeysNum() - 1;

        if (node.isLeaf()) {
            if(node.getKeysNum() == 0) {
                node.getKeys()[0] = key;
            } else {
                while (i >= 0 && key.compareTo(node.getKeys()[i]) < 0) {
                    node.getKeys()[i + 1] = node.getKeys()[i];
                    i--;
                }
                node.getKeys()[i + 1] = key;
                diskWrite(node);
            }
            node.setKeysNum(node.getKeysNum() + 1);
        } else {
            while (i >= 0 && key.compareTo(node.getKeys()[i])<0) {
                i--;
            }
            i++;
            BTreeNode<T> readIthChild = node.getChildren()[i]; //BTreeNode<T> readIthChild = diskRead(node.getChildren()[i]);
            if(readIthChild.getKeysNum() == 2*t - 1) {
                splitChild(node, i);
                if(key.compareTo(node.getKeys()[i])>0) {
                    i++;
                }
            }
            insertNonFull(node.getChildren()[i], key);
        }
    }

    public void diskWrite(BTreeNode<T> node) {
        String filePath = this.getClass().getClassLoader().getResource(FILE_NAME).getPath();
        try(ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(filePath, true))) {
            objectOut.writeObject(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BTreeNode<T> diskRead(BTreeNode<T> node) {
        List<BTreeNode<T>> nodeList = new ArrayList<>();
        File file = new File(this.getClass().getClassLoader().getResource(FILE_NAME).getFile());

        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(file))) {
            while(true) {
                Object object = objectIn.readObject();
                if (object instanceof BTreeNode) {
                    nodeList.add((BTreeNode<T>) object);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nodeList.stream().filter(node::equals).findFirst().get();
    }

    public int getHeight() {
        return this.height;
    }

    public BTreeNode<T> getRoot() {
        return this.root;
    }

}
