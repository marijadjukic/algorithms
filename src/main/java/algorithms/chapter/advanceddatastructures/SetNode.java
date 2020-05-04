package algorithms.chapter.advanceddatastructures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SetNode<T extends Comparable<? super T>> implements Comparable<SetNode<T>>{

    private T key;

    private int rank;

    private SetNode<T> parent;

    public SetNode(T key) {
        this.key = key;
    }

    @Override
    public int compareTo(SetNode<T> o) {
        return this.key.compareTo(o.getKey());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetNode<?> setNode = (SetNode<?>) o;
        return key.equals(setNode.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
