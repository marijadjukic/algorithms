package algorithms.chapter.graphalgorithms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Graph<T extends Comparable<? super T>> {

    /**
     * Graph map with keys representing vertices
     * and each vertex has adjacency list which represents edges
     */
    private Map<Vertex<T>, Vertex<T>[]> graphMap;

}
