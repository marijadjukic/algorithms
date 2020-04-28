package algorithms.chapter.advanceddatastructures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BTreeNode<T> implements Serializable {

    private static final long serialVersionUID = -1564422953887138204L;

    private int keysNum;

    private T[] keys;

    private boolean isLeaf;

    private BTreeNode<T>[] children;

    public Object[] keysToArray() {
        Object[] array = new Object[keysNum];
        for(int i=0; i<keysNum; i++) {
            array[i] = keys[i];
        }
        return array;
    }

    public Object[] childrenToArray() {
        List<BTreeNode<T>> children = new ArrayList<>(Arrays.asList(this.children));
        children.removeIf(Objects::isNull);

        return children.toArray();
    }

    @Override
    public String toString() {
        return MessageFormat.format("Number of keys: {0}. Keys: {1}. Leaf: {2}. Children: {3}",
                keysNum, Arrays.asList(keysToArray()), isLeaf, Arrays.asList(childrenToArray()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BTreeNode<?> bTreeNode = (BTreeNode<?>) o;
        return keysNum == bTreeNode.keysNum &&
                isLeaf == bTreeNode.isLeaf &&
                Arrays.equals(keys, bTreeNode.keys) &&
                Arrays.equals(children, bTreeNode.children);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(keysNum, isLeaf);
        result = 31 * result + Arrays.hashCode(keys);
        result = 31 * result + Arrays.hashCode(children);
        return result;
    }
}
