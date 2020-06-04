package algorithms.chapter.graphalgorithms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Vertex<T extends Comparable<? super T>> implements Comparable<Vertex<T>>{

    private T value;

    private int distance;

    private int discoveryTime;

    private int finishingTime;

    private GraphColors color;

    private Vertex<T> parent;

    private int reweightedDistance;

    public Vertex(T value) {
        this.value = value;
        this.distance = Integer.MAX_VALUE;
        this.color = GraphColors.WHITE;
    }

    @Override
    public int compareTo(Vertex<T> o) {
        return this.value.compareTo(o.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(value, vertex.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
