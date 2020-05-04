package algorithms.chapter.advanceddatastructures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Graph<T extends Comparable<? super T>> {

    private List<T> vertices;

    private List<Edge<T>> edges;

}
