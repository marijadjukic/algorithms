package algorithms.chapter.datastructures;

public interface LinkedList<E> {

    int size();

    boolean isEmpty();

    Object search(E key);

    void insert(E key);

    void delete(E key);

    Object toArray();

}
