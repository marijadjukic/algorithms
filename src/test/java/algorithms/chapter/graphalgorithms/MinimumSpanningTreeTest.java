package algorithms.chapter.graphalgorithms;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import algorithms.chapter.advanceddatastructures.Edge;
import algorithms.chapter.advanceddatastructures.Graph;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class MinimumSpanningTreeTest {

    private MinimumSpanningTree<String> minimumSpanningTree = new MinimumSpanningTree<>();

    private List<String> vertices = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");
    private List<Edge<String>> edges = Arrays.asList(
            new Edge<>("a", "b", 4),
            new Edge<>("a", "h", 8),
            new Edge<>("b", "h", 11),
            new Edge<>("b", "c", 8),
            new Edge<>("h", "g", 1),
            new Edge<>("c", "d", 7),
            new Edge<>("c", "i", 2),
            new Edge<>("c", "f", 4),
            new Edge<>("h", "i", 7),
            new Edge<>("g", "i", 6),
            new Edge<>("g", "f", 2),
            new Edge<>("d", "f", 14),
            new Edge<>("d", "e", 9),
            new Edge<>("e", "f", 10)
    );
    private Graph<String> graph = new Graph<>(vertices, edges);

    @Test
    public void testKruskalMinimumSpanningTree() {
        Set<Edge<String>> spanningTree = minimumSpanningTree.kruskalMinimumSpanningTree(graph);
        int spanningTreeWeight = minimumSpanningTree.getSpanningTreeWeight(spanningTree);

        assertThat(spanningTree.size(), is(8));
        assertThat(spanningTreeWeight, is(37));
    }
}
