package algorithms.chapter.advanceddatastructures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Edge<T extends Comparable<? super T>> {

    private T xVertex;

    private T yVertex;

    private Integer weight;

    public Edge(T xVertex, T yVertex) {
        this.xVertex = xVertex;
        this.yVertex = yVertex;
    }
}
