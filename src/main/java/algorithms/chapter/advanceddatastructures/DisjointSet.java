package algorithms.chapter.advanceddatastructures;

import java.util.*;

public class DisjointSet<T extends Comparable<? super T>> {

    private Map<T, SetNode<T>> disjointSetsMap;

    public DisjointSet() {
        disjointSetsMap = new HashMap<>();
    }

    /**
     * Creates a new disjoint set tree whose only member (and thus representative) is element.
     * Procedure takes O(1)
     * @param element
     */
    public void makeSet(T element) {
       SetNode<T> node = new SetNode<>();
       node.setKey(element);
       node.setRank(0);
       node.setParent(node);
        disjointSetsMap.put(element, node);
    }

    /**
     * Returns a pointer to the representative (root) of the tree representing disjoint set
     * that contains an element.
     * This procedure takes path compression approach.
     * Running time is O(logN)
     * @param element
     * @return
     */
    public SetNode<T> findSet(T element) {
        SetNode<T> node = disjointSetsMap.get(element);
        return findSetRecursive(node);
    }

    private SetNode<T> findSetRecursive(SetNode<T> node) {
        if(node.compareTo(node.getParent()) != 0) {
            node.setParent(findSetRecursive(node.getParent()));
        }
        return node.getParent();
    }

    /**
     * Unites the dynamic sets that contain x and y into a
     * new set that is the union of these two sets.
     * Running time is linear in m in all practical situations.
     * @param x
     * @param y
     */
    public void union(T x, T y) {
        SetNode<T> xRoot = findSet(x);
        SetNode<T> yRoot = findSet(y);

        if(xRoot.getRank() > yRoot.getRank()) {
            yRoot.setParent(xRoot);
        } else {
            xRoot.setParent(yRoot);
            if(xRoot.getRank() == yRoot.getRank()) {
                yRoot.setRank(yRoot.getRank() + 1);
            }
        }
    }
}
